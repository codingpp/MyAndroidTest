package com.codingpp.myandroid.cleancache;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类
 *
 * @author sunpan
 * @date 2019/2/19
 */
class FileUtils {

    /**
     * 拷贝Assets中文件到文件夹
     *
     * @param context  context
     * @param outPath  输出路径
     * @param fileName 文件名称
     */
    static void copyAssetsSingleFile(Context context, String outPath, String fileName) {
        File file = new File(outPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            File outFile = new File(file, System.currentTimeMillis() + ".apk");
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
