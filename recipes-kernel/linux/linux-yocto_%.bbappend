FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://diary.cfg \
            file://0001-diary-mark-kernel-banner.patch \
            "
