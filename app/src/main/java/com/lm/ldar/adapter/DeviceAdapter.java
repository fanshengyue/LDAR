package com.lm.ldar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Factory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/20.
 */

public class DeviceAdapter extends BaseAdapter {
    private Activity activity;
    private List<Device> data;

    public DeviceAdapter(Activity activity, List<Device> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_text, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvText.setText(data.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
