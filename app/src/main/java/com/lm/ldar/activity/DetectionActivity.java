package com.lm.ldar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.network.framework.FailCallback;
import com.example.network.framework.NetworkFactory;
import com.example.network.framework.SuccessfulCallback;
import com.example.network.workUtils.OkhttpFactory;
import com.lm.ldar.R;
import com.lm.ldar.adapter.InstrumentAdapter;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.InstrumentDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.CheckInfo;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Element;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.Instrument;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.SelectEntity;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.ImageUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.JsonPaser;
import com.lm.ldar.util.ListDialog;
import com.lm.ldar.util.NetUtil;
import com.lm.ldar.view.MyAlertDialog;
import com.lm.ldar.view.NoScrollListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class DetectionActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.bt_download)
    Button btDownload;
    @BindView(R.id.bt_see)
    Button btSee;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.cb_v)
    CheckBox cbV;
    @BindView(R.id.cb_p)
    CheckBox cbP;
    @BindView(R.id.cb_f)
    CheckBox cbF;
    @BindView(R.id.cb_c)
    CheckBox cbC;
    @BindView(R.id.cb_a)
    CheckBox cbA;
    @BindView(R.id.cb_r)
    CheckBox cbR;
    @BindView(R.id.cb_n)
    CheckBox cbN;
    @BindView(R.id.cb_o)
    CheckBox cbO;
    @BindView(R.id.cb_s)
    CheckBox cbS;
    @BindView(R.id.cb_q)
    CheckBox cbQ;
    @BindView(R.id.bt_instrument)
    Button btInstrument;
    @BindView(R.id.et_checkpeople)
    EditText etCheckpeople;
    @BindView(R.id.bt_upload)
    Button btUpload;

    private String vid;//版本id
    private SelectEntity selectEntity;
    private ListDialog listDialog;
    private List<PictureDownload> pictureDownloadList;
    private List<CheckInfo> checkInfoList;
    private List<Element> elementList;

    private String ecode;//组织结构代码
    private String download_path;//下载图片路径
    private Long inid;//检测仪器id
    private List<CheckBox> checkBoxs=new ArrayList<>();//checkbox集合
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        ButterKnife.bind(this);
        init();
        initListener();
        initCheckBox();
    }

    private void initCheckBox(){
        checkBoxs.add(cbV);
        checkBoxs.add(cbP);
        checkBoxs.add(cbF);
        checkBoxs.add(cbC);
        checkBoxs.add(cbA);
        checkBoxs.add(cbR);
        checkBoxs.add(cbN);
        checkBoxs.add(cbO);
        checkBoxs.add(cbS);
        checkBoxs.add(cbQ);
    }

    private void init() {
        selectEntity = new SelectEntity();
        listDialog = new ListDialog(DetectionActivity.this, selectEntity, daoSession);
        Enterprise epEntity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(epId)).unique();
        if (epEntity != null) {
            ecode = epEntity.getEcode();
        }
        download_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DOWNLOAD_CHECK;
    }

    private void initListener() {
        btVersion.setOnClickListener(this);
        btChildArea.setOnClickListener(this);
        btDepartment.setOnClickListener(this);
        btDevice.setOnClickListener(this);
        btFactory.setOnClickListener(this);
        btSee.setOnClickListener(this);
        btDownload.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btInstrument.setOnClickListener(this);
        btUpload.setOnClickListener(this);
    }

    /**
     * 获取checkbox的值
     * @return
     */
    private String getCheckValue(){
        String checked = "";
        for (CheckBox cbx : checkBoxs) {
            if (cbx.isChecked()) {
                checked += getValue(""+cbx.getText()) + ",";
            }
        }
        if(!IsNullOrEmpty.isEmpty(checked)){
            checked=checked.substring(0,checked.length()-1);
        }
        return checked;
    }

    private String getValue(String text){
        String content="";
        switch (text){
            case "V(阀门)":
                content="V";
                break;
            case "P(泵)":
                content="P";
                break;
            case "F(法兰)":
                content="F";
                break;
            case "C(压缩机":
                content="C";
                break;
            case "A(搅拌器)":
                content="A";
                break;
            case "R(泄压装置)":
                content="R";
                break;
            case "N(连接件)":
                content="N";
                break;
            case "O(开口阀)":
                content="O";
                break;
            case "S(采样连接系统)":
                content="S";
                break;
            case "Q(其他类型)":
                content="Q";
                break;
            default:
                break;
        }
        return content;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_version:
                //版本
                List<Pictureversion> versionList = pictureversionDao.queryBuilder().where(PictureversionDao.Properties.Eid.eq(epId)).build().list();
                if (versionList != null && versionList.size() > 0) {
                    vid = listDialog.VersionDialog(versionList, btVersion);
                } else {
                    Toast.makeText(DetectionActivity.this, "暂无版本数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_factory:
                //厂区
                List<Factory> factoryList = factoryDao.queryBuilder().where(FactoryDao.Properties.Eid.eq(epId)).build().list();
                if (factoryList != null && factoryList.size() > 0) {
                    listDialog.FactoryDialog(factoryList, btFactory);
                } else {
                    Toast.makeText(DetectionActivity.this, "暂无厂区数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_department:
                //部门
                if (selectEntity != null) {
                    if (!IsNullOrEmpty.isEmpty(selectEntity.getFid())) {
                        List<Department> departmentList = departmentDao.queryBuilder().where(DepartmentDao.Properties.Fid.eq(selectEntity.getFid())).build().list();
                        if (departmentList != null && departmentList.size() > 0) {
                            listDialog.DepartmentDialog(departmentList, btDepartment);
                        } else {
                            Toast.makeText(DetectionActivity.this, "暂无部门数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请先选择厂区", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_device:
                //装置
                if (selectEntity != null) {
                    if (!IsNullOrEmpty.isEmpty(selectEntity.getDid())) {
                        List<Device> deviceList = deviceDao.queryBuilder().where(DeviceDao.Properties.Did.eq(selectEntity.getDid())).build().list();
                        if (deviceList != null && deviceList.size() > 0) {
                            listDialog.DeviceDialog(deviceList, btDevice);
                        } else {
                            Toast.makeText(DetectionActivity.this, "暂无装置数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请先选择部门", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_child_area:
                //子区域
                if (selectEntity != null) {
                    if (!IsNullOrEmpty.isEmpty(selectEntity.getEid())) {
                        List<Area> areaList = areaDao.queryBuilder().where(AreaDao.Properties.Did.eq(selectEntity.getEid())).build().list();
                        if (areaList != null && areaList.size() > 0) {
                            listDialog.AreaDialog(areaList, btChildArea);
                        } else {
                            Toast.makeText(DetectionActivity.this, "暂无子区域数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请先选择装置", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.bt_see:
                //查看
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            List<PictureDownload> data = DaoUtil.getDownloadPicList(pictureDownloadDao, Long.parseLong(selectEntity.getAid()), 0);
//                            List<PictureDownload> data = DaoUtil.getDownloadPicList(pictureDownloadDao, Long.parseLong(13+""), 0);
                            Intent intent = new Intent(DetectionActivity.this, ImageListActivity.class);
                            intent.putExtra("imagelist", (Serializable) data);
                            intent.putExtra("type", 1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_download:
                //下载
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            if(!IsNullOrEmpty.isEmpty(getCheckValue())){
                                DownloadData(vid, selectEntity.getAid(),getCheckValue());
                            }else{
                                Toast.makeText(DetectionActivity.this, "请选择组件类型", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_delete:
                //删除检测信息和图片
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DetectionActivity.this);
                            builder.setMessage("确定要删除:" + selectEntity.getAname() + "检测数据和图片吗?");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //删除数据库
                                    DaoUtil.DeleteDownloadPicListByAid(pictureDownloadDao, Long.parseLong(selectEntity.getAid()), 0);
                                    List<PictureDownload> pictureList = DaoUtil.getDownloadPicList(pictureDownloadDao, Long.parseLong(selectEntity.getAid()), 0);
                                    if (pictureList != null && pictureList.size() > 0) {
                                        for (PictureDownload picture : pictureList) {
                                            if (picture != null) {
                                                String imagePath = download_path + selectEntity.getAid() + "/" + picture.getElementname();
                                                ImageUtil.DeleteImage(imagePath);
                                            }
                                        }
                                        Toast.makeText(DetectionActivity.this, "数据删除成功", Toast.LENGTH_SHORT).show();
                                    }

                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("取消", null);
                            builder.setTitle("提示");
                            builder.create().show();
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_instrument:
                //检测仪器
                List<Instrument> instrumentList = instrumentDao.queryBuilder().where(InstrumentDao.Properties.Eid.eq(epId)).build().list();
                if (instrumentList != null && instrumentList.size() > 0) {
                    InstrumentDialog(instrumentList, btInstrument);
                } else {
                    Toast.makeText(DetectionActivity.this, "暂无检测仪器设备", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_upload:
                //上传
                if (!IsNullOrEmpty.isEmpty(vid)) {
                    if (selectEntity != null) {
                        if (!IsNullOrEmpty.isEmpty(selectEntity.getAid())) {
                            if(!IsNullOrEmpty.isEmpty(getCheckValue())){
                                if(!IsNullOrEmpty.isEmpty(inid+"")){
                                    if(!IsNullOrEmpty.isEmpty(etCheckpeople.getText().toString())){
                                        List<CheckInfo>checkInfoList=DaoUtil.getCheckInfoList(checkInfoDao,Long.parseLong(selectEntity.getAid()));
                                        try {
                                            UploadDetection(inid+"",selectEntity.getAid()+"",selectEntity.getDid()+"",
                                                    getCheckValue(),etCheckpeople.getText().toString(),ListToJsonArray(DealCheckInfoList(checkInfoList))+"");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }else{
                                        Toast.makeText(DetectionActivity.this, "检测人员不能为空", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(DetectionActivity.this, "请选择检测仪器", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(DetectionActivity.this, "请选择组件类型", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DetectionActivity.this, "请选择子区域", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetectionActivity.this, "请选择版本号", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    public void InstrumentDialog(final List<Instrument> data, final Button button){
        if(data!=null&&data.size()>0){
            InstrumentAdapter adapter=new InstrumentAdapter(DetectionActivity.this,data);
            final AlertDialog alertDialog = new AlertDialog.Builder(DetectionActivity.this).create();
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
            alertDialog.show();
            Window window = alertDialog.getWindow();
            window.setContentView(R.layout.layout_list);
            //设置对话框宽高
            WindowManager wm = getWindowManager();
            int width = wm.getDefaultDisplay().getWidth();

            WindowManager.LayoutParams p = window.getAttributes();
            p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
            p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
            window.setAttributes(p);
            final NoScrollListView listView=window.findViewById(R.id.lv_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    button.setText(data.get(position).getInsname());
                    inid=data.get(position).getId();
                    alertDialog.dismiss();
                }
            });
        }
    }
    /**
     * 下载数据
     */
    private void DownloadData(String pvid, String areaid,String etype) {
        NetworkFactory factory = OkhttpFactory.getInstance();
        SuccessfulCallback successfulCallback = new SuccessfulCallback() {
            @Override
            public void success(String str) throws JSONException {

                if (str == null) {
                    return;
                }
                if (NetUtil.DealCode(DetectionActivity.this, str)) {
                    String data = NetUtil.JsonInner(DetectionActivity.this, str);
                    if (!IsNullOrEmpty.isEmpty(data)) {
                        JSONObject jsonObject = new JSONObject(data);
                        String array_pic = jsonObject.optString("pictureList");
                        pictureDownloadList = JsonPaser.getDownloadImgData(array_pic, 0);
                        if (pictureDownloadList != null && pictureDownloadList.size() > 0) {
                            for (int i = 0; i < pictureDownloadList.size(); i++) {
                                DaoUtil.updatePicDownload(pictureDownloadDao, pictureDownloadList.get(i));
                                //下载图片
                                if (!IsNullOrEmpty.isEmpty(pictureDownloadList.get(i).getElementname())) {
                                    String imageUrl = ImageUtil.getImageUrl(urlManager.getBaseUrl(), ecode, vid, pictureDownloadList.get(i).getElementname());
                                    ImageUtil.DownloadImageAndSave(DetectionActivity.this, download_path + selectEntity.getAid(), imageUrl, pictureDownloadList.get(i).getElementname());
                                }
                            }

                        }
                        //CheckInfo
                        String arr_check = jsonObject.optString("checkinfoList");
                        checkInfoList = JsonPaser.getCheckInfoData(arr_check);
                        if (checkInfoList != null && checkInfoList.size() > 0) {
                            for (int i = 0; i < checkInfoList.size(); i++) {
                                DaoUtil.updateCheckInfo(checkInfoDao, checkInfoList.get(i));
                            }
                        }
                        //后期需要加element表
                        String arr_element = jsonObject.optString("elementList");
                        elementList = JsonPaser.getElementData(arr_element);
                        if (elementList != null && elementList.size() > 0) {
                            for (int i = 0; i < elementList.size(); i++) {
                                DaoUtil.updateElement(elementDao, elementList.get(i));
                            }
                        }
                        Toast.makeText(DetectionActivity.this, "下载完成", Toast.LENGTH_SHORT).show();

                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void success(InputStream ism, long conentLength) {

            }
        };
        FailCallback failCallback = new FailCallback() {
            @Override
            public void fail(String str) {
                loadingDialog.dismiss();
            }
        };
        HashMap<String, String> params = new HashMap<>();
        params.put("eid", epId + "");
        params.put("pvid", pvid);
        params.put("areaid", areaid);
        params.put("etype", etype);
//        params.put("eid", "5");
//        params.put("pvid", "2");
//        params.put("areaid", "13");
//        params.put("etype", "V,P,F");
        loadingDialog.show();
        factory.start(OkhttpFactory.METHOD_POST, urlManager.getCheckDownloadUrl(), params, successfulCallback, failCallback);
    }

    /**
     * 监测信息上传
     */
    private void UploadDetection(String instrumentId,String areaid,String deviceid,String etype,String checkpeople,String checkinforList){
        NetworkFactory factory = OkhttpFactory.getInstance();
        SuccessfulCallback successfulCallback=new SuccessfulCallback() {
            @Override
            public void success(String str) throws JSONException {
                loadingDialog.dismiss();
                loadingDialog.dismiss();
                if (IsNullOrEmpty.isEmpty(str)) {
                    return;
                }
                NetUtil.DealCode(DetectionActivity.this, str);
            }

            @Override
            public void success(InputStream ism, long conentLength) throws IOException {

            }
        };
        FailCallback failCallback=new FailCallback() {
            @Override
            public void fail(String str) {
                loadingDialog.dismiss();
                MyAlertDialog.showDialog(DetectionActivity.this, str);
            }
        };
        HashMap<String, String> params = new HashMap<>();
        params.put("eid", epId + "");
        params.put("instrument",instrumentId);
        params.put("areaid", areaid);
        params.put("deviceid",deviceid);
        params.put("etype", etype);
        params.put("checkpeople",checkpeople);
        params.put("checkinforList",checkinforList);
        loadingDialog.show();
        factory.start(OkhttpFactory.METHOD_POST,urlManager.detectionUploadUrl(),params,successfulCallback,failCallback);
    }

    /**
     * 组件Checkbox筛选条件
     * @return
     */
    private List<String> getCheckValueNumber(){
        List<String> data=new ArrayList<>();
        String str=getCheckValue();
        if(!IsNullOrEmpty.isEmpty(str)){
            List<String> values= Arrays.asList(str.split(","));
            String content="";
            if(values!=null&&values.size()>0){
                for(int i=0;i<values.size();i++){
                    switch (values.get(i)){
                        case "V(阀门)":
                            content="101";
                            break;
                        case "P(泵)":
                            content="102";
                            break;
                        case "F(法兰)":
                            content="103";
                            break;
                        case "C(压缩机":
                            content="104";
                            break;
                        case "A(搅拌器)":
                            content="105";
                            break;
                        case "R(泄压装置)":
                            content="106";
                            break;
                        case "N(连接件)":
                            content="107";
                            break;
                        case "O(开口阀)":
                            content="108";
                            break;
                        case "S(采样连接系统)":
                            content="109";
                            break;
                        case "Q(其他类型)":
                            content="110";
                            break;
                        default:
                            break;
                    }
                    data.add(content);
                }
            }
        }
        return data;

    }

    /**
     * 取出选中的CheckInfoList
     * @param checkInfoList
     * @return
     */
    private List<CheckInfo> DealCheckInfoList(List<CheckInfo>checkInfoList){
        List<CheckInfo>checkInfos=new ArrayList<>();
        List<String> data=getCheckValueNumber();
        if(checkInfoList!=null && checkInfoList.size()>0 && data!=null && data.size()>0){
            for(int i=0;i<checkInfoList.size();i++){
                if(data.contains(checkInfoList.get(i).getType())){
                    checkInfos.add(checkInfoList.get(i));
                }
            }
        }
        return checkInfos;
    }

    /**
     * List转JsonArray
     * @param checkInfoList
     * @return
     * @throws JSONException
     */
    private JSONArray ListToJsonArray(List<CheckInfo> checkInfoList) throws JSONException {
        JSONArray jsonArray=new JSONArray();
        if(checkInfoList!=null&&checkInfoList.size()>0){
            for(int i=0;i<checkInfoList.size();i++){
                CheckInfo checkInfo=checkInfoList.get(i);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",checkInfo.getId());
                jsonObject.put("date",checkInfo.getDate());
                jsonObject.put("time",checkInfo.getTime());
                jsonObject.put("medium",checkInfo.getMedium());
                jsonObject.put("type",checkInfo.getType());
                jsonObject.put("tag",checkInfo.getTag());
                jsonObject.put("pbvalue",checkInfo.getPbvalue());
                jsonObject.put("pbunit",checkInfo.getPbunit());
                jsonObject.put("pbstate",checkInfo.getPbstate());
                jsonObject.put("pcvalue",checkInfo.getPcvalue());
                jsonObject.put("pcunit",checkInfo.getPcunit());
                jsonObject.put("pcstate",checkInfo.getPcstate());
                jsonObject.put("fbvalue",checkInfo.getFbvalue());
                jsonObject.put("fbunit",checkInfo.getFbunit());
                jsonObject.put("fbstate",checkInfo.getFbstate());
                jsonObject.put("fcvalue",checkInfo.getFcvalue());
                jsonObject.put("fcunit",checkInfo.getFcunit());
                jsonObject.put("fcstate",checkInfo.getFcstate());
                jsonObject.put("define",checkInfo.getDefine());
                jsonObject.put("uid",checkInfo.getUid());
                jsonObject.put("insid",checkInfo.getInsid());
                jsonObject.put("did",checkInfo.getDid());
                jsonObject.put("aid",checkInfo.getAid());
                jsonObject.put("tid",checkInfo.getTid());
                jsonObject.put("wid",checkInfo.getWid());
                jsonObject.put("eleid",checkInfo.getEleid());
                jsonObject.put("days",checkInfo.getDays());
                jsonObject.put("horus",checkInfo.getHorus());
                jsonObject.put("isleak",checkInfo.getIsleak());
                jsonObject.put("leakagerate",checkInfo.getLeakagerate());
                jsonObject.put("leakageratepjxs",checkInfo.getLeakageratepjxs());
                jsonObject.put("det",checkInfo.getDet());
                jsonObject.put("leak",checkInfo.getLeak());
                jsonObject.put("leaksource",checkInfo.getLeaksource());
                jsonObject.put("repairmethod",checkInfo.getRepairmethod());
                jsonObject.put("originalfcvalue",checkInfo.getOriginalfcvalue());
                jsonObject.put("pvid",checkInfo.getPvid());
                jsonObject.put("checkpeople",checkInfo.getCheckpeople());
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }
}
