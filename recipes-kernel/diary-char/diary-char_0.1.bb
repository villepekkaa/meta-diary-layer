SUMMARY = "Custom diary char device module"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "file://Makefile \
           file://diary_char.c \
           file://COPYING \
          "
S = "${WORKDIR}"

# module auto-load on boot:
KERNEL_MODULE_AUTOLOAD += "diary_char"