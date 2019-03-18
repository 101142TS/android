package android.app.fupk3;

import android.util.ArrayMap;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import android.app.fupk3.Global;

public class Fupk {
    private String mPackageName;

    static {
//        System.loadLibrary("Fupk3");
        System.load(Global.hookSo);
    }

    public Fupk(String packageName) {
        Log.d("101142ts", "Init unpack for package " + packageName);
        mPackageName = packageName;
    }


    public void unpackAfter(final int millis) {
        Thread t = new Thread() {
            @Override
            public void run() {
                unpackNow(millis);
            }
        };
        t.start();
    }

    public void unpackNow(int millis) {
        Log.e("101142ts", "unpack now");
        unpackAll("/data/data/" + mPackageName, millis);
    }

    private native void unpackAll(String folder, int millis);
}
