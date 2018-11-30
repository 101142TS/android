#!/usr/bin/python3
# -*- coding: utf-8 -*- 

import os 
import os,sys 
import hashlib
def Test2(rootDir): 
    for lists in os.listdir(rootDir): 
        path = os.path.join(rootDir, lists) 
         
        if os.path.isdir(path): 
            Test2(path)
        else:
            if path.endswith("cntfile.txt"):
                fp = open(path, "r")
                n = [int(x) for x in next(fp).split()][0]
                fp.close()
                
                
                md5list = []
                for i in range(1, int(n) + 1):
                    itpath = path.replace("cntfile.txt", str(i))
                    f1 = open(itpath, "rb")
                    md5obj = hashlib.md5()
                    md5obj.update(f1.read())
                    md5list.append(md5obj.hexdigest())
                    f1.close()
                
                md5list = list(set(md5list))

                fp = open(path, "w")
                print(len(md5list), file = fp)
                fp.close()

                if len(md5list) > 1:
                    print(path)

                for i in range(1, int(n) + 1):
                    itpath = path.replace("cntfile.txt", str(i))

                    f1 = open(itpath, "rb")
                    md5obj = hashlib.md5()
                    md5obj.update(f1.read())
                    x = md5obj.hexdigest()
                    f1.close()

                    if x in md5list:
                        md5list.remove(x)
                    else:
                        os.remove(itpath)

def main():
    Test2(os.getcwd() + "/sample/code/")
if __name__=="__main__": main()

 