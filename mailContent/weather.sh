#! /bin/bash
export LANG=zh_CN.UTF-8
cd /root/java_pro/weather
date +"%Y-%m-%d %H:%M" > log
java -Dfile.encoding=UTF-8  -jar /root/java_pro/weather/weather.jar

