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
     * 清除登录信息
     */
    public void deleteShare(){
        SharedPreferences sp = mContext.getSharedPreferences(SharePreferenceKey.UserInfo, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(SharePreferenceKey.UserId,0);
        edit.putString(SharePreferenceKey.UserName,"");
        edit.putString(SharePreferenceKey.UserPass,"");
        edit.commit();
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

}
