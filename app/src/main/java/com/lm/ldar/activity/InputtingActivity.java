package com.lm.ldar.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;
import com.lm.ldar.util.IsNullOrEmpty;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 * 建档
 */

public class InputtingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_start_inputting)
    Button btStartInputting;
    /**
     * 相机拍照的返回码
     */
    public final static int STAKE_PHOTO_RESULT_CODE = 200;
    @BindView(R.id.tv_image_path)
    TextView tvImagePath;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputting);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        btStartInputting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_inputting:
//                takePhoto();
                startActivity(new Intent(InputtingActivity.this,DrawPointActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            String imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DIR_NAME;
            File f = new File(imageFilePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
            //根据当前时间生成图片的名称
            String timestamp = "/" + formatter.format(new Date()) + ".jpg";
            File imageFile = new File(imageFilePath + timestamp);// 通过路径创建保存文件
            Global.IMAGE_PATH = imageFilePath + timestamp;
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
            tvImagePath.setText(Global.IMAGE_PATH);
            if(Global.IMAGE_PATH!=null){
                startActivity(new Intent(InputtingActivity.this,DrawPointActivity.class));
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inSampleSize = 2;
//                Bitmap bm = BitmapFactory.decodeFile(Global.IMAGE_PATH, options);
//                ivImage.setImageBitmap(bm);
            }

        }
    }
}
