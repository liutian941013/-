package com.example.liufan.jingdongapplication.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.fragment.Classify_Fragment1;
import com.example.liufan.jingdongapplication.fragment.Find_Fragment2;
import com.example.liufan.jingdongapplication.fragment.Home_Fragment;
import com.example.liufan.jingdongapplication.fragment.My_Fragment4;
import com.example.liufan.jingdongapplication.fragment.Shopping_Fragment3;

public class ZhuActivity extends AppCompatActivity {

    private RadioGroup rg;
    private Home_Fragment home_fragment;
    private Classify_Fragment1 classify_fragment1;
    private Find_Fragment2 find_fragment2;
    private Shopping_Fragment3 shopping_fragment3;
    private My_Fragment4 my_fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        rg = findViewById(R.id.rg);
        home_fragment = new Home_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl,home_fragment).commit();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Hined();
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                switch (checkedId){
                    case R.id.home_rb:
                        supportFragmentManager.beginTransaction().show(home_fragment).commit();
                        break;
                    case R.id.classify_rb:
                        if (classify_fragment1==null){
                            classify_fragment1 = new Classify_Fragment1();
                            supportFragmentManager.beginTransaction().add(R.id.fl,classify_fragment1).commit();
                        }else {
                            supportFragmentManager.beginTransaction().show(classify_fragment1).commit();
                        }
                        break;
                    case R.id.find_rb:
                        if (find_fragment2==null){
                            find_fragment2 = new Find_Fragment2();
                            supportFragmentManager.beginTransaction().add(R.id.fl,find_fragment2).commit();
                        }else {
                            supportFragmentManager.beginTransaction().show(find_fragment2).commit();
                        }
                        break;
                    case R.id.shopping_rb:
                        if (shopping_fragment3==null){
                            shopping_fragment3 = new Shopping_Fragment3();
                            supportFragmentManager.beginTransaction().add(R.id.fl,shopping_fragment3).commit();
                        }else {
                            supportFragmentManager.beginTransaction().show(shopping_fragment3).commit();
                        }
                        break;
                    case R.id.my_rb:
                        if (my_fragment4==null){
                            my_fragment4 = new My_Fragment4();
                            supportFragmentManager.beginTransaction().add(R.id.fl,my_fragment4).commit();
                        }else {
                            supportFragmentManager.beginTransaction().show(my_fragment4).commit();
                        }
                        break;
                }
            }
        });

    }
    public void Hined(){
        if (home_fragment!=null&&home_fragment.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(home_fragment).commit();
        }
        if (classify_fragment1!=null&&classify_fragment1.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(classify_fragment1).commit();
        }
        if (find_fragment2!=null&&find_fragment2.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(find_fragment2).commit();
        }
        if (shopping_fragment3!=null&&shopping_fragment3.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(shopping_fragment3).commit();
        }
        if (my_fragment4!=null&&my_fragment4.isAdded()){
            getSupportFragmentManager().beginTransaction().hide(my_fragment4).commit();
        }
    }
}
