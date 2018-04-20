package com.example.liufan.jingdongapplication.view;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

public class MApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        Fresco.initialize(this,CiPanHuanCun.getDefaultImagePipelineConfig(this));

}
}
