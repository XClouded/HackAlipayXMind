package com.tencent.qqfoundation.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.qqfoundation.QQApplication;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void jumpActivity(String url){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(QQApplication.APP_SCHEMA+"://"+url));
        startActivity(intent);

    }

}
