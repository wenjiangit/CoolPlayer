package com.wenjian.base.data.db;


import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;
import android.util.Log;

import com.wenjian.base.data.db.source.collection.Collection;
import com.wenjian.base.data.db.source.collection.CollectionDao;
import com.wenjian.base.data.db.source.record.Record;
import com.wenjian.base.data.db.source.record.RecordDao;


/**
 * Description: PlayerDatabase
 * Date: 2018/1/26
 *
 * @author jian.wen@ubtrobot.com
 */
@Database(entities = {Collection.class, Record.class}, version = 2)
@TypeConverters(Converters.class)
public abstract class PlayerDatabase extends RoomDatabase {

    private static final String TAG = "PlayerDatabase";
    private static final Object LOCK = new Object();
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Collection ADD COLUMN update_time INTEGER");

            Log.d(TAG, "MIGRATION_1_2: " + database.getVersion());


        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE Collection ADD COLUMN update_time INTEGER");

            Log.d(TAG, "MIGRATION_2_3: " + database.getVersion());
        }
    };


    private static PlayerDatabase INSTANCE;


    public static void init(Application application) {
        synchronized (LOCK) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(application,
                        PlayerDatabase.class, "player.db")
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
        }
    }

    public static PlayerDatabase getInstance() {
        return INSTANCE;
    }

    public abstract RecordDao recordDao();

    public abstract CollectionDao collectionDao();


}
