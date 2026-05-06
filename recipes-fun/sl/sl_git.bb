# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Toyoda"
LIC_FILES_CHKSUM = "file://LICENSE;md5=74e9dd589a0ab212a9002b15ef2b82f0"

SRC_URI = "git://github.com/eyJhb/sl.git;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git"
SRCREV = "e419525050ef3fa684c62cf98b89d29cc2adf84d"

S = "${WORKDIR}/git"

# NOTE: some of these dependencies may be optional, check the Makefile and/or upstream documentation
DEPENDS = "ncurses"

# NOTE: this is a Makefile-only piece of software, so we cannot generate much of the
# recipe automatically - you will need to examine the Makefile yourself and ensure
# that the appropriate arguments are passed in.

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# You will almost certainly need to add additional arguments here
 	${CC} ${CFLAGS} ${LDFLAGS} -o sl sl.c -lncurses
}
do_install () {
	# NOTE: unable to determine what to put here - there is a Makefile but no
	# target named "install", so you will need to define this yourself 
	 install -d ${D}${bindir}
   	 install -m 0755 sl ${D}${bindir}/sl

   	 if [ -f sl.1 ]; then
   	     install -d ${D}${mandir}/man1
   	     install -m 0644 sl.1 ${D}${mandir}/man1/sl.1
   	 fi
}

