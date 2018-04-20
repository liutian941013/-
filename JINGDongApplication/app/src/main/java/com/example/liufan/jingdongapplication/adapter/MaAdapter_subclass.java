package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.SubclassBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by liufan on 2018/4/10.
 */

public class MaAdapter_subclass extends XRecyclerView.Adapter<MaAdapter_subclass.MaHodle_lunbo> {

    private  List<SubclassBean.DataBean> data;
    private  Context context;
    public MaAdapter_subclass(Context context, List<SubclassBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public MaHodle_lunbo onCreateViewHolder(ViewGroup parent, int viewType) {
         View inflate = View.inflate(context, R.layout.xrecyclerview_3, null);
        MaHodle_lunbo maHodle_lunbo = new MaHodle_lunbo(inflate);
        return maHodle_lunbo;
    }

    @Override
    public void onBindViewHolder(MaHodle_lunbo holder, int position) {
         MaEx maEx = new MaEx(context,data);
        holder.elv.setAdapter(maEx);
    }


    @Override
    public int getItemCount() {
        return data.size();

    }

    public class MaHodle_lunbo extends XRecyclerView.ViewHolder {
        private final ExpandableListView elv;

        public MaHodle_lunbo(View itemView) {
            super(itemView);
             this.elv = itemView.findViewById(R.id.elv);
        }
    }


}
