package com.tencent.qqfoundation.app2;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.qqfoundation.framework.core.loader.App.QQApp;

/**
 * Created by Jackrex on 8/1/14.
 */
public class Demo2App extends QQApp {


    private Bundle bundle;


    @Override
    protected void onCreate(Bundle params) {
       this.bundle = params;
    }

    @Override
    public String getEntryClassName() {
       // return FirstActivity.class.getName();
        return  null;
    }

    @Override
    protected void onStart() {

        if (bundle != null){

            String action = bundle.getString("action");
            String entryClass = null;

            if (!TextUtils.isEmpty(action) && action.equalsIgnoreCase("second")){

                entryClass = SecondActivity.class.getName();
            }else if (!TextUtils.isEmpty(action) && action.equalsIgnoreCase("first")){

                entryClass = FirstActivity.class.getName();

            }

            getApplicationContext().startActivity(this,entryClass);

            return;
        }

        Log.e("Jump Error","Bundle is null");


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
