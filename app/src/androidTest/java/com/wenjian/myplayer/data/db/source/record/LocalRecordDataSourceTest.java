package com.wenjian.myplayer.data.db.source.record;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wenjian.myplayer.AppExecutors;
import com.wenjian.myplayer.data.db.PlayerDatabase;
import com.wenjian.myplayer.data.db.source.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Description: LocalRecordDataSourceTest
 * Date: 2018/2/1
 *
 * @author jian.wen@ubtrobot.com
 */
@RunWith(AndroidJUnit4.class)
public class LocalRecordDataSourceTest {

    private static final Record RECORD_1 = new Record("小黄人", "http://www.baidu.com",
            20, System.currentTimeMillis(), 100, "myid");

    private static final Record RECORD_2 = new Record("haha", "http://www.baidu.com",
            20, System.currentTimeMillis(), 100, "dao");

    private Context mAppContext = InstrumentationRegistry.getTargetContext();
    private RecordDataSource mLocalRecordDataSource;
    private PlayerDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        mDatabase = PlayerDatabase.getInstance(mAppContext);
        mLocalRecordDataSource = LocalRecordDataSource.getInstance(mDatabase.recordDao(), new AppExecutors());
    }

    @After
    public void tearDown() throws Exception {
        mDatabase.close();
    }

    @Test
    public void saveList() throws Exception {
        List<Record> mList = new ArrayList<>();
        mList.add(RECORD_1);
        mList.add(RECORD_2);
        mLocalRecordDataSource.saveList(mList);

        mLocalRecordDataSource.loadAllAsync(new DataSource.LoadCallback<Record>() {
                                                @Override
                                                public void onDataLoaded(List<Record> dataList) {
                                                    assertEquals(2, dataList.size());
                                                }

                                                @Override
                                                public void onDataNotAvailable() {

                                                }
                                            }
        );

    }

    @Test
    public void saveListAsync() throws Exception {
//        mLocalRecordDataSource.saveListAsync();
    }

    @Test
    public void saveSingle() throws Exception {
        mLocalRecordDataSource.saveSingle(RECORD_1);

        mLocalRecordDataSource.loadAllAsync(new DataSource.LoadCallback<Record>() {
            @Override
            public void onDataLoaded(List<Record> dataList) {
                assertEquals(RECORD_1, dataList.get(0));
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    @Test
    public void loadAll() throws Exception {
        saveList();
    }

    @Test
    public void loadAllAsync() throws Exception {
        final List<Record> mList = new ArrayList<>();
        mList.add(RECORD_1);
        mList.add(RECORD_2);
        mLocalRecordDataSource.saveList(mList);

        mLocalRecordDataSource.loadAllAsync(new DataSource.LoadCallback<Record>() {
            @Override
            public void onDataLoaded(List<Record> dataList) {
                assertArrayEquals(mList.toArray(new Record[0]), dataList.toArray(new Record[0]));
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Test
    public void getRecordById() throws Exception {
        mLocalRecordDataSource.saveSingle(RECORD_1);

        Record record = mLocalRecordDataSource.getRecordById(RECORD_1.getId());
        assertEquals(RECORD_1, record);
    }

    @Test
    public void deleteRecordById() throws Exception {
        mLocalRecordDataSource.saveSingle(RECORD_1);

        mLocalRecordDataSource.deleteRecordById(RECORD_1.getId());

        mLocalRecordDataSource.loadAllAsync(new DataSource.LoadCallback<Record>() {
            @Override
            public void onDataLoaded(List<Record> dataList) {
                assertNull(dataList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });


    }

    @Test
    public void clearAll() throws Exception {
        mLocalRecordDataSource.saveSingle(RECORD_1);

        mLocalRecordDataSource.clearAll();

        mLocalRecordDataSource.loadAllAsync(new DataSource.LoadCallback<Record>() {
            @Override
            public void onDataLoaded(List<Record> dataList) {
                assertNull(dataList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

}