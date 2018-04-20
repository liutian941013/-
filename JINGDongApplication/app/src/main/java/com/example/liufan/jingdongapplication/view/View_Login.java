package com.example.liufan.jingdongapplication.view;
import com.example.liufan.jingdongapplication.bean.LoginBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Login {
    void ViewLoadError(String s);
    void ViewLoadSuccess(LoginBean loginBean);
}
