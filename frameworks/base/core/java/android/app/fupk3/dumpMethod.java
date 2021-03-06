package android.app.fupk3;

import android.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.ClassLoader;
import java.lang.String;
import android.app.fupk3.Fupk;
import java.lang.Integer;
import android.os.Bundle;
import android.os.Debug;

public class dumpMethod {
    
    static Class useClassLoader(String tmpName)  {
        ClassLoader classLoader = Class.exgetClassLoader();
        String className = tmpName.substring(1, tmpName.length() - 1).replace('/', '.');
        
        Class result = null;
        try {
            result = classLoader.loadClass(className);
        } catch (Exception e) {
        } catch (Error r) {
        }
        return result;
    }
    
    /*
        当前所在的DvmDex序号，class序号，Method序号
    */
    static boolean hookMethod(String tmpName, int numDvmDex, int numClass, int stMethod) {
        Log.e("101142ts", tmpName);

        Class clazz;
        Object obj;
        Method[] methods;
        Constructor[] constructors;
        try {
            //clazz = ClassLoader.exgetClass(className);        //原
            clazz = useClassLoader(tmpName);                  //现
            /*
                clazz的获取方式或许可以修改
            */
            if (clazz == null)
                return false;
            obj = clazz.exnewInstance();
            constructors = clazz.getDeclaredConstructors();
            methods = clazz.getDeclaredMethods();
        } 
        catch (InstantiationException IE) {
            //exnewInstance的正常錯誤，不算異常
            return false;
        } catch (Exception e) {
            return false;
        } catch (Error r) {
            return false;
        }
        
        Log.e("101142ts", "methods&constructors.length = " + (constructors.length + methods.length));
        
        
        int n = constructors.length + methods.length;
        for (int i = stMethod; i < n; i++) {
            if (i < constructors.length) {
                int x = i;
                Log.e("101142ts", i + " " + constructors[x].toString());
                try {
                    constructors[x].setAccessible(true);
                    constructors[x].exnewInstance(numDvmDex, numClass, i);
                } catch (Exception e) {
                } catch (Error er) {
                }
            }
            else {
                int x = i - constructors.length;
                Log.e("101142ts", i + " " + methods[x].toString());
                try {
                    methods[x].setAccessible(true);
                    methods[x].exinvoke(numDvmDex, numClass, i, obj);
                } catch (Exception e) {
                } catch (Error er) {
                }
            }   
        }
        
        /*
        int n = methods.length;
        for (int i = stMethod; i < n; i++) {
            Log.e("101142ts", i + " " + methods[i].toString());
            try {
                methods[i].exinvoke(numDvmDex, numClass, i, obj);
            } catch (Exception e) {
            } catch (Error er) {
            }
        }
        */
        return true;
    }
}
