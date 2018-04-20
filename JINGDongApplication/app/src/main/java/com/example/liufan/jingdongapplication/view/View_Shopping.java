package com.example.liufan.jingdongapplication.view;

import com.example.liufan.jingdongapplication.bean.ShoppingBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Shopping {
    void ViewLoadError(String s);
    void ViewLoadSuccess(ShoppingBean shoppingBean);

}
