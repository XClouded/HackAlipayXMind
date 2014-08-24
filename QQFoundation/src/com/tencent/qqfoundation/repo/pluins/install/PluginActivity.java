package com.tencent.qqfoundation.repo.pluins.install;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class PluginActivity extends Activity {

    protected enum LANUCHMODE{

        STANDARD,SINGLETOP,SINGLEINSTANCE,SINGLETASK

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void setOrientation(int screenMode){
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setRequestedOrientation(screenMode);
    }


    //TODO
    protected void startActivityWithLanuchMode(Intent intent,LANUCHMODE lanuchmode ){

        switch (lanuchmode){

            case STANDARD:

                startActivity(intent);


                break;
            case SINGLETOP:

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;
            case SINGLEINSTANCE:


                break;

            case SINGLETASK:
                break;
        }



    }








}
