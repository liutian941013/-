package com.example.liufan.jingdongapplication.view;

import com.example.liufan.jingdongapplication.bean.GoodBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Seek {
    void ViewGoodError(String s);
    void ViewGoodSuccess(GoodBean goodBean);
}
