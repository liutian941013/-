package com.example.liufan.jingdongapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.RegBean;

public class RegActivity extends AppCompatActivity implements View_Reg{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
    }

    @Override
    public void ViewLoadError(String s) {

    }

    @Override
    public void ViewLoadSuccess(RegBean regBean) {

    }
}
