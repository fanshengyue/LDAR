package com.lm.ldar.entity;

/**
 * Created by sunray on 2017-7-20.
 */

public class MobileDevice {
    private String mDeviceName;
    private String mMacAddress;

    public MobileDevice(String mDeviceName, String mMacAddress){
        this.mDeviceName = mDeviceName;
        this.mMacAddress = mMacAddress;
    }

    public String getmDeviceName() {
        return mDeviceName;
    }

    public String getmMacAddress() {
        return mMacAddress;
    }
}
