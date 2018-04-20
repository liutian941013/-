package com.example.liufan.jingdongapplication.modle;

import java.io.File;

/**
 * Created by liufan on 2018/4/10.
 */

public interface Modle {
    void GetHome(String url,Modle1 modle1);
    void GetJiu(String url,Modle2 modle2);
    void GetFenLei(String url,Modle3 modle3);
    void GetFenLeiZiLei(String url,String cid,Modle4 modle4);
    void GetProduct(String url,String pscid,int page,Modle5 modle5);
    void GetParticulars(String url,String pid,Modle6 modle6);
    void GetShopping(String url,String token,String uid,Modle7 modle7);
    void GetLogin(String url,String mobile,String password,Modle8 modle8);
    void GetREg(String url,String mobile,String password,Modle9 modle9);

    void GetGouWu(String url,String uid,String pid,String token,Modle10 modle10);

    void GetSCGouWu(String url,String uid,String pid,String token,Modle11 modle11);

    void GetSeek(String url,String keywords,String page,Modle12 modle12);

    void GetLocation(String url,String uid,String addr,String mobile,String name,String token,Modle13 modle13);

    void GetHuoQuLocation(String url,String uid,Modle14 modle14);
    void GetUpload(String url, File f, String uid, String token, Modle15 modle15);
    void GetCreateorder(String url,String uid, String token, String price,Modle16 modle16);
    void GetUser(String url,String uid,Modle17 modle17);
}
