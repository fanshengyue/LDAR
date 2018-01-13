package com.lm.ldar.util;

import android.app.Activity;
import android.widget.Toast;

import com.lm.ldar.view.MyAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wen Yun on 2017/1/13
 */
public class NetUtil {
    /**
     * 一般的code处理
     * @param activity
     * @param str
     * @return
     */
    public static boolean DealCode(Activity activity,String str) {
        boolean flag=true;
        if(IsNullOrEmpty.isEmpty(str)){
            flag=false;
            MyAlertDialog.showDialog(activity,"没有数据");
        }else{
            try {
                JSONObject jsonObject = new JSONObject(str);
                String msg=jsonObject.optString("message");
                if(!IsNullOrEmpty.isEmpty(msg)){
                    Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
                }
                if (jsonObject.optBoolean("success") == true) {
                    flag=true;
                }else{
                    flag=false;
                }
            } catch (JSONException e) {
                MyAlertDialog.showDialog(activity,str);
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 解析data数据，最好先判断DealCode为true时再用此方法
     * 本方法不进行code判断了
     * @param activity
     * @param str
     * @return
     */
    public static String JsonInner(Activity activity,String str){
        String result=null;
        if(!IsNullOrEmpty.isEmpty(str)){
            try {
                JSONObject jsonObject=new JSONObject(str);
                result=jsonObject.optString("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
