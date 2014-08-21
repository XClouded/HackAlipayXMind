package com.tencent.qqfoundation.framework.core.loader;

import android.os.Bundle;
import com.tencent.qqfoundation.framework.core.loader.App.AppDescription;
import com.tencent.qqfoundation.framework.core.loader.App.QQApp;


/**
 * Created by Jackrex on 8/1/14.
 */
public interface ApplicationManager {

    public QQApp findAppByName(String name);

    public boolean startApp(String sourceApp, String targetApp ,Bundle params);

    public void startApp(String schema, Bundle bundle);

    public  QQApp getTopRunningApp();

    public void finishApp(String appName);

    public void onDestroyApp(QQApp app);

    public void startEntryApp(Bundle params);

    public void addDescription(AppDescription description);

    public AppDescription findDescriptionByName(String appName);

    public void setEntryAppName(String appName);

    public void exit();

    public void clear();

    public void clearTop(QQApp app);



}
