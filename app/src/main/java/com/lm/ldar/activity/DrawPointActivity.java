package com.lm.ldar.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.entity.ImageInfoEntity;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.ImageUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.Util;
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
    @BindView(R.id.bt_last_step)
    Button btLastStep;
    @BindView(R.id.rb_F)
    RadioButton rbF;
    @BindView(R.id.rb_V)
    RadioButton rbV;
    @BindView(R.id.rb_C)
    RadioButton rbC;
    @BindView(R.id.rb_O)
    RadioButton rbO;
    @BindView(R.id.rb_Y)
    RadioButton rbY;
    @BindView(R.id.rb_A)
    RadioButton rbA;
    @BindView(R.id.rb_R)
    RadioButton rbR;
    @BindView(R.id.rb_P)
    RadioButton rbP;
    @BindView(R.id.rb_S)
    RadioButton rbS;
    @BindView(R.id.rb_Q)
    RadioButton rbQ;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.iv_arrow_more)
    ImageView ivArrowMore;
    @BindView(R.id.rg_tag)
    RadioGroup rgTag;

    private String image_name;
    private String image_path;

    private ImageInfoEntity entity;

    private boolean isOpen = false;//是否打开,默认关闭

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawpoint);
        ButterKnife.bind(this);
        image_name = getIntent().getStringExtra("image_name");
        image_path = getIntent().getStringExtra("image_path");
        entity = (ImageInfoEntity) getIntent().getSerializableExtra("image_info");
        initListener();
        if (!IsNullOrEmpty.isEmpty(image_path) && !IsNullOrEmpty.isEmpty(image_name)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(image_path + image_name + ".jpg", options);
            Drawable drawable = new BitmapDrawable(bm);
            tagview.setBackground(drawable);
        } else {
            Toast.makeText(DrawPointActivity.this, getString(R.string.not_find_image), Toast.LENGTH_SHORT).show();
        }

        /**
         * RadioGroup点击事件
         */
        rgTag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                F(法兰)、V（阀门）、C(连接件)、O（开口）四个组件类型，剩余六个Y（压缩机）、A（搅拌器）、R（泄压装置）、P（泵）、S（采样连接系统）、Q（其他）
                switch (checkedId){
                    case R.id.rb_F:
                        //法兰
                        tagview.ChangeTag("F");
                        break;
                    case R.id.rb_V:
                        //阀门
                        tagview.ChangeTag("V");
                        break;
                    case R.id.rb_C:
                        //连接件
                        tagview.ChangeTag("C");
                        break;
                    case R.id.rb_O:
                        //开口
                        tagview.ChangeTag("O");
                        break;
                    case R.id.rb_Y:
                        //压缩机
                        tagview.ChangeTag("Y");
                        break;
                    case R.id.rb_A:
                        //搅拌器
                        tagview.ChangeTag("A");
                        break;
                    case R.id.rb_R:
                        //泄压装置
                        tagview.ChangeTag("R");
                        break;
                    case R.id.rb_P:
                        //泵
                        tagview.ChangeTag("P");
                        break;
                    case R.id.rb_S:
                        //采样连接系统
                        tagview.ChangeTag("S");
                        break;
                    case R.id.rb_Q:
                        //其他
                        tagview.ChangeTag("Q");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initListener() {
        btClear.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btLastStep.setOnClickListener(this);
        llMore.setOnClickListener(this);
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
            ImageUtil.saveBitmap(DrawPointActivity.this, bitmap, image_path, name);
        }
        if(entity!=null){
            Picture picture=new Picture();
            picture.setNumber("");
            picture.setStatus("");
            picture.setElementname("");
            picture.setName(image_path + image_name + ".jpg");
            picture.setCreatetime(Util.getCurrentTime());
            picture.setDeviceinfo(entity.getEquip());
            picture.setMaterial(entity.getMaterial());
            picture.setPosition(getPosition(entity));
            picture.setDid(7);
            picture.setAid(13);
            picture.setEid(5);
            picture.setPidnumber(entity.getPid());
            picture.setPvid(2);
            picture.setSketch(image_path+"c_"+image_name+".jpg");
            DaoUtil.addPicture(pictureDao,picture);
        }
        ImageInfoActivity.isFinish=true;
        InputtingActivity.isCamera=true;
        dialog.dismiss();
        finish();
    }

    /**
     * 拼接Position
     * @param imageInfoEntity
     * @return
     */
    private String getPosition(ImageInfoEntity imageInfoEntity){
        String position="";
        if(!IsNullOrEmpty.isEmpty(imageInfoEntity.getFloor())){
            position=position+imageInfoEntity.getFloor();
        }
        if(!IsNullOrEmpty.isEmpty(imageInfoEntity.getDistance())){
            position=position+imageInfoEntity.getDistance();
        }
        if(!IsNullOrEmpty.isEmpty(imageInfoEntity.getLocation())){
            position=position+imageInfoEntity.getLocation();
        }
        if(!IsNullOrEmpty.isEmpty(imageInfoEntity.getDirection())){
            position=position+imageInfoEntity.getDirection();
        }
        if(!IsNullOrEmpty.isEmpty(imageInfoEntity.getHeight())){
            position=position+imageInfoEntity.getHeight();
        }
        return position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_clear:
                tagview.initTag();
                tagview.removeAllViews();
                break;
            case R.id.bt_save:
                dialog.show();
                saveView(tagview, "c_" + image_name + ".jpg");
                break;
            case R.id.bt_last_step:
                ImageInfoActivity.isFinish=false;
                finish();
                break;
            case R.id.ll_more:
                clickMore();
                break;
            default:
                break;
        }
    }

    /**
     * 点击更多
     */
    private void clickMore() {
//        Y（压缩机）、A（搅拌器）、R（泄压装置）、P（泵）、S（采样连接系统）、Q（其他）
        if (isOpen) {
            isOpen = false;
            rbY.setVisibility(View.GONE);
            rbA.setVisibility(View.GONE);
            rbR.setVisibility(View.GONE);
            rbP.setVisibility(View.GONE);
            rbS.setVisibility(View.GONE);
            rbQ.setVisibility(View.GONE);
            ivArrowMore.setImageResource(R.drawable.arrow_more_down);
        } else {
            isOpen = true;
            rbY.setVisibility(View.VISIBLE);
            rbA.setVisibility(View.VISIBLE);
            rbR.setVisibility(View.VISIBLE);
            rbP.setVisibility(View.VISIBLE);
            rbS.setVisibility(View.VISIBLE);
            rbQ.setVisibility(View.VISIBLE);
            ivArrowMore.setImageResource(R.drawable.arrow_more_up);
        }
    }


}
