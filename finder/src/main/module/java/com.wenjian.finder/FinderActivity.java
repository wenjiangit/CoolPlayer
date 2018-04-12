package com.wenjian.finder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wenjian.home.R;

public class FinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);

        FinderFragment fragment = new FinderFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content, fragment)
                .commit();

    }
}
