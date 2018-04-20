package com.example.liufan.jingdongapplication.view;


import com.example.liufan.jingdongapplication.bean.Createorder;
import com.example.liufan.jingdongapplication.bean.ParticularsBean;

/**
 * Created by liufan on 2018/4/10.
 */

public interface View_particulars {
    void ModleLoadError1(String s);
    void ModleLoadSuccess1(ParticularsBean particularsBean);
    void ModleDingDanError1(String s);
    void ModleDingDanSuccess1(Createorder createorder);

}
