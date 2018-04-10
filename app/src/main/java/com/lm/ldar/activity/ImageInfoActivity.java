package com.lm.ldar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.ImageInfoEntity;
import com.lm.ldar.entity.SelectEntity;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/15.
 * 图片初始化
 */

public class ImageInfoActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_floor)
    TextView tvFloor;
    @BindView(R.id.et_floor)
    EditText etFloor;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.et_distance)
    EditText etDistance;
    @BindView(R.id.bt_loc_A)
    Button btLocA;
    @BindView(R.id.bt_loc_B)
    Button btLocB;
    @BindView(R.id.ll_loc_button)
    LinearLayout llLocButton;
    @BindView(R.id.et_location)
    EditText etLocation;
    @BindView(R.id.bt_direction)
    Button btDirection;
    @BindView(R.id.bt_height_zero)
    Button btHeightZero;
    @BindView(R.id.bt_height_one)
    Button btHeightOne;
    @BindView(R.id.bt_height_two)
    Button btHeightTwo;
    @BindView(R.id.ll_height_button)
    LinearLayout llHeightButton;
    @BindView(R.id.tv_height_h)
    TextView tvHeightH;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_equip)
    EditText etEquip;
    @BindView(R.id.et_material)
    EditText etMaterial;
    @BindView(R.id.et_pid)
    EditText etPid;
    @BindView(R.id.bt_last_step)
    Button btLastStep;
    @BindView(R.id.bt_next_step)
    Button btNextStep;
    private String image_name;
    private String image_path;
    private String vid;//版本id
    private SelectEntity selectEntity;

    public static boolean isFinish=false;//是否销毁此页面

    /**
     * 本页面信息
     * @param savedInstanceState
     */
    private String location;//位置
    private String direction;//方向
    private String equip;//设备信息
    private String material;//物质信息
    private String pid;//PID图号
    private String distance;//距离
    private String floor;//楼层
    private String height;//高度

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_info);
        ButterKnife.bind(this);
        init();
        initListener();
    }

    private void init() {
        image_name = getIntent().getStringExtra("image_name");
        image_path = getIntent().getStringExtra("image_path");
        vid=getIntent().getStringExtra("vid");
        selectEntity= (SelectEntity) getIntent().getSerializableExtra("select");
        initTitleBar(getString(R.string.image_info));
        if(Global.imageInfoEntity!=null){
            ImageInfoEntity entity=Global.imageInfoEntity;
            if(!IsNullOrEmpty.isEmpty(entity.getFloor())){
                etFloor.setText(entity.getFloor());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getDistance())){
                etDistance.setText(entity.getDistance());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getLocation())){
                etLocation.setText(entity.getLocation());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getDirection())){
                btDirection.setText(entity.getDirection());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getHeight())){
                etHeight.setText(entity.getHeight());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getEquip())){
                etEquip.setText(entity.getEquip());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getMaterial())){
                etMaterial.setText(entity.getMaterial());
            }
            if(!IsNullOrEmpty.isEmpty(entity.getPid())){
                etPid.setText(entity.getPid());
            }

        }
    }

    private void initListener() {
        btLastStep.setOnClickListener(this);
        btNextStep.setOnClickListener(this);
        btDirection.setOnClickListener(this);
        btHeightZero.setOnClickListener(this);
        btHeightOne.setOnClickListener(this);
        btHeightTwo.setOnClickListener(this);
        btLocA.setOnClickListener(this);
        btLocB.setOnClickListener(this);

        Util.EditDigitalListener(etFloor);
        Util.EditDigitalListener(etHeight);
        Util.EditDigitalListener(etDistance);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next_step:
                floor=etFloor.getText().toString();
                distance=etDistance.getText().toString();
                location=etLocation.getText().toString();
                direction=etDistance.getText().toString();
                height=etHeight.getText().toString();
                equip=etEquip.getText().toString();
                material=etMaterial.getText().toString();
                pid=etPid.getText().toString();

                if(isNotNull(floor,"楼层")){
                    if(isNotNull(direction,"方向")){
                        if(isNotNull(height,"高度")){
                            if(isNotNull(equip,"设备信息")){
                                if(isNotNull(material,"物质信息")){
                                    if(isNotNull(pid,"PID图号")){
                                        Global.imageInfoEntity=new ImageInfoEntity();
                                        Global.imageInfoEntity.setFloor(floor);
                                        Global.imageInfoEntity.setDistance(distance);
                                        Global.imageInfoEntity.setLocation(location);
                                        Global.imageInfoEntity.setDirection(direction);
                                        Global.imageInfoEntity.setHeight(height);
                                        Global.imageInfoEntity.setEquip(equip);
                                        Global.imageInfoEntity.setMaterial(material);
                                        Global.imageInfoEntity.setPid(pid);
                                        Intent intent = new Intent(ImageInfoActivity.this, DrawPointActivity.class);
                                        intent.putExtra("image_name", image_name);
                                        intent.putExtra("image_path", image_path);
                                        intent.putExtra("image_info",Global.imageInfoEntity);
                                        intent.putExtra("vid",vid);
                                        intent.putExtra("select",selectEntity);
                                        startActivity(intent);
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case R.id.bt_last_step:
                InputtingActivity.isCamera=false;
                finish();
                break;
            case R.id.bt_direction:
                DirectionDialog(ImageInfoActivity.this);
                break;
            case R.id.bt_height_zero:
                etHeight.setText("0");
                height="0";
                break;
            case R.id.bt_height_one:
                etHeight.setText("1");
                height="1";
                break;
            case R.id.bt_height_two:
                etHeight.setText("2");
                height="2";
                break;
            case R.id.bt_loc_A:
                etLocation.setText("A");
                location="A";
                break;
            case R.id.bt_loc_B:
                etLocation.setText("B");
                location="B";
                break;
            default:
                break;
        }
    }

    /**
     * 不能为空
     * @param name
     * @param str
     * @return
     */
    private boolean isNotNull(String name,String str){
        boolean isNull=true;
        if(IsNullOrEmpty.isEmpty(name)){
            isNull=false;
            Toast.makeText(ImageInfoActivity.this,str+"不能为空",Toast.LENGTH_SHORT).show();
        }
        return isNull;
    }

    /**
     * 选择方向
     */
    public void DirectionDialog(final Activity activity) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.view_direction);
        //设置对话框宽高
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();//获取屏幕宽度
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (width * 0.8); // 宽度设置为屏幕的0.8
        p.height = (int) (width * 0.8); // 高度设置为屏幕的0.8
        window.setAttributes(p);
        window.findViewById(R.id.bt_N).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.N);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_S).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.S);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_W).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.W);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_E).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.E);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_NNE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.NNE);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_NE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.NE);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_ENE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.ENE);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_ESE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.ESE);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_SE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.SE);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_SSE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.SSE);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_SSW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.SSW);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_SW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.SW);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_WSW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.WSW);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_WNW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.WNW);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_NW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.NW);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.bt_NNW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction = activity.getString(R.string.NNW);
                btDirection.setText(direction);
                alertDialog.dismiss();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFinish){
            finish();
            isFinish=false;
        }
    }
}
