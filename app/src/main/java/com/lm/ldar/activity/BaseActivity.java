package com.lm.ldar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lm.ldar.LMApplication;
import com.lm.ldar.R;
import com.lm.ldar.api.UrlManager;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.CtypeDao;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.NamerulesDao;
import com.lm.ldar.dao.PictureDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.dao.WorkplanDao;
import com.lm.ldar.entity.Ctype;
import com.lm.ldar.entity.LoginUserEntity;
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
    public AreaDao areaDao;
    public PictureversionDao pictureversionDao;
    public DeviceDao deviceDao;
    public NamerulesDao namerulesDao;
    public CtypeDao ctypeDao;
    public WorkplanDao workplanDao;
    public DepartmentDao departmentDao;
    public PictureDao pictureDao;


    public Long userId;//用户id
    public Long epId;//企业id

    private TextView tv_title, tv_right;
    private ImageView ivRight;
    private LinearLayout ll_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initDao();
    }

    private void init() {
        dialog = new LoadingDialog(BaseActivity.this);
        urlManager = new UrlManager(BaseActivity.this);
        userUtil = new LoginUserUtil(BaseActivity.this);
        epId = userUtil.getEnterPriseId();
        LoginUserEntity entity = userUtil.getLoginUserInfo();
        if (entity != null) {
            userId = entity.getId();
        }
    }

    private void initDao() {
        daoSession = ((LMApplication) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        enterpriseDao = daoSession.getEnterpriseDao();
        factoryDao = daoSession.getFactoryDao();
        areaDao = daoSession.getAreaDao();
        pictureversionDao = daoSession.getPictureversionDao();
        deviceDao = daoSession.getDeviceDao();
        namerulesDao = daoSession.getNamerulesDao();
        ctypeDao = daoSession.getCtypeDao();
        workplanDao = daoSession.getWorkplanDao();
        departmentDao = daoSession.getDepartmentDao();
        pictureDao=daoSession.getPictureDao();
    }

    public void initTitleBar(String title) {
        ll_back = findViewById(R.id.ll_back);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        ivRight = findViewById(R.id.iv_right);
        tv_right.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);
        tv_title.setText(title);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
