package com.example.liufan.jingdongapplication.view;

import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;
import com.example.liufan.jingdongapplication.bean.SubclassBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Classify {
    void ViewLoadError1(String s);
    void ViewLoadSuccess1(ClassifyBean classifyBean);
    void ViewLoadError(String s);
    void ViewLoadSuccess(SubclassBean subclassBean);
}
