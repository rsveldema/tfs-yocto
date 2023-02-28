

#
if [ -n "$BASH_SOURCE" ]; then
    THIS_SCRIPT=$BASH_SOURCE
elif [ -n "$ZSH_NAME" ]; then
    THIS_SCRIPT=$0
else
    THIS_SCRIPT="$(pwd)/oe-init-build-env"
    if [ ! -e "$THIS_SCRIPT" ]; then
        echo "Error: $THIS_SCRIPT doesn't exist!" >&2
        echo "Please run this script in init-env's directory." >&2
        exit 1
    fi
fi


if [ -z "$OEROOT" ]; then
    OEROOT=$(dirname "$THIS_SCRIPT")
    OEROOT=$(readlink -f "$OEROOT")
    export OEROOT=$OEROOT/poky
fi
echo "OEROOT= $OEROOT"

unset THIS_SCRIPT

export BDIR=$OEROOT/../build
export BITBAKEDIR=$OEROOT/bitbake

ls "$OEROOT"/scripts/oe-buildenv-internal

. "$OEROOT"/scripts/oe-buildenv-internal &&
    TEMPLATECONF="$TEMPLATECONF" "$OEROOT"/scripts/oe-setup-builddir || {
    unset OEROOT
    return 1
}


bitbake-layers add-layer ./meta-tfs