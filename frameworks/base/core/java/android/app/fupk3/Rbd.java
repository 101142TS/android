package android.app.fupk3;

import android.util.ArrayMap;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import android.app.fupk3.Global;

public class Rbd {
    private String mPackageName;
    private int mMode;

    static {
//        System.loadLibrary("Fupk3");
        System.load(Global.rebuildSo);
    }

    public Rbd(String packageName, int mode) {
        Log.d("101142ts", "Init unpack for package " + packageName);
        mPackageName = packageName;
        mMode = mode;
    }


    public void rebuildAfter(final int millis) {
        Thread t = new Thread() {
            @Override
            public void run() {
                rebuildNow(millis);
            }
        };
        t.start();
    }

    public void rebuildNow(int millis) {
        Log.d("101142ts", "rebuild now");
        rebuildAll("/data/data/" + mPackageName, millis, mMode);
    }

    private native void rebuildAll(String folder, int millis, int mMode);
}
