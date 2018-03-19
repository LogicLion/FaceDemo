package com.example.tufei.facedemo.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件读取工具类
 *
 * @author wzh
 * @date 2018/1/11
 */
public class FileUtil {
    /**
     * 读取Assets目录中的图片，并转换成bytes数组返回
     *
     * @param context  上下文
     * @param fileName 图片名称(包含后缀)
     * @return
     */
    public static byte[] getBytesFromAssets(Context context, String fileName) {
        byte[] bytes = null;
        InputStream inputStream=null;
        try {
            inputStream = context.getAssets().open(fileName);
            int len;
            len = inputStream.available();
            bytes = new byte[len];
            inputStream.read(bytes, 0, len);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 读取res文件夹中的图片，并转换成bytes数组返回
     *
     * @param fileName 图片名称(包含后缀)
     * @param context  上下文
     * @return
     * @throws IOException
     */
//    public static byte[] getBytesFromRes(Context context,String fileName) {
//        byte[] bytes=null;
//        InputStream inputStream=null;
//        try {
//            inputStream = getClass().getResourceAsStream(fileName);
//            int len;
//            len = inputStream.available();
//            bytes = new byte[len];
//            inputStream.read(bytes, 0, len);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return bytes;
//    }


    /**
     res/raw和assets的不同点：
     1.res/raw中的文件会被映射到R.java文件中，访问的时候直接使用资源ID即R.id.filename；assets文件夹下的文件不会被映射到R.java中，访问的时候需要AssetManager类。
     2.res/raw不可以有目录结构，而assets则可以有目录结构，也就是assets目录下可以再建立文件夹

     读取res/raw下的文件资源：InputStream is =getResources().openRawResource(R.raw.filename);
     读取Assets下的文件资源：InputStream is = context.getResources().getAssets().open(fileName);
     */
}
