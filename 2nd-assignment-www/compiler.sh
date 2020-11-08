#!/bin/bash

PROJECT_NAME=hw2
classes_dir=/opt/tomcat/latest/webapps/hw2/WEB-INF/classes

cd lib

# create a temp file with all .java files to be compiled
ls ../src  | grep  ".java" |  grep -v "mysql-connector-java-8.0.22.jar" > files_to_compile.tmp

# compile the .java files
while read line; do javac -cp  .:mysql-connector-java-8.0.22.jar:servlet-api.jar:  -d  $classes_dir  ../src/$line; done < files_to_compile.tmp

# delete the .tmp file we used.
rm files_to_compile.tmp


