package com.example.liufan.jingdongapplication.view;

import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_et {
    void ViewLoadError(String s);
    void ViewLoadSuccess(HomeBean homeBean);
    void ViewLoadError1(String s);
    void ViewLoadSuccess1(ClassifyBean classifyBean);
}
