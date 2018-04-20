package com.example.liufan.jingdongapplication.view;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.alipay.PayResult;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PaymentActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PaymentActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;


                }
            }
        }
    };
    private CheckBox zfb_cb;
    private CheckBox wx_cb;
    String url="http://169.254.33.192:8080/PayServer/AlipayDemo";
    private static final int SDK_PAY_FLAG = 1;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        price = intent.getStringExtra("Price");
        zfb_cb = findViewById(R.id.zfb_cb);
        wx_cb = findViewById(R.id.wx_cb);
        zfb_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wx_cb.setChecked(false);
            }
        });
        wx_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zfb_cb.setChecked(false);
            }
        });

    }

    public void pay(View view) {
            StringBuffer sb = new StringBuffer("?");
            sb.append("subject=");
            sb.append("来自Server测试的商品");
            sb.append("&");
            sb.append("body=");
            sb.append("该测试商品的详细描述");
            sb.append("&");
            sb.append("total_fee=");
            sb.append(price);
            url = url + sb.toString();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // 获取必须来自服务端 预支付结果订单信息
                    final String signResult = response.body().string();
                    Log.i("TAG", signResult);
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            // 构造PayTask 对象
                            PayTask alipay = new PayTask(PaymentActivity.this);
                            // 调用支付接口，获取支付结果
                            String result = alipay.pay(signResult, true);
                            Log.i("TAG", "走了pay支付方法.............");
                            Log.d("少时诵诗书所所", result.toString());
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                }
            });
        }

    }

