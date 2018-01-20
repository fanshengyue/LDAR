package com.lm.ldar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.view.PullToRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/18.
 */

public class ImageListActivity extends BaseActivity implements View.OnClickListener,PullToRefreshView.OnHeaderRefreshListener,
        PullToRefreshView.OnFooterRefreshListener {

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
    @BindView(R.id.lv_image)
    ListView lvImage;
    @BindView(R.id.refresh_view)
    PullToRefreshView refreshView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);
        ButterKnife.bind(this);
        initTitleBar("图片列表");

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

    }
}
