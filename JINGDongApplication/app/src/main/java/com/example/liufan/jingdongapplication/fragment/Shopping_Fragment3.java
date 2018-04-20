package com.example.liufan.jingdongapplication.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.Shopping;
import com.example.liufan.jingdongapplication.bean.ShanChuGouWuBean;
import com.example.liufan.jingdongapplication.bean.ShoppingBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.example.liufan.jingdongapplication.view.LoginActivity;
import com.example.liufan.jingdongapplication.view.Total;
import com.example.liufan.jingdongapplication.view.View_ShanChu;
import com.example.liufan.jingdongapplication.view.View_Shopping;

import java.math.BigDecimal;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by liufan on 2018/4/9.
 */

public class Shopping_Fragment3 extends Fragment implements View_Shopping, Total, View_ShanChu {

    private ExpandableListView edlist;
    private CheckBox cd;
    private TextView hj;
    private Button jiesuan;
    private LinearLayout wancheng;
    private LinearLayout bianji;
    private Button shanchu;
    private Shopping shopping;
    private List<ShoppingBean.DataBean> data;
    private boolean ss;
    private boolean bo;
    private SharedPreferences sharedPreferences;
    private int uid1;
    boolean flog = true;
    private TextView te_bj;
    private String token1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View inflate = View.inflate(getActivity(), R.layout.shopping_layout, null);
        //初始化控件
        edlist = inflate.findViewById(R.id.edlist);
        te_bj = inflate.findViewById(R.id.tv_bj);
        cd = inflate.findViewById(R.id.cb);
        hj = inflate.findViewById(R.id.hj);
        jiesuan = inflate.findViewById(R.id.jiesuan);
        wancheng = inflate.findViewById(R.id.wancheng);
        bianji = inflate.findViewById(R.id.bianji);
        shanchu = inflate.findViewById(R.id.shanchu);
        //判断是否是登录状态
        sharedPreferences = getActivity().getSharedPreferences("User", MODE_PRIVATE);
        bo = sharedPreferences.getBoolean("bo", ss);
        if (bo == false) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            getActivity().startActivity(intent);
        } else {
            int uid = sharedPreferences.getInt("uid", sharedPreferences.getInt("uid", uid1));
             String token = sharedPreferences.getString("token",token1);
            IPresenter iPresenter = new IPresenter();
            iPresenter.GetShopping(new IModle(), uid, token,this);
        }
        //全选的方法
        quanxuan(cd);
        //删除的方法
        shanchu();
        //编辑的点击事件
        te_bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flog) {
                    te_bj.setText("完成");
                    flog = false;
                    wancheng.setVisibility(View.VISIBLE);
                    bianji.setVisibility(View.GONE);
                } else {
                    te_bj.setText("编辑");
                    flog = true;
                    bianji.setVisibility(View.VISIBLE);
                    wancheng.setVisibility(View.GONE);
                }
            }
        });
        edlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), data.get(groupPosition).getList().get(childPosition).getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return inflate;
    }


    @Override
    public void ViewLoadError(String s) {

    }

    @Override
    public void ViewLoadSuccess(ShanChuGouWuBean shanChuGouWuBean) {
         String code = shanChuGouWuBean.getCode();
         if (code.equals("0")){
             Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_LONG).show();
         }else {
             Toast.makeText(getActivity(),"删除失败",Toast.LENGTH_LONG).show();
         }
    }

    @Override
    public void ViewLoadSuccess(ShoppingBean shoppingBean) {
        data = shoppingBean.getData();
        shopping = new Shopping(getActivity(), data, this);
        edlist.setAdapter(shopping);
        int groupCount = shopping.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            edlist.expandGroup(i);
        }
    }

    //全选的方法
    private void quanxuan(CheckBox cb) {
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data.size(); i++) {
                    boolean presenterck = data.get(i).isPresenterck();
                    data.get(i).setPresenterck(!presenterck);
                    List<ShoppingBean.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        boolean childck = list.get(j).isChildck();
                        list.get(j).setChildck(!childck);
                    }
                }
                heji();
                shopping.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setadapter(Context context) {

    }

    @Override
    //计算总价的方法
    public void heji() {
        double add = 0;
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            List<ShoppingBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                boolean childck = list.get(j).isChildck();
                if (childck) {
                    count++;
                    int mycount = list.get(j).getCount() + 1;
                    double mul = mul(list.get(j).getPrice(), mycount);
                    add = add(add, mul);
                }
            }
        }
        hj.setText(add + "");
        jiesuan.setText("去结算" + "(" + count + ")");
        //结算的点击事件
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("结算？")
                        .setMessage("你确定？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "等我研发出来再说吧谢谢，呵呵。。", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("算啦吧", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "那就算啦，欢迎下次光临！", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    //乘法
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    //加法
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    @Override
    //再次获取焦点的方法
    public void onResume() {
        super.onResume();
        int uid = sharedPreferences.getInt("uid", sharedPreferences.getInt("uid", uid1));
        String token = sharedPreferences.getString("token",token1);
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetShopping(new IModle(), uid, token,this);
    }

    //删除购物车的方法
    private void shanchu() {
        shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "删除！", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < data.size(); i++) {
                    List<ShoppingBean.ListBean> list = data.get(i).getList();
                    boolean presenterck = data.get(i).isPresenterck();
                    if (presenterck) {
                        data.remove(i);
                        heji();
                        shopping.notifyDataSetChanged();
                    }
                    for (int j = 0; j < list.size(); j++) {
                        boolean childck = list.get(j).isChildck();
                        if (childck) {
                            list.remove(j);
                            heji();
                            bo = sharedPreferences.getBoolean("bo", ss);
                            if (bo == false) {
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                getActivity().startActivity(intent);
                            } else {
                                int anInt = sharedPreferences.getInt("uid", sharedPreferences.getInt("uid", uid1));
                                 String token = sharedPreferences.getString("token", token1);
                                final IPresenter iPresenter1 = new IPresenter();
                                iPresenter1.GetSCGouWu(new IModle(), anInt + "", data.get(i).getList().get(j).getPid() + "",token, Shopping_Fragment3.this);
                            }
                            shopping.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }
}
