package com.tencent.qqfoundation.framework.core.loader.App;

/**
 * Created by Jackrex on 8/1/14.
 * 每一个app的描述
 * app 名字和 启动class
 */
public class AppDescription {

    private String name;
    private String className;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
