package com.tencent.qqfoundation.repo.pluins;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;

import java.lang.reflect.Method;

/**
 * Created by jackrexxie on 2014/8/11.
 */
public class PluginContext extends ContextThemeWrapper {

    private AssetManager assetManager;
    private Resources resources;
    private Resources.Theme theme;
    private ClassLoader classLoader;
    private int resId;
    private AssetManager getAssetManager(String apkpath){

        AssetManager manager = null;
        try {
            manager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            addAssetPathMethod.invoke(manager,apkpath);
        }catch (Throwable e){
            e.printStackTrace();
        }
        return manager;

    }

    private Resources getRes(Context ctx, AssetManager selfAsset) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        Configuration con = ctx.getResources().getConfiguration();
        return new Resources(selfAsset, metrics, con);
    }

    private Resources.Theme getTheme(Resources selfResources) {
        Resources.Theme theme = selfResources.newTheme();
        resId = getInnerRIdValue("com.android.internal.R.style.Theme");
        theme.applyStyle(resId, true);
        return theme;
    }

    private int getInnerRIdValue(String rStrnig) {
        int value = -1;
        try {
            int rindex = rStrnig.indexOf(".R.");
            String Rpath = rStrnig.substring(0, rindex + 2);
            int fieldIndex = rStrnig.lastIndexOf(".");
            String fieldName = rStrnig.substring(fieldIndex + 1, rStrnig.length());
            rStrnig = rStrnig.substring(0, fieldIndex);
            String type = rStrnig.substring(rStrnig.lastIndexOf(".") + 1, rStrnig.length());
            String className = Rpath + "$" + type;
            Class<?> cls = Class.forName(className);
            value = cls.getDeclaredField(fieldName).getInt(null);


        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value;
    }


    public PluginContext(Context base, int themeres) {
        super(base, themeres);
    }

    public PluginContext(Context base, int themeres,String apkPath, ClassLoader classLoader) {
        super(base, themeres);
        assetManager = getAssetManager(apkPath);
        resources = getRes(base,assetManager);
        theme = getTheme(resources);

    }

    @Override
    public Resources getResources() {
        return resources;
    }

    @Override
    public AssetManager getAssets() {
        return assetManager;
    }

    @Override
    public Resources.Theme getTheme() {
        return theme;
    }

    @Override
    public ClassLoader getClassLoader() {
        if (classLoader != null) {
            return classLoader;
        }
        return super.getClassLoader();
    }
}
