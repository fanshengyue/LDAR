package com.lm.ldar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lm.ldar.LMApplication;
import com.lm.ldar.api.UrlManager;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.UserDao;
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
    public UserDao userDao;
    public EnterpriseDao enterpriseDao;
    public FactoryDao factoryDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog=new LoadingDialog(BaseActivity.this);
        urlManager=new UrlManager(BaseActivity.this);
        userUtil=new LoginUserUtil(BaseActivity.this);
        initDao();

    }

    private void initDao(){
        daoSession = ((LMApplication)getApplication()).getDaoSession();
        userDao=daoSession.getUserDao();
        enterpriseDao=daoSession.getEnterpriseDao();
        factoryDao=daoSession.getFactoryDao();
    }


}
