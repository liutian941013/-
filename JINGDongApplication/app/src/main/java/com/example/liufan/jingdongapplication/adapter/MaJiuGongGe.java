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
import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;

import java.util.List;

/**
 * Created by liufan on 2018/4/11.
 */
//九宫格的适配器
public class MaJiuGongGe extends RecyclerView.Adapter<MaJiuGongGe.MaHodle> {
    private final Context context;
    private final List<ClassifyBean.DataBean> data1;

   //构造方法
    public MaJiuGongGe(Context context, List<ClassifyBean.DataBean> data1){
        this.context=context;
        this.data1=data1;
    }

    @Override
    //加载布局
    public MaHodle onCreateViewHolder(ViewGroup parent, int viewType) {
         View inflate = View.inflate(context, R.layout.jiugongge_layout, null);
        MaHodle maHodle = new MaHodle(inflate);
        return maHodle;
    }

    @Override
    //控件赋值
    public void onBindViewHolder(MaHodle holder, int position) {
        holder.name.setText (data1.get (position).getName());
        String images = data1.get (position).getIcon ();
        if(images.contains ("|")){
            String[] split = images.split ("\\|");
            Glide.with (context).load (split[0]).into (holder.img);
        }else{
            Glide.with (context).load (images).into (holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return data1.size();
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
