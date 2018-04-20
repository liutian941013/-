package com.example.liufan.jingdongapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.MaAdapter_subclass;
import com.example.liufan.jingdongapplication.adapter.MaEx;
import com.example.liufan.jingdongapplication.adapter.Maadapter_classfiy;
import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.SubclassBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.example.liufan.jingdongapplication.view.View_Classify;


import java.util.List;

/**
 * Created by liufan on 2018/4/9.
 */

public class Classify_Fragment1 extends Fragment implements View_Classify {

    private ListView lv;
    private List<ClassifyBean.DataBean> data;
    private ExpandableListView elv;
    private SwipyRefreshLayout swip;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View inflate = View.inflate(getActivity(), R.layout.classify, null);
        //请求数据
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetFenLei(new IModle(), this);
        //初始化控件
        handler = new Handler();
        swip = inflate.findViewById(R.id.swip);
        swip.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swip.setColorSchemeColors(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
        swip.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            //上拉刷新
            public void onRefresh(int index) {
                Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swip.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            //下拉加载
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swip.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        lv = inflate.findViewById(R.id.lv);
        elv = inflate.findViewById(R.id.elv);
        //ListView的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int cid = data.get(position).getCid();
                final IPresenter iPresenter1 = new IPresenter();
                iPresenter1.GetFenLeiZiLei(new IModle(), cid + "", Classify_Fragment1.this);
            }
        });
        return inflate;
    }

    @Override
    //分类左边失败的方法
    public void ViewLoadError1(String s) {

    }

    @Override
    //分类左边成功的方法
    public void ViewLoadSuccess1(ClassifyBean classifyBean) {
        data = classifyBean.getData();
        Log.d("斤斤计较000005", data.size() + "");
        int cid = data.get(0).getCid();
        final IPresenter iPresenter1 = new IPresenter();
        iPresenter1.GetFenLeiZiLei(new IModle(), cid + "", Classify_Fragment1.this);
        //listview设置适配器
        Maadapter_classfiy maadapter_classfiy = new Maadapter_classfiy(getActivity(), data);
        lv.setAdapter(maadapter_classfiy);
    }

    @Override
    //分类右边失败的方法
    public void ViewLoadError(String s) {

    }

    @Override
    //分类右边成功的方法
    public void ViewLoadSuccess(SubclassBean subclassBean) {
        List<SubclassBean.DataBean> data = subclassBean.getData();
        //二级列表设置适配器
        MaEx maEx = new MaEx(getActivity(), data);
        elv.setGroupIndicator(null);
        elv.setAdapter(maEx);
        int groupCount = maEx.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            elv.expandGroup(i);
        }
    }
}
