SUMMARY = "FS Kernel Module"
DESCRIPTION = "TFS kernel module"
LICENSE = "CLOSED"

inherit systemd


BB_STRICT_CHECKSUM = "0"
__BBSEENSRCREV="1"

#SRCREV = "main"
#PV = "0.1+git${SRCPV}"
SRC_URI = "git://github.com/rsveldema/tfs.git;branch=main;protocol=https"
SRC_URI += "file://tfs-setup.sh"
SRC_URI += "file://tfs.service"


SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"


inherit cmake
inherit module

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "tfs.service"

EXTRA_OECMAKE = "-D yocto=1"

RPROVIDES:${PN} += "kernel-module-tfs-kmod"

FILES:${PN} += "${systemd_unitdir}/system/tfs.service"
FILES:${PN} += " /etc/tfs-setup.sh"
FILES:${PN} += " /etc/modules-load.d"
FILES:${PN} += " /loopfs/dummy.txt"
FILES:${PN} += " /data"
FILES:${PN} += " /data/dummy.txt"

do_install:append() {
  rm -rf ${D}/etc/modules-load.d

  install -d ${D}/data
  install -d ${D}/loopfs

  echo "dummy" > ${D}/data/dummy.txt
  echo "dummy" > ${D}/loopfs/dummy.txt


  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/tfs.service ${D}/${systemd_unitdir}/system
  install -m 0755 ${WORKDIR}/tfs-setup.sh ${D}/etc/tfs-setup.sh
}

