package com.lm.ldar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.lm.ldar.R;
import com.lm.ldar.util.LoginUserUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class MeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_quit)
    Button btQuit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        btQuit.setOnClickListener(this);
    }

    /**
     * 退出登录
     */
    private void Logout(){
        Intent intents = new Intent(MeActivity.this, LoginActivity.class);
        startActivity(intents);
        userUtil.deleteShare();
        finish();
        System.exit(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_quit:
                Logout();
                break;
            default:
                break;
        }
    }
}
