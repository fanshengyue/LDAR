package com.lm.ldar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.LoginUserEntity;
import com.lm.ldar.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class MeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_quit)
    Button btQuit;
    @BindView(R.id.tv_ename)
    TextView tvEname;
    @BindView(R.id.tv_ecode)
    TextView tvEcode;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_truename)
    TextView tvTruename;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_industry)
    TextView tvIndustry;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);

        // 获取当前用户信息
        LoginUserEntity loginUserInfo = userUtil.getLoginUserInfo();
        Long uid = loginUserInfo.getId();
        User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(uid)).unique();

        // 获取当前企业信息
        Long eid = userUtil.getEnterPriseId();
        Enterprise enterprise = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(eid)).unique();

        // 企业名称
        tvEname.setText(enterprise.getEname());
        // 组织机构代码
        tvEcode.setText(enterprise.getEcode());
        // 用户名
        tvUsername.setText(user.getUsername());
        // 真实姓名
        tvTruename.setText(user.getTruename());
        // 手机号
        tvPhone.setText(user.getPhone());
        // 邮箱
        tvEmail.setText(user.getEmail());
        // 行业类型
        tvIndustry.setText(enterprise.getIndustry());

        initListener();
    }

    private void initListener() {
        btQuit.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    /**
     * 退出登录
     */
    private void Logout() {
        Intent intents = new Intent(MeActivity.this, LoginActivity.class);
        startActivity(intents);
        userUtil.deleteShare();
        finish();
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_quit:
                Logout();
                break;
            case R.id.iv_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            default:
                break;
        }
    }
}
