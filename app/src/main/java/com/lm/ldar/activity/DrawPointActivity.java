package com.lm.ldar.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;
import com.lm.ldar.util.ImageUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.view.PictureTagLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawPointActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tagview)
    PictureTagLayout tagview;
    @BindView(R.id.bt_clear)
    Button btClear;
    @BindView(R.id.bt_save)
    Button btSave;

    private ImageUtil imageUtil=new ImageUtil();
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawpoint);
        ButterKnife.bind(this);
        initListener();
        if (!IsNullOrEmpty.isEmpty(Global.IMAGE_PATH)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(Global.IMAGE_PATH, options);
            Drawable drawable = new BitmapDrawable(bm);
            tagview.setBackground(drawable);
        }
    }

    private void initListener() {
        btClear.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_clear:
                tagview.removeAllViews();
                break;
            case R.id.bt_save:
                break;
            default:
                break;
        }
    }


}
