package com.example.shangchauntouxaing.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.shangchauntouxaing.R;
import com.example.shangchauntouxaing.bean.Uptou;
import com.example.shangchauntouxaing.mode.Mymode;
import com.example.shangchauntouxaing.presenter.Mypresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Iview{
    private static String path = "/sdcard/myHead/";// sd路径
    private SimpleDraweeView sd;
    protected  Uri tempUri;
    private File defaltefile;
    private File defaltefile1;
    private Uri uri;
    private File file;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sd = findViewById(R.id.sd);
       // Mypresenter mypresenter=new Mypresenter();
        //mypresenter.getmv(new Mymode(),this);
        initdata();

    }

    public void initdata() {

        sd.setOnClickListener(new View.OnClickListener() {

            private PopupWindow popupWindow;

            @Override
            public void onClick(View v) {
                View view = View.inflate(MainActivity.this, R.layout.popupwindow, null);
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                popupWindow.setOutsideTouchable(true);
                //显示在底部  main为activity_main布局控件中 最大的LinearLayout 的id
                popupWindow.showAtLocation(MainActivity.this.findViewById(R.id.main),
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
                        Intent intent=new Intent(Intent.ACTION_PICK);
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
            }
        });

    }

    @Override
    public void gettu(String tu) {

        sd.setImageURI(Uri.parse(tu));
    }

    @Override
    public void upcheng(Uptou uptou) {
        if (uptou.getCode().equals("0")){
            sd.setImageURI(uri);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图库照片的路径
        if (requestCode==1&&resultCode==RESULT_OK) {
            cropPhoto(data.getData());
        }

        //相机的路径为f File f=new File(Environment.getExternalStorageDirectory(),"ni.jpg");
        if (requestCode==2&&resultCode==RESULT_OK){
            File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
            cropPhoto(Uri.fromFile(temp));// 裁剪图片
        }
        if (requestCode==3&&resultCode==RESULT_OK){
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap head = extras.getParcelable("data");
                if (head != null) {
                    setPicToView(head);// 保存在SD卡中
                    uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), head, null,null));
                    Mypresenter mypresenter=new Mypresenter();
                    File f=new File(fileName);
                    mypresenter.getmv1(f,"10134",new Mymode(),MainActivity.this);
                }
            }

        }

        super.onActivityResult(requestCode, resultCode, data);

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
}
