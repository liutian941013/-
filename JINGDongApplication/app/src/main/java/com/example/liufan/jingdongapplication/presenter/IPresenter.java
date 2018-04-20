package com.example.liufan.jingdongapplication.presenter;

import android.util.Log;

import com.example.liufan.jingdongapplication.adapter.MaEx;
import com.example.liufan.jingdongapplication.api.Api;
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
import com.example.liufan.jingdongapplication.fragment.Classify_Fragment1;
import com.example.liufan.jingdongapplication.fragment.Home_Fragment;
import com.example.liufan.jingdongapplication.fragment.My_Fragment4;
import com.example.liufan.jingdongapplication.fragment.Shopping_Fragment3;
import com.example.liufan.jingdongapplication.modle.IModle;
import com.example.liufan.jingdongapplication.modle.Modle1;
import com.example.liufan.jingdongapplication.modle.Modle10;
import com.example.liufan.jingdongapplication.modle.Modle11;
import com.example.liufan.jingdongapplication.modle.Modle12;
import com.example.liufan.jingdongapplication.modle.Modle13;
import com.example.liufan.jingdongapplication.modle.Modle14;
import com.example.liufan.jingdongapplication.modle.Modle15;
import com.example.liufan.jingdongapplication.modle.Modle16;
import com.example.liufan.jingdongapplication.modle.Modle17;
import com.example.liufan.jingdongapplication.modle.Modle2;
import com.example.liufan.jingdongapplication.modle.Modle3;
import com.example.liufan.jingdongapplication.modle.Modle4;
import com.example.liufan.jingdongapplication.modle.Modle5;
import com.example.liufan.jingdongapplication.modle.Modle6;
import com.example.liufan.jingdongapplication.modle.Modle7;
import com.example.liufan.jingdongapplication.modle.Modle8;
import com.example.liufan.jingdongapplication.modle.Modle9;
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

