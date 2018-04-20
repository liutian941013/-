package com.example.liufan.jingdongapplication.view;

import com.example.liufan.jingdongapplication.bean.ProductlistBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_product {
    void ModleLoadError1(String s);
    void ModleLoadSuccess1(ProductlistBean productlistBean);

}
