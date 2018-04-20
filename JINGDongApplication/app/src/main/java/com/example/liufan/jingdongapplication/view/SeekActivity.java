package com.example.liufan.jingdongapplication.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.Maadapter_Shousuo;
import com.example.liufan.jingdongapplication.bean.GoodBean;
import com.example.liufan.jingdongapplication.bean.ProductlistBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SeekActivity extends AppCompatActivity implements View_Seek{
    int page=1;
    private Button ss_bt;
    private EditText shousuo;
    private ImageView qiehuan;
    private XRecyclerView ss_xlv;
   private  boolean flao=false;
    Handler handler = new Handler();
    List<GoodBean.DataBean> list = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        ss_bt = findViewById(R.id.ss_bt);
        shousuo = findViewById(R.id.shousuo);
        qiehuan = findViewById(R.id.qiehuan);
        ss_xlv = findViewById(R.id.ss_xlv);
        ss_xlv.setLayoutManager(new LinearLayoutManager(this));
        ss_xlv.setPullRefreshEnabled(true);
        ss_xlv.setLoadingMoreEnabled(true);
        //搜索的点击事件
        ss_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                String shousuo1 = shousuo.getText().toString();
                final IPresenter iPresenter = new IPresenter();
                iPresenter.GetSeek(new IModle(),shousuo1,page+"",SeekActivity.this);
            }
        });
        //切换不同布局的点击事件
        qiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (flao==false){
                  qiehuan.setImageResource(R.drawable.kind_grid);
                  GridLayoutManager gridLayoutManager =new GridLayoutManager(SeekActivity.this,2);
                  ss_xlv.setLayoutManager(gridLayoutManager);
                  flao=true;
              }else if (flao==true){
                  qiehuan.setImageResource(R.drawable.kind_liner);
                 ss_xlv.setLayoutManager(new LinearLayoutManager(SeekActivity.this));
                 flao=false;
              }
            }
        });
        //上拉刷新下拉加载
        ss_xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        String shousuo1 = shousuo.getText().toString();
                        final IPresenter iPresenter = new IPresenter();
                        iPresenter.GetSeek(new IModle(),shousuo1,page+"",SeekActivity.this);
                        ss_xlv.refreshComplete();
                    }

                    }, 1000);
            }

            @Override
            public void onLoadMore() {
                page++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String shousuo1 = shousuo.getText().toString();
                        final IPresenter iPresenter = new IPresenter();
                        iPresenter.GetSeek(new IModle(),shousuo1,page+"",SeekActivity.this);
                        ss_xlv.refreshComplete();
                    }
                }, 1000);
            }

        });
    }

    @Override
    public void ViewGoodError(String s) {

    }

    @Override
    public void ViewGoodSuccess(GoodBean goodBean) {
         List<GoodBean.DataBean> data = goodBean.getData();
        if (data.size() == 0) {
            Toast.makeText(SeekActivity.this, "亲,已经没有数据了", Toast.LENGTH_LONG).show();
        } else {
            list.addAll(data);
        }
        final Maadapter_Shousuo maadapter_shousuo = new Maadapter_Shousuo(SeekActivity.this,list);
        ss_xlv.setAdapter(maadapter_shousuo);
    }
}
