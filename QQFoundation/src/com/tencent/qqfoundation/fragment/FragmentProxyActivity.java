package com.tencent.qqfoundation.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tencent.qqfoundation.activity.BaseActivity;
import com.tencent.qqfoundation.repo.pluins.install.QQClassLoader;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class FragmentProxyActivity extends BaseActivity {

    private FrameLayout rootView;
    private String fragmentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = new FrameLayout(this);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.setId(android.R.id.primary);
        setContentView(rootView);

        fragmentName = "com.tencent.fragmentdemo.HelloFragment";

        Fragment fragment = null;
        try {
            fragment = (Fragment) QQClassLoader.getClassLoader().loadClass(fragmentName)
                    .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(android.R.id.primary, fragment);
        ft.commit();


    }
}
