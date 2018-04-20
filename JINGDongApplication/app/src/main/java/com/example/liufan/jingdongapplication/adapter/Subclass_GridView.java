package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.SubclassBean;

import java.util.List;

/**
 * Created by liufan on 2018/4/10.
 */

public class Subclass_GridView extends BaseAdapter {
    private Context context;
    private List<SubclassBean.DataBean.ListBean> list;

    public  Subclass_GridView(Context context, List<SubclassBean.DataBean.ListBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=View.inflate(context, R.layout.xrecyclerview_2,null);
        }
         ImageView GridView1_iv = convertView.findViewById(R.id.GridView1_iv);
         TextView GridView1_te = convertView.findViewById(R.id.GridView1_te);
        Glide.with(context).load(list.get(position%list.size()).getIcon()).into(GridView1_iv);
        GridView1_te.setText(list.get(position%list.size()).getName());
        return convertView;
    }
}
