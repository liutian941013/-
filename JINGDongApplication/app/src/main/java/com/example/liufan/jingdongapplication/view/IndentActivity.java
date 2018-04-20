package com.example.liufan.jingdongapplication.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.alipay.PayResult;
import com.example.liufan.jingdongapplication.bean.Createorder;
import com.example.liufan.jingdongapplication.bean.HuoQuLocationBean;
import com.example.liufan.jingdongapplication.bean.LocationBean;
import com.example.liufan.jingdongapplication.bean.ParticularsBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.youth.banner.Banner;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IndentActivity extends AppCompatActivity implements View_particulars, View_Location {

    private Button indent_bt_bj;
    private Button indent_bt_tj;
    private Button indent_bt_qr;
    private ImageView indent_iv;
    private TextView indent_te;
    private TextView indent_te1;
    private TextView indent_te2;
    private TextView indent_te3;
    private TextView indent_te4;
    int uid1;
    String token1;
    private static final int SDK_PAY_FLAG = 1;
    private ParticularsBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent);
        indent_bt_bj = findViewById(R.id.indent_bt_bj);
        indent_bt_tj = findViewById(R.id.indent_bt_tj);
        indent_bt_qr = findViewById(R.id.indent_bt_qr);
        indent_iv = findViewById(R.id.indent_iv);
        indent_te = findViewById(R.id.indent_te);
        indent_te1 = findViewById(R.id.indent_te1);
        indent_te2 = findViewById(R.id.indent_te2);
        indent_te3 = findViewById(R.id.indent_te3);
        indent_te4 = findViewById(R.id.indent_te4);
        //请求商品信息
         final Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetIndent(new IModle(), pid, this);
        iPresenter.GetHuoQuLocation(new IModle(),"71",this);
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", token1);
        //添加的点击事件
        indent_bt_tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(IndentActivity.this, CompileActivity.class);
                 double bargainPrice = data.getBargainPrice();
                intent1.putExtra("bargainPrice",bargainPrice+"");
                startActivity(intent1);
            }
        });
        //确认购买
        indent_bt_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(IndentActivity.this,PaymentActivity.class);
                intent1.putExtra("Price",data.getPrice()+"");
                startActivity(intent1);
            }
        });
    }


    @Override
    public void ModleLoadError1(String s) {

    }

    @Override
    public void ModleLoadSuccess1(ParticularsBean particularsBean) {
        data = particularsBean.getData();
        String[] split = data.getImages().split("\\|");
        Glide.with(IndentActivity.this).load(split[0]).into(indent_iv);
        indent_te1.setText(data.getTitle());
        indent_te2.setText(data.getSubhead());
        indent_te3.setText("原价:" + data.getPrice() + "");
        indent_te4.setText("优惠价:" + data.getBargainPrice() + "");
    }

    @Override
    //创建订单失败方法
    public void ModleDingDanError1(String s) {

    }

    @Override
    //创建订单成功方法
    public void ModleDingDanSuccess1(Createorder createorder) {

    }

    @Override
    public void ViewLoadError(String s) {

    }

    @Override
    public void ViewLoadSuccess(LocationBean locationBean) {
        final String code = locationBean.getCode();
        if (code.equals("0")){
            Toast.makeText(IndentActivity.this,"添加成功",Toast.LENGTH_LONG).show();
        }else if (code.equals("0")){
            Toast.makeText(IndentActivity.this,"添加失败",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ViewLoadError2(String s) {

    }

    @Override
    public void ViewLoadSuccess2(HuoQuLocationBean huoQuLocationBean) {
        Log.d("灌灌灌灌",huoQuLocationBean.getData().getAddr());
        String addr = huoQuLocationBean.getData().getAddr();
        indent_te.setText(addr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int uid = sharedPreferences.getInt("uid", uid1);
        String token = sharedPreferences.getString("token", token1);
        Intent intent = getIntent();
        String addr = intent.getStringExtra("addr");
        String mobile = intent.getStringExtra("mobile");
        String name = intent.getStringExtra("name");
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetLocation(new IModle(), uid + "", addr, mobile, name, token, this);
    }
}
