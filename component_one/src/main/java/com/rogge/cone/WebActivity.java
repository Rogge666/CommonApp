package com.rogge.cone;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.rogge.api.ComponentOneAPI;
import com.rogge.base.BaseActivity;

import butterknife.BindView;

@Route(path = ComponentOneAPI.WEB_ACTIVITY)
public class WebActivity extends BaseActivity {

    @Autowired
    public String title;
    @Autowired
    public String content;
    @Autowired(name = "isUrl")
    boolean isUrl;

    @BindView(R2.id.web_wv)
    WebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        ARouter.getInstance().inject(this);
        mToolBarManager.setTitle(title);
        WebSettings mWebSettings = mWebView.getSettings();
        //支持javascript
        mWebSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebSettings.setSupportZoom(true);
        // 设置出现缩放工具
        mWebSettings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        mWebSettings.setUseWideViewPort(true);
        //自适应屏幕
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setLoadWithOverviewMode(true);
        if (isUrl) {
            mWebView.loadUrl(content);
        } else {
            mWebView.loadData(content, "text/html; charset=UTF-8", null);
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }

}
