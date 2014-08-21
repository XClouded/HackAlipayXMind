package com.tencent.qqfoundation.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.qqfoundation.QQApplication;

/**
 * Created by jackrexxie on 2014/8/11.
 *
 * 代理跳转activity
 */
public class SchemeJumpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri.getScheme().equalsIgnoreCase(QQApplication.APP_SCHEMA)){

            String packageName = uri.getHost();
            String className = uri.getPath();
            className = packageName+"."+className.replace("/","");

            try{
                Intent jumpIntent = new Intent();
                jumpIntent.setClassName(packageName,className);
                startActivity(jumpIntent);

            }catch (ActivityNotFoundException exception){
                exception.printStackTrace();
            }

        }else {
           // 一般处理
            startActivity(intent);

        }

        finish();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}
