package com.example.shangchauntouxaing.mode;

import java.io.File;

/**
 * Created by Administrator on 2018/2/3.
 */

public interface Imode {
    void getjson(Getnetjson getnetjson);

    //修改头像的方法
    void uptouxiang(File f, String id, Uptoulisteneter uptoulisteneter);

}
