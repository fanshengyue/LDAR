package com.lm.ldar.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    @BindView(R.id.bt_change)
    Button btChange;
    private String image_name;
    private String image_path;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawpoint);
        ButterKnife.bind(this);
        image_name=getIntent().getStringExtra("image_name");
        image_path=getIntent().getStringExtra("image_path");
        initListener();
        if (!IsNullOrEmpty.isEmpty(image_path)&&!IsNullOrEmpty.isEmpty(image_name)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(image_path+image_name+".jpg", options);
            Drawable drawable = new BitmapDrawable(bm);
            tagview.setBackground(drawable);
        }else{
            Toast.makeText(DrawPointActivity.this,getString(R.string.not_find_image),Toast.LENGTH_SHORT).show();
        }
    }

    private void initListener() {
        btClear.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btChange.setOnClickListener(this);
    }

    /**
     * 保存画布
     *
     * @param view
     * @param name
     */
    public void saveView(View view, String name) {
        Bitmap bitmap = ImageUtil.convertViewToBitmap(view);
        if (bitmap != null) {
            ImageUtil.saveBitmap(DrawPointActivity.this,bitmap, image_path, name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_clear:
                tagview.initTag();
                tagview.removeAllViews();
                break;
            case R.id.bt_save:
                saveView(tagview, "c_"+image_name+".jpg");
                break;
            case R.id.bt_change:
                tagview.ChangeTag("V");
                break;
            default:
                break;
        }
    }


}
