package com.example.shangchauntouxaing.presenter;

import com.example.shangchauntouxaing.mode.Imode;
import com.example.shangchauntouxaing.view.Iview;

import java.io.File;

/**
 * Created by Administrator on 2018/2/3.
 */

public interface Ipresenter {
    //void getmv(Imode imode, Iview iview);
    void getmv1(File f, String id, Imode imode, Iview iview);

}
