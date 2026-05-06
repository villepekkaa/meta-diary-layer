FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://90-wheel"

FILES:${PN} += "${sysconfdir}/sudoers.d/90-wheel"

do_install:append() {
    install -d ${D}${sysconfdir}/sudoers.d
    install -m 0440 ${WORKDIR}/90-wheel ${D}${sysconfdir}/sudoers.d/90-wheel
}


