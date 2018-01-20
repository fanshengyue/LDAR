package com.lm.ldar.util;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by fanshengyue on 2018/1/14.
 */

public class Util {
    /**
     * 字符串MD5加密
     * @param string
     * @return
     */
    public static String MD5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * EditText输入数字小数点监听
     */
    public static void EditDigitalListener(final EditText editText){
        //数字键盘
        editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        editText.addTextChangedListener(new TextWatcher() {
            String tmp = "";
            String digits = ".0123456789";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tmp=s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String text=editText.getText().toString();
                if(text.contains(".")||text.length()==0){
                    //已经有了小数点或第一位不能是小数点
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }else{
                    editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                    String str = s.toString();
                    if(str.equals(tmp)){
                        return;
                    }
                    StringBuffer sb = new StringBuffer();
                    for(int i = 0; i < str.length(); i++){
                        if(digits.indexOf(str.charAt(i)) >= 0){
                            sb.append(str.charAt(i));
                        }
                    }
                    tmp = sb.toString();
                    editText.setText(tmp);
                    editText.setSelection(editText.getText().length());
                }
            }
        });
    }

    //获取当前时间
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }

    //判断一个字符串是否是数字
    public static boolean isNumeric(String str){

        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();

    }
}
