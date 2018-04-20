package com.example.liufan.jingdongapplication.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.LoginBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;

public class LoginActivity extends AppCompatActivity implements View_Login{

    private EditText login_mobile;
    private EditText login_password;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_mobile = findViewById(R.id.login_mobile);
        login_password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = login_mobile.getText().toString();
                String password = login_password.getText().toString();
                IPresenter iPresenter = new IPresenter();
                iPresenter.GetLogin(new IModle(),mobile,password,LoginActivity.this);
            }
        });
    }

    @Override
    public void ViewLoadError(String s) {

    }

    @Override
    public void ViewLoadSuccess(LoginBean loginBean) {
         String mobile = loginBean.getData().getMobile();
         String icon = loginBean.getData().getIcon();
         int uid = loginBean.getData().getUid();
         String token = loginBean.getData().getToken();
         SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
         SharedPreferences.Editor edit = sharedPreferences.edit();
         edit.putString("name",mobile);
         edit.putString("url",icon);
         edit.putInt("uid",uid);
         edit.putBoolean("bo",true);
         edit.putString("token",token);
         edit.commit();
         finish();
    }
}
