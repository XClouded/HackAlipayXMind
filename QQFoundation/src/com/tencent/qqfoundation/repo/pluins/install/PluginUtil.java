package com.tencent.qqfoundation.repo.pluins.install;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.*;

/**
 * Created by jackrexxie on 2014/8/12.
 */
public class PluginUtil {

    public  static void copyAssets(Context context) {
        AssetManager assetManager = context.getAssets();
        String filename = "Fragment.apk";

            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(context.getFilesDir(), filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " , e);
            }
        }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }


}
