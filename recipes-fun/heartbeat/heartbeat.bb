SUMMARY = "Simple heartbeat service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://heartbeat.sh file://heartbeat.service"

inherit systemd
SYSTEMD_SERVICE:${PN} = "heartbeat.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/heartbeat.sh ${D}${bindir}/heartbeat.sh
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/heartbeat.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${bindir}/heartbeat.sh ${systemd_system_unitdir}/heartbeat.service"