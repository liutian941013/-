package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.SubclassBean;
import com.example.liufan.jingdongapplication.view.ProductActivity;

import java.util.List;

/**
 * Created by liufan on 2018/4/10.
 */

public class MaEx extends BaseExpandableListAdapter {
    private final Context context;
    private final List<SubclassBean.DataBean> data;

    public MaEx(Context context, List<SubclassBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    //一级列表集合的长度
    public int getGroupCount() {
        return data.size();
    }

    @Override
    //二级列表集合的长度
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    //一级列表集合数据的长度
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    //二级列表集合数据的长度
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //初始化控件   控件赋值
        TextView textView = new TextView(context);
        textView.setText(data.get(groupPosition).getName());
        textView.setTextSize(20);
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
           //加载布局
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.xrecyclerview_3, null);
        }
        final List<SubclassBean.DataBean.ListBean> list = data.get(groupPosition).getList();
        //GridView的适配器
        GridView gv = convertView.findViewById(R.id.classify_gv);
        Subclass_GridView subclass_gridView = new Subclass_GridView(context, list);
        gv.setAdapter(subclass_gridView);
        //GridView的点击事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pscid = list.get(position).getPscid();
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("pscid",pscid+"");
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
