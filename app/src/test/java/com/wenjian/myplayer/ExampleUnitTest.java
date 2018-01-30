package com.wenjian.myplayer;

import android.net.Uri;

import org.junit.Test;

import java.net.URI;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void agb() throws Exception {
        System.out.println("透明度 | 十六进制");
        System.out.println("---- | ----");
        for (double i = 1; i >= 0; i -= 0.01) {
            i = Math.round(i * 100) / 100.0d;
            int alpha = (int) Math.round(i * 255);
            String hex = Integer.toHexString(alpha).toUpperCase();
            if (hex.length() == 1) hex = "0" + hex;
            int percent = (int) (i * 100);
            System.out.println(String.format("%d%% | %s", percent, hex));
        }
    }


    @Test
    public void uri_test() throws Exception {
        String url = "http://www.baidu.com?catalogId=dadadfhalfjadajd";

        String query = URI.create(url).getQuery();

        System.out.println(query);
    }

   @Test
    public void date_test() throws Exception {
       System.out.println(String.format("%tF", System.currentTimeMillis()));
    }


}