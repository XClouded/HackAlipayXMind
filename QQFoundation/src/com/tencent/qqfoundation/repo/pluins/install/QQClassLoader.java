package com.tencent.qqfoundation.repo.pluins.install;

import com.tencent.qqfoundation.QQApplication;
import dalvik.system.DexClassLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class QQClassLoader extends DexClassLoader {


    public QQClassLoader(String dexPath, String optimizedDirectory, String libraryPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, libraryPath, parent);
    }


    public static QQClassLoader getClassLoader(){

        File dir = QQApplication.getQQApplication().getFilesDir();
        File apkPath = new File(dir,"FragmentDemo.apk");

        if (!apkPath.exists()){
            try {
                apkPath.createNewFile();
                PluginUtil.copyAssets(QQApplication.getQQApplication());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File dexoutdir = new File(dir,"dexout");
        dexoutdir.mkdir();

        QQClassLoader qqClassLoader = new QQClassLoader(apkPath.getAbsolutePath(),dexoutdir.getAbsolutePath(),null,QQApplication.getQQApplication().getClassLoader());

        return qqClassLoader;


    }




}
