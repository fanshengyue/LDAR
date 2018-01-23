package com.lm.ldar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.dao.WorkplanDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.SelectEntity;
import com.lm.ldar.entity.Workplan;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.ListDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class ReviewActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.bt_plan)
    Button btPlan;
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

    private String wpid;//检测计划id
    private SelectEntity selectEntity;
    private ListDialog listDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void init(){
        selectEntity=new SelectEntity();
        listDialog=new ListDialog(ReviewActivity.this,selectEntity,daoSession);
    }

    private void initListener(){
        btPlan.setOnClickListener(this);
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
            case R.id.bt_plan:
                //检测计划
                List<Workplan>workplanList=workplanDao.queryBuilder().where(WorkplanDao.Properties.Eid.eq(epId)).build().list();
                if(workplanList!=null&&workplanList.size()>0){
                    wpid=listDialog.WorkPlanDialog(workplanList,btPlan);
                }else{
                    Toast.makeText(ReviewActivity.this,"暂无监测计划数据",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_factory:
                //厂区
                List<Factory> factoryList=factoryDao.queryBuilder().where(FactoryDao.Properties.Eid.eq(epId)).build().list();
                if(factoryList!=null&&factoryList.size()>0){
                    listDialog.FactoryDialog(factoryList);
                    UpdateButtonState();
                }else{
                    Toast.makeText(ReviewActivity.this,"暂无厂区数据",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_department:
                //部门
                if(selectEntity!=null){
                    if(!IsNullOrEmpty.isEmpty(selectEntity.getFid())){
                        List<Department>departmentList=departmentDao.queryBuilder().where(DepartmentDao.Properties.Fid.eq(selectEntity.getFid())).build().list();
                        if(departmentList!=null&&departmentList.size()>0){
                            listDialog.DepartmentDialog(departmentList);
                            UpdateButtonState();
                        }else{
                            Toast.makeText(ReviewActivity.this,"暂无部门数据",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ReviewActivity.this,"请先选择厂区",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_device:
                //装置
                if(selectEntity!=null){
                    if(!IsNullOrEmpty.isEmpty(selectEntity.getDid())){
                        List<Device>deviceList=deviceDao.queryBuilder().where(DeviceDao.Properties.Did.eq(selectEntity.getDid())).build().list();
                        if(deviceList!=null&&deviceList.size()>0){
                            listDialog.DeviceDialog(deviceList);
                            UpdateButtonState();
                        }else{
                            Toast.makeText(ReviewActivity.this,"暂无装置数据",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ReviewActivity.this,"请先选择部门",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_child_area:
                //子区域
                if(selectEntity!=null){
                    if(!IsNullOrEmpty.isEmpty(selectEntity.getEid())){
                        List<Area>areaList=areaDao.queryBuilder().where(AreaDao.Properties.Did.eq(selectEntity.getEid())).build().list();
                        if(areaList!=null&&areaList.size()>0){
                            listDialog.AreaDialog(areaList);
                            UpdateButtonState();
                        }else{
                            Toast.makeText(ReviewActivity.this,"暂无子区域数据",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ReviewActivity.this,"请先选择装置",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_see:
                //查看
                Intent intent=new Intent(ReviewActivity.this,ImageListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_download:
                //下载
                break;
            default:
                break;
        }
    }

    private void UpdateButtonState(){
        if(selectEntity!=null){
            //厂区
            if(!IsNullOrEmpty.isEmpty(selectEntity.getFname())){
                btFactory.setText(selectEntity.getFname());
            }else{
                btFactory.setText(getString(R.string.please_select));
            }
            //部门
            if(!IsNullOrEmpty.isEmpty(selectEntity.getDname())){
                btDepartment.setText(selectEntity.getDname());
            }else{
                btDepartment.setText(getString(R.string.please_select));
                if(!IsNullOrEmpty.isEmpty(selectEntity.getFname())){
                    Toast.makeText(ReviewActivity.this,"请先选择厂区",Toast.LENGTH_SHORT).show();
                }
            }
            //装置
            if(!IsNullOrEmpty.isEmpty(selectEntity.getEname())){
                btDevice.setText(selectEntity.getEname());
            }else{
                btDevice.setText(getString(R.string.please_select));
                if(!IsNullOrEmpty.isEmpty(selectEntity.getEname())){
                    Toast.makeText(ReviewActivity.this,"请先选择部门",Toast.LENGTH_SHORT).show();
                }
            }
            //子区域
            if(!IsNullOrEmpty.isEmpty(selectEntity.getAname())){
                btChildArea.setText(selectEntity.getAname());
            }else{
                btChildArea.setText(getString(R.string.please_select));
                if(!IsNullOrEmpty.isEmpty(selectEntity.getAname())){
                    Toast.makeText(ReviewActivity.this,"请先选择装置",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
