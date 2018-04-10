package com.example.liufan.jingdongapplication.api;

import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by liufan on 2018/4/9.
 */

public interface ApiSerner {
    //请求首页
@GET("getAd")
    Observable<HomeBean> GetHome();
    //请求九宫格
@GET("getCatagory")
    Observable<ClassifyBean> GetJiu();

}
