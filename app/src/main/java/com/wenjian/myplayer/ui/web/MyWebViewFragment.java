package com.wenjian.myplayer.ui.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

/**
 * Description: MyWebViewFragment
 * Date: 2017/12/7
 *
 * @author jian.wen@ubtrobot.com
 */

public class MyWebViewFragment extends Fragment {

    private static final String TAG = "MyWebViewFragment";
    private String mUrl;
    public static final String ARGS_URL = "url";
    private WebView mWebView;
    private boolean mIsWebViewAvailable = false;
    @Nullable
    private IChrome mChrome;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private FullscreenHolder mFullscreenHolder;

    public static MyWebViewFragment newInstance(@NonNull String url) {
        Bundle args = new Bundle();
        args.putString(ARGS_URL, url);
        MyWebViewFragment fragment = new MyWebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IChrome) {
            mChrome = (IChrome) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = (String) getArguments().get(ARGS_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mWebView != null) {
            mWebView.destroy();
        }
        mWebView = new WebView(getContext());
        mIsWebViewAvailable = true;
        return mWebView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initWebView(mWebView);
        mWebView.loadUrl(mUrl);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(WebView webView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());

        final WebSettings settings = webView.getSettings();

        //允许混合加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //支持JavaScript调用
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        //文件访问权限
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setPluginState(WebSettings.PluginState.ON);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        mWebView.onResume();
        super.onResume();
        mWebView.resumeTimers();
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        mWebView.onPause();
        mWebView.pauseTimers();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        mIsWebViewAvailable = false;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        if (mWebView != null) {
            clearWebViewCache();
            mWebView.removeAllViews();
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * 清除WebView缓存,包括js缓存
     * 具体见博客说明 http://blog.csdn.net/liwei123liwei123/article/details/52624826
     */
    private void clearWebViewCache() {
        CookieSyncManager.createInstance(getContext());  //Create a singleton CookieSyncManager within a context
        CookieManager cookieManager = CookieManager.getInstance(); // the singleton CookieManager instance
        cookieManager.removeAllCookie();// Removes all cookies.
        CookieSyncManager.getInstance().sync(); // forces sync manager to sync now
        mWebView.setWebChromeClient(null);
        mWebView.setWebViewClient(null);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.clearCache(true);
    }

    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }


    /**
     * 自身能否处理返回事件
     *
     * @return 能则返回true 否则返回false
     */
    public boolean canHandleBack() {
        final WebView webView = getWebView();
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return false;
    }

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }

        FrameLayout decorView = (FrameLayout) getActivity().getWindow().getDecorView();
        mFullscreenHolder = new FullscreenHolder(getActivity());
        mFullscreenHolder.addView(view);
        decorView.addView(mFullscreenHolder);
        mFullscreenHolder.setScaleX(0);
        mFullscreenHolder.setScaleY(0);
        mFullscreenHolder.setAlpha(0);
        mFullscreenHolder.animate()
                .scaleX(1)
                .scaleY(1)
                .alpha(1)
                .setDuration(300);
        mCustomView = view;
        mCustomViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (mCustomView == null) {
            return;
        }

        final FrameLayout decorView = (FrameLayout) getActivity().getWindow().getDecorView();
        mFullscreenHolder.animate()
                .alpha(0)
                .scaleX(0)
                .scaleY(0)
                .setDuration(300)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        decorView.removeView(mFullscreenHolder);
                        mCustomViewCallback.onCustomViewHidden();
                        mFullscreenHolder = null;
                        mCustomView = null;
                        mWebView.setVisibility(View.VISIBLE);
                    }
                });
        mWebView.setVisibility(View.GONE);
    }

    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ContextCompat.getColor(ctx, android.R.color.black));
        }

    }


    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.d(TAG, "onProgressChanged: " + newProgress);
            if (mChrome != null) {
                mChrome.setLoadingProgress(newProgress);
            }
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            showCustomView(view, callback);

        }

        @Override
        public void onHideCustomView() {
            hideCustomView();
        }

        // @Override
        // 这个是2.3以前的
        @Override
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            Log.d(TAG, "line: " + lineNumber + ",sourceID: " + sourceID + ", " + message);
        }

        // @Override
        // 这个是2.3以及2.3以后的
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            onConsoleMessage(consoleMessage.message(), consoleMessage.lineNumber(),
                    consoleMessage.sourceId());
            return true;
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mChrome != null) {
                mChrome.setWebTitle(title);
            }
        }
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "shouldOverrideUrlLoading: url >>> " + url);
            view.loadUrl(url);
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            Uri url = request.getUrl();
            Log.d(TAG, "request url >>> : " + url.toString());
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e(TAG, "onReceivedError:  errorCode >>> " + errorCode + " description >>> " + description + " failingUrl >>> " + failingUrl);
            if (mChrome != null) {
                mChrome.showError();
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (mChrome != null) {
                mChrome.showLoading();
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (mChrome != null) {
                mChrome.stopLoading();
            }
        }


    }


}
