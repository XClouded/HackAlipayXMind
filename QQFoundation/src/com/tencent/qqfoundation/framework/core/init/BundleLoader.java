package com.tencent.qqfoundation.framework.core.init;

import android.content.Intent;
import android.content.res.AssetManager;

import android.util.Log;

import com.tencent.qqfoundation.framework.core.loader.App.AppDescription;
import com.tencent.qqfoundation.framework.core.loader.Constant.MsgCodeConstants;


import com.tencent.qqfoundation.framework.core.loader.QQApplicationContext;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Jackrex on 8/1/14.
 */
public class BundleLoader extends DefaultHandler {


    private static final String BUNDLES_CONFIG = "bundles.xml";
    private QQApplicationContext qqApplicationContext;


    public BundleLoader(QQApplicationContext qqApplicationContext) {
        this.qqApplicationContext = qqApplicationContext;
    }

    /**
     * 具体异常决定于解析方式
     *
     * @throws Exception 先用sax 解析 xml吧
     */
    public void load() throws ParserConfigurationException, SAXException {

        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(this);

        AssetManager assetManager = qqApplicationContext.getApplicationContext().getAssets();
        InputSource source;
        try {
            source = new InputSource(assetManager.open(BUNDLES_CONFIG));
        } catch (IOException e) {
            throw new ParserConfigurationException("open bundle file fail");
        }

        try {
            xmlReader.parse(source);
        } catch (IOException e) {
            throw new ParserConfigurationException("parse bundle file fail");
        }

        // 初始化完成
        Intent intent = new Intent();
        intent.setAction(MsgCodeConstants.FRAMEWORK_INITED);
    //    LocalBroadcastManager.getInstance(qqApplicationContext.getApplicationContext()).sendBroadcast(intent);
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        super.startElement(uri, localName, qName, attrs);


        Log.e("Start Element","Start");
        if ( localName.equalsIgnoreCase("application")){
            AppDescription appDescription = new AppDescription();
            appDescription.setName(attrs.getValue("name"));
            appDescription.setClassName(attrs.getValue("clazz"));
            qqApplicationContext.getApplicationManager().addDescription(appDescription);

            if ("true".equals(attrs.getValue("entry"))){
                qqApplicationContext.getApplicationManager().setEntryAppName(attrs.getValue("name"));
                Log.e("Bundler Loader AppliacationManager obj is ",qqApplicationContext.getApplicationManager().toString());

                Log.e("EntryName is ",attrs.getValue("name"));

            }

        }else if(localName.equalsIgnoreCase("")){



        }

    }
}
