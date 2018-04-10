package com.lm.ldar.bluetoothssp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.lm.ldar.R;
import com.lm.ldar.entity.MobileDevice;

import java.util.List;

/**
 * Created by sunray on 2017-7-20.
 */

public class MobileDeviceAdapter extends ArrayAdapter<MobileDevice> {
    private int resourceId;
    public MobileDeviceAdapter(Context context, int textResourceId, List<MobileDevice> data) {
        super(context, textResourceId,data);
        resourceId = textResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MobileDevice mobileDevice = getItem(position);
        View view ;
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder.deviceAddress = view.findViewById(R.id.content_view);
            viewHolder.deviceName = view.findViewById(R.id.name_view);
            view.setTag(viewHolder);
        }else
        {
            view= convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.deviceName.setText(mobileDevice.getmDeviceName());
        viewHolder.deviceAddress.setText(mobileDevice.getmMacAddress());
        return view;
    }

    class ViewHolder{
        TextView deviceName;
        TextView deviceAddress;
    }
}
