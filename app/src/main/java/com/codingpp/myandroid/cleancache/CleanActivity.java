package com.codingpp.myandroid.cleancache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codingpp.myandroid.databinding.ActivityCleanBinding;

import java.io.File;
import java.util.Locale;

/**
 * 测试Activity
 *
 * @author sunpan
 * @date 2019/2/19
 */
public class CleanActivity extends AppCompatActivity {

    private File cacheFile;
    private File externalCacheFile;

    /**
     * KB与Byte的倍数
     */
    private static final int KB = 1024;
    /**
     * MB与KB的倍数
     */
    private static final int MB = KB * 1024;
    /**
     * GB与KB的倍数
     */
    private static final int GB = MB * 1024;

    private ActivityCleanBinding binding;

    public static void jump(Context context) {
        Intent intent = new Intent(context, CleanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCleanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView() {
        binding.contentMain.layCreateCache.setOnClickListener(view -> {
            //生成缓存
            copyAssets();
        });
        binding.contentMain.layCleanCache.setOnClickListener(view -> {
            //清理缓存
            deleteFolderFile(cacheFile.getAbsolutePath(), false);
            deleteFolderFile(externalCacheFile.getAbsolutePath(), false);
        });
    }

    /**
     * 初始化数据
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void initData() {
        cacheFile = getCacheDir().getAbsoluteFile();
        externalCacheFile = getExternalCacheDir().getAbsoluteFile();
        if (!externalCacheFile.exists()) {
            externalCacheFile.mkdirs();
        }
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        copyAssets();
    }

    /**
     * 拷贝Assets中文件到文件夹
     */
    private void copyAssets() {
        FileUtils.copyAssetsSingleFile(this, cacheFile.getAbsolutePath(), "app.apk");
        FileUtils.copyAssetsSingleFile(this, externalCacheFile.getAbsolutePath(), "app.apk");
        refreshCacheSize();
    }

    /**
     * 刷新显示缓存大小
     */
    private void refreshCacheSize() {
        long cacheSize = getTotalSize(cacheFile);
        long externalSize = getTotalSize(externalCacheFile);
        String result = byte2FitSize(cacheSize + externalSize);
        binding.contentMain.tvCacheSize.setText(result);
    }

    /**
     * 字节数转合适大小,保留3位小数
     *
     * @param byteNum 字节数
     * @return 1...1024 unit
     */
    private String byte2FitSize(long byteNum) {
        if (byteNum < 0) {
            return "";
        } else if (byteNum < KB) {
            return String.format(Locale.getDefault(), "%.1fB", (double) byteNum);
        } else if (byteNum < MB) {
            return String.format(Locale.getDefault(), "%.1fKB", (double) byteNum / KB);
        } else if (byteNum < GB) {
            return String.format(Locale.getDefault(), "%.1fMB", (double) byteNum / MB);
        } else {
            return String.format(Locale.getDefault(), "%.1fGB", (double) byteNum / GB);
        }
    }

    /**
     * 获取文件或文件夹大小
     *
     * @param file 文件或文件夹
     * @return 文件或文件夹大小
     */
    private long getTotalSize(File file) {
        if (file.isFile()) {
            return file.length();
        }
        File[] files = file.listFiles();
        long total = 0;
        if (files != null) {
            for (File child : files) {
                total += getTotalSize(child);
            }
        }
        return total;
    }

    /**
     * 清理缓存
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    if (file.isDirectory()) {
                        File[] files = file.listFiles();
                        for (File child : files) {
                            deleteFolderFile(child.getAbsolutePath(), true);
                        }
                    }
                    if (deleteThisPath) {
                        if (!file.isDirectory()) {
                            file.delete();
                        } else {
                            if (file.listFiles().length == 0) {
                                file.delete();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "清理失败", Toast.LENGTH_SHORT).show();
            }
        }
        refreshCacheSize();
    }
}
