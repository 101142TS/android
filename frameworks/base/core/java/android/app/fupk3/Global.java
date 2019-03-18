package android.app.fupk3;
//===-----------------------------------------------------------*- xxxx -*-===//
//
//                     Created by 101142ts on 2018/4/4.
//                   Copyright (c) 2018. All rights reserved.
//===----------------------------------------------------------------------===//
// define some global value
//===----------------------------------------------------------------------===//

public class Global {
    //同时负责unpack 和 rebuild
    public final static String hookFile = "/data/local/tmp/unpack.txt";
    
    public final static String hookSo = "/data/local/tmp/libunpack.so";

    public final static String rebuildSo = "/data/local/tmp/librebuild.so";

    public final static String MainPackageName = "101142ts.fupk3";
}
