package com.example.liufan.jingdongapplication.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liufan.jingdongapplication.R;

public class MainActivity extends AppCompatActivity {

    private TextView te;
    private ImageView iv;
    private boolean flao;
    int i = 3;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {
                if (i != 1) {
                    handler.sendEmptyMessageDelayed(0, 1000);
                    i--;
                } else {
                    Intent intent = new Intent(MainActivity.this, ZhuActivity.class);
                    startActivity(intent);
                }

            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            iv = findViewById(R.id.iv);
            iv.setImageResource(R.drawable.sanm);
            handler.sendEmptyMessageDelayed(0, 1000);
        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("bbb",true);
        edit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        final boolean bbb = sharedPreferences.getBoolean("bbb", flao);
        if (bbb==true){
            handler.sendEmptyMessageDelayed(0, 1000);
        }else if (bbb==false){
            Intent intent =new Intent(MainActivity.this, ZhuActivity.class);
            startActivity(intent);
        }
    }
}
