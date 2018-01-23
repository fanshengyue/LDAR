package com.lm.ldar.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

import com.lm.ldar.R;
import com.lm.ldar.activity.InputtingActivity;
import com.lm.ldar.adapter.AreaAdapter;
import com.lm.ldar.adapter.DepartmentAdapter;
import com.lm.ldar.adapter.DeviceAdapter;
import com.lm.ldar.adapter.FactoryAdapter;
import com.lm.ldar.adapter.PicVersionAdapter;
import com.lm.ldar.adapter.WorkPlanAdapter;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.SelectEntity;
import com.lm.ldar.entity.Workplan;
import com.lm.ldar.view.NoScrollListView;

import java.util.List;

/**
 * Created by fanshengyue on 2018/1/23.
 */

public class ListDialog {
    private Activity activity;
    private String vid;//版本id
    private String wpid;//检测计划id
    private SelectEntity selectEntity;
    private int width;
    private DaoSession daoSession;
    public ListDialog(Activity activity, SelectEntity selectEntity, DaoSession daoSession){
        this.activity=activity;
        this.selectEntity=selectEntity;
        this.daoSession=daoSession;
        //设置对话框宽高
        WindowManager wm = activity.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
    }

    /**
     * 版本
     * @param data
     * @param button
     * @return
     */
    public String VersionDialog(final List<Pictureversion> data, final Button button){
        if(data!=null&&data.size()>0){
            vid=data.get(0).getId()+"";
            PicVersionAdapter adapter=new PicVersionAdapter(activity,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    vid=data.get(position).getId()+"";
                    button.setText(data.get(position).getName());
                    alertDialog.dismiss();
                }
            });
        }else{
            button.setText(activity.getString(R.string.please_select));
        }
        return vid;
    }


    /**
     * 检测计划
     * @param data
     * @param button
     * @return
     */
    public String WorkPlanDialog(final List<Workplan> data, final Button button){
        if(data!=null&&data.size()>0){
            wpid=data.get(0).getId()+"";
            WorkPlanAdapter adapter=new WorkPlanAdapter(activity,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    wpid=data.get(position).getId()+"";
                    button.setText(data.get(position).getName());
                    alertDialog.dismiss();
                }
            });
        }else{
            button.setText(activity.getString(R.string.please_select));
        }
        return wpid;
    }

    /**
     * 厂区
     */
    public void FactoryDialog(final List<Factory> data){
        if(data!=null&&data.size()>0){
            selectEntity.setFid(data.get(0).getId()+"");
            selectEntity.setFname(data.get(0).getName());
            FactoryAdapter adapter=new FactoryAdapter(activity,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    long fid=data.get(position).getId();
                    selectEntity.setFid(fid+"");
                    selectEntity.setFname(data.get(position).getName());
                    DepartmentDao departmentDao=daoSession.getDepartmentDao();
                    List<Department>departmentList=departmentDao.queryBuilder().where(DepartmentDao.Properties.Fid.eq(fid)).build().list();
                    if(departmentList!=null&& departmentList.size()>0){
                        selectEntity.setDid(departmentList.get(0).getId()+"");
                        selectEntity.setDname(departmentList.get(0).getName());
                    }
                    alertDialog.dismiss();
                }
            });
        }
    }

    /**
     * 部门
     */
    public void DepartmentDialog(final List<Department> data){
        if(data!=null&&data.size()>0){
            selectEntity.setDid(data.get(0).getId()+"");
            selectEntity.setDname(data.get(0).getName());
            DepartmentAdapter adapter=new DepartmentAdapter(activity,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    long did=data.get(position).getId();
                    selectEntity.setDid(did+"");
                    selectEntity.setDname(data.get(position).getName());
                    DeviceDao deviceDao=daoSession.getDeviceDao();
                    List<Device>deviceList=deviceDao.queryBuilder().where(DeviceDao.Properties.Did.eq(did)).build().list();
                    if(deviceList!=null&& deviceList.size()>0){
                        selectEntity.setEid(deviceList.get(0).getId()+"");
                        selectEntity.setEname(deviceList.get(0).getName());
                    }
                    alertDialog.dismiss();
                }
            });
        }
    }


    /**
     * 装置
     */
    public void DeviceDialog(final List<Device> data){
        if(data!=null&&data.size()>0){
            selectEntity.setEid(data.get(0).getId()+"");
            selectEntity.setEname(data.get(0).getName());
            DeviceAdapter adapter=new DeviceAdapter(activity,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    long eid=data.get(position).getId();
                    selectEntity.setEid(eid+"");
                    selectEntity.setEname(data.get(position).getName());
                    AreaDao areaDao=daoSession.getAreaDao();
                    List<Area>areaList=areaDao.queryBuilder().where(AreaDao.Properties.Eid.eq(eid)).build().list();
                    if(areaList!=null&& areaList.size()>0){
                        selectEntity.setEid(areaList.get(0).getId()+"");
                        selectEntity.setEname(areaList.get(0).getName());
                    }
                    alertDialog.dismiss();
                }
            });
        }
    }

    /**
     * 子区域
     */
    public void AreaDialog(final List<Area> data){
        if(data!=null&&data.size()>0){
            selectEntity.setAid(data.get(0).getId()+"");
            selectEntity.setAname(data.get(0).getName());
            AreaAdapter adapter=new AreaAdapter(activity,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectEntity.setAid(data.get(position).getId()+"");
                    selectEntity.setAname(data.get(position).getName());
                    alertDialog.dismiss();
                }
            });
        }
    }
}
