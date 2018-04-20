package com.example.liufan.jingdongapplication.modle;

import android.util.Log;

import com.example.liufan.jingdongapplication.api.Api;
import com.example.liufan.jingdongapplication.api.ApiSerner;
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
import com.example.liufan.jingdongapplication.util.RetrofitUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liufan on 2018/4/10.
 */

public class IModle implements Modle {

    private RetrofitUtil inData;

    @Override
    //首页的方法
    public void GetHome(String url, final Modle1 modle1) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<HomeBean> homeBeanObservable = apiSerner.GetHome();
        homeBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("急急急1111", e.getMessage().toString());
                        modle1.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.d("急急急1111", homeBean.getData().toString());
                        modle1.ModleLoadSuccess(homeBean);
                    }
                });
    }

    @Override
    //九宫格的方法
    public void GetJiu(String url, final Modle2 modle2) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<ClassifyBean> classifyBeanObservable = apiSerner.GetJiu();
        classifyBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("三生三世1111", e.getMessage().toString());
                        modle2.ModleLoadError1(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                        Log.d("三生三世1111", classifyBean.getData().toString());
                        modle2.ModleLoadSuccess1(classifyBean);
                    }
                });
    }

    @Override
    //分类的方法
    public void GetFenLei(String url, final Modle3 modle3) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<ClassifyBean> classifyBeanObservable = apiSerner.GetJiu();
        classifyBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("三生三世1111", e.getMessage().toString());
                        modle3.ModleLoadError1(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                        Log.d("三生三世1111", classifyBean.getData().toString());
                        modle3.ModleLoadSuccess1(classifyBean);
                    }
                });
    }

    @Override
    //分类的子类的方法
    public void GetFenLeiZiLei(String url, String cid, final Modle4 modle4) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<SubclassBean> subclassBeanObservable = apiSerner.GetZiLei(cid);
        subclassBeanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubclassBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle4.ModleLoadError1(e.getMessage());
                    }

                    @Override
                    public void onNext(SubclassBean subclassBean) {
                        modle4.ModleLoadSuccess1(subclassBean);
                    }
                });
    }

    @Override
    //子类的列表的方法
    public void GetProduct(String url, String pscid, int page, final Modle5 modle5) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<ProductlistBean> android = apiSerner.GetProduct("android", pscid, page);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductlistBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle5.ModleLoadError1(e.getMessage());
                    }

                    @Override
                    public void onNext(ProductlistBean productlistBean) {
                        modle5.ModleLoadSuccess1(productlistBean);
                    }
                });
    }

    @Override
    public void GetParticulars(String url, String pid, final Modle6 modle6) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<ParticularsBean> android = apiSerner.GetParticulars("android", pid);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ParticularsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle6.ModleLoadError1(e.getMessage());
                    }

                    @Override
                    public void onNext(ParticularsBean particularsBean) {
                        modle6.ModleLoadSuccess1(particularsBean);
                    }
                });
    }

    @Override
    //购物车的方法
    public void GetShopping(String url,String token,String uid, final Modle7 modle7) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<ShoppingBean> android = apiSerner.GetShopping("android", uid,token);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("反反复复44444",e.getMessage().toString());
                        modle7.ModleLoadError1(e.getMessage());
                    }

                    @Override
                    public void onNext(ShoppingBean shoppingBean) {
                        Log.d("反反复复2222",shoppingBean.getData().toString());
                        modle7.ModleLoadSuccess1(shoppingBean);
                    }
                });
    }

    @Override
    //登录的方法
    public void GetLogin(String url, String mobile, String password, final Modle8 modle8) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
         Observable<LoginBean> android = apiSerner.GetLogin("android", mobile, password);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle8.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        modle8.ModleLoadSuccess(loginBean);
                    }
                });
    }

    @Override
    //注册的方法
    public void GetREg(String url, String mobile, String password, final Modle9 modle9) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
         Observable<RegBean> android = apiSerner.GetReg("android", mobile, password);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle9.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        modle9.ModleLoadSuccess(regBean);
                    }
                });
    }

    @Override
    //添加购物车的方法
    public void GetGouWu(String url, String uid, String pid, String token,final Modle10 modle10) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
         Observable<TianJIanGouWuBean> android = apiSerner.GetGouWu("android", uid, pid,token);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TianJIanGouWuBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle10.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(TianJIanGouWuBean tianJIanGouWuBean) {
                        modle10.ModleLoadSuccess(tianJIanGouWuBean);
                    }
                });
    }

    @Override
    //删除购物车的方法
    public void GetSCGouWu(String url, String uid, String pid, String token,final Modle11 modle11) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<ShanChuGouWuBean> android = apiSerner.GetSCGouWu("android", uid, pid,token);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShanChuGouWuBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle11.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(ShanChuGouWuBean shanChuGouWuBean) {
                        modle11.ModleLoadSuccess(shanChuGouWuBean);
                    }
                });
    }

    @Override
    public void GetSeek(String url, String keywords, String page, final Modle12 modle12) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<GoodBean> android = apiSerner.GetSeek("android", keywords, page);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle12.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodBean goodBean) {
                        modle12.ModleLoadSuccess(goodBean);
                    }
                });
    }

    @Override
    //添加常用地址
    public void GetLocation(String url, String uid, String addr, String mobile, String name, String token,final Modle13 modle13) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
         Observable<LocationBean> android = apiSerner.GetLocation("android", uid, addr, mobile, name,token);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle13.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(LocationBean locationBean) {
                        modle13.ModleLoadSuccess(locationBean);
                    }
                });
    }

    @Override
    //获取地址
    public void GetHuoQuLocation(String url, String uid, final Modle14 modle14) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<HuoQuLocationBean> android = apiSerner.GetHuoQuLocation("android", uid);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HuoQuLocationBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle14.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(HuoQuLocationBean huoQuLocationBean) {
                        modle14.ModleLoadSuccess(huoQuLocationBean);
                    }
                });
    }

    @Override
    //上传头像
    public void GetUpload(String url, File f, String uid, String token, final Modle15 modle15) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        RequestBody uidBody = RequestBody.create(MediaType.parse("multipart/form-data"), uid);
        //f为file路径
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",f.getName(),RequestBody.create(
                MediaType.parse("application/octet-stream"),f
        ));
         Observable<Uptou> upload = apiSerner.Upload(uidBody, filePart);
         upload.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<Uptou>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {
                         modle15.ModleLoadError(e.getMessage());
                     }

                     @Override
                     public void onNext(Uptou uptou) {
                         modle15.ModleLoadSuccess(uptou);
                     }
                 });
    }

    @Override
    //创建订单
    public void GetCreateorder(String url, String uid, String token, String price, final Modle16 modle16) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
         Observable<Createorder> android = apiSerner.GetCreateorder("android", uid, price);
         android.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<Createorder>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {
                         modle16.ModleLoadError(e.getMessage());
                     }

                     @Override
                     public void onNext(Createorder createorder) {
                         modle16.ModleLoadSuccess(createorder);
                     }
                 });
    }

    @Override
    public void GetUser(String url, String uid, final Modle17 modle17) {
        inData = RetrofitUtil.getInData();
        ApiSerner apiSerner = inData.getRetrofit(url, ApiSerner.class);
        Observable<UserBean> android = apiSerner.GetUser("android", uid);
        android.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        modle17.ModleLoadError(e.getMessage());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        modle17.ModleLoadSuccess(userBean);
                    }
                });
    }
}
