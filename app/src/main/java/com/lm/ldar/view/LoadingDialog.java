package com.lm.ldar.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lm.ldar.LMApplication;
import com.lm.ldar.R;
import com.lm.ldar.api.UrlManager;
import com.lm.ldar.util.DisplayUtil;


/**
 * Created by yuekuapp on 14-11-19.
 */
public class LoadingDialog extends AlertDialog {

    private AnimationDrawable animDrawable;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(getContext());
        this.setCancelable(false);
    }


    private void initView(Context context) {
        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setBackgroundResource(R.drawable.loading_bg);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView view=new ImageView(context);
        animDrawable= (AnimationDrawable) context.getResources().getDrawable(R.drawable.loading);
        view.setImageDrawable(animDrawable);
        int padding= DisplayUtil.dip2px(context, 25);
        view.setPadding(padding, DisplayUtil.dip2px(context, 15), padding, DisplayUtil.dip2px(context, 15));
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER;
        rootLayout.addView(view, params);
        TextView t=new TextView(context);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(Color.WHITE);
        t.setText("加载中...");
        t.setTextSize(12);
        t.setPadding(0, 0, 0, DisplayUtil.dip2px(context, 15));
        t.setLayoutParams(params);
        rootLayout.addView(t);
        rootLayout.setPadding(25,20,25,20);
        setContentView(rootLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = DisplayUtil.dip2px(context,UrlManager.getScreenWidth(context))/2;
        getWindow().setAttributes(p);

    }

    @Override
    public void show() {
        super.show();
        animDrawable.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animDrawable.stop();
    }
}
