package com.lm.ldar.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.LoginUserEntity;
import com.lm.ldar.entity.User;
import com.lm.ldar.util.DaoUtil;
import com.lm.ldar.util.LoginUserUtil;
import com.lm.ldar.util.NetUtil;
import com.lm.ldar.view.MyAlertDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class LoginUserListAdapter extends BaseAdapter {
    private Activity activity;
    private List<LoginUserEntity> data;
    private DaoSession daoSession;

    public LoginUserListAdapter(Activity activity, List<LoginUserEntity> data,DaoSession daoSession) {
        this.activity = activity;
        this.data = data;
        this.daoSession=daoSession;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_login_userlist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvUser.setText(data.get(position).getUsername());
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setMessage("确定要删除用户:"+data.get(position).getUsername()+"及其所有信息吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除数据库
                        DaoUtil.DeleteByUserId(activity,data.get(position).getId());
                        data.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.setTitle("提示");
                builder.create().show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_user)
        TextView tvUser;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
