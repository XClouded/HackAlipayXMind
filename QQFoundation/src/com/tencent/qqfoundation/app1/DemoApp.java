package com.tencent.qqfoundation.app1;

import android.os.Bundle;
import com.tencent.qqfoundation.framework.core.loader.App.QQApp;


/**
 * Created by Jackrex on 8/1/14.
 */
public class DemoApp extends QQApp {




    @Override
    protected void onCreate(Bundle params) {



    }

    @Override
    public String getEntryClassName() {
        return DemoActivity.class.getName();
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onRestart(Bundle params) {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onDestroy(Bundle params) {

    }
}
