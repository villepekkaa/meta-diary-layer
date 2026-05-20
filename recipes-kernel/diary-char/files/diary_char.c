#include <linux/module.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <linux/uaccess.h>
#include <linux/device.h>
#include <linux/moduleparam.h>

#define DEVNAME "diary0"
#define BUFSZ   256

static dev_t           devno;
static struct cdev     cdev;
static struct class   *cls;
static char            buf[BUFSZ];
static size_t          len;
static int verbose = 0;
module_param(verbose, int, 0644);
MODULE_PARM_DESC(verbose, "Log every read/write when non-zero");

static ssize_t diary_read(struct file *f, char __user *ubuf, size_t count, loff_t *off) {
    size_t n;

    if (*off >= len) return 0;
    n = min(count, len - *off);

    if (copy_to_user(ubuf, buf + *off, n)) return -EFAULT;
    *off += n;

    if (verbose) pr_info("diary: read %zu bytes\n", n);

    return n;
}

static ssize_t diary_write(struct file *f, const char __user *ubuf, size_t count, loff_t *off) {
    size_t n = min(count, (size_t)BUFSZ);
    if (copy_from_user(buf, ubuf, n)) return -EFAULT;
    len = n;
    if (verbose) pr_info("diary: wrote %zu bytes\n", n);
    return n;
}

static const struct file_operations fops = {
    .owner = THIS_MODULE,
    .read  = diary_read,
    .write = diary_write,
};

static int __init diary_init(void) {
    alloc_chrdev_region(&devno, 0, 1, DEVNAME);
    cdev_init(&cdev, &fops);
    cdev_add(&cdev, devno, 1);
    cls = class_create("diary");
    device_create(cls, NULL, devno, NULL, DEVNAME);
    pr_info("diary: registered %s (major %d)\n", DEVNAME, MAJOR(devno));
    return 0;
}

static void __exit diary_exit(void) {
    device_destroy(cls, devno);
    class_destroy(cls);
    cdev_del(&cdev);
    unregister_chrdev_region(devno, 1);
    pr_info("diary: unregistered\n");
}

module_init(diary_init);
module_exit(diary_exit);
MODULE_LICENSE("GPL");