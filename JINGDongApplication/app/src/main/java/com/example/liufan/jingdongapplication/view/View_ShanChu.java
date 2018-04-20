package com.example.liufan.jingdongapplication.view;
import com.example.liufan.jingdongapplication.bean.ShanChuGouWuBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_ShanChu {
    void ViewLoadError(String s);
    void ViewLoadSuccess(ShanChuGouWuBean shanChuGouWuBean);

}
