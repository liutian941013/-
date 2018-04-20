package com.example.shangchauntouxaing.mode;

import com.example.shangchauntouxaing.bean.Uptou;
import com.example.shangchauntouxaing.bean.User;
import com.example.shangchauntouxaing.util.Getnet;
import com.example.shangchauntouxaing.util.Testservice;
import com.example.shangchauntouxaing.util.Util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/2/3.
 */

public class Mymode implements Imode {



    @Override
    public void getjson(final Getnetjson getnetjson) {
        Map<String,String> map=new HashMap<>();
        map.put("","");
        map.put("","");
        Observable<User> getuser = Util.getmInstance().getnetjson(Getnet.net).getuser(map);
        getuser.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                       getnetjson.getNtetjson(user);
                    }
                });
    }

    @Override
    public void uptouxiang(File f,String id, final Uptoulisteneter uptoulisteneter) {
        Testservice testservice = Util.getmInstance().getnetjson(Getnet.genghuan);
        RequestBody uidBody = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        //f为file路径
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",f.getName(),RequestBody.create(
                MediaType.parse("application/octet-stream"),f
        ));

        Observable<Uptou> upload = testservice.upload(uidBody, filePart);
        upload.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Uptou>() {
                    @Override
                    public void accept(Uptou uptou) throws Exception {
                        uptoulisteneter.uptou(uptou);
                    }
                });
    }


}
