package com.tencent.qqfoundation.app1;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tencent.qqfoundation.QQApplication;
import com.tencent.qqfoundation.R;
import com.tencent.qqfoundation.framework.core.loader.QQApplicationContext;



public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }




    public void jumpSecond(View v){

        Bundle bundle = new Bundle();
        bundle.putString("action","second");
        QQApplicationContext applicationContext = ((QQApplication)getApplication()).getQqApplicationContext();
        applicationContext.updateActivity(this);
        QQApplication.getInstance().getQqApplicationContext().getApplicationManager().startApp(null,"demo2",bundle);



    }



    public void jump(View v){
        Bundle bundle = new Bundle();
        bundle.putString("action","first");
        QQApplicationContext applicationContext = ((QQApplication)getApplication()).getQqApplicationContext();
        applicationContext.updateActivity(this);
        QQApplication.getInstance().getQqApplicationContext().getApplicationManager().startApp(null,"demo2",bundle);


    }

}



