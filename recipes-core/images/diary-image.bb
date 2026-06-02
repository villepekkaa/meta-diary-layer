SUMMARY = "Custom image for the custom-linux course"
LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-openssh package-management"
IMAGE_INSTALL = " \
	packagegroup-core-boot \
	packagegroup-core-full-cmdline \
	kernel-modules \
	diary-config \
	vim less curl sl diary-config \
	"

inherit core-image

IMAGE_ROOTFS_EXTRA_SPACE = "65536"

diary_set_motd() {
	if [ -f "${IMAGE_ROOTFS}/etc/diary-motd" ]; then
		install -m 0644 "${IMAGE_ROOTFS}/etc/diary-motd" "${IMAGE_ROOTFS}/etc/motd"
	fi
}

ROOTFS_POSTPROCESS_COMMAND += "diary_set_motd; "

inherit extrausers

PASSWD = "\$5\$1xmr2dX5TL/MVXqz\$WxDdLetJt5yvocmsTykxF.PVrTn8QWsTdsrBhdn1tUB"
EXTRA_USERS_PARAMS = "\
    useradd -p '${PASSWD}' student; \
    usermod -a -G wheel student; \
    "

IMAGE_INSTALL:append = " sudo"
IMAGE_INSTALL:append = " heartbeat"
IMAGE_INSTALL:append = " diary-char"
IMAGE_INSTALL:append = " grub-efi"

IMAGE_FSTYPES += "wic wic.bmap"
WKS_FILE = "mkefidisk.wks"

