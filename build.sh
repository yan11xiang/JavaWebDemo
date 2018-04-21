#!/usr/bin/env bash
mvn -U -Dmaven.test.skip=true clean compile package
echo "mvn end"
cp ./demo-admin/target/demo-admin-1.0.0-SNAPSHOT.war ./build/demo-admin.war
echo "cp ./demo-admin/target/demo-admin-1.0.0-SNAPSHOT.war ./build/demo-admin.war"
cd build
pwd
sudo docker build -t webdemo .
echo "docker build end"
# docker push webDemo:latest