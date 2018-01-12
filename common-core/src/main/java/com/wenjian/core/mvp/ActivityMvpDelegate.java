package com.wenjian.core.mvp;

import android.os.Bundle;

/**
 * mvp的activity生命周期的代理
 *
 * @author wenjian
 * @date 2017/8/31
 */

public interface ActivityMvpDelegate {

    void onCreate(Bundle savedInstanceState);

    void onResume();

    void onRestart();

    void onPause();

    void onDestroy();

    void onStart();

    void onStop();

}
