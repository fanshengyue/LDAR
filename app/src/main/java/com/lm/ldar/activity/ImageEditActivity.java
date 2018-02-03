package com.lm.ldar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/2/3.
 */

public class ImageEditActivity extends BaseActivity implements View.OnClickListener{
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
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.tv_position)
    TextView tvPosition;


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

    private PictureDownload picture;
    private int type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);
        ButterKnife.bind(this);
        initTitleBar("编辑图片");
        init();
        initListener();

    }

    private void init() {
        picture = (PictureDownload) getIntent().getSerializableExtra("picture");
        type=getIntent().getIntExtra("type",0);
        if (picture != null) {
            if (!IsNullOrEmpty.isEmpty(picture.getMaterial())) {
                etMaterial.setText(picture.getMaterial());
            }
            if (!IsNullOrEmpty.isEmpty(picture.getDeviceinfo())) {
                etEquip.setText(picture.getDeviceinfo());
            }
            if (!IsNullOrEmpty.isEmpty(picture.getPidnumber())) {
                etPid.setText(picture.getPidnumber());
            }
            if(!IsNullOrEmpty.isEmpty(picture.getPosition())){
                tvPosition.setText(picture.getPosition());
            }

        }
    }

    private void initListener() {
        btSave.setOnClickListener(this);
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
            Toast.makeText(ImageEditActivity.this,str+"不能为空",Toast.LENGTH_SHORT).show();
        }
        return isNull;
    }

    /**
     * 保存并更新数据库
     */
    private void SaveAndUpdateSql(){
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
                                picture.setPosition(getPosition(floor,distance,location,direction,height));
                                picture.setDeviceinfo(equip);
                                picture.setMaterial(material);
                                picture.setPidnumber(pid);
                                if(type==0){
                                    Picture pic=new Picture();
                                    pic.setId(picture.getId());
                                    pic.setNumber(picture.getNumber());
                                    pic.setName(picture.getName());
                                    pic.setStatus(picture.getStatus());
                                    pic.setCreatetime(picture.getCreatetime());
                                    pic.setDeviceinfo(picture.getDeviceinfo());
                                    pic.setMaterial(picture.getMaterial());
                                    pic.setPosition(picture.getPosition());
                                    pic.setDid(picture.getDid());
                                    pic.setAid(picture.getAid());
                                    pic.setEid(picture.getEid());
                                    pic.setElementname(picture.getElementname());
                                    pic.setPidnumber(picture.getPidnumber());
                                    pic.setPvid(picture.getPvid());
                                    pic.setSketch(picture.getSketch());
                                    pic.setLatitude(picture.getLatitude());
                                    pic.setLongitude(picture.getLongitude());
                                    DaoUtil.updatePicture(pictureDao,pic);
                                }
                                else{
                                    DaoUtil.updatePicDownload(pictureDownloadDao,picture);
                                }
                                Toast.makeText(ImageEditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * 拼接Position
     * @param
     * @return
     */
    private String getPosition(String floor,String distance,String location,String direction,String height){
        String position="";
        if(!IsNullOrEmpty.isEmpty(floor)){
            position=position+floor+"F";
        }
        if(!IsNullOrEmpty.isEmpty(distance)){
            position=position+distance+"M";
        }
        if(!IsNullOrEmpty.isEmpty(location)){
            position=position+location;
        }
        if(!IsNullOrEmpty.isEmpty(direction)){
            position=position+direction;
        }
        if(!IsNullOrEmpty.isEmpty(height)){
            position=position+height+"H";
        }
        return position;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save:
                SaveAndUpdateSql();
                break;
            case R.id.bt_direction:
                DirectionDialog(ImageEditActivity.this);
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
}
