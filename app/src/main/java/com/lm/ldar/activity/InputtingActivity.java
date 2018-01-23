package com.lm.ldar.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.example.network.framework.FailCallback;
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
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.SelectEntity;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.ListDialog;
import com.lm.ldar.view.MyAlertDialog;
import com.lm.ldar.view.NoScrollListView;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 * 建档
 */

public class InputtingActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 相机拍照的返回码
     */
    public final static int STAKE_PHOTO_RESULT_CODE = 200;
    @BindView(R.id.bt_inputting)
    Button btInputting;
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
    @BindView(R.id.bt_see)
    Button btSee;
    @BindView(R.id.bt_upload)
    Button btUpload;

    private String image_name;//图片名字
    private String image_path;//文件夹路径

    public static boolean isCamera=false;

    private String vid;//版本id
    private SelectEntity selectEntity;
    private ListDialog listDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputting);
        ButterKnife.bind(this);
        init();
        initListener();
    }
    private void init(){
        selectEntity=new SelectEntity();
        listDialog=new ListDialog(InputtingActivity.this,selectEntity,daoSession);
    }

    private void initListener() {
        btInputting.setOnClickListener(this);
        btVersion.setOnClickListener(this);
        btChildArea.setOnClickListener(this);
        btDepartment.setOnClickListener(this);
        btDevice.setOnClickListener(this);
        btFactory.setOnClickListener(this);
        btUpload.setOnClickListener(this);
        btSee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_inputting:
                //建档
                takePhoto();
                break;
            case R.id.bt_version:
                //版本
                List<Pictureversion>versionList=pictureversionDao.queryBuilder().where(PictureversionDao.Properties.Eid.eq(epId)).build().list();
                if(versionList!=null&&versionList.size()>0){
                   vid=listDialog.VersionDialog(versionList,btVersion);
                }else{
                    Toast.makeText(InputtingActivity.this,"暂无版本数据",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_factory:
                //厂区
                List<Factory>factoryList=factoryDao.queryBuilder().where(FactoryDao.Properties.Eid.eq(epId)).build().list();
                if(factoryList!=null&&factoryList.size()>0){
                    listDialog.FactoryDialog(factoryList);
                    UpdateButtonState();
                }else{
                    Toast.makeText(InputtingActivity.this,"暂无厂区数据",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(InputtingActivity.this,"暂无部门数据",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(InputtingActivity.this,"请先选择厂区",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(InputtingActivity.this,"暂无装置数据",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(InputtingActivity.this,"请先选择部门",Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(InputtingActivity.this,"暂无子区域数据",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(InputtingActivity.this,"请先选择装置",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_see:
                //查看
                Intent intent=new Intent(InputtingActivity.this,ImageListActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_upload:
                //上传
                List<Picture>pictureList=DaoUtil.getPictureList(pictureDao,epId);
                if(pictureList!=null&&pictureList.size()>0){
                    for(Picture picture:pictureList){
                        File fileName=new File(picture.getName());
                        File fileSketch=new File(picture.getSketch());
                        HashMap<String,String> parmas=new HashMap<>();
                        parmas.put("name",picture.getName());
                        parmas.put("createtime",picture.getCreatetime());
                        parmas.put("deviceinfo",picture.getDeviceinfo());
                        parmas.put("material",picture.getMaterial());
                        parmas.put("position",picture.getPosition());
                        parmas.put("did",picture.getDid()+"");
                        parmas.put("aid",picture.getAid()+"");
                        parmas.put("eid",picture.getEid()+"");
                        parmas.put("pidnumber",picture.getPidnumber());
                        parmas.put("pvid",picture.getPidnumber());
                        parmas.put("sketch",picture.getSketch());
                        uploadFiles(urlManager.uploadUrl(),parmas,fileName,fileSketch);
                    }
                }
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
                    Toast.makeText(InputtingActivity.this,"请先选择厂区",Toast.LENGTH_SHORT).show();
                }
            }
            //装置
            if(!IsNullOrEmpty.isEmpty(selectEntity.getEname())){
                btDevice.setText(selectEntity.getEname());
            }else{
                btDevice.setText(getString(R.string.please_select));
                if(!IsNullOrEmpty.isEmpty(selectEntity.getEname())){
                    Toast.makeText(InputtingActivity.this,"请先选择部门",Toast.LENGTH_SHORT).show();
                }
            }
            //子区域
            if(!IsNullOrEmpty.isEmpty(selectEntity.getAname())){
                btChildArea.setText(selectEntity.getAname());
            }else{
                btChildArea.setText(getString(R.string.please_select));
                if(!IsNullOrEmpty.isEmpty(selectEntity.getAname())){
                    Toast.makeText(InputtingActivity.this,"请先选择装置",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 上传一组图片
     * @param url
     * @param map
     * @param
     */

    private void uploadFiles(String url, Map<String, String> map, File fileName,File fileSketch) {
        dialog.show();
        OkhttpFactory.getInstance().uploadImages(InputtingActivity.this, url, map, fileName,fileSketch, new SuccessfulCallback() {
            @Override
            public void success(String str) {
                dialog.dismiss();
                if (IsNullOrEmpty.isEmpty(str)) {
                    return;
                }

                Log.i("返回值：-------------", str);

            }


            @Override
            public void success(InputStream ism, long conentLength) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, new FailCallback() {
            @Override
            public void fail(String str) {
                dialog.dismiss();
                Log.i("上传失败：", str);
                MyAlertDialog.showDialog(InputtingActivity.this, str);
            }
        }, "file_name","file_sketch");
    }

    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DIR_NAME + "/";

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
            //根据当前时间生成图片的名称
            String timestamp = formatter.format(new Date()) + ".jpg";

            Enterprise epEntity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(epId)).unique();
            if (epEntity != null) {
                image_path = imageFilePath + epEntity.getEcode() + "/";
            } else {
                image_path = imageFilePath;
            }
            File f = new File(image_path);
            if (!f.exists()) {
                f.mkdirs();
            }

            File imageFile = new File(image_path + timestamp);// 通过路径创建保存文件

            image_name = formatter.format(new Date());
            Uri imageFileUri = Uri.fromFile(imageFile);// 获取文件的Uri
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
            startActivityForResult(intent, STAKE_PHOTO_RESULT_CODE);
        } else {
            Toast.makeText(this, getString(R.string.nosdcard), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(isCamera){
            takePhoto();
            isCamera=false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STAKE_PHOTO_RESULT_CODE && resultCode == RESULT_OK) {
            if (Global.IMAGE_PATH != null) {
                Intent intent = new Intent(InputtingActivity.this, ImageInfoActivity.class);
                intent.putExtra("image_name", image_name);
                intent.putExtra("image_path", image_path);
                startActivity(intent);
            }

        }
    }
}
