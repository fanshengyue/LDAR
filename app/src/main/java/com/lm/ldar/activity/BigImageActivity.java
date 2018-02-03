package com.lm.ldar.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.Picture;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by fanshengyue on 2018/1/29.
 */

public class BigImageActivity extends BaseActivity {
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
    @BindView(R.id.photoview)
    PhotoView photoview;

    private Picture picture;
    private int type;
    private String path;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigimage);
        ButterKnife.bind(this);
        initTitleBar("查看大图");
        init();

    }

    private void init() {
        picture = (Picture) getIntent().getSerializableExtra("imageinfo");
        type=getIntent().getIntExtra("type",0);
        if(type==0){
            path=picture.getSketch();
        }
        else if(type==1){
            path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ Global.IMAGE_DOWNLOAD_CHECK+picture.getAid()+"/"+picture.getElementname();
        }
        else if(type==2){
            path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ Global.IMAGE_DOWNLOAD_REVIEW+picture.getAid()+"/"+picture.getElementname();
        }
        if (picture != null) {
            File file = new File(path);
            if (file.exists()) {
                photoview.setImageURI(Uri.fromFile(file));
            } else {
                photoview.setImageDrawable(getResources().getDrawable(R.drawable.default_img));
            }
        }
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("查看位置");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BigImageActivity.this, MapAvtivity.class);
                intent.putExtra("latitude", picture.getLatitude() + "");
                intent.putExtra("longitude", picture.getLongitude() + "");
                startActivity(intent);
            }
        });
    }

}
