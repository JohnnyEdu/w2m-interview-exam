#!/bin/bash
docker-compose up config -d
docker-compose up redis -d
sleep 10s
docker-compose up w2m-interview-exam -d