package com.lm.ldar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lm.ldar.LMApplication;
import com.lm.ldar.R;
import com.lm.ldar.api.UrlManager;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.CheckInfoDao;
import com.lm.ldar.dao.CtypeDao;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.ElementDao;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.InstrumentDao;
import com.lm.ldar.dao.NamerulesDao;
import com.lm.ldar.dao.PictureDao;
import com.lm.ldar.dao.PictureDownloadDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.dao.RepairDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.dao.WorkplanDao;
import com.lm.ldar.entity.Ctype;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.LoginUserEntity;
import com.lm.ldar.util.LoginUserUtil;
import com.lm.ldar.view.LoadingDialog;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class BaseActivity extends Activity {
    public LoadingDialog loadingDialog;
    public UrlManager urlManager;
    public LoginUserUtil userUtil;
    public DaoSession daoSession;
    public UserDao userDao;
    public EnterpriseDao enterpriseDao;
    public FactoryDao factoryDao;
    public AreaDao areaDao;
    public PictureversionDao pictureversionDao;
    public DeviceDao deviceDao;
    public NamerulesDao namerulesDao;
    public CtypeDao ctypeDao;
    public WorkplanDao workplanDao;
    public DepartmentDao departmentDao;
    public PictureDao pictureDao;
    public PictureDownloadDao pictureDownloadDao;
    public InstrumentDao instrumentDao;
    public RepairDao repairDao;
    public CheckInfoDao checkInfoDao;
    public ElementDao elementDao;

    public Long userId;//用户id
    public Long epId;//企业id
    public String ecode;//企业组织结构代码

    private TextView tv_title, tv_right;
    private ImageView ivRight;
    private LinearLayout ll_back;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initDao();
        initLocation();
    }

    private void initLocation(){
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        //可选，设置定位模式，默认高精度
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置返回经纬度坐标类型，默认gcj02,定位SDK默认输出GCJ02坐标，地图SDK默认输出BD09ll
        option.setCoorType("bd09ll");
        //设置是否使用gps，默认false
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);

    }

    private void init() {
        loadingDialog = new LoadingDialog(BaseActivity.this);
        urlManager = new UrlManager(BaseActivity.this);
        userUtil = new LoginUserUtil(BaseActivity.this);
        epId = userUtil.getEnterPriseId();
        ecode=userUtil.getEcode();
        LoginUserEntity entity = userUtil.getLoginUserInfo();
        if (entity != null) {
            userId = entity.getId();
        }
    }

    private void initDao() {
        daoSession = ((LMApplication) getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        enterpriseDao = daoSession.getEnterpriseDao();
        factoryDao = daoSession.getFactoryDao();
        areaDao = daoSession.getAreaDao();
        pictureversionDao = daoSession.getPictureversionDao();
        deviceDao = daoSession.getDeviceDao();
        namerulesDao = daoSession.getNamerulesDao();
        ctypeDao = daoSession.getCtypeDao();
        workplanDao = daoSession.getWorkplanDao();
        departmentDao = daoSession.getDepartmentDao();
        pictureDao=daoSession.getPictureDao();
        pictureDownloadDao=daoSession.getPictureDownloadDao();
        instrumentDao=daoSession.getInstrumentDao();
        repairDao=daoSession.getRepairDao();
        checkInfoDao=daoSession.getCheckInfoDao();
        elementDao=daoSession.getElementDao();
    }

    public void initTitleBar(String title) {
        ll_back = findViewById(R.id.ll_back);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        ivRight = findViewById(R.id.iv_right);
        tv_right.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);
        tv_title.setText(title);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * 开启定位
     */
    public void startLocation(){
        if(mLocationClient!=null){
            mLocationClient.start();
        }
    }

    /**
     * 停止定位
     */
    public void stopLocation(){
        if(mLocationClient!=null){
            mLocationClient.stop();
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            if(latitude>0){
                Global.Latitude=latitude;
            }
            if(longitude>0){
                Global.Longitude=longitude;
            }
//            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
        }
    }

}
