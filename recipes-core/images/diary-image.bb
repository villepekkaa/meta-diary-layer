SUMMARY = "Custom image for the custom-linux course"
LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-openssh package-management"
IMAGE_INSTALL = " \
	packagegroup-core-boot \
	packagegroup-core-full-cmdline \
	kernel-modules \
	vim less curl \
	"

inherit core-image

IMAGE_ROOTFS_EXTRA_SPACE = "65536"
