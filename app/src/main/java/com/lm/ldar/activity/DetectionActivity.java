package com.lm.ldar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.network.framework.FailCallback;
import com.example.network.framework.NetworkFactory;
import com.example.network.framework.SuccessfulCallback;
import com.example.network.workUtils.OkhttpFactory;
import com.lm.ldar.R;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.SelectEntity;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.ImageUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.JsonPaser;
import com.lm.ldar.util.ListDialog;
import com.lm.ldar.util.NetUtil;

import org.json.JSONException;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class DetectionActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.bt_delete)
    Button btDelete;

    private String vid;//版本id
    private SelectEntity selectEntity;
    private ListDialog listDialog;
    private List<PictureDownload> pictureDownloadList;

    private String ecode;//组织结构代码
    private String download_path;//下载图片路径

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void init() {
        selectEntity = new SelectEntity();
        listDialog = new ListDialog(DetectionActivity.this, selectEntity, daoSession);
        Enterprise epEntity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(epId)).unique();
        if (epEntity != null) {
            ecode = epEntity.getEcode();
        }
        download_path=Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DOWNLOAD_CHECK;
    }

    private void initListener() {
        btVersion.setOnClickListener(this);
        btChildArea.setOnClickListener(this);
        btDepartment.setOnClickListener(this);
        btDevice.setOnClickListener(this);
        btFactory.setOnClickListener(this);
        btSee.setOnClickListener(this);
        btDownload.setOnClickListener(this);
        btDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_version:
                //版本
                List<Pictureversion> versionList = pictureversionDao.queryBuilder().where(PictureversionDao.Properties.Eid.eq(epId)).build().list();
                if (versionList != null && versionList.size() > 0) {
                    vid = listDialog.VersionDialog(versionList, btVersion);
                } else {
                    Toast.makeText(DetectionActivity.this, "暂无版本数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_factory:
                //厂区
                List<Factory> factoryList = factoryDao.queryBuilder().where(FactoryDao.Properties.Eid.eq(epId)).build().list();
                if (factoryList != null && factoryList.size() > 0) {
                    listDialog.FactoryDialog(factoryList, btFactory);
                } else {
                    Toast.makeText(DetectionActivity.this, "暂无厂区数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_department:
                //部门
                if (selectEntity != null) {
                    if (!IsNullOrEmpty.isEmpty(selectEntity.getFid())) {
                        List<Department> departmentList = departmentDao.queryBuilder().where(DepartmentDao.Properties.Fid.eq(selectEntity.getFid())).build().list();
                        if (departmentList != null && departmentList.size() > 0) {
                            listDialog.DepartmentDialog(departmentList, btDepartment);
                        } else {
                            Toast.makeText(DetectionActivity.this, "暂无部门数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请先选择厂区", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_device:
                //装置
                if (selectEntity != null) {
                    if (!IsNullOrEmpty.isEmpty(selectEntity.getDid())) {
                        List<Device> deviceList = deviceDao.queryBuilder().where(DeviceDao.Properties.Did.eq(selectEntity.getDid())).build().list();
                        if (deviceList != null && deviceList.size() > 0) {
                            listDialog.DeviceDialog(deviceList, btDevice);
                        } else {
                            Toast.makeText(DetectionActivity.this, "暂无装置数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请先选择部门", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_child_area:
                //子区域
                if (selectEntity != null) {
                    if (!IsNullOrEmpty.isEmpty(selectEntity.getEid())) {
                        List<Area> areaList = areaDao.queryBuilder().where(AreaDao.Properties.Did.eq(selectEntity.getEid())).build().list();
                        if (areaList != null && areaList.size() > 0) {
                            listDialog.AreaDialog(areaList, btChildArea);
                        } else {
                            Toast.makeText(DetectionActivity.this, "暂无子区域数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请先选择装置", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_see:
                //查看
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            List<PictureDownload> data = DaoUtil.getDownloadPicList(pictureDownloadDao, Long.parseLong(selectEntity.getAid()),0);
                            Intent intent=new Intent(DetectionActivity.this,ImageListActivity.class);
                            intent.putExtra("imagelist", (Serializable) data);
                            intent.putExtra("type",1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_download:
                //下载
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            DownloadData(vid, selectEntity.getAid());
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_delete:
                //删除检测信息和图片
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(DetectionActivity.this);
                            builder.setMessage("确定要删除:"+selectEntity.getAname()+"检测数据和图片吗?");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //删除数据库
                                    DaoUtil.DeleteDownloadPicListByAid(pictureDownloadDao,Long.parseLong(selectEntity.getAid()),0);
                                    List<PictureDownload> pictureList = DaoUtil.getDownloadPicList(pictureDownloadDao, Long.parseLong(selectEntity.getAid()),0);
                                    if(pictureList!=null&&pictureList.size()>0){
                                        for(PictureDownload picture:pictureList){
                                            if(picture!=null){
                                                String imagePath = download_path+ selectEntity.getAid()+"/"+picture.getElementname();
                                                ImageUtil.DeleteImage(imagePath);
                                            }
                                        }
                                        Toast.makeText(DetectionActivity.this,"数据删除成功",Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();

                                }
                            });
                            builder.setNegativeButton("取消",null);
                            builder.setTitle("提示");
                            builder.create().show();
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 下载数据
     */
    private void DownloadData(String pvid, String areaid) {
        NetworkFactory factory = OkhttpFactory.getInstance();
        SuccessfulCallback successfulCallback = new SuccessfulCallback() {
            @Override
            public void success(String str) throws JSONException {

                if (str == null) {
                    return;
                }
                if (NetUtil.DealCode(DetectionActivity.this, str)) {
                    String data = NetUtil.JsonInner(DetectionActivity.this, str);
                    if (!IsNullOrEmpty.isEmpty(data)) {
                        pictureDownloadList = JsonPaser.getDownloadImgData(data, 0);
                        if (pictureDownloadList != null && pictureDownloadList.size() > 0) {
                            for (int i = 0; i < pictureDownloadList.size(); i++) {
                                DaoUtil.updatePicDownload(pictureDownloadDao, pictureDownloadList.get(i));
                                //下载图片
                                if (!IsNullOrEmpty.isEmpty(pictureDownloadList.get(i).getElementname())) {
                                    String imageUrl = ImageUtil.getImageUrl(urlManager.getBaseUrl(), ecode, vid, pictureDownloadList.get(i).getElementname());
                                    ImageUtil.DownloadImageAndSave(DetectionActivity.this, download_path+selectEntity.getAid(),imageUrl , pictureDownloadList.get(i).getElementname());
                                }
                            }
                            Toast.makeText(DetectionActivity.this, "下载完成", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void success(InputStream ism, long conentLength) {

            }
        };
        FailCallback failCallback = new FailCallback() {
            @Override
            public void fail(String str) {
                loadingDialog.dismiss();
            }
        };
        HashMap<String, String> params = new HashMap<>();
        params.put("eid", epId + "");
        params.put("pvid", pvid);
        params.put("areaid", areaid);
        loadingDialog.show();
        factory.start(OkhttpFactory.METHOD_POST, urlManager.getCheckDownloadUrl(), params, successfulCallback, failCallback);
    }

}
