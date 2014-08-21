package com.tencent.qqfoundation.activity.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.tencent.qqfoundation.QQApplication;
import com.tencent.qqfoundation.R;
import com.tencent.qqfoundation.activity.BaseActivity;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class Demo1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
    }


    /**
     * 通过scheme 匹配跳转
     * @param v
     */
    public void jump(View v){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("app://www.qq.com:80/demo/2");
        intent.setData(uri);
        startActivity(intent);

    }

}
