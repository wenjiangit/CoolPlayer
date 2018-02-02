package com.wenjian.myplayer.ui.mine;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: DbManager
 * Date: 2018/1/31
 *
 * @author jian.wen@ubtrobot.com
 */

public class DbManager {

    private SQLiteDatabase mSqLiteDatabase;

    private static final String TAG = "DbManager";

    private static class Holder {
        private static final DbManager INSTANCE = new DbManager();
    }

    public static DbManager getInstance() {
        return Holder.INSTANCE;
    }

    public SQLiteDatabase getDatabase() {
        return mSqLiteDatabase;
    }

    private DbManager() {
        File dbFile = new File(Environment.getExternalStorageDirectory(), "DB_TEST");
        boolean success = true;
        if (!dbFile.exists()) {
            success = dbFile.mkdir();
        }
        if (!success) {
            throw new RuntimeException("创建数据库文件失败!!!");
        }
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(new File(dbFile, "test.db"), null);
    }


    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS USER (`ID` TEXT PRIMARY KEY, `NAME` TEXT, `AGE` INTEGER, `SEX` INTEGER NOT NULL)";
        mSqLiteDatabase.execSQL(createTableSql);
    }


    public long insert(User user) {
        startTimer();
        String insetSql = "INSERT OR REPLACE INTO USER (`ID`, `NAME`, `AGE`, `SEX`) VALUES (?,?,?,?)";
        SQLiteStatement stat = mSqLiteDatabase.compileStatement(insetSql);
        Log.d(TAG, "insert SQLiteStatement: " + stat);
        mSqLiteDatabase.beginTransaction();
        long result = 0;
        try {
            stat.bindString(1, user.getId());
            stat.bindString(2, user.getName());
            stat.bindLong(3, user.getAge());
            stat.bindLong(4, user.getSex());
            result = stat.executeInsert();
            mSqLiteDatabase.setTransactionSuccessful();
        } finally {
            mSqLiteDatabase.endTransaction();
        }
        endTimer();
        return result;
    }


    public List<User> getUsers() {
        startTimer();
        String querySql = "SELECT * FROM USER";
        Cursor cursor = mSqLiteDatabase.rawQuery(querySql, null);
        try {
            final int idColumnIndex = cursor.getColumnIndexOrThrow("ID");
            final int nameColumnIndex = cursor.getColumnIndexOrThrow("NAME");
            final int ageColumnIndex = cursor.getColumnIndexOrThrow("AGE");
            final int sexColumnIndex = cursor.getColumnIndexOrThrow("SEX");
            final List<User> result = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                String id = cursor.getString(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                int age = cursor.getInt(ageColumnIndex);
                int sex = cursor.getInt(sexColumnIndex);
                User user = new User(id, name, age, sex,0);
                result.add(user);
            }
            endTimer();
            return result;
        } finally {
            cursor.close();
        }
    }

    private static User createUserByCursor(Cursor cursor) {
        final int idColumnIndex = cursor.getColumnIndexOrThrow("ID");
        final int nameColumnIndex = cursor.getColumnIndexOrThrow("NAME");
        final int ageColumnIndex = cursor.getColumnIndexOrThrow("AGE");
        final int sexColumnIndex = cursor.getColumnIndexOrThrow("SEX");
        String id = cursor.getString(idColumnIndex);
        String name = cursor.getString(nameColumnIndex);
        int age = cursor.getInt(ageColumnIndex);
        int sex = cursor.getInt(sexColumnIndex);
        return new User(id, name, age, sex,0);
    }


    public User getUserById(String id) {
        startTimer();
        String sql = "SELECT * FROM USER WHERE ID = ?";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, new String[]{id});
        try {
            if (cursor.moveToFirst()) {
                return createUserByCursor(cursor);
            }
        } finally {
            endTimer();
            cursor.close();
        }
        return null;
    }

    public void deleteAll() {
        startTimer();
        final String sql = "DELETE FROM USER";
        SQLiteStatement stmt = mSqLiteDatabase.compileStatement(sql);
        mSqLiteDatabase.beginTransaction();
        try {
            stmt.executeUpdateDelete();
            mSqLiteDatabase.setTransactionSuccessful();
        } finally {
            endTimer();
            mSqLiteDatabase.endTransaction();
        }
    }

    public void saveAll(List<User> userList) {
        startTimer();
        String insetSql = "INSERT OR REPLACE INTO USER (`ID`, `NAME`, `AGE`, `SEX`) VALUES (?,?,?,?)";
        SQLiteStatement stat = mSqLiteDatabase.compileStatement(insetSql);
        Log.d(TAG, "insert SQLiteStatement: " + stat);
        mSqLiteDatabase.beginTransaction();
        try {
            for (User user : userList) {
                stat.bindString(1, user.getId());
                stat.bindString(2, user.getName());
                stat.bindLong(3, user.getAge());
                stat.bindLong(4, user.getSex());
                stat.executeInsert();
            }
            mSqLiteDatabase.setTransactionSuccessful();
        } finally {
            mSqLiteDatabase.endTransaction();
        }
        endTimer();
    }


    long start;

    private void startTimer() {
        start = System.currentTimeMillis();
    }

    private void endTimer() {
        long time = System.currentTimeMillis() - start;
        Log.d(TAG, "endTimer: " + time);
    }


}
