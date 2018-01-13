package com.lm.ldar.api;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.util.DisplayUtil;
import com.lm.ldar.util.NetWorkUtil;


/**
 * Created by Wen Yun on 2016/5/19.
 */
public class UrlManager {
    private String mVersion;
    private NetWorkUtil netWorkUtil;
    private Context mContext;
    public UrlManager(Context mContext){
        super();
        this.mContext=mContext;
        netWorkUtil = new NetWorkUtil(mContext);
        PackageManager manager = mContext.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (info != null)
            mVersion = info.versionName;

    }
    public String getVersion(){
        return mVersion;
    }
    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width=display.getWidth();
        return DisplayUtil.px2dip(context, width);
    }

    public String getAndroidVersion(){
        String carrier= android.os.Build.MANUFACTURER;//厂商
        String model= android.os.Build.MODEL;//手机型号
        int sysAPI=android.os.Build.VERSION.SDK_INT;//android系统API
        String sysVersion=android.os.Build.VERSION.RELEASE;//android系统版本号
        return carrier+" "+model+"[Android_"+sysVersion+"]";
    }
    /**
     * 登录接口
     */
    public String getAppLogin(){
        return getBaseUrl()+"/app-login.action";
    }
    /**
     *获取基础IP地址
     * @return
     * @throws
     */
    public String getBaseUrl(){
        return "http://192.168.1.14:8080/LDAR2.0";
    }
}