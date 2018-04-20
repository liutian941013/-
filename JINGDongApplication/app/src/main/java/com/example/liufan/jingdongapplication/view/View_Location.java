package com.example.liufan.jingdongapplication.view;
import com.example.liufan.jingdongapplication.bean.HuoQuLocationBean;
import com.example.liufan.jingdongapplication.bean.LocationBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Location {
    void ViewLoadError(String s);
    void ViewLoadSuccess(LocationBean locationBean);
    void ViewLoadError2(String s);
    void ViewLoadSuccess2(HuoQuLocationBean huoQuLocationBean);
}
