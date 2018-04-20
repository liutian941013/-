package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.ProductlistBean;
import com.example.liufan.jingdongapplication.bean.SubclassBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by liufan on 2018/4/10.
 */

public class MaAdapter_product extends XRecyclerView.Adapter<MaAdapter_product.MaHodle_lunbo> {

    List<ProductlistBean.DataBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public MaAdapter_product(Context context, List<ProductlistBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MaHodle_lunbo onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = View.inflate(context, R.layout.product_list_1, null);
        MaHodle_lunbo maHodle_lunbo = new MaHodle_lunbo(inflate);
        return maHodle_lunbo;
    }

    @Override
    public void onBindViewHolder(MaHodle_lunbo holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split(".jpg!q70.jpg");
        Glide.with(context).load(split[0] + ".jpg!q70.jpg").into(holder.product_list_iv);
        holder.product_list_te.setText(list.get(position).getTitle());
        holder.product_list_te1.setText(list.get(position).getPrice() + "");
        if( mOnItemClickListener!= null){
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder. itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();

    }

    public class MaHodle_lunbo extends XRecyclerView.ViewHolder {


        private final ImageView product_list_iv;
        private final TextView product_list_te;
        private final TextView product_list_te1;

        public MaHodle_lunbo(View itemView) {
            super(itemView);
            this.product_list_iv = itemView.findViewById(R.id.product_list_iv);
            this.product_list_te = itemView.findViewById(R.id.product_list_te);
            this.product_list_te1 = itemView.findViewById(R.id.product_list_te1);
        }
    }
    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }

}
