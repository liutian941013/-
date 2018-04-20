package com.example.liufan.jingdongapplication.api;

import com.example.liufan.jingdongapplication.bean.ClassifyBean;
import com.example.liufan.jingdongapplication.bean.Createorder;
import com.example.liufan.jingdongapplication.bean.GoodBean;
import com.example.liufan.jingdongapplication.bean.HomeBean;
import com.example.liufan.jingdongapplication.bean.HuoQuLocationBean;
import com.example.liufan.jingdongapplication.bean.LocationBean;
import com.example.liufan.jingdongapplication.bean.LoginBean;
import com.example.liufan.jingdongapplication.bean.ParticularsBean;
import com.example.liufan.jingdongapplication.bean.ProductlistBean;
import com.example.liufan.jingdongapplication.bean.RegBean;
import com.example.liufan.jingdongapplication.bean.ShanChuGouWuBean;
import com.example.liufan.jingdongapplication.bean.ShoppingBean;
import com.example.liufan.jingdongapplication.bean.SubclassBean;
import com.example.liufan.jingdongapplication.bean.TianJIanGouWuBean;
import com.example.liufan.jingdongapplication.bean.Uptou;
import com.example.liufan.jingdongapplication.bean.UserBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liufan on 2018/4/9.
 */

public interface ApiSerner {
    //请求首页
    @GET("getAd")
    Observable<HomeBean> GetHome();
    //请求九宫格
    @GET("getCatagory")
    Observable<ClassifyBean> GetJiu();
    //请求子类的
    @GET("getProductCatagory")
    Observable<SubclassBean> GetZiLei(@Query("cid") String cid);
    //请求子类的
    @GET("getProducts")
    Observable<ProductlistBean> GetProduct(@Query("source") String android,@Query("pscid") String pscid,@Query("page") int page);
    //请求子类的的详情
    @GET("getProductDetail")
    Observable<ParticularsBean> GetParticulars(@Query("source") String android, @Query("pid") String pid);
    //请求购物车
    @GET("getCarts")
    Observable<ShoppingBean> GetShopping(@Query("source") String android, @Query("uid") String uid, @Query("token") String token);
    //登录的方法
    @GET("login")
    Observable<LoginBean> GetLogin(@Query("source") String android, @Query("mobile") String mobile, @Query("password") String password);
    //注册的方法
    @GET("reg")
    Observable<RegBean> GetReg(@Query("source") String android, @Query("mobile") String mobile, @Query("password") String password);
    //添加购物车的方法
    @GET("addCart")
    Observable<TianJIanGouWuBean> GetGouWu(@Query("source") String android, @Query("uid") String uid, @Query("pid") String pid, @Query("token") String token);
    //删除购物车的方法
    @GET("addCart")
    Observable<ShanChuGouWuBean> GetSCGouWu(@Query("source") String android, @Query("uid") String uid, @Query("pid") String pid, @Query("token") String token);
    //搜索的方法
    @GET("searchProducts")
    Observable<GoodBean> GetSeek(@Query("source") String android, @Query("keywords") String keywords, @Query("page") String page);
    //添加常用地址的方法
    @GET("addAddr")
    Observable<LocationBean> GetLocation(@Query("source") String android, @Query("uid") String uid, @Query("addr") String addr, @Query("mobile") String mobile,@Query("name") String name,@Query("token") String token);
    //获取地址的方法
    @GET("getDefaultAddr")
    Observable<Createorder> GetCreateorder(@Query("source") String android, @Query("uid") String uid, @Query("price") String price);
    //第一种方式上传头像
    @POST("file/upload")
    @Multipart
    Observable<Uptou> Upload(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);
    //获取地址的方法
    @GET("createOrder")
    Observable<HuoQuLocationBean> GetHuoQuLocation(@Query("source") String android, @Query("uid") String uid);
    //获取用户信息
    @GET("getUserInfo")
    Observable<UserBean> GetUser(@Query("source") String android, @Query("uid") String uid);

}
