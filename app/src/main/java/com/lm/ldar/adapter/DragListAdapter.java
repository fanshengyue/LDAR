package com.lm.ldar.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.activity.DragListActivity;
import com.lm.ldar.activity.ImageBluetoothActivity;
import com.lm.ldar.dao.PictureDao;
import com.lm.ldar.dao.PictureDownloadDao;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.util.DaoUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fanshengyue on 2018/4/8.
 */

public class DragListAdapter extends RecyclerView.Adapter<DragListAdapter.MainContentViewHolder> {
    /**
     * Item点击监听
     */
    private OnItemClickListener mItemOnClickListener;

    /**
     * Item点击监听
     */
    private OnLongClickListener mLongClickListener;

    /**
     * 数据
     */
    private List<Picture> pictureList = null;

    /**
     * Item拖拽滑动帮助
     */
    private ItemTouchHelper itemTouchHelper;

    private Activity activity;

    private PictureDao pictureDao;

    public DragListAdapter(List<Picture> pictureList, Activity activity, PictureDao pictureDao) {
        this.pictureList = pictureList;
        this.activity=activity;
        this.pictureDao=pictureDao;
    }

    public void notifyDataSetChanged(List<Picture> pictureList) {
        this.pictureList = pictureList;
        super.notifyDataSetChanged();
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemOnClickListener = onItemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener mLongClickListener) {
        this.mLongClickListener = mLongClickListener;
    }

    @Override
    public MainContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(MainContentViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return pictureList == null ? 0 : pictureList.size();
    }

    public Picture getData(int position) {
        return pictureList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnLongClickListener {
        void onLongClick(View view, int position);
    }

    class MainContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener{

        /**
         * 名字和描述
         */
        private TextView tvName, tvDesc;
        /**
         * 图片
         */
        private ImageView image;

        private RelativeLayout rlmain;

        private Button btDelete;

        public MainContentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            image =  itemView.findViewById(R.id.image);
            rlmain=itemView.findViewById(R.id.rl_main);
            btDelete=itemView.findViewById(R.id.bt_deletes);
            image.setOnTouchListener(this);
            rlmain.setOnClickListener(this);
            btDelete.setOnClickListener(this);
        }

        /**
         * 给这个Item设置数据
         */
        public void setData() {
            Picture picture = getData(getAdapterPosition());
            tvName.setText(picture.getName());
            String desc=picture.getPosition()+"_"+picture.getDeviceinfo()+"_"+picture.getMaterial();
            tvDesc.setText(desc);
            File file=new File(picture.getSketch());
            if(file.exists()){
                image.setImageURI(Uri.fromFile(file));
            }else{
                image.setImageDrawable(activity.getResources().getDrawable(R.drawable.default_img));
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_main:
                    List<PictureDownload>pictures=new ArrayList<>();
                    if(pictureList!=null&&pictureList.size()>0){
                        for(Picture picture:pictureList){
                            PictureDownload download=new PictureDownload();
                            download.setId(picture.getId());
                            download.setNumber(picture.getNumber());
                            download.setName(picture.getName());
                            download.setStatus(picture.getStatus());
                            download.setCreatetime(picture.getCreatetime());
                            download.setDeviceinfo(picture.getDeviceinfo());
                            download.setMaterial(picture.getMaterial());
                            download.setPosition(picture.getPosition());
                            download.setDid(picture.getDid());
                            download.setAid(picture.getAid());
                            download.setEid(picture.getEid());
                            download.setElementname(picture.getElementname());
                            download.setPidnumber(picture.getPidnumber());
                            download.setPvid(picture.getPvid());
                            download.setSketch(picture.getSketch());
                            download.setLatitude(picture.getLatitude());
                            download.setLongitude(picture.getLongitude());
                            download.setIscheck(0);
                            download.setXid(picture.getXid());
                            pictures.add(download);
                        }
                    }
                    Intent intent = new Intent(activity, ImageBluetoothActivity.class);
                    intent.putExtra("imagelist", (Serializable) pictures);
                    intent.putExtra("type", 0);
                    intent.putExtra("position", getAdapterPosition());
                    activity.startActivity(intent);
                    break;
                case R.id.bt_deletes:
                    if (pictureList != null) {
                        final long lid = pictureList.get(getAdapterPosition()).getId();
                        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage("确定要删除此张图片及其数据吗？");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //删除数据库
                                DaoUtil.deletePic(pictureDao, lid);
                                pictureList.remove(getAdapterPosition());
                                notifyItemRemoved(getAdapterPosition());
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("取消", null);
                        builder.setTitle("提示");
                        builder.create().show();
                    }
                    break;

            }
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (view == image)
                itemTouchHelper.startDrag(this);
            return true;
        }

    }
}
