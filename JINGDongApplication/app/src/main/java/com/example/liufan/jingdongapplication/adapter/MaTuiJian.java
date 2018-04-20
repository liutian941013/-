package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.HomeBean;

import java.util.List;

/**
 * Created by liufan on 2018/4/11.
 */
//推荐的适配器
public class MaTuiJian extends RecyclerView.Adapter<MaTuiJian.MaHodle> {
    private final Context context;
    private final List<HomeBean.TuijianBean.ListBean> list1;

    //构造方法
    public MaTuiJian(Context context,List<HomeBean.TuijianBean.ListBean> list){
        this.context=context;
        this.list1=list;
    }

    @Override
    //加载布局
    public MaHodle onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.tuijain_layout, null);
        MaHodle maHodle = new MaHodle(inflate);
        return maHodle;
    }

    @Override
    //控件赋值
    public void onBindViewHolder(MaHodle holder, int position) {
        holder.name.setText (list1.get (position).getTitle());
        String images = list1.get (position).getImages();
            String[] split = images.split ("\\|");
            Glide.with (context).load (split[0]).into (holder.img);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
   //优化类
    public class MaHodle extends RecyclerView.ViewHolder{
        private final ImageView img;
        private final TextView name;
        public MaHodle(View itemView) {
            super(itemView);
            this.img = itemView.findViewById (R.id.img);
            this.name =  itemView.findViewById (R.id.name);
        }
    }
}
