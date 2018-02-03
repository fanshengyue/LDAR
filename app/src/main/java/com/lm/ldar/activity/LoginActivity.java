package com.lm.ldar.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.network.framework.FailCallback;
import com.example.network.framework.NetworkFactory;
import com.example.network.framework.SuccessfulCallback;
import com.example.network.workUtils.OkhttpFactory;
import com.lm.ldar.LMApplication;
import com.lm.ldar.R;
import com.lm.ldar.SharePreferenceKey;
import com.lm.ldar.adapter.LoginUserListAdapter;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Ctype;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.LoginUserEntity;
import com.lm.ldar.entity.Namerules;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.User;
import com.lm.ldar.entity.Workplan;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.FAST;
import com.lm.ldar.util.JsonPaser;
import com.lm.ldar.util.LoginUserUtil;
import com.lm.ldar.util.NetUtil;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.util.Util;
import com.lm.ldar.view.MyAlertDialog;
import com.lm.ldar.view.NoScrollListView;

import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.ll_logo)
    LinearLayout llLogo;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.login_btn_commit)
    Button loginBtnCommit;
    @BindView(R.id.tv_login_list)
    TextView tvLoginList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initListener();

    }

    /**
     * 初始化两个输入框，若曾经登陆过，则显示最近一次登录的用户名，密码
     */
    private void initExitText(){
        LoginUserEntity entity=userUtil.getLoginUserInfo();
        if(entity!=null&&!IsNullOrEmpty.isEmpty(entity.getUsername())&&!IsNullOrEmpty.isEmpty(entity.getPassword())){
            etUsername.setText(entity.getUsername());
        }
    }

    private void initListener(){
        tvLoginList.setOnClickListener(this);
        loginBtnCommit.setOnClickListener(this);
    }

    private void Login(){
        String mUsername=etUsername.getText().toString();
        String mPassword=etPassword.getText().toString();
        if(!IsNullOrEmpty.isEmpty(mUsername)){
            if(!IsNullOrEmpty.isEmpty(mPassword)){
                //登录逻辑
                startLogin(mUsername,Util.MD5(mPassword));
            }else{
                Toast.makeText(LoginActivity.this,getString(R.string.passwordempty),Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this,getString(R.string.usernameempty),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录,password是MD5加密后的数据
     * @param username
     * @param password
     */
    private void startLogin(final String username, final String password){
        NetworkFactory factory= OkhttpFactory.getInstance();
        SuccessfulCallback successfulCallback=new SuccessfulCallback() {
            @Override
            public void success(String str) {
                loadingDialog.dismiss();
                if(str==null){
                    return;
                }
                if(NetUtil.DealCode(LoginActivity.this,str)){
                    String data=NetUtil.JsonInner(LoginActivity.this,str);
                    if(!IsNullOrEmpty.isEmpty(data)){
                        try {
                            org.json.JSONObject jsonObject=new org.json.JSONObject(data);
                            // 用户
                            String str_user=jsonObject.optString("user");
                            if(!IsNullOrEmpty.isEmpty(str_user)){
                                User user= JsonPaser.parseUser(str_user);
                                if(user!=null){
                                    //登录用户信息更新shareprefrence
                                    userUtil.updateShareUser(user.getId(),user.getUsername(),user.getPassword());
                                    DaoUtil.UpdateUser(userDao,user);
                                }
                            }
                            // 企业
                            String str_enterprise=jsonObject.optString("enterprise");
                            if(!IsNullOrEmpty.isEmpty(str_enterprise)){
                                Enterprise enterprise=JsonPaser.parseEnterprise(str_enterprise);
                                if(enterprise!=null){
                                    //企业id更新shareprefrence
                                    userUtil.updateEnterPrise(enterprise.getId(),enterprise.getEcode());
                                    DaoUtil.UpdateEnterprise(enterpriseDao,enterprise);
                                }
                            }

                            // 厂区
                            String str_fac=jsonObject.optString("factoryList");
                            if(!IsNullOrEmpty.isEmpty(str_fac)){
                                List<Factory> factories=JsonPaser.parseFactory(str_fac);
                                if(factories!=null&&factories.size()>0){
                                    for(Factory factory:factories){
                                        if(factory!=null){
                                            DaoUtil.UpdateFactory(factoryDao,factory);
                                        }
                                    }
                                }
                            }
                            // 部门列表
                            String str_dep = jsonObject.optString("departmentList");
                            if(!IsNullOrEmpty.isEmpty(str_dep)){
                                List<Department> departments = JsonPaser.parseDepartment(str_dep);
                                if(departments!=null&&departments.size()>0){
                                    for (Department department:departments){
                                        if(department!=null){
                                            DaoUtil.updateDepartment(departmentDao,department);
                                        }
                                    }
                                }
                            }
                            // 装置
                            String str_device = jsonObject.optString("deviceList");
                            if(!IsNullOrEmpty.isEmpty(str_device)){
                                List<Device> devices = JsonPaser.parseDevice(str_device);
                                if(devices!=null&&devices.size()>0){
                                    for (Device device:devices){
                                        if(device!=null){
                                            DaoUtil.updateDevice(deviceDao,device);
                                        }
                                    }
                                }
                            }
                            // 子区域
                            String str_area = jsonObject.optString("areaList");
                            if(!IsNullOrEmpty.isEmpty(str_area)){
                                List<Area> areas = JsonPaser.parseArea(str_area);
                                if(areas!=null&&areas.size()>0){
                                    for(Area area:areas){
                                        if(area!=null){
                                            DaoUtil.updateArea(areaDao,area);
                                        }
                                    }
                                }
                            }

                            // 图片版本
                            String str_picv = jsonObject.optString("pictureversionlist");
                            if(!IsNullOrEmpty.isEmpty(str_picv)){
                                List<Pictureversion> pictureversions = JsonPaser.parsePictureversion(str_picv);
                                if(pictureversions!=null&&pictureversions.size()>0){
                                    for (Pictureversion pictureversion:pictureversions){
                                        if(pictureversion!=null){
                                            DaoUtil.updatePictureversion(pictureversionDao,pictureversion);
                                        }
                                    }
                                }
                            }
                            // 命名规则
                            String str_namerules = jsonObject.optString("nameRules");
                            if(!IsNullOrEmpty.isEmpty(str_namerules)){
                                Namerules namerules = JsonPaser.parseNamerules(str_namerules);
                                if(namerules!=null){
                                    DaoUtil.updateNamerules(namerulesDao,namerules);
                                }
                            }
//                            // 组件类型
//                            String str_ctype = jsonObject.optString("ctypeList");
//                            if(!IsNullOrEmpty.isEmpty(str_ctype)){
//                                List<Ctype> ctypes = JsonPaser.parseCtype(str_ctype);
//                                if(ctypes!=null&&ctypes.size()>0){
//                                    for (Ctype ctype:ctypes){
//                                        if(ctype!=null){
//                                            if(!IsNullOrEmpty.isEmpty(ctype.getId())&&!IsNullOrEmpty.isEmpty(ctype.getDescription())){
//                                                DaoUtil.updateCtype(ctypeDao,ctype);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
                            // 工作计划
                            String str_workplan = jsonObject.optString("workplan");
                            if(!IsNullOrEmpty.isEmpty(str_workplan)){
                                Workplan workplan = JsonPaser.parseWorkplan(str_workplan);
                                if(workplan!=null){
                                    DaoUtil.updateWorkplan(workplanDao,workplan);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }

            @Override
            public void success(InputStream ism, long conentLength) {

            }
        };

        FailCallback failCallback=new FailCallback() {
            @Override
            public void fail(String str) {
                loadingDialog.dismiss();
                MyAlertDialog.showDialog(LoginActivity.this, str);
            }
        };
        HashMap<String,String> params=new HashMap<>();
        params.put("username",username);
        params.put("password", password);
        loadingDialog.show();
        factory.start(NetworkFactory.METHOD_POST,urlManager.getAppLogin() , params, successfulCallback, failCallback);
    }

    public  void UserList(Activity activity){
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        if(alertDialog.isShowing()){
            alertDialog.dismiss();
        }
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.layout_login_userlist);
        //设置对话框宽高
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();//获取屏幕宽度
        int height=wm.getDefaultDisplay().getHeight();//获取屏幕高度
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值

        p.width = (int) (width * 0.8); // 宽度设置为屏幕的0.8
        p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
        window.setAttributes(p);
        NoScrollListView listView=window.findViewById(R.id.lv_login);
        List<User> users=userDao.queryBuilder().build().list();
        final List<LoginUserEntity>data=new ArrayList<>();
        if(users!=null&&users.size()>0){
            for(User user:users){
                LoginUserEntity entity=new LoginUserEntity();
                entity.setId(user.getId());
                entity.setUsername(user.getUsername());
                entity.setPassword(user.getPassword());
                data.add(entity);
            }
        }
        if(data!=null){
            LoginUserListAdapter adapter=new LoginUserListAdapter(LoginActivity.this,data);
            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long user_id=data.get(position).getId();
                User userinfo = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
                if(userinfo!=null){
                    userUtil.updateShareUser(user_id,userinfo.getUsername(),userinfo.getPassword());
                    Enterprise ep_Entity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(userinfo.getEid())).unique();
                    if(ep_Entity!=null){
                        userUtil.updateEnterPrise(ep_Entity.getId(),ep_Entity.getEcode());
                    }
                    alertDialog.dismiss();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(LoginActivity.this,"暂无此用户信息",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_list:
                UserList(LoginActivity.this);
                break;
            case R.id.login_btn_commit:
                Login();
                break;
            default:
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        initExitText();
    }
}
