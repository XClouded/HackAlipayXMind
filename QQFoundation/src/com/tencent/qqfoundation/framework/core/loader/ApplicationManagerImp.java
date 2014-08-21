package com.tencent.qqfoundation.framework.core.loader;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.qqfoundation.framework.core.loader.App.AppDescription;
import com.tencent.qqfoundation.framework.core.loader.App.QQApp;
import com.tencent.qqfoundation.framework.core.loader.Exception.QQLoadException;

import java.util.*;


/**
 * Created by Jackrex on 8/1/14.
 */
public class ApplicationManagerImp implements ApplicationManager {


    private QQApplicationContext qqApplicationContext;
    private Stack<QQApp> apps;
    private Map<String, QQApp> appMap;
    private List<AppDescription> appDescriptions;
    private String entryApp;


    public ApplicationManagerImp() {

        apps = new Stack<QQApp>();
        appMap = new HashMap<String, QQApp>();
        appDescriptions = new ArrayList<AppDescription>();
    }

    public void attachContext(QQApplicationContext applicationContext) {
        qqApplicationContext = applicationContext;
    }

    @Override
    public QQApp findAppByName(String name) {
        return appMap.get(name);
    }

    @Override
    public boolean startApp(String sourceApp, String targetApp, Bundle params) {

        if (targetApp == null) {

            throw new QQLoadException();
        }


        if (appMap.containsKey(targetApp)) {

            doRestart(targetApp, params);
        }


        //获取要启动的目标App
        AppDescription targetAppDescription = findDescriptionByName(targetApp);
        if (targetAppDescription != null) {
            QQApp app = createApp(targetAppDescription, params);
            app.setSourceapp(sourceApp);

            if (!apps.isEmpty())
                apps.peek().stop();

            apps.push(app);
            appMap.put(targetApp, app);
            app.start();
        }


        return true;
    }


    private void doRestart(String targetApp, Bundle params) {

        QQApp app = appMap.get(targetApp);
        QQApp tmp = null;
        while (app != (tmp = apps.peek())) {

            apps.pop();
            tmp.destory(params);

        }

        app.restart(params);


    }


    private QQApp createApp(AppDescription targetAppDes, Bundle params) {
        Object object = null;
        String targetAppClassName = targetAppDes.getClassName();
        try {
            object = Class.forName(targetAppClassName).newInstance();
        } catch (ClassNotFoundException e) {
            throw new QQLoadException("app class not found");
        } catch (IllegalAccessException e) {
            throw new QQLoadException("app class not accessed");
        } catch (InstantiationException e) {
            throw new QQLoadException("app class not instantiated");
        }

        if (!(object instanceof QQApp)) {
            throw new QQLoadException("app class not TeslaApp");
        }

        QQApp app = (QQApp) object;
        app.setAppName(targetAppDes.getName());
        app.attachContext(qqApplicationContext);
        app.create(params);
        return app;
    }

    @Override
    public void startApp(String schema, Bundle bundle) {

        //TODO UIBUS SPLITE SCHEMA TO JUMP


    }

    @Override
    public QQApp getTopRunningApp() {
        return null;
    }

    @Override
    public void finishApp(String appName) {
        QQApp app = appMap.get(appName);
        if (app != null) {
            app.destory(null);
        }
    }

    @Override
    public void onDestroyApp(QQApp app) {

        appMap.remove(app);
        apps.remove(app);

    }

    @Override
    public void startEntryApp(Bundle params) {

        startApp(null, entryApp, params);
    }

    @Override
    public void addDescription(AppDescription description) {

        appDescriptions.add(description);

    }

    @Override
    public AppDescription findDescriptionByName(String appName) {

        if (appDescriptions != null && !TextUtils.isEmpty(appName)){

            for (AppDescription description : appDescriptions){

                if (description != null){

                    if (appName.equalsIgnoreCase(description.getName())){

                        return description;

                    }

                }
            }

        }


        return null;
    }

    @Override
    public void setEntryAppName(String appName) {

        entryApp = appName;
    }

    @Override
    public void exit() {

        while (!apps.isEmpty()){
            QQApp app = apps.pop();
            app.destory(null);
        }

        appMap.clear();

    }

    @Override
    public void clear() {

        apps.clear();
        appMap.clear();

    }

    @Override
    public void clearTop(QQApp app) {
        QQApp tmp = null;
        while (app != (tmp = apps.peek())) {
            apps.pop();
            appMap.remove(tmp.getAppName());
            break;
        }
    }
}
