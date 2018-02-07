package com.wenjian.myplayer.data.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Description: Converters
 * Date: 2018/2/5
 *
 * @author jian.wen@ubtrobot.com
 */

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
