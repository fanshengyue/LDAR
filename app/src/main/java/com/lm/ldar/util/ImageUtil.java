package com.lm.ldar.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;
import android.view.View;

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
    public Bitmap convertViewToBitmap(View view) {

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);

        //把view中的内容绘制在画布上
        view.draw(canvas);

        return bitmap;
    }


}
