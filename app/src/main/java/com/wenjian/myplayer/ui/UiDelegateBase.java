package com.wenjian.myplayer.ui;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wenjian.core.utils.ToastUtils;
import com.wenjian.myplayer.R;
import com.wenjian.myplayer.ui.dialog.AppProgressDialog;

/**
 * Description: UiDelegateBase
 * Date: 2018/1/10
 *
 * @author jian.wen@ubtrobot.com
 */

public class UiDelegateBase implements UiDelegate {

    private AppCompatActivity mContext;

    private AppProgressDialog mProgressDialog;

    public UiDelegateBase(Context context) {
        if (context instanceof AppCompatActivity) {
            mContext = (AppCompatActivity) context;
        } else {
            throw new IllegalArgumentException("context must be AppCompatActivity !!!");
        }
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = AppProgressDialog.create();
        }
        mProgressDialog.show(mContext.getSupportFragmentManager());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onError(int resId) {
        showSnackBar(mContext.getString(resId));

    }

    @Override
    public void onError(String message) {
        showSnackBar(message);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showMessage(int resId) {
        ToastUtils.showShort(resId);
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make((mContext).findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        snackbar.show();
    }
}
