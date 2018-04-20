package com.example.liufan.jingdongapplication.view;

import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;
import com.example.liufan.jingdongapplication.bean.TianJIanGouWuBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_TianJIanGouWu {
    void ViewLoadError1(String s);
    void ViewLoadSuccess1(TianJIanGouWuBean tianJIanGouWuBean);

}
