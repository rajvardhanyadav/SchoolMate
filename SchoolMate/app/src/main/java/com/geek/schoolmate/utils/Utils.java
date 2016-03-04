package com.geek.schoolmate.utils;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by ry41071 on 16-02-2016.
 */
public class Utils {
    private static final String TAG = "Utils";

    public static String readExternalStorageFile(String fileName) {
        Log.d(TAG, "readExternalStorageFile()");
        StringBuilder jsonBufString = new StringBuilder();
        try {
            File myFile = new File(new StringBuilder(Environment.getExternalStorageDirectory().toString()).
                    append("/").append(fileName).toString());
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            String line = "";
            while ((line = myReader.readLine()) != null) {
                jsonBufString.append(line);
            }
            myReader.close();

        } catch (Exception e) {

        }
        return jsonBufString.toString();
    }

    public static void copyFileFromAssetsToExternalStorage(String fileName, AssetManager assetManager) {
        Log.d(TAG, "copyFileFromAssetsToExternalStorage()");
        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(fileName);
            out = new FileOutputStream(new StringBuilder(Environment.getExternalStorageDirectory().toString()).
                    append("/").append(fileName).toString());
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        Log.d(TAG, "copyFile()");
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static boolean canMakeSmores() {
        Log.d(TAG, "canMakeSmores()");
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
}
