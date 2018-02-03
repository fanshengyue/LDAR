package com.lm.ldar.entity;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class Global {
    /**
     * 应用路径
     */
    public static String APP_DIR="LADR/";
    /**
     * 建档路径
     */
    public static String IMAGE_DIR_NAME=APP_DIR+"LADR_IMAGE/";
    /**
     * 检测图片下载路径
     */
    public static String IMAGE_DOWNLOAD_CHECK=APP_DIR+"LADR_DOWNLOAD_CHECK/";
    /**
     * 维修复测图片下载路径
     */
    public static String IMAGE_DOWNLOAD_REVIEW=APP_DIR+"LADR_DOWNLOAD_REVIEW/";
    /**
     * 下载图片拼接路径
     */
    public static String Image_PATH="/attachment/";
    /**
     * 临时存储静态类
     */
    public static ImageInfoEntity imageInfoEntity;

    /**
     * 当前位置信息
     */
    public static double Latitude;
    public static double Longitude;
}
