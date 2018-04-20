package com.example.liufan.jingdongapplication.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.MaAdapter_product;
import com.example.liufan.jingdongapplication.bean.ProductlistBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements View_product {

    private XRecyclerView xlv;
    int i = 1;
    Handler handler = new Handler();
    private String pscid;
    List<ProductlistBean.DataBean> list = new ArrayList();
    private MaAdapter_product maAdapter_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetProduct(new IModle(), pscid, i, this);
        xlv = findViewById(R.id.xlv);
        xlv.setPullRefreshEnabled(true);
        xlv.setLoadingMoreEnabled(true);
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                i = 1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        IPresenter iPresenter = new IPresenter();
                        iPresenter.GetProduct(new IModle(), pscid, i, ProductActivity.this);
                        xlv.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                i++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IPresenter iPresenter = new IPresenter();
                        iPresenter.GetProduct(new IModle(), pscid, i, ProductActivity.this);
                        xlv.refreshComplete();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void ModleLoadError1(String s) {

    }

    @Override
    public void ModleLoadSuccess1(ProductlistBean productlistBean) {
        List<ProductlistBean.DataBean> data = productlistBean.getData();
        if (data.size() == 0) {
            Toast.makeText(ProductActivity.this, "亲,已经没有数据了", Toast.LENGTH_LONG).show();
        } else {
            list.addAll(data);
        }
        xlv.setLayoutManager(new LinearLayoutManager(this));
        maAdapter_product = new MaAdapter_product(ProductActivity.this, list);
        xlv.setAdapter(maAdapter_product);
        //详情页面的点击方法
        maAdapter_product.setOnItemClickListener(new MaAdapter_product.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                int pid = list.get(position).getPid();
                Intent intent = new Intent(ProductActivity.this, ProductActivity1.class);
                intent.putExtra("pid", pid + "");
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });
    }
}
