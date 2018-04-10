package com.lm.ldar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.SharePreferenceKey;
import com.lm.ldar.util.TimeUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/3/14.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.rl_topcontainer)
    RelativeLayout rlTopcontainer;
    @BindView(R.id.bt_model)
    Button btModel;

    @BindView(R.id.bt_frequency)
    Button btFrequency;
    @BindView(R.id.ll_frequency)
    LinearLayout llFrequency;
    private AlertDialog.Builder builder;
    private int choiceIndex = 0;
    final String[] arrayModels = {"自选模式", "固定模式", "智能模式"};
    private ArrayList<Integer> timeArray;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitleBar("设置");
        sharedPreferences = getSharedPreferences(SharePreferenceKey.MODEL, Activity.MODE_PRIVATE);
        timeArray = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            timeArray.add(i);
        }
        llBack.setOnClickListener(this);
        btModel.setOnClickListener(this);
        btFrequency.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.bt_model:
                showModelDialog();
                break;
            case R.id.bt_frequency:
                if (timeArray != null && timeArray.size() > 0) {
                    TimeUtil.alertBottomWheelOption(SettingActivity.this, timeArray, new TimeUtil.OnWheelViewClick() {
                        @Override
                        public void onClick(View view, int postion) {
                            btFrequency.setText(timeArray.get(postion) + "秒");
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putInt(SharePreferenceKey.FREQUENCY, timeArray.get(postion));
                            edit.commit();
                        }

                        @Override
                        public void onEmpty() {
                            btFrequency.setText(getResources().getString(R.string.please_select));
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putInt(SharePreferenceKey.FREQUENCY, 10);
                            edit.commit();
                        }
                    });
                }

                break;
            default:
                break;
        }
    }

    private void showModelDialog() {
        choiceIndex = sharedPreferences.getInt(SharePreferenceKey.CHOICEINDEX, 0);
        builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(arrayModels, choiceIndex, new DialogInterface.OnClickListener() {// 第二个参数是设置默认选中哪一项-1代表默认都不选
            @Override
            public void onClick(DialogInterface dialog, int i) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(SharePreferenceKey.CHOICEINDEX, i);
                edit.commit();
                btModel.setText(arrayModels[i]);
                if (i == 1) {
                    llFrequency.setVisibility(View.VISIBLE);
                }else{
                    llFrequency.setVisibility(View.GONE);
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);// dialog弹出后，点击界面其他部分dialog消失
    }
}
