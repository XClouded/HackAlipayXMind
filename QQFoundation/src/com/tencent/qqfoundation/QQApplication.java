package com.tencent.qqfoundation;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.tencent.qqfoundation.framework.core.loader.QQApplicationContext;
import com.tencent.qqfoundation.repo.pluins.PluginContext;
import com.tencent.qqfoundation.repo.pluins.install.PluginUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class QQApplication extends Application {


    public static String APP_SCHEMA = "qqapp";
    public static QQApplication sApplication;
    public static Context soutContext;
    private QQApplicationContext qqApplicationContext;
    private static QQApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;


        // Demo 简单演示 就不说性能了 jackrex
        File dir = QQApplication.getQQApplication().getFilesDir();
        File apkPath = new File(dir,"Fragment.apk");

        if (!apkPath.exists()){
            try {
                apkPath.createNewFile();
                PluginUtil.copyAssets(QQApplication.getQQApplication());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        soutContext =  new PluginContext(this, 0, "/data/data/com.tencent.qqfoundation/files/Fragment.apk", getClassLoader());

        try {
            qqApplicationContext = (QQApplicationContext) Class.forName("com.tencent.qqfoundation.framework.core.loader.QQApplicationContextImp").newInstance();
            qqApplicationContext.attachContext(this);
            sInstance = this;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    public static QQApplication getQQApplication(){
    	
    	return sApplication;
    }
    
    public static Context getOutContext(){
    	
    	return soutContext;
    }
    
    
    public static QQApplication getInstance(){

        return sInstance;
    }

    public QQApplicationContext getQqApplicationContext(){

        return qqApplicationContext;
    }
    
}
