package com.lm.ldar.activity;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.bluetoothssp.BluetoothClientService;
import com.lm.ldar.bluetoothssp.BluetoothTools;
import com.lm.ldar.bluetoothssp.ClsUtils;
import com.lm.ldar.bluetoothssp.MobileDeviceAdapter;
import com.lm.ldar.entity.MobileDevice;
import com.lm.ldar.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

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


    /**
     * 蓝牙相关
     * @param savedInstanceState
     */
    public String action;
    private String pin="0000";
    public int REQUEST_ENABLE = 1;
    private List<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
    private List<MobileDevice> deviList = new ArrayList<>();

    public BluetoothDevice mBluetoothDevice = null;
    public BluetoothDevice mLastBluetoothDevice = null;
    public BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public MobileDeviceAdapter adapter;

    private NoScrollListView listView;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabItem(itemTitle,itemIcon,viewTabHost,activities);

        initBluetooth();
        startSearchDevice();
        adapter = new MobileDeviceAdapter(MainActivity.this,R.layout.device_item,deviList);
        showBlueToothDialog();
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

    /**
     * 开始搜索蓝牙
     */
    private void startSearchDevice(){
        //若蓝牙适配器未启动，则自动启动
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();//异步的，不会等待结果，直接返回。

            //如果设备正在寻找
        } else if(!mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.startDiscovery();

        }
    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, final Intent intent) {
            action = intent.getAction();
            //如果找到蓝牙设备
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                mBluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                deviceList.add(mBluetoothDevice);
                MobileDevice device = new MobileDevice(mBluetoothDevice.getName(),mBluetoothDevice.getAddress());
                if(!deviList.contains(device)) {
                    deviList.add(device);
                }
                Log.i("deviList---:",deviList.toString());
                Log.i("deviceList---:",deviceList.toString());
                adapter.notifyDataSetChanged();

            }

            //如果连接失败
            if(BluetoothTools.ACTION_CONNECT_ERROR.equals(action)){
                Toast.makeText(context,"连接失败",Toast.LENGTH_SHORT).show();
            }


            //如果连接成功
            if(BluetoothTools.ACTION_CONNECT_SUC.equals(action)){
                Toast.makeText(context,"连接成功",Toast.LENGTH_LONG).show();
            }
        }
    };

    private void showBlueToothDialog(){
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        if(alertDialog.isShowing()){
            alertDialog.dismiss();
        }
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.layout_title_list);
        //设置对话框宽高
        WindowManager wm = MainActivity.this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams p = window.getAttributes();
        p.width = (int) (width * 0.9); // 宽度设置为屏幕的0.9
        p.height= WindowManager.LayoutParams.WRAP_CONTENT;//高度自适应
        window.setAttributes(p);
        listView=window.findViewById(R.id.lv_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLastBluetoothDevice = deviceList.get(position);
                try
                {
                    ClsUtils.createBond(mLastBluetoothDevice.getClass(), mLastBluetoothDevice);
                    ClsUtils.setPin(mLastBluetoothDevice.getClass(), mLastBluetoothDevice,pin);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent succIntent = new Intent();
                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable("Pairing_Succ",mLastBluetoothDevice);
                    succIntent.setAction(BluetoothTools.ACTION_PAIRING_SUCC);
                    succIntent.putExtras(mBundle);
                    sendBroadcast(succIntent);
                }
                alertDialog.dismiss();
            }
        });
        Log.i("showDialog","--------------");
    }

    //进行服务注册、广播注册
    @Override
    protected void onStart() {
        //启动服务
        Intent startServ = new Intent(MainActivity.this, BluetoothClientService.class);
        startService(startServ);
        //注册广播
        IntentFilter discoveryFilter = new IntentFilter();
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        discoveryFilter.addAction(BluetoothDevice.ACTION_FOUND);
        discoveryFilter.addAction(BluetoothTools.ACTION_CONNECT_ERROR);
        discoveryFilter.addAction(BluetoothTools.ACTION_CONNECT_SUC);
        discoveryFilter.addAction(BluetoothTools.ACTION_RECEIVE_DATA);
        registerReceiver(mBluetoothReceiver, discoveryFilter);

        super.onStart();
    }

    //获取蓝牙适配器，当没有开启时开启
    private void initBluetooth() {
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE);
        }
    }

    @Override
    protected void onDestroy() {
        Intent startServ = new Intent(MainActivity.this, BluetoothClientService.class);
        stopService(startServ);
        if(mBluetoothReceiver!=null){
            unregisterReceiver(mBluetoothReceiver);
        }

        super.onDestroy();

    }
}
