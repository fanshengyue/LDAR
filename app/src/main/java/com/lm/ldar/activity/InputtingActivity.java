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
import android.widget.TextView;
import android.widget.Toast;

import com.example.network.framework.FailCallback;
import com.example.network.framework.SuccessfulCallback;
import com.example.network.workUtils.OkhttpFactory;
import com.lm.ldar.R;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Global;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.view.MyAlertDialog;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    @BindView(R.id.bt_test)
    Button btTest;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputting);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        btTest.setOnClickListener(this);
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
            case R.id.bt_upload:
                //上传
                break;
            case R.id.bt_test:
                HashMap<String, Object> params = new HashMap<>();
                params.put("name", "123456");
                File file = new File(Global.IMAGE_PATH_TEST);
                if (file != null) {
                    uploadFile("http://192.168.1.14:8080/LDAR2.0/app-insertpicture.action", params, file);
                }
                break;
            default:
                break;
        }
    }

    private void uploadFile(String url, Map<String, Object> map, File file) {
        dialog.show();
        OkhttpFactory.getInstance().post_file(InputtingActivity.this, url, map, file, new SuccessfulCallback() {
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
        }, "image_file");
    }

    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DIR_NAME + "/";
            File f = new File(imageFilePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
            //根据当前时间生成图片的名称
            String timestamp = formatter.format(new Date()) + ".jpg";
            File imageFile = new File(imageFilePath + timestamp);// 通过路径创建保存文件
            Enterprise epEntity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(epId)).unique();
            if (epEntity != null) {
                image_path = imageFilePath + epEntity.getEcode() + "/";
            } else {
                image_path = imageFilePath;
            }
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
