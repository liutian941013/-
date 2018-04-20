package com.example.shangchauntouxaing.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/2/3.
 */

public class Util {
    private volatile static Util util;
    private Util(){

    }
    public static Util getmInstance(){
        if (util==null){
        synchronized (Util.class){
            if (util==null){
                util=new Util();
            }
        }
        }

        return util;
    }

    public Testservice getnetjson(String uri){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(uri)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Testservice testservice = retrofit.create(Testservice.class);
        return testservice;
    }

}
