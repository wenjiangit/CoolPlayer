package com.wenjian.myplayer.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenjian.myplayer.R;
import com.wenjian.myplayer.ui.base.BaseDialog;

/**
 * Description: AppProgressDialog
 * Date: 2018/1/8
 *
 * @author jian.wen@ubtrobot.com
 */

public class AppProgressDialog extends BaseDialog {

    private static final String TAG = "AppProgressDialog";

    public static AppProgressDialog create() {
        return new AppProgressDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.lay_custom_dialog, container, false);
    }

    @Override
    public void setup(View view) {
    }


    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }


}
