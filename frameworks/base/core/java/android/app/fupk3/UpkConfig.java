package android.app.fupk3;
import android.app.fupk3.Global;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class UpkConfig {
    public String mTargetPackage = "";
    public String mTargetApplication = "";
    public String mTargetActivity = "";

    public UpkConfig() {

    }

    public boolean load() {
        File file = new File(Global.hookFile);
        if (!file.exists()) {
            return false;
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            mTargetPackage = br.readLine();
            mTargetApplication = br.readLine();
            mTargetActivity = br.readLine();
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return !mTargetPackage.isEmpty();
    }
}
