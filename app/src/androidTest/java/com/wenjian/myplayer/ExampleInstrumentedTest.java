package com.wenjian.myplayer;

import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.wenjian.myplayer", appContext.getPackageName());
    }

    @Test
    public void uri_test() throws Exception {
        String url = "http://www.baidu.com?catalogId=dadadfhalfjadajd";
        String catalogId = Uri.parse(url).getQueryParameter("catalogId");
        System.out.println(catalogId);
    }

    @Test
    public void db_test() throws Exception {

        /* for (int i = 0; i < 10; i++) {
             Record record = new Record();
             record.setId(UUID.randomUUID().toString());
             record.setTitle("test" + i);
             AppDbHelper.getInstance().saveSingle(record);
         }

         Thread.sleep(2000);

         List<Record> records = AppDbHelper.getInstance().loadAllRecords();
         Log.d("test", records.toString());*/

    }

    @Test
    public void initDatabase() {



    }

    @Test
    public void insertUsers() {

    }


}
