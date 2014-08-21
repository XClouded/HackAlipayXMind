package com.tencent.qqfoundation.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.tencent.qqfoundation.QQApplication;
import com.tencent.qqfoundation.R;
import com.tencent.qqfoundation.framework.core.loader.QQApplicationContext;
import com.tencent.qqfoundation.fragment.FragmentProxyActivity;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private QQApplicationContext applicationContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * 通过将scheme交给统一SchemeActivity处理
     * @param view
     */
    public void jumpdemo1(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(QQApplication.APP_SCHEMA + "://" + "com.tencent.qqfoundation/activity.demo.Demo1Activity"));
        startActivity(intent);
    }


    public  void loadfragment(View v){

        Intent intent = new Intent();
        intent.setClass(this, FragmentProxyActivity.class);
        startActivity(intent);



    }



    public void startapp(View view){

        applicationContext = ((QQApplication)getApplication()).getQqApplicationContext();
        applicationContext.updateActivity(this);
        Log.e("Main AppliacationManager obj is ", applicationContext.getApplicationManager().toString());
        applicationContext.getApplicationManager().startEntryApp(null);

    }


}
