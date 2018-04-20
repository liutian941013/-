package com.example.shangchauntouxaing.presenter;

import com.example.shangchauntouxaing.bean.Uptou;
import com.example.shangchauntouxaing.bean.User;
import com.example.shangchauntouxaing.mode.Getnetjson;
import com.example.shangchauntouxaing.mode.Imode;
import com.example.shangchauntouxaing.mode.Uptoulisteneter;
import com.example.shangchauntouxaing.view.Iview;

import java.io.File;

/**
 * Created by Administrator on 2018/2/3.
 */

public class Mypresenter implements Ipresenter {
   /* @Override
    public void getmv(Imode imode, final Iview iview) {
        imode.getjson(new Getnetjson() {
            @Override
            public void getNtetjson(User user) {
              
                    User.DataBean data = user.getData();
                    if (data!=null) {
                        data.getIcon();
                        iview.gettu(icon);
                    }

            }
        });
    }*/

    @Override
    public void getmv1(File f, String id, Imode imode, final Iview iview) {
        imode.uptouxiang(f, id, new Uptoulisteneter() {
            @Override
            public void uptou(Uptou uptou) {
                iview.upcheng(uptou);
            }
        });
    }
}
