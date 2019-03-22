#!/usr/bin/python3
# -*- coding: utf-8 -*- 

import os 
import os,sys 
import hashlib
import shutil
import time
import datetime

#输入ID，返回设备名
def trans_ID(ID):
    fp = open('/home/b/Desktop/tools/dev.txt', 'r')
    
    cnt = 0;
    name = fp.readline();   
    while name:
        name = name.strip()
        if cnt == ID:
            break;
        
        cnt = cnt + 1;
        name = fp.readline()    

    fp.close()
    return name

def main():
    if len(sys.argv) != 2:
        print("未输入手机的ID")
        return; 

    phoneID = trans_ID(int(sys.argv[1]))

    os.system('cd ..')
    os.system('adb -s %s reboot bootloader' % phoneID)
    os.system('fastboot -s %s boot boot.img' % phoneID)
if __name__=="__main__": main()


