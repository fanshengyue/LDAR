package com.lm.ldar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.lm.ldar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class ReviewActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.bt_version)
    Button btVersion;
    @BindView(R.id.bt_factory)
    Button btFactory;
    @BindView(R.id.bt_department)
    Button btDepartment;
    @BindView(R.id.bt_device)
    Button btDevice;
    @BindView(R.id.bt_child_area)
    Button btChildArea;
    @BindView(R.id.bt_download)
    Button btDownload;
    @BindView(R.id.bt_see)
    Button btSee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener(){
        btVersion.setOnClickListener(this);
        btChildArea.setOnClickListener(this);
        btDepartment.setOnClickListener(this);
        btDevice.setOnClickListener(this);
        btFactory.setOnClickListener(this);
        btSee.setOnClickListener(this);
        btDownload.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_version:
                //版本
                break;
            case R.id.bt_factory:
                //厂区
                break;
            case R.id.bt_department:
                //部门
                break;
            case R.id.bt_device:
                //装置
                break;
            case R.id.bt_child_area:
                //子区域
                break;
            case R.id.bt_see:
                //查看
                break;
            case R.id.bt_download:
                break;
            default:
                break;
        }
    }
}
