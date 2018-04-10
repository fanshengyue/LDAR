package com.lm.ldar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.adapter.DragListAdapter;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.itemtouch.DefaultItemTouchHelpCallback;
import com.lm.ldar.itemtouch.DefaultItemTouchHelper;
import com.lm.ldar.itemtouch.SwipeItemLayout;
import com.lm.ldar.util.DaoUtil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/4/8.
 */

public class DragListActivity extends BaseActivity {

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
    @BindView(R.id.lv_drag)
    RecyclerView lvDrag;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private DragListAdapter dragListAdapter;
    private List<Picture> pictures;
    /**
     * 滑动拖拽的帮助类
     */
    private DefaultItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draglist);
        ButterKnife.bind(this);
        initTitleBar("建档图片列表");
        init();

    }

    private void init() {
        pictures = (List<Picture>) getIntent().getSerializableExtra("imagelist");
        lvDrag.setItemAnimator(new DefaultItemAnimator());
        // 必须要设置一个布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lvDrag.setLayoutManager(linearLayoutManager);
        lvDrag.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        lvDrag.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        swipeRefresh.setColorSchemeColors(Color.RED);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                },2000);
            }
        });

        dragListAdapter = new DragListAdapter(pictures, DragListActivity.this,pictureDao);
        lvDrag.setAdapter(dragListAdapter);

        // 把ItemTouchHelper和itemTouchHelper绑定
        itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
        itemTouchHelper.attachToRecyclerView(lvDrag);
        dragListAdapter.setItemTouchHelper(itemTouchHelper);
        //设置是否可拖拽
        itemTouchHelper.setDragEnable(true);
        //设置是否可滑动
//        itemTouchHelper.setSwipeEnable(true);

    }

    private DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwiped(final int adapterPosition) {
//            //左右滑删除
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            //拖拽更改排序
            if (pictures != null) {
                Picture opicture = pictures.get(srcPosition);
                Picture npicture = pictures.get(targetPosition);

                Long oid = pictures.get(srcPosition).getXid();
                Long nid = pictures.get(targetPosition).getXid();

                opicture.setXid(nid);
                npicture.setXid(oid);
                //更新数据库中的排序
                DaoUtil.UpdateRank(pictureDao, opicture, npicture);
                // 更换数据源中的数据Item的位置
                Collections.swap(pictures, srcPosition, targetPosition);
                // 更新UI中的Item的位置，主要是给用户看到交互效果
                dragListAdapter.notifyItemMoved(srcPosition, targetPosition);
                return true;
            }
            return false;
        }
    };


}
