package web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wenjian.web.R;
import com.wenjian.web.WebActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        WebActivity.start(this,"http://www.baidu.com");

    }
}
