#!/bin/bash

#nohup gunicorn --worker-class=gevent rest:app -b 127.0.0.1:5000 
gunicorn --worker-class=gevent rest:app -b 127.0.0.1:5000
