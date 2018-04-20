package com.example.liufan.jingdongapplication.view;
import com.example.liufan.jingdongapplication.bean.RegBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Reg {
    void ViewLoadError(String s);
    void ViewLoadSuccess(RegBean regBean);
}
