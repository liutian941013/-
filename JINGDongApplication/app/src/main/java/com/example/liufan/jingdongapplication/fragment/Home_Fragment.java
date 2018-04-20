package com.example.liufan.jingdongapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.MaJiuGongGe;
import com.example.liufan.jingdongapplication.adapter.MaMiaosha;
import com.example.liufan.jingdongapplication.adapter.MaTuiJian;
import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.example.liufan.jingdongapplication.view.SeekActivity;
import com.example.liufan.jingdongapplication.view.View_et;
import com.example.liufan.jingdongapplication.view.ZhuActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liufan on 2018/4/9.
 */
public class Home_Fragment extends Fragment implements View_et {
    List<String> imags = new ArrayList<>();
    //全局变量
    private TextView miao;
    private RecyclerView rev;
    private TextView tui;
    private RecyclerView rev1;
    private RecyclerView gvrev;
    private Banner bann;
    private HomeBean.MiaoshaBean miaosha;
    private HomeBean.TuijianBean tuijian;
    private List<ClassifyBean.DataBean> data1;
    private EditText sousuo;
    private ImageView sao;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View inflate = View.inflate(getActivity(), R.layout.home_fragment, null);
        //请求数据
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetHJiu(new IModle(), this);
        iPresenter.GetHome(new IModle(), this);
        //初始化控件
        bann = inflate.findViewById(R.id.ban);
        miao = inflate.findViewById(R.id.miao);
        rev = inflate.findViewById(R.id.rev);
        tui = inflate.findViewById(R.id.tui);
        rev1 = inflate.findViewById(R.id.rev1);
        gvrev = inflate.findViewById(R.id.gvrev);
        sousuo = inflate.findViewById(R.id.sousuo);
        sao = inflate.findViewById(R.id.sao);
        //搜索的方法
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                getActivity().startActivity(intent);
            }
        });
        sao.setOnClickListener(new View.OnClickListener() {
            public int REQUEST_CODE;
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        return inflate;
    }

    @Override
    //首页失败的方法
    public void ViewLoadError(String s) {

    }

    @Override
    //首页成功的方法
    public void ViewLoadSuccess(HomeBean homeBean) {
        List<HomeBean.DataBean> data = homeBean.getData();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            Log.d("kkkkkkkkkk", icon);
            imags.add(icon);
        }
        Log.d("pppp", imags.size() + "");
        //给Banner设置值
        bann.setDelayTime(1000);
        bann.setImages(imags);
        bann.setImageLoader(new GlideImageLoader());
        bann.start();
        miaosha = homeBean.getMiaosha();
        //秒杀的方法
        miaosha();
        tuijian = homeBean.getTuijian();
        //推荐的方法
        tuijina();
    }

    @Override
    //九宫格失败的方法
    public void ViewLoadError1(String s) {

    }

    @Override
    //九宫格成功的方法
    public void ViewLoadSuccess1(ClassifyBean classifyBean) {
        data1 = classifyBean.getData();
        jiugongge();
    }

    //秒杀
    public void miaosha() {
        //布局管理器
        GridLayoutManager GridLayoutManager = new GridLayoutManager(getActivity(), 1);
        GridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //京东秒杀倒计时
        final SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        CountDownTimer CountDownTimer = new CountDownTimer(miaosha.getTime(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String format = SimpleDateFormat.format(new Date(millisUntilFinished));
                miao.setText(miaosha.getName() + format);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        //展示数据
        rev.setLayoutManager(GridLayoutManager);
        List<HomeBean.MiaoshaBean.ListBeanX> list = miaosha.getList();
        MaMiaosha maMiaosha = new MaMiaosha(getActivity(), list);
        rev.setAdapter(maMiaosha);
    }

    //推荐
    public void tuijina() {
        GridLayoutManager GridLayoutManager = new GridLayoutManager(getActivity(), 2);
        GridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //推荐设置适配器
        rev1.setLayoutManager(GridLayoutManager);
        tui.setText(tuijian.getName());
        List<HomeBean.TuijianBean.ListBean> list = tuijian.getList();
        MaTuiJian maTuiJian = new MaTuiJian(getActivity(), list);
        rev1.setAdapter(maTuiJian);

    }

    //九宫格
    public void jiugongge() {
        GridLayoutManager GridLayoutManager = new GridLayoutManager(getActivity(), 2);
        GridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //九宫格设置适配器
        gvrev.setLayoutManager(GridLayoutManager);
        MaJiuGongGe maJiuGongGe = new MaJiuGongGe(getActivity(), data1);
        gvrev.setAdapter(maJiuGongGe);
    }
   //轮播图图片的赋值的方法
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
