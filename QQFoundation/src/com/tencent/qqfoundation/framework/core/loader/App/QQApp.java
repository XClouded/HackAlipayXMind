package com.tencent.qqfoundation.framework.core.loader.App;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import com.tencent.qqfoundation.framework.core.loader.QQApplicationContext;


import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by Jackrex on 8/1/14.
 */
public abstract class QQApp {

    private QQApplicationContext applicationContext;

    private Stack<WeakReference<Activity>> activitys;
    private String sourceapp;
    private String appName;


    protected QQApp() {

        activitys = new Stack<WeakReference<Activity>>();
    }

    public void create(Bundle params){

        onCreate(params);
    }



    public void start(){

        String className = getEntryClassName();

        if (className!=null){

            try {

                getApplicationContext().startActivity(this,className);
                return;
            }catch (ActivityNotFoundException e){

            }

        }



        onStart();


    }

    public void stop(){
        onStop();
    }

    public void restart(Bundle params){
        onRestart(params);
    }

    protected abstract void onCreate(Bundle params);


    public abstract String getEntryClassName();


    protected abstract void onStart();


    protected abstract void onRestart(Bundle params);


    protected abstract void onStop();


    protected abstract void onDestroy(Bundle params);


    public final void pushActivity(Activity activity) {
        if(!activitys.isEmpty()&&activitys.peek().get()==null){//被恢复的时候替换
            activitys.pop();
        }
        WeakReference<Activity> item = new WeakReference<Activity>(activity);
        activitys.push(item);
    }


    public void removeActivity(Activity activity) {
        WeakReference<Activity> dirtyItem = null;
        for (WeakReference<Activity> item : activitys) {
            if (item.get() == null) {
                continue;
            }
            if (item.get() == activity) {
                dirtyItem = item;
                break;
            }
        }
        activitys.remove(dirtyItem);
        if (activitys.isEmpty()) {
          destory(null);
        }
    }


    public Activity getTopActivity() {
        if (activitys.isEmpty())
            return null;
        return activitys.peek().get();
    }


    public void attachContext(QQApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void destory(Bundle params){

        WeakReference<Activity> reference;
        Activity activity;
        while (!activitys.isEmpty()&&((reference = activitys.pop())!=null)){

            activity = reference.get();
            if (activity!=null&&!activity.isFinishing()){

                activity.finish();
            }

        }

        onDestroy(params);

    }


    public QQApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public void setApplicationContext(QQApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public String getSourceapp() {
        return sourceapp;
    }

    public void setSourceapp(String sourceapp) {
        this.sourceapp = sourceapp;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
