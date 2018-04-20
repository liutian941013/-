package com.example.liufan.jingdongapplication.view;
import com.example.liufan.jingdongapplication.bean.Uptou;
import com.example.liufan.jingdongapplication.bean.UserBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_Upload {
    void ViewLoadError(String s);
    void ViewLoadSuccess(Uptou uptou);
    void ViewUserError1(String s);
    void ViewUIserSuccess2(UserBean userBean);
}
