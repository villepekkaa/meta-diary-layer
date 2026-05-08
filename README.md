# meta-diary

A small example layer used in the custom-linux course. It provides a sample image,
a handful of example recipes, and a few bbappends that demonstrate layer
customization.

## Compatible Yocto release

- scarthgap (see `LAYERSERIES_COMPAT_meta-diary` in `conf/layer.conf`)

## Dependencies

This layer depends on OE-Core (the `meta` layer from Poky).

- URI: https://git.yoctoproject.org/poky
- Branch: scarthgap (or compatible)

## Quick start

Clone the layer alongside your other Yocto layers:

```sh
git clone -b main https://github.com/villepekkaa/meta-diary-layer
```

Add the layer to your build:

```sh
source oe-init-build-env
bitbake-layers add-layer ../meta-diary
```

Build the example image:

```sh
bitbake diary-image
```

## Provided recipes and appends

Recipes:

- `diary-image.bb` (recipes-core/images)
  - Core image with SSH, package management, and common CLI tools.
  - Installs `diary-config`, `sl`, `heartbeat`, `sudo`, and creates a `student` user.
- `diary-config_0.1.bb` (recipes-diary/diary-config)
  - Installs `/etc/diary-motd` and writes `/etc/diary-version` at build time.
- `example_0.1.bb` (recipes-example/example)
  - Displays a build banner (created by `bitbake-layers`).
- `heartbeat.bb` (recipes-fun/heartbeat)
  - Installs a simple systemd service that prints a heartbeat timestamp.
- `sl_git.bb` (recipes-fun/sl)
  - Builds the classic `sl` terminal program from Git.

Appends:

- `busybox_%.bbappend` (recipes-core/busybox)
  - Adds `diary.cfg` and a local patch adjusting `echo` help text.
- `sudo_%.bbappend` (recipes-extended/sudo)
  - Installs `/etc/sudoers.d/90-wheel` and enables `pam-wheel` for sudo.

## Yocto Project Compatible self-audit

Reference: https://docs.yoctoproject.org/current/dev-manual/layers.html#making-sure-your-layer-is-compatible-with-yocto-project

Checklist:

- Layer naming: `meta-diary` follows the `meta-*` convention. (Yes)
- `LAYERSERIES_COMPAT` set for the claimed release. (Yes: scarthgap)
- README present and non-empty. (Yes: this file)
- `yocto-check-layer` run and PASS. (FAIL: layer present in BBLAYERS; remove meta-diary from build/conf/bblayers.conf before running)
- SECURITY policy present at repo root. (Needs SECURITY.md if this is a standalone repo)
- Patch files include `Upstream-Status`. (Yes: busybox patch)
- Layer does not set a machine or distro on inclusion. (Yes)

Notes:
- Run the checker from your build directory when ready:

```sh
source oe-init-build-env
yocto-check-layer --dependency ../meta ../meta-diary
```
