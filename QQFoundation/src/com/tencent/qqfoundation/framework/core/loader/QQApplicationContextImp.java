package com.tencent.qqfoundation.framework.core.loader;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;


import com.tencent.qqfoundation.framework.core.init.BundleLoader;
import com.tencent.qqfoundation.framework.core.loader.App.QQApp;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Jackrex on 8/1/14.
 */
public class QQApplicationContextImp implements QQApplicationContext {


    private ApplicationManager applicationManager;
    private Application application;
    private Activity activity;

    @Override
    public void attachContext(Application application) {

        this.application = application;
        init();
    }


    private void init(){

        ApplicationManagerImp applicationManager = new ApplicationManagerImp();
        applicationManager.attachContext(this);
        this.applicationManager = applicationManager;

        try {
            new BundleLoader(QQApplicationContextImp.this).load();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Application getApplicationContext() {
        return application;
    }

    @Override
    public void startActivity(QQApp app, String className) {

        Intent intent = new Intent();
        intent.setClassName(activity, className);
        activity.startActivity(intent);


    }

    @Override
    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }



    public void exit() {
        applicationManager.exit();
        android.os.Process.killProcess( android.os.Process.myPid());
        System.exit(10);
    }


    @Override
    public void updateActivity(Activity activity) {
        this.activity = null;
        this.activity = activity;
    }
}
