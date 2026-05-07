SUMMARY = "Simple heartbeat service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://files/common-licenses/MIT;md5=f199be05ebc4b8e133ae119bc88c6e88"

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