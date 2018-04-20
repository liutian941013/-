package com.example.liufan.jingdongapplication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.MaParticulars;
import com.example.liufan.jingdongapplication.bean.Createorder;
import com.example.liufan.jingdongapplication.bean.ParticularsBean;
import com.example.liufan.jingdongapplication.bean.TianJIanGouWuBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;

public class ProductActivity1 extends AppCompatActivity implements View_particulars,View_TianJIanGouWu {
    private ListView lv;
    private Button xx_bt;
    private Button xx_bt1;
    private RadioGroup rg11;
    private ParticularsBean.DataBean data;
    private boolean ss;
    private boolean bo;
    private SharedPreferences user;
    private int uid;
    private int uid1;
    private int uid2;
    private String token1;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product1);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetParticulars(new IModle(), pid, this);
        lv = findViewById(R.id.lv);
        xx_bt = findViewById(R.id.but);
        xx_bt1 = findViewById(R.id.but1);
        //加入购物车的点击事件
        jiaru();
        //立即购买的点击事件
        liji();
    }
    //立即购买的点击事件
    private void liji() {
        xx_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = getSharedPreferences("User", MODE_PRIVATE);
                bo = user.getBoolean("bo", ss);
                if (bo == false) {
                    Intent intent = new Intent(ProductActivity1.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                     int pid = data.getPid();
                    Intent intent =new Intent(ProductActivity1.this,IndentActivity.class);
                   intent.putExtra("pid",pid+"");
                   startActivity(intent);
                }

            }
        });
    }

    //加入购物车的点击事件
    private void jiaru() {
        xx_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = getSharedPreferences("User", MODE_PRIVATE);
                bo = user.getBoolean("bo", ss);
                if (bo == false) {
                    Intent intent = new Intent(ProductActivity1.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    uid = user.getInt("uid", user.getInt("uid", uid1));
                    String token = user.getString("token", token1);
                    int uid = user.getInt("uid", uid1);
                     int pid = data.getPid();
                     Double price = data.getPrice();
                    final IPresenter iPresenter1 = new IPresenter();
                    iPresenter1.GetGouWu(new IModle(),uid+"", pid +"",token,ProductActivity1.this);
                    iPresenter1.GetCreateorder(new IModle(),uid+"",token,price+"",ProductActivity1.this);
                }
            }
        });
    }

    @Override
    public void ModleLoadError1(String s) {

    }

    @Override
    public void ModleLoadSuccess1(ParticularsBean particularsBean) {
        Log.d("反反复复", particularsBean.getData().toString());
        data = particularsBean.getData();
        MaParticulars maParticulars = new MaParticulars(ProductActivity1.this, data);
        lv.setAdapter(maParticulars);
    }

    @Override
    public void ModleDingDanError1(String s) {

    }

    @Override
    //创建订单成功
    public void ModleDingDanSuccess1(Createorder createorder) {
        final String code = createorder.getCode();
        if (code.equals("0")){
            Intent intent =new Intent(ProductActivity1.this,PaymentActivity.class);
            startActivity(intent);
        }else {

        }
    }


    @Override
    public void ViewLoadError1(String s) {
    }

    @Override
    public void ViewLoadSuccess1(TianJIanGouWuBean tianJIanGouWuBean) {
         String code = tianJIanGouWuBean.getCode();
         Log.d("少时诵诗书",code);
          if (code.equals("0")){
              Toast.makeText(ProductActivity1.this,"添加成功",Toast.LENGTH_LONG).show();
          }else {
              Toast.makeText(ProductActivity1.this,"添加失败",Toast.LENGTH_LONG).show();
          }
    }
}
