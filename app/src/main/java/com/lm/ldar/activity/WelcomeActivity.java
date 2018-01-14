package com.lm.ldar.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.network.framework.FailCallback;
import com.example.network.framework.NetworkFactory;
import com.example.network.framework.SuccessfulCallback;
import com.example.network.workUtils.OkhttpFactory;
import com.lm.ldar.LMApplication;
import com.lm.ldar.R;
import com.lm.ldar.SharePreferenceKey;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.LoginUserEntity;
import com.lm.ldar.entity.User;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.JsonPaser;
import com.lm.ldar.util.NetUtil;
import com.lm.ldar.view.MyAlertDialog;

import org.json.JSONException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class WelcomeActivity extends BaseActivity {
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gotoMain();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        LoginUserEntity entity=userUtil.getLoginUserInfo();
                        if(entity!=null&&!IsNullOrEmpty.isEmpty(entity.getUsername())&&!IsNullOrEmpty.isEmpty(entity.getPassword())){
                            //自动登录
                            startLogin(entity.getUsername(),entity.getPassword());
                        }else{
                            Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            WelcomeActivity.this.finish();
                        }

                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void gotoMain() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void startLogin(final String username, final String password){
        NetworkFactory factory= OkhttpFactory.getInstance();
        SuccessfulCallback successfulCallback=new SuccessfulCallback() {
            @Override
            public void success(String str) {
                if(str==null){
                    return;
                }
                if(NetUtil.DealCode(WelcomeActivity.this,str)){
                    String data=NetUtil.JsonInner(WelcomeActivity.this,str);
                    if(!IsNullOrEmpty.isEmpty(data)){
                        try {
                            org.json.JSONObject jsonObject=new org.json.JSONObject(data);
                            //用户
                            String str_user=jsonObject.optString("user");
                            if(!IsNullOrEmpty.isEmpty(str_user)){
                                User user= JsonPaser.parseUser(str_user);
                                if(user!=null){
                                    //登录用户信息更新shareprefrence
                                    userUtil.updateShareUser(user.getId(),user.getUsername(),user.getPassword());
                                    DaoUtil.UpdateUser(userDao,user);
                                }
                            }
                            //企业
                            String str_enterprise=jsonObject.optString("enterprise");
                            if(!IsNullOrEmpty.isEmpty(str_enterprise)){
                                Enterprise enterprise=JsonPaser.parseEnterprise(str_enterprise);
                                if(enterprise!=null){
                                    DaoUtil.UpdateEnterprise(enterpriseDao,enterprise);
                                }
                            }
                            //厂区
                            String str_fac=jsonObject.optString("factoryList");
                            if(!IsNullOrEmpty.isEmpty(str_fac)){
                                List<Factory> factories=JsonPaser.parseFactory(str_fac);
                                if(factories!=null&&factories.size()>0){
                                    for(Factory factory:factories){
                                        DaoUtil.UpdateFactory(factoryDao,factory);
                                    }
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            }

            @Override
            public void success(InputStream ism, long conentLength) {

            }
        };

        FailCallback failCallback=new FailCallback() {
            @Override
            public void fail(String str) {
                MyAlertDialog.showDialog(WelcomeActivity.this, str);
            }
        };
        HashMap<String,String> params=new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        factory.start(NetworkFactory.METHOD_POST,urlManager.getAppLogin() , params, successfulCallback, failCallback);
    }
}
