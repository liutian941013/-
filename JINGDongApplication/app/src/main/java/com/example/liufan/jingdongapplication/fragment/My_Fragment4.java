package com.example.liufan.jingdongapplication.fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liufan.jingdongapplication.R;
import com.example.liufan.jingdongapplication.adapter.MaTuiJian;
import com.example.liufan.jingdongapplication.bean.HomeBean;
import com.example.liufan.jingdongapplication.bean.Uptou;
import com.example.liufan.jingdongapplication.bean.UserBean;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.modle.Modle1;
import com.example.liufan.jingdongapplication.presenter.IPresenter;
import com.example.liufan.jingdongapplication.view.LoginActivity;
import com.example.liufan.jingdongapplication.view.ProductActivity;
import com.example.liufan.jingdongapplication.view.View_Upload;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static android.app.Activity.RESULT_OK;

/**
 * Created by liufan on 2018/4/9.
 */

public class My_Fragment4 extends Fragment implements Modle1 ,View_Upload{
    private static String path = "/sdcard/myHead/";// sd路径
    private XRecyclerView xrev3;
    Handler handler = new Handler();
    private TextView long_zhu;
    private Uri uri;
    private boolean ss = false;
    private SimpleDraweeView iv_user;
    private boolean bo;
    private ImageView zh_shezhi;
    private SharedPreferences sharedPreferences;
    private String fileName;
    private int uid1;
    private int uid;
    private String token1;
    private String token;
    private File file;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View inflate = View.inflate(getActivity(), R.layout.my_fragment, null);
        //初始化控件
        long_zhu = inflate.findViewById(R.id.long_zhu);
        iv_user = inflate.findViewById(R.id.iv_user);
        zh_shezhi = inflate.findViewById(R.id.zh_shezhi);
        xrev3 = inflate.findViewById(R.id.xrev3);
        //请求网络数据
        IPresenter iPresenter = new IPresenter();
        iPresenter.GetHome1(new IModle(), this);
        //XRecyclerView的上拉刷新下拉加载的方法
        xrev3.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IPresenter iPresenter = new IPresenter();
                        iPresenter.GetHome1(new IModle(), My_Fragment4.this);
                        xrev3.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IPresenter iPresenter = new IPresenter();
                        iPresenter.GetHome1(new IModle(), My_Fragment4.this);
                        xrev3.refreshComplete();
                    }
                }, 1000);
            }
        });
        //登录注册的方法
        long_zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bo==false){
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    getActivity().startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"已经登录过了",Toast.LENGTH_LONG).show();
                }
            }
        });
        //切换头像
        iv_user.setOnClickListener(new View.OnClickListener() {
            private PopupWindow popupWindow;

            @Override
            public void onClick(View v) {
                if (bo == true) {
                    View view = View.inflate(getActivity(), R.layout.popupwindow, null);
                    popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                    popupWindow.setOutsideTouchable(true);
                    //显示在底部  main为activity_main布局控件中 最大的LinearLayout 的id
                    popupWindow.showAtLocation(getActivity().findViewById(R.id.main),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    Button bt = view.findViewById(R.id.bt);
                    Button bt1 = view.findViewById(R.id.bt1);
                    Button bt2 = view.findViewById(R.id.bt2);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                            startActivityForResult(intent2, 2);// 采用ForResult打开
                            popupWindow.dismiss();
                        }
                    });
                    bt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, 1);
                            popupWindow.dismiss();
                        }
                    });
                    bt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                }else {

                }
            }

        });
        //设置的点击事件
        zh_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long_zhu.setText("登录/注册");
                Glide.with(getContext()).load(R.drawable.user).into(iv_user);
                sharedPreferences.edit().clear().commit();
                 bo=false;
                Toast.makeText(getContext(), "已退出登录", Toast.LENGTH_LONG).show();
            }
        });
        return inflate;
    }

    @Override
    public void ModleLoadError(String s) {

    }

    @Override
    public void ModleLoadSuccess(HomeBean homeBean) {
        //XRecyclerView的适配器
        GridLayoutManager GridLayoutManager = new GridLayoutManager(getActivity(), 2);
        GridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrev3.setLayoutManager(GridLayoutManager);
        List<HomeBean.TuijianBean.ListBean> list = homeBean.getTuijian().getList();
        MaTuiJian maTuiJian = new MaTuiJian(getActivity(), list);
        xrev3.setAdapter(maTuiJian);
    }

    @Override
    //再次获取焦点的方法
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        bo = sharedPreferences.getBoolean("bo", ss);
        if (bo == true) {
            String name = sharedPreferences.getString("name", long_zhu + "");
            String url = sharedPreferences.getString("url", iv_user + "");
            Uri uri = Uri.parse(url);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setTapToRetryEnabled(true)
                    .build();
            iv_user.setController(controller);
            long_zhu.setText(name);
            Glide.with(getContext()).load(url).into(iv_user);
            sharedPreferences.edit().commit();
        } else if (bo == false) {
            Toast.makeText(getActivity(), "请注册", Toast.LENGTH_LONG).show();
        }
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        boolean bo = sharedPreferences.getBoolean("bo", ss);
        if (bo==true){
            int uid = sharedPreferences.getInt("uid", uid1);
            IPresenter iPresenter = new IPresenter();
            iPresenter.GetUser(new IModle(), uid+"",My_Fragment4.this);
        }else {

        }


    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        file = new File(path);
        file.mkdirs();// 创建文件夹
        // 图片名字
        fileName = path + "head1.jpg";
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图库照片的路径
        if (requestCode == 1 && resultCode == RESULT_OK) {
            cropPhoto(data.getData());
        }

        //相机的路径为f File f=new File(Environment.getExternalStorageDirectory(),"ni.jpg");
        if (requestCode == 2 && resultCode == RESULT_OK) {
            File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
            cropPhoto(Uri.fromFile(temp));// 裁剪图片
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap head = extras.getParcelable("data");
                if (head != null) {
                    setPicToView(head);// 保存在SD卡中
                    uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), head, null, null));
                    final IPresenter iPresenter = new IPresenter();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                    boolean bo = sharedPreferences.getBoolean("bo", ss);
                    if (bo == true) {
                        uid = sharedPreferences.getInt("uid", uid1);
                        token = sharedPreferences.getString("token", token1 + "");
                    }
                    File f = new File(fileName);
                    iPresenter.GetUpload(new IModle(), uid + "", token, f, My_Fragment4.this);
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    //上传头像失败
    public void ViewLoadError(String s) {

    }

    @Override
    //上传头像成功
    public void ViewLoadSuccess(Uptou uptou) {
        String code = uptou.getCode();
        if (code.equals("0")){
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
           boolean bo = sharedPreferences.getBoolean("bo", ss);
            if (bo == true) {
                String name = sharedPreferences.getString("name", long_zhu + "");
                String url = sharedPreferences.getString("url", iv_user + "");
                Uri uri = Uri.parse(url);
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setTapToRetryEnabled(true)
                        .build();
                iv_user.setController(controller);
                long_zhu.setText(name);
                //Glide.with(getContext()).load(url).into(iv_user);
                sharedPreferences.edit().commit();
            }else {

            }
        }
    }

    @Override
    public void ViewUserError1(String s) {

    }

    @Override
    public void ViewUIserSuccess2(UserBean userBean) {
         String icon = userBean.getData().getIcon();
        Uri uri = Uri.parse(icon);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .build();
        iv_user.setController(controller);
    }
}
