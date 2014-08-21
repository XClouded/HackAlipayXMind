package com.tencent.qqfoundation.framework.core.loader;

import android.app.Activity;
import android.app.Application;
import com.tencent.qqfoundation.framework.core.loader.App.QQApp;


/**
 * Created by Jackrex on 8/1/14.
 */
public interface QQApplicationContext {

    /**
     *
     * @param application
     */
    void attachContext(Application application);


    public Application getApplicationContext();



    public void startActivity(QQApp app, String className);


    public ApplicationManager getApplicationManager();


    public void updateActivity(Activity activity);


}
