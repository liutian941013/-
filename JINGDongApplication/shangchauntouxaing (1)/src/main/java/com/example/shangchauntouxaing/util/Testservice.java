package com.example.shangchauntouxaing.util;

import com.example.shangchauntouxaing.bean.Uptou;
import com.example.shangchauntouxaing.bean.User;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2018/2/3.
 */

public interface Testservice {

    @POST("user/login")
    @FormUrlEncoded
    Observable<User> getuser(@FieldMap Map<String,String> map);

    //第一种方式上传头像
    @POST("file/upload")
    @Multipart
    Observable<Uptou> upload(@Part("uid") RequestBody uid, @Part MultipartBody.Part file);




}
