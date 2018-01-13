package com.lm.ldar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.lm.ldar.R;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class WelcomeActivity extends BaseActivity {
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        gotoMain();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        WelcomeActivity.this.finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void gotoMain() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
