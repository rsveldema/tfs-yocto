#!/bin/bash


dd if=/dev/zero of=/data/loopbackfile1.img count=1000
dd if=/dev/zero of=/data/loopbackfile2.img count=1000

losetup -fP /data/loopbackfile1.img
losetup -fP /data/loopbackfile2.img

losetup -a|grep file

#mount -o loop -t tfs /dev/loop0 /loopfs
mount -o loop -o dummy1 -t tfs /dev/loop0 /loopfs -O workme


