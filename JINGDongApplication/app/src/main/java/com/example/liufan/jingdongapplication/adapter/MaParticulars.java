package com.example.liufan.jingdongapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.bean.ParticularsBean;
import com.example.liufan.jingdongapplication.fragment.Home_Fragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufan on 2018/4/11.
 */

public class MaParticulars extends BaseAdapter {
    private Context context;
    private  ParticularsBean.DataBean data;
    private TextView particulars_te;
    private TextView particulars_te1;
    private TextView particulars_te2;
    private TextView particulars_te3;
    private Banner particulars_bann;
    List<String> imags1 = new ArrayList<>();
    public MaParticulars(Context context,ParticularsBean.DataBean data){
        this.context=context;
        this.data=data;
    }
    @Override
    public int getCount() {
        return 1;
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
            convertView=View.inflate(context, R.layout.particulars_layout,null);
        }
        particulars_bann = convertView.findViewById(R.id.particulars_bann);
        particulars_te = convertView.findViewById(R.id.particulars_te);
        particulars_te.setTextColor(Color.parseColor("#000000"));
        particulars_te1 = convertView.findViewById(R.id.particulars_te1);
        particulars_te2 = convertView.findViewById(R.id.particulars_te2);
        particulars_te2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        particulars_te3 = convertView.findViewById(R.id.particulars_te3);
         String images = data.getImages();
        Log.d("三生三世222255555",images.toString());
         String[] split = images.split("\\|");
        Log.d("三生三世2222",split.length+"");
        for (int i = 0; i <split.length ; i++) {
            Log.d("三生三世",split[i]);
            imags1.add(split[i]);
        }
        particulars_bann.setImages(imags1);
        particulars_bann.setDelayTime(1000);
        particulars_bann.setImageLoader(new GlideImageLoader());
        particulars_bann.start();
        particulars_te.setText(data.getTitle());
        particulars_te1.setText(data.getSubhead());
        particulars_te2.setText("原价:"+data.getPrice()+"");
        particulars_te3.setText("优惠价:"+data.getBargainPrice() + "");
        return convertView;
    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
