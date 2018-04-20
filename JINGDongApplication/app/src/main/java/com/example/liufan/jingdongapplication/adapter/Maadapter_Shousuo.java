package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.GoodBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by liufan on 2018/4/12.
 */

public class Maadapter_Shousuo extends XRecyclerView.Adapter<Maadapter_Shousuo.MaHodle> {
    private final Context context;
    private final List<GoodBean.DataBean> data;

    public Maadapter_Shousuo(Context context,List<GoodBean.DataBean> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public MaHodle onCreateViewHolder(ViewGroup parent, int viewType) {
         View inflate = View.inflate(context, R.layout.product_list_1, null);
        MaHodle maHodle =new MaHodle(inflate);
        return maHodle;
    }

    @Override
    public void onBindViewHolder(MaHodle holder, int position) {
         String images = data.get(position).getImages();
         String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.te.setText(data.get(position).getTitle());
        holder.te1.setText(data.get(position).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MaHodle extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView te;
        private final TextView te1;

        public MaHodle(View itemView) {
            super(itemView);
              this.iv=itemView.findViewById(R.id.product_list_iv);
              this.te=itemView.findViewById(R.id.product_list_te);
              this.te1=itemView.findViewById(R.id.product_list_te1);
        }
    }
}
