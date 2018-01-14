package com.lm.ldar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lm.ldar.LMApplication;
import com.lm.ldar.api.UrlManager;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.util.LoginUserUtil;
import com.lm.ldar.view.LoadingDialog;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class BaseActivity extends Activity {
    public LoadingDialog dialog;
    public UrlManager urlManager;
    public LoginUserUtil userUtil;
    public DaoSession daoSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog=new LoadingDialog(BaseActivity.this);
        urlManager=new UrlManager(BaseActivity.this);
        userUtil=new LoginUserUtil(BaseActivity.this);
        daoSession = ((LMApplication)getApplication()).getDaoSession();
    }


}
