#!/bin/sh

PORT=5001

ulimit -n 32768
#nohup python -u app.py $PORT &
python -u app.py $PORT
