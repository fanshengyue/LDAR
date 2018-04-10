package com.lm.ldar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.adapter.ImageListAdapter;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.Util;
import com.lm.ldar.view.XListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/18.
 */

public class ImageListActivity extends BaseActivity implements View.OnClickListener, XListView.IXListViewListener {

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
    XListView lvImage;

    List<PictureDownload> pictures;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.ll_nodata)
    LinearLayout llNodata;
    private ImageListAdapter mAdapter;

    private int mIndex = 0;
    private int pagesize=10;

    private List<List<PictureDownload>>data=new ArrayList<>();
    private List<PictureDownload>result=new ArrayList<>();
    private int type;//0代表建档图片，1代表检测图片，2代表复测图片

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);
        ButterKnife.bind(this);
        initTitleBar("图片列表");
        init();
        lvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ImageListActivity.this,ImageBluetoothActivity.class);
                intent.putExtra("imagelist", (Serializable) pictures);
                intent.putExtra("type",type);
                intent.putExtra("position",position-1);
                startActivity(intent);
            }
        });
        lvImage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final long lid=result.get(position-1).getId();
                final AlertDialog.Builder builder=new AlertDialog.Builder(ImageListActivity.this);
                builder.setMessage("确定要删除此张图片及其数据吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除数据库
                        if(type==0){
                            DaoUtil.deletePic(pictureDao,lid);
                        }
                        result.remove(position-1);
                        mAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.setTitle("提示");
                builder.create().show();
                return true;
            }
        });
    }

    private void init() {
        pictures = (List<PictureDownload>) getIntent().getSerializableExtra("imagelist");
        type=getIntent().getIntExtra("type",0);
        lvImage.setPullRefreshEnable(true);
        lvImage.setPullLoadEnable(true);
        lvImage.setAutoLoadEnable(true);
        lvImage.setXListViewListener(this);
        lvImage.setRefreshTime(Util.getCurrentTime());
        if (pictures != null&&pictures.size()>0) {
            dealPicData();
            llNodata.setVisibility(View.GONE);
            lvImage.setVisibility(View.VISIBLE);
            refresh();
        }else{
            llNodata.setVisibility(View.VISIBLE);
            lvImage.setVisibility(View.GONE);
        }

    }

    /**
     * 将pictures分割，给data赋值
     */
    private void dealPicData(){
        if(pictures!=null&&pictures.size()>0){
            if(pictures.size()>pagesize){
                lvImage.setFootVisible();
                if(pictures.size() % pagesize>0){
                    for (int i = 0; i < pictures.size() / pagesize + 1; i++) {
                        if(i==pictures.size()/pagesize){
                            List<PictureDownload>newPics=new ArrayList<>();
                            newPics.addAll(pictures.subList(pagesize * i,pictures.size()));
                            data.add(newPics);
                        }else{
                            List<PictureDownload>newPics=new ArrayList<>();
                            newPics.addAll(pictures.subList(pagesize * i,pagesize *(i+1)));
                            data.add(newPics);
                        }
                    }
                }else{
                    for(int i = 0; i < pictures.size() / pagesize + 1; i++){
                        List<PictureDownload>newPics=new ArrayList<>();
                        newPics.addAll(pictures.subList(pagesize * i,pagesize *(i+1)));
                        data.add(newPics);
                    }
                }
            }else{
                data.add(pictures);
                lvImage.setFootGone();
            }
        }
    }

    /**
     * 第一次和下拉刷新操作
     */
    private void refresh(){
        if(data!=null&&data.size()>0){
            mIndex=0;
            lvImage.setPullLoadEnable(true);
            result=new ArrayList<>();
            for(int i=0;i<data.get(mIndex).size();i++){
                result.add(data.get(mIndex).get(i));
            }
            mAdapter = new ImageListAdapter(ImageListActivity.this, result,type);
            lvImage.setAdapter(mAdapter);
        }
        onLoad();
    }

    /**
     * 上拉加载更多操作
     * @param
     */
    private void loadMore(){
        if(data!=null&&data.size()>0){
            mIndex++;
            if(mIndex<data.size()){
                for(int i=0;i<data.get(mIndex).size();i++){
                    result.add(data.get(mIndex).get(i));
                }
                mAdapter.notifyDataSetChanged();
            }
        }
        onLoad();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMore() {
        loadMore();
    }

    private void onLoad() {
        lvImage.stopRefresh();
        lvImage.stopLoadMore();
        lvImage.setRefreshTime(Util.getCurrentTime());
    }
}
