package com.wenjian.myplayer.utils;

import android.os.Environment;

import com.wenjian.core.utils.CloseUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Description: FileUtils
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class FileUtils {


    /**
     * 根据key-value保存一个临时文件到sdcard
     *
     * @param key   文件名
     * @param value 字符串
     */
    public static boolean save(String key, String value) {
        File storageDirectory = Environment.getExternalStorageDirectory();
        File saveFile = new File(storageDirectory, key);
        if (saveFile.exists()) {
            saveFile.delete();
        }
        FileOutputStream fos = null;
        PrintWriter writer = null;
        try {
            fos = new FileOutputStream(saveFile);
            writer = new PrintWriter(fos);
            writer.write(value);
            writer.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIOQuietly(fos);
            CloseUtils.closeIOQuietly(writer);
        }
        return false;
    }
}
