SUMMARY = "FS Kernel Module"
DESCRIPTION = "TFS kernel module"
LICENSE = "CLOSED"


BB_STRICT_CHECKSUM = "0"
__BBSEENSRCREV="1"

#SRCREV = "main"
#PV = "0.1+git${SRCPV}"
SRC_URI = "git://github.com/rsveldema/tfs.git;branch=main;protocol=https"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit cmake
inherit module

EXTRA_OECMAKE = "-D yocto=1"

RPROVIDES_${PN} += "kernel-module-tfs-kmod"