public class IPresenter implements Presenter {
    @Override
    //首页的方法
    public void GetHome(IModle iModle, final Home_Fragment home_fragment) {
        iModle.GetHome(Api.HOMEURL, new Modle1() {
            @Override
            public void ModleLoadError(String s) {
                Log.d("急急急", s.toString());
                home_fragment.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(HomeBean homeBean) {
                Log.d("急急急", homeBean.getData().toString());
                home_fragment.ViewLoadSuccess(homeBean);
            }
        });
    }

    @Override
    //我的推荐的方法
    public void GetHome1(IModle iModle, final My_Fragment4 my_fragment4) {
        iModle.GetHome(Api.HOMEURL, new Modle1() {
            @Override
            public void ModleLoadError(String s) {
                Log.d("急急急", s.toString());
                my_fragment4.ModleLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(HomeBean homeBean) {
                Log.d("急急急000000", homeBean.getData().toString());
                my_fragment4.ModleLoadSuccess(homeBean);
            }
        });
    }

    @Override
    //九宫格的方法
    public void GetHJiu(IModle iModle, final Home_Fragment home_fragment) {
        iModle.GetJiu(Api.JIUURL, new Modle2() {
            @Override
            public void ModleLoadError1(String s) {
                Log.d("三生三世", s.toString());
                home_fragment.ViewLoadError1(s);
            }

            @Override
            public void ModleLoadSuccess1(ClassifyBean classifyBean) {
                Log.d("三生三世", classifyBean.getData().toString());
                home_fragment.ViewLoadSuccess1(classifyBean);
            }
        });
    }

    @Override
    //分类的方法
    public void GetFenLei(IModle iModle, final Classify_Fragment1 classify_fragment1) {
        iModle.GetFenLei(Api.JIUURL, new Modle3() {
            @Override
            public void ModleLoadError1(String s) {
                classify_fragment1.ViewLoadError1(s);
            }

            @Override
            public void ModleLoadSuccess1(ClassifyBean classifyBean) {
                classify_fragment1.ViewLoadSuccess1(classifyBean);
            }
        });
    }

    @Override
    //分类的子类的方法
    public void GetFenLeiZiLei(IModle iModle, String cid, final Classify_Fragment1 classify_fragment1) {
        iModle.GetFenLeiZiLei(Api.ZILEI, cid, new Modle4() {
            @Override
            public void ModleLoadError1(String s) {
                classify_fragment1.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess1(SubclassBean subclassBean) {
                Log.d("凄凄切切群群",subclassBean.toString());
                classify_fragment1.ViewLoadSuccess(subclassBean);
            }
        });
    }

    @Override
    //子类的列表的方法
    public void GetProduct(IModle iModle, String pscid, int page, final ProductActivity productActivity) {
        iModle.GetProduct(Api.Product, pscid, page, new Modle5() {
            @Override
            public void ModleLoadError1(String s) {
                productActivity.ModleLoadError1(s);
            }

            @Override
            public void ModleLoadSuccess1(ProductlistBean productlistBean) {
                productActivity.ModleLoadSuccess1(productlistBean);
            }
        });
    }

    @Override
    //子类的列表的详情方法
    public void GetParticulars(IModle iModle, String pid, final ProductActivity1 productActivity1) {
        iModle.GetParticulars(Api.Particulars, pid, new Modle6() {
            @Override
            public void ModleLoadError1(String s) {
                productActivity1.ModleLoadError1(s);
            }

            @Override
            public void ModleLoadSuccess1(ParticularsBean particularsBean) {
                productActivity1.ModleLoadSuccess1(particularsBean);
            }
        });
    }

    @Override
    //订单中获取商品的方法
    public void GetIndent(IModle iModle, String pid, final IndentActivity indentActivity) {
        iModle.GetParticulars(Api.Particulars, pid, new Modle6() {
            @Override
            public void ModleLoadError1(String s) {
                indentActivity.ModleLoadError1(s);
            }

            @Override
            public void ModleLoadSuccess1(ParticularsBean particularsBean) {
                indentActivity.ModleLoadSuccess1(particularsBean);
            }
        });
    }

    @Override
    //购物车的方法
    public void GetShopping(IModle iModle, int uid,String token ,final Shopping_Fragment3 shopping_fragment3) {
        iModle.GetShopping(Api.Shopping,token,uid+"",new  Modle7() {
            @Override
            public void ModleLoadError1(String s) {
                shopping_fragment3.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess1(ShoppingBean shoppingBean) {
                Log.d("吾问无为谓无无无无", shoppingBean.getData().toString());
                shopping_fragment3.ViewLoadSuccess(shoppingBean);
            }
        });
    }

    @Override
    //登录的方法
    public void GetLogin(IModle iModle, String mobile, String password, final LoginActivity loginActivity) {
        iModle.GetLogin(Api.Login, mobile, password, new Modle8() {
            @Override
            public void ModleLoadError(String s) {
                loginActivity.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(LoginBean loginBean) {
                loginActivity.ViewLoadSuccess(loginBean);
            }
        });
    }

    @Override
    //注册的方法
    public void GetReg(IModle iModle, String mobile, String password, final RegActivity regActivity) {
        iModle.GetREg(Api.Reg, mobile, password, new Modle9() {
            @Override
            public void ModleLoadError(String s) {
                regActivity.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(RegBean regBean) {
                regActivity.ViewLoadSuccess(regBean);
            }
        });
    }

    @Override
    //添加购物车的方法
    public void GetGouWu(IModle iModle, String uid, String pid, String token,final ProductActivity1 productActivity1) {
        iModle.GetGouWu(Api.TianJianGouW, uid, pid, token,new Modle10() {
            @Override
            public void ModleLoadError(String s) {
                productActivity1.ViewLoadError1(s);
            }

            @Override
            public void ModleLoadSuccess(TianJIanGouWuBean tianJIanGouWuBean) {
                productActivity1.ViewLoadSuccess1(tianJIanGouWuBean);
            }
        });
    }

    @Override
    //删除购物车
    public void GetSCGouWu(IModle iModle, String uid, String pid, String token,final Shopping_Fragment3 shopping_fragment3) {
        iModle.GetSCGouWu(Api.ShanChuGouW, uid, pid, token,new Modle11() {
            @Override
            public void ModleLoadError(String s) {
                shopping_fragment3.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(ShanChuGouWuBean shanChuGouWuBean) {
                shopping_fragment3.ViewLoadSuccess(shanChuGouWuBean);
            }
        });
    }

    @Override
    //搜索商品
    public void GetSeek(IModle iModle, String keywords, String page, final SeekActivity seekActivity) {
        iModle.GetSeek(Api.Seek, keywords, page, new Modle12() {
            @Override
            public void ModleLoadError(String s) {
                seekActivity.ViewGoodError(s);
            }

            @Override
            public void ModleLoadSuccess(GoodBean goodBean) {
                seekActivity.ViewGoodSuccess(goodBean);
            }
        });
    }

    @Override
    //添加常用地址
    public void GetLocation(IModle iModle, String uid, String addr, String mobile, String name,String token,final IndentActivity indentActivity) {
        iModle.GetLocation(Api.Location, uid, addr, mobile, name, token,new Modle13() {
            @Override
            public void ModleLoadError(String s) {
                indentActivity.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(LocationBean locationBean) {
                indentActivity.ViewLoadSuccess(locationBean);
            }
        });
    }

    @Override
    //获取地址
    public void GetHuoQuLocation(IModle iModle, String uid, final IndentActivity indentActivity) {
             iModle.GetHuoQuLocation(Api.HuoQuLocation, uid, new Modle14() {
                 @Override
                 public void ModleLoadError(String s) {
                     indentActivity.ViewLoadError2(s);
                 }

                 @Override
                 public void ModleLoadSuccess(HuoQuLocationBean huoQuLocationBean) {
                     indentActivity.ViewLoadSuccess2(huoQuLocationBean);
                 }
             });
    }

    @Override
    //上传头像
    public void GetUpload(IModle iModle, String uid, String token, File f, final My_Fragment4 my_fragment4) {
        iModle.GetUpload(Api.Pictures, f, uid, token, new Modle15() {
            @Override
            public void ModleLoadError(String s) {
                my_fragment4.ViewLoadError(s);
            }

            @Override
            public void ModleLoadSuccess(Uptou uptou) {
                my_fragment4.ViewLoadSuccess(uptou);
            }
        });
    }

    @Override
    //创建订单
    public void GetCreateorder(IModle iModle, String uid, String token, String price, final ProductActivity1 productActivity1) {
        iModle.GetCreateorder(Api.Createorder, uid, token, price, new Modle16() {
            @Override
            public void ModleLoadError(String s) {
                productActivity1.ModleDingDanError1(s);
            }

            @Override
            public void ModleLoadSuccess(Createorder createorder) {
                productActivity1.ModleDingDanSuccess1(createorder);
            }
        });
    }

    @Override
    public void GetUser(IModle iModle, String uid, final My_Fragment4 my_fragment4) {
        iModle.GetUser(Api.User, uid, new Modle17() {
            @Override
            public void ModleLoadError(String s) {
                my_fragment4.ViewUserError1(s);
            }

            @Override
            public void ModleLoadSuccess(UserBean userBean) {
                my_fragment4.ViewUIserSuccess2(userBean);
            }
        });
    }
}
