package com.example.liufan.jingdongapplication.presenter;

import com.example.liufan.jingdongapplication.fragment.Classify_Fragment1;
import com.example.liufan.jingdongapplication.fragment.Home_Fragment;
import com.example.liufan.jingdongapplication.fragment.My_Fragment4;
import com.example.liufan.jingdongapplication.fragment.Shopping_Fragment3;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.view.IndentActivity;
import com.example.liufan.jingdongapplication.view.LoginActivity;
import com.example.liufan.jingdongapplication.view.ProductActivity;
import com.example.liufan.jingdongapplication.view.ProductActivity1;
import com.example.liufan.jingdongapplication.view.RegActivity;
import com.example.liufan.jingdongapplication.view.SeekActivity;

import java.io.File;


/**
 * Created by liufan on 2018/4/10.
 */

public interface Presenter {
    void GetHome(IModle iModle, Home_Fragment home_fragment);
    void GetHome1(IModle iModle, My_Fragment4 my_fragment4);
    void GetHJiu(IModle iModle, Home_Fragment home_fragment);
    void GetFenLei(IModle iModle, Classify_Fragment1 classify_fragment1);
    void GetFenLeiZiLei(IModle iModle,String cid,Classify_Fragment1 classify_fragment1);
    void GetProduct(IModle iModle, String cid,int page ,ProductActivity productActivity);
    void GetParticulars(IModle iModle, String pid ,ProductActivity1 productActivity1);
    void GetIndent(IModle iModle, String pid ,IndentActivity indentActivity);
    void GetShopping(IModle iModle,int uid,String token,Shopping_Fragment3 shopping_fragment3);
    void GetLogin(IModle iModle,String mobile,String password,LoginActivity loginActivity);
    void GetReg(IModle iModle,String mobile,String password,RegActivity regActivity);
    void GetGouWu(IModle iModle,String uid,String pid,String token,ProductActivity1 productActivity1);
    void GetSCGouWu(IModle iModle,String uid,String pid,String token,Shopping_Fragment3 shopping_fragment3);
    void GetSeek(IModle iModle, String keywords, String page, SeekActivity seekActivity);
    void GetLocation(IModle iModle, String uid, String addr, String mobile, String name,String token ,IndentActivity indentActivity);
    void GetHuoQuLocation(IModle iModle, String uid,IndentActivity indentActivity);
    void GetUpload(IModle iModle, String uid, String token, File f, My_Fragment4 my_fragment4);
    void GetCreateorder(IModle iModle, String uid, String token,String price,ProductActivity1 productActivity1);
    void GetUser(IModle iModle, String uid,My_Fragment4 my_fragment4);
}
