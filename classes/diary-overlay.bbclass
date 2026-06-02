ROOTFS_OVERLAY_DIR ?= ""

# Copy the overlay into the rootfs during image creation.
ROOTFS_POSTPROCESS_COMMAND:append = " diary_overlay_install; "

diary_overlay_install() {
    if [ -n "${ROOTFS_OVERLAY_DIR}" ] && [ -d "${ROOTFS_OVERLAY_DIR}" ]; then
        cp -a "${ROOTFS_OVERLAY_DIR}"/* "${IMAGE_ROOTFS}/"
    fi
}
