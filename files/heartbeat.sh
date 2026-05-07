#!/bin/sh
while true; do
    echo "heartbeat $(date -u +%FT%TZ)"
    sleep 10
done