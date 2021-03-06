package com.wenjian.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.wenjian.base.ui.BaseSimpleActivity;
import com.wenjian.base.widget.CommonTitleBar;

import butterknife.BindView;


/**
 * @author ubt
 */
public class WebActivity extends BaseSimpleActivity implements IChrome {

    @BindView(R2.id.title_bar)
    CommonTitleBar mTitleBar;
    private String mLoadUrl;

    private static final String EXTRA_LOAD_URL = "load_url";

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(EXTRA_LOAD_URL, url);
        context.startActivity(starter);
    }

    @Override
    protected Object getContentLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        mLoadUrl = bundle.getString(EXTRA_LOAD_URL);
        return !TextUtils.isEmpty(mLoadUrl);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, MyWebViewFragment.newInstance(mLoadUrl))
                .commit();
    }

    @Override
    public void stopLoading() {
        this.hideLoading();
    }

    @Override
    public void setLoadingProgress(int progress) {
    }

    @Override
    public void showError() {
    }

    @Override
    public void setWebTitle(String title) {
        mTitleBar.setTitle(title);
    }


    @Override
    public void showLoading() {
//        super.showLoading();

    }
}
