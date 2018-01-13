package com.lm.ldar.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.lm.ldar.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivityGroup {
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(R.id.view_tab_host)
    TabHost viewTabHost;
    private String TAG = "MainActivity";
    private ArrayList<View> bottomArrayList;
    private String[] itemTitle={"建档","检测","维修复测","我的"};//底部标签文字
    private int[] itemIcon={R.drawable.selector_bottomtab_inputting,R.drawable.selector_bottomtab_detection,
            R.drawable.selector_bottomtab_review,R.drawable.selector_bottomtab_me};//底部标签图标
    private String[] activities={"InputtingActivity","DetectionActivity","ReviewActivity","MeActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabItem(itemTitle,itemIcon,viewTabHost,activities);
    }

    private void initTabItem(String[] itemTitle,int[] itemIcon,TabHost tabHost,String[] toActivity){
        bottomArrayList = new ArrayList<View>();
        // 加载TabSpec
        tabHost.setup(getLocalActivityManager());
        tabHost.setCurrentTab(0);//默认选中第一个
        for(int i=0;i<itemTitle.length;i++){
            RelativeLayout tab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_tabhost, null);
            ImageView icon = (ImageView) tab.findViewById(R.id.icon);
            icon.setBackgroundResource(itemIcon[i]);
            TextView title = (TextView) tab.findViewById(R.id.title);
            title.setText(itemTitle[i]);
            TabHost.TabSpec tabSpec=tabHost.newTabSpec("Tag"+i);
            tabSpec.setIndicator(tab);
            Intent intent=new Intent();
            intent.setClassName(this, getPackageName()+".activity." + toActivity[i]);
            tabSpec.setContent(intent);
            tabHost.addTab(tabSpec);
            bottomArrayList.add(tab);
        }
        //设置标签栏背景颜色
        TabWidget tw = tabHost.getTabWidget();
        tw.setBackgroundResource(R.color.white);

    }
}
