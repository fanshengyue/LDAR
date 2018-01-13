package com.lm.ldar.view;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class MyAlertDialog {
    public static void showDialog(Context c, String content){
        AlertDialog.Builder builder=new AlertDialog.Builder(c);
        builder.setMessage(content);
        builder.setPositiveButton("确定",null);
        builder.create().show();
    }
    public static void showDialog(Context c,String content,String title){
        AlertDialog.Builder builder=new AlertDialog.Builder(c);
        builder.setMessage(content);
        builder.setPositiveButton("确定", null);
        builder.setTitle(title);
        builder.create().show();
    }
}
