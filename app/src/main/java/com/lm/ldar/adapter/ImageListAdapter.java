package com.lm.ldar.adapter;

import android.app.Activity;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/31.
 */

public class ImageListAdapter extends BaseAdapter {
    private Activity activity;
    private List<PictureDownload> data;
    private int type;
    String path;
    public ImageListAdapter(Activity activity, List<PictureDownload> data,int type) {
        this.activity = activity;
        this.data = data;
        this.type=type;
        path=Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_imagelist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String desc=data.get(position).getPosition()+"_"+data.get(position).getDeviceinfo()+"_"+data.get(position).getMaterial();
        viewHolder.tvName.setText(data.get(position).getId()+"");
        viewHolder.tvDesc.setText(desc);
        File file = null;

        if(type==0){
            file=new File(data.get(position).getSketch());
        }
        else if(type==1){
            file=new File( path+Global.IMAGE_DOWNLOAD_CHECK+data.get(position).getAid()+"/"+data.get(position).getElementname());
        }
        else if(type==2){
            file=new File(path+Global.IMAGE_DOWNLOAD_REVIEW+data.get(position).getAid()+"/"+data.get(position).getElementname());
        }

        if(file.exists()){
            viewHolder.image.setImageURI(Uri.fromFile(file));
        }else{
            viewHolder.image.setImageDrawable(activity.getResources().getDrawable(R.drawable.default_img));
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
