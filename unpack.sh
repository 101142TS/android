#!/bin/bash
adb root
sleep 10

rm -rf ./sample
mkdir ./sample
adb pull /data/local/tmp/unpack.txt ./sample/unpack.txt
packagename=`cat ./sample/unpack.txt | head -n 1`
dir="/data/data/"${packagename}
schefile=${dir}"/sche.txt"
recordfile=${dir}"/record.txt"
codedir=${dir}"/code"
while true;
do
    adb shell cat ${schefile}
    adb shell monkey -p $packagename 1
    sleep 12
    
    ans=`adb shell ls $dir | grep "finishdump.txt" | wc -l`
    if [ $ans -ne 0 ]
    then
        break
    fi

    
    data=`adb shell ps | grep $packagename | awk '{print $2}'`

    for line in $data
    do 
        adb shell kill -9 "$line"
    done

done

adb pull $recordfile ./sample/record.txt
adb pull $codedir ./sample/code
cat ./sample/record.txt | sort -n  | uniq > ./sample/uniq.txt
