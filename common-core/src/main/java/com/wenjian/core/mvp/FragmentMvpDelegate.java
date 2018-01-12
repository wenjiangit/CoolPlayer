package com.wenjian.core.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * mvp中fragment生命周期的代理
 *
 * @author douliu
 * @date 2017/8/31
 */

public interface FragmentMvpDelegate {

    void onCreate(Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onDestroyView();
}
