package com.wenjian.myplayer.data.db;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.wenjian.myplayer.data.db.model.Record;
import com.wenjian.myplayer.data.db.model.Record_Table;

import java.util.Collection;
import java.util.List;

/**
 * Description: AppDbHelper
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppDbHelper implements DbHelper {

    private AppDbHelper() {
    }

    private static class Holder {
        private static final AppDbHelper INSTANCE = new AppDbHelper();
    }

    public static AppDbHelper getInstance() {
        return Holder.INSTANCE;
    }


    @Override
    public <T> void save(final Class<T> clz, final Collection<T> ts) {
        if (ts == null || ts.isEmpty()) {
            return;
        }

        DatabaseDefinition database = FlowManager.getDatabase(DbflowDatabase.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(clz)
                        .saveAll(ts);
            }
        }).build().execute();
    }

    @Override
    public <T> void save(Class<T> clz, T t) {
        if (t == null) {
            return;
        }
        FlowManager.getModelAdapter(clz).save(t);
    }

    @Override
    public <T> void delete(final Class<T> clz, final Collection<T> ts) {
        if (ts == null || ts.isEmpty()) {
            return;
        }
        DatabaseDefinition database = FlowManager.getDatabase(DbflowDatabase.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                FlowManager.getModelAdapter(clz)
                        .deleteAll(ts);
            }
        }).build().execute();
    }

    @Override
    public <T> void delete(Class<T> clz, T t) {
        if (t == null) {
            return;
        }
        FlowManager.getModelAdapter(clz).delete(t);
    }

    public void deleteById(Class clz,String id) {
        SQLite.delete().from(clz)
                .where(Record_Table.id.eq(id))
                .query();
    }


    @Override
    public <T> List<T> loadAllSync(Class<T> clz) {
        return SQLite.select()
                .from(clz)
                .queryList();
    }

    @Override
    public <T> void loadAllAsync(Class<T> clz, final QueryCallback<T> callback) {
        SQLite.select()
                .from(clz)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<T>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @NonNull List<T> tResult) {
                        if (callback != null) {
                            callback.onQuerySuccess(tResult);
                        }
                    }
                }).execute();
    }



}
