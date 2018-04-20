package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.ClassifyBean;

import java.util.List;

/**
 * Created by liufan on 2018/4/10.
 */

public class Maadapter_classfiy extends BaseAdapter {
    private final List<ClassifyBean.DataBean> data;
    private Context context;

    public Maadapter_classfiy(Context context, List<ClassifyBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
        //加载布局
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_view, null);
        }
    //控件赋值
        TextView te_classfiy = convertView.findViewById(R.id.te_classfiy);
        te_classfiy.setText(data.get(position).getName());
        return convertView;
    }
}
