package com.lm.ldar.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.network.framework.FailCallback;
import com.example.network.framework.NetworkFactory;
import com.example.network.framework.SuccessfulCallback;
import com.example.network.workUtils.OkhttpFactory;
import com.lm.ldar.R;
import com.lm.ldar.api.UrlManager;
import com.lm.ldar.entity.Global;
import com.lm.ldar.view.LoadingDialog;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class ImageUtil {
    public ImageUtil(){}
    /**
     * view转成Bitmap
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);

        //把view中的内容绘制在画布上
        view.draw(canvas);

        return bitmap;
    }

    /** 保存方法 */
    public static void saveBitmap(Context mContext,Bitmap bm, String image_path,String pic_name,boolean isShowToast) {
        File f = new File(image_path, pic_name);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i(TAG, "已经保存");
            if(isShowToast){
                Toast.makeText(mContext,"图片已保存到"+image_path+pic_name,Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 删除图片
     * @param imagePath
     */
    public static void DeleteImage(String imagePath){
        if(!IsNullOrEmpty.isEmpty(imagePath)){
            File file = new File(imagePath);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }


    /**
     * 下载图片并存储
     */
    public static void DownloadImageAndSave(final Context mContext, final String dir_path, String imageUrl, final String imageName){
        File f = new File(dir_path);
        if (!f.exists()) {
            f.mkdirs();
        }

        NetworkFactory factory= OkhttpFactory.getInstance();
        SuccessfulCallback successfulCallback=new SuccessfulCallback() {
            @Override
            public void success(String str) throws JSONException {

            }

            @Override
            public void success(InputStream ism, long conentLength) throws IOException {
                if(ism!=null){
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize = 1;
                    Bitmap bitmap = BitmapFactory.decodeStream(ism,null,options);
                    if(bitmap!=null){
                        saveBitmap(mContext,bitmap,dir_path,imageName,false);
                    }
                }
            }
        };
        FailCallback failCallback=new FailCallback() {
            @Override
            public void fail(String str) {

            }
        };
        ((OkhttpFactory)factory).downloadFile(imageUrl,successfulCallback,failCallback);
    }

    /**
     * 拼接图片下载地址
     */
    public static String getImageUrl(String baseurl,String ecode,String vid,String imagename){
//        BaseUrl/LDAR2.0/attachment/组织机构代码/版本号ID/图片名称
        String imageUrl="";
        if(!IsNullOrEmpty.isEmpty(imagename)){
            imageUrl=baseurl+Global.Image_PATH+ecode+"/"+vid+"/modify/"+imagename;
        }
        return imageUrl;
    }

}
