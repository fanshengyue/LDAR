package com.lm.ldar.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public static void saveBitmap(Context mContext,Bitmap bm, String image_path,String pic_name) {
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
            Toast.makeText(mContext,"图片已保存到"+image_path+pic_name,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }




}
