package com.lm.ldar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.SharePreferenceKey;
import com.lm.ldar.adapter.AreaAdapter;
import com.lm.ldar.bluetoothssp.BluetoothClientService;
import com.lm.ldar.bluetoothssp.BluetoothTools;
import com.lm.ldar.bluetoothssp.ClsUtils;
import com.lm.ldar.bluetoothssp.MobileDeviceAdapter;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.BlueToothMax;
import com.lm.ldar.entity.CheckInfo;
import com.lm.ldar.entity.Element;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.MobileDevice;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.view.NoScrollListView;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/3/14.
 */

public class ImageBluetoothActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_imgname)
    TextView tvImgname;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_imgnumber)
    TextView tvImgnumber;
    @BindView(R.id.tv_lastnum)
    TextView tvLastnum;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_max)
    EditText etMax;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_stop)
    Button btStop;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.tv_state)
    TextView tvState;
    private int type;
    private ArrayList<PictureDownload> pictures;
    private int pagerPosition;
    //倒计时
    private CountDownTimer timer;
    private SharedPreferences sharedPreferences;
    private int choiceIndex, frequency;
    private boolean isDetect = false;//是否是正在检测中
    private PictureDownload picture;
    private List<Element> elementList;

    /**
     * 蓝牙相关
     * @param savedInstanceState
     */
    public String action;
    public MobileDeviceAdapter adapter;


    private int tagIndex=0;
    private List<BlueToothMax> blueToothMaxList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagebluetooth);
        ButterKnife.bind(this);
        initTitleBar("检测");
        initData();
        initListener();
    }

    private void initData() {
        sharedPreferences = getSharedPreferences(SharePreferenceKey.MODEL, Activity.MODE_PRIVATE);
        pagerPosition = getIntent().getIntExtra("position", 0);
        pictures = (ArrayList<PictureDownload>) getIntent().getSerializableExtra("imagelist");
        type = getIntent().getIntExtra("type", 0);

        if (pictures != null && pictures.size() > 0) {
            if(pagerPosition<pictures.size()){
                picture=pictures.get(pagerPosition);
//            tvImgnumber.setText(pictures.get(pagerPosition).getNumber());
//            tvImgname.setText(getName(pictures.get(pagerPosition)));
                String path = "";
                if (type == 0) {
                    path = pictures.get(pagerPosition).getSketch();
                } else if (type == 1) {
                    path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DOWNLOAD_CHECK +
                            pictures.get(pagerPosition).getAid() + "/" + pictures.get(pagerPosition).getElementname();
                } else if (type == 2) {
                    path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DOWNLOAD_REVIEW +
                            pictures.get(pagerPosition).getAid() + "/" + pictures.get(pagerPosition).getElementname();
                }
                File file = new File(path);
                if (file.exists()) {
                    ivImage.setImageURI(Uri.fromFile(file));
                } else {
                    ivImage.setImageDrawable(getResources().getDrawable(R.drawable.default_img));
                }

                elementList= DaoUtil.getElementListByPid(elementDao,picture.getId());
                tagIndex=0;
                blueToothMaxList=new ArrayList<>();

                if(elementList!=null&&elementList.size()>0){
                    //初始存储数据集合
                    for(int i=0;i<elementList.size();i++){
                        BlueToothMax blueToothMax=new BlueToothMax();
                        blueToothMax.setEleid(elementList.get(i).getId());
                        CheckInfo checkInfo=DaoUtil.getCheckInfoByEleid(checkInfoDao,elementList.get(i).getId());
                        if(checkInfo!=null){
                            blueToothMax.setFcvalue(checkInfo.getFcvalue());
                        }else{
                            blueToothMax.setFcvalue("");
                        }
                        blueToothMax.setMax(0);
                        blueToothMaxList.add(blueToothMax);
                    }
                    //组件编号
                    tvImgnumber.setText(elementList.get(tagIndex).getNumber());
                    //上次净检测值
                    if(blueToothMaxList!=null&&blueToothMaxList.size()>0&&tagIndex<blueToothMaxList.size()){
                        tvLastnum.setText(blueToothMaxList.get(tagIndex).getFcvalue());
                    }
                }


                //检测状态初始化
                isDetect=false;
                tvState.setText(getResources().getString(R.string.no_detect));
                tvState.setTextColor(getResources().getColor(R.color.font_text));
                //检测模式
                choiceIndex = sharedPreferences.getInt(SharePreferenceKey.CHOICEINDEX, 0);
                tagIndex=0;
                if (choiceIndex == 0) {
                    //自选模式，自动开启定时检测
                    btStart.setVisibility(View.GONE);
                    btStop.setVisibility(View.GONE);
                    frequency = sharedPreferences.getInt(SharePreferenceKey.FREQUENCY, 10);
                    if(elementList!=null&&elementList.size()>0){
                        TimeDown(frequency);
                    }
                }
                else if(choiceIndex==1){
                    //固定模式
                    btStart.setVisibility(View.VISIBLE);
                    btStop.setVisibility(View.VISIBLE);

                }

            }

        }

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                etNumber.setText(s.toString());
                if (isDetect) {
                    if (IsNullOrEmpty.isEmpty(etMax.getText().toString())) {
                        etMax.setText(s.toString());
                    } else {
                        double number = Double.parseDouble(s.toString());
                        double max = Double.parseDouble(etMax.getText().toString());
                        if (number > max) {
                            etMax.setText(s.toString());
                            blueToothMaxList.get(tagIndex).setMax(max);
                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        //注册广播
        IntentFilter discoveryFilter = new IntentFilter();
        discoveryFilter.addAction(BluetoothTools.ACTION_RECEIVE_DATA);
        registerReceiver(mBluetoothReceiver, discoveryFilter);
        super.onStart();
    }

    private void initListener() {
        llBack.setOnClickListener(this);
        ivImage.setOnClickListener(this);
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, final Intent intent) {
            action = intent.getAction();
            //如果收到数据
            if(BluetoothTools.ACTION_RECEIVE_DATA.equals(action)){
                String recData = ((String)intent.getExtras().get("recData")).trim();
                etNumber.setText(recData);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_image:
                Intent intent = new Intent(this, ImagePagerActivity.class);
                intent.putExtra("imagelist", pictures);
                intent.putExtra("type", type);
                intent.putExtra("position", pagerPosition);
                startActivity(intent);
                break;
            case R.id.bt_start:
                if(tagIndex<elementList.size()){
                    isDetect = true;
                    tvState.setText(getResources().getString(R.string.detecting));
                    tvState.setTextColor(getResources().getColor(R.color.font_text3));
                    tvImgnumber.setText(elementList.get(tagIndex).getNumber());
                    if(blueToothMaxList!=null&&blueToothMaxList.size()>0&&tagIndex<blueToothMaxList.size()){
                        tvLastnum.setText(blueToothMaxList.get(tagIndex).getFcvalue());
                    }
                }else{
                    Toast.makeText(ImageBluetoothActivity.this,"已全部检测完",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_stop:
                if(tagIndex<elementList.size()){
                    isDetect = false;
                    tvState.setText(getResources().getString(R.string.no_detect));
                    tvState.setTextColor(getResources().getColor(R.color.font_text));
                    tagIndex++;
                }
                break;
            case R.id.bt_save:
                //保存，只保存最大值到CheckInfo
                if(blueToothMaxList!=null&&blueToothMaxList.size()>0){
                    for(int i=0;i<blueToothMaxList.size();i++){
                        CheckInfo checkInfo=DaoUtil.getCheckInfoByEleid(checkInfoDao,blueToothMaxList.get(i).getEleid());
                        if(checkInfo!=null){
                            checkInfo.setFcvalue(blueToothMaxList.get(i).getFcvalue());
                            DaoUtil.updateCheckInfo(checkInfoDao,checkInfo);
                        }
                    }
                    Toast.makeText(ImageBluetoothActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 保存数据
     */
    private void SaveData(){

    }

    /**
     * 固定监测模式使用
     * @param time
     */
    private void TimeDown(int time) {
        /** 倒计时60秒，一次1秒 */
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(millisUntilFinished / 1000 + "s");
                isDetect = true;
                tvState.setText(getResources().getString(R.string.detecting));
                tvState.setTextColor(getResources().getColor(R.color.font_text3));
            }

            @Override
            public void onFinish() {
                tvTime.setVisibility(View.GONE);
                isDetect = false;
                tvState.setText(getResources().getString(R.string.no_detect));
                tvState.setTextColor(getResources().getColor(R.color.font_text));
                if(elementList!=null&& elementList.size()>0){
                    if(tagIndex<elementList.size()){
                        tvImgnumber.setText(elementList.get(tagIndex).getNumber());
                        if(blueToothMaxList!=null&&blueToothMaxList.size()>0&&tagIndex<blueToothMaxList.size()){
                            tvLastnum.setText(blueToothMaxList.get(tagIndex).getFcvalue());
                        }
                        TimeDown(frequency);
                    }
                }
                tagIndex++;
            }
        }.start();

    }


    private String getName(PictureDownload picture) {
        String name;
        if (type == 0) {
            name = picture.getPosition() + "_" + picture.getDeviceinfo() + "_" + picture.getMaterial();
        } else {
            name = picture.getElementname();
            if (!IsNullOrEmpty.isEmpty(name)) {
                if (name.endsWith(".jpeg")) {
                    name = name.replace(".jpeg", "");
                }
                if (name.endsWith(".JPEG")) {
                    name = name.replace(".JPEG", "");
                }
                if (name.endsWith(".jpg")) {
                    name = name.replace(".jpg", "");
                }
                if (name.endsWith(".JPG")) {
                    name = name.replace(".JPG", "");
                }
                if (name.endsWith("png")) {
                    name = name.replace(".png", "");
                }
                if (name.endsWith("PNG")) {
                    name = name.replace(".PNG", "");
                }
            }
        }
        return name;
    }


    @Override
    protected void onResume() {
        super.onResume();
        choiceIndex = sharedPreferences.getInt(SharePreferenceKey.CHOICEINDEX, 0);

    }



    @Override
    protected void onDestroy() {
        if(mBluetoothReceiver!=null){
            unregisterReceiver(mBluetoothReceiver);
        }
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }

    }
}
