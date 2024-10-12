#!/bin/bash

docker build -t sapiens-app-backend .
docker-compose up --build
