package com.lm.ldar.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.lm.ldar.SharePreferenceKey;
import com.lm.ldar.entity.LoginUserEntity;

/**
 * Created by fanshengyue on 2018/1/13.
 * SharedPreferences存储用户登录信息
 */

public class LoginUserUtil {
    Context mContext;
    public LoginUserUtil(Context mContext){
        this.mContext=mContext;
    }

    /**
     * 更新当前登录的用户信息
     * @param id
     * @param username
     * @param password
     */
    public void updateShareUser(Long id,String username,String password){
        SharedPreferences sp = mContext.getSharedPreferences(SharePreferenceKey.UserInfo, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(SharePreferenceKey.UserId,id);
        edit.putString(SharePreferenceKey.UserName,username);
        edit.putString(SharePreferenceKey.UserPass,password);
        edit.commit();

    }

    /**
     * 更新当前企业信息
     */
    public void updateEnterPrise(Long id,String ecode){
        SharedPreferences sp_ep = mContext.getSharedPreferences(SharePreferenceKey.EnterPriseInfo, 0);
        SharedPreferences.Editor edit_ep = sp_ep.edit();
        edit_ep.putLong(SharePreferenceKey.EnterPriseId,id);
        edit_ep.putString(SharePreferenceKey.EnterCode,ecode);
        edit_ep.commit();
    }

    /**
     * 清除登录信息
     */
    public void deleteShare(){
        SharedPreferences sp = mContext.getSharedPreferences(SharePreferenceKey.UserInfo, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(SharePreferenceKey.UserId,0);
        edit.putString(SharePreferenceKey.UserName,"");
        edit.putString(SharePreferenceKey.UserPass,"");
        edit.commit();

        SharedPreferences sp_ep = mContext.getSharedPreferences(SharePreferenceKey.EnterPriseInfo, 0);
        SharedPreferences.Editor edit_ep = sp_ep.edit();
        edit_ep.putLong(SharePreferenceKey.EnterPriseId,0);
        edit_ep.putString(SharePreferenceKey.EnterCode,"");
        edit_ep.commit();
    }

    public LoginUserEntity getLoginUserInfo(){
        LoginUserEntity userEntity=new LoginUserEntity();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SharePreferenceKey.UserInfo,
                Activity.MODE_PRIVATE);
        String username = sharedPreferences.getString(SharePreferenceKey.UserName, "");
        String password = sharedPreferences.getString(SharePreferenceKey.UserPass, "");
        Long id=sharedPreferences.getLong(SharePreferenceKey.UserId,0);
        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        return userEntity;

    }

    public Long getEnterPriseId(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SharePreferenceKey.EnterPriseInfo,
                Activity.MODE_PRIVATE);
        return sharedPreferences.getLong(SharePreferenceKey.EnterPriseId,0);
    }

    public String getEcode(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SharePreferenceKey.EnterPriseInfo,
                Activity.MODE_PRIVATE);
        return sharedPreferences.getString(SharePreferenceKey.EnterCode,"");
    }

}
