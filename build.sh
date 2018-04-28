#!/usr/bin/env bash
mvn -U -Dmaven.test.skip=true clean compile package
echo "mvn end"
mkdir ./build/demo-admin/
unzip ./demo-admin/target/demo-admin-1.0.0-SNAPSHOT.war -d ./build/demo-admin/
echo "unzip ./demo-admin/target/demo-admin-1.0.0-SNAPSHOT.war ./build/demo-admin/"
cd build
pwd
sudo docker build -t webdemo .
echo "docker build end"
# docker push webDemo:latest