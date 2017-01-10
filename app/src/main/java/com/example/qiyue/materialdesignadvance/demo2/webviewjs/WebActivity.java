package com.example.qiyue.materialdesignadvance.demo2.webviewjs;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

import cn.pedant.SafeWebViewBridge.InjectedChromeClient;

/**
 * 使用第三方https://github.com/pedant/safe-java-js-webview-bridge
 */
public class WebActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WebView wv = new WebView(this);
        setContentView(R.layout.web_layout);
        TextView textView = (TextView)findViewById(R.id.tv_textView);
        textView.setText("这里通过第三方库实现js调用本地的安全实现，" +
                "之前版本通过JavascriptInterface和@JavascriptInterface存在兼容和安全的问题，这里" +
                "用另一种方式，就是我们设置一个WebchrhromeClient这里面有一些方法是监听webView的一些window对象调用" +
                "因此我们利用这里方法做一些处理，在这个方法当中我们可以使用用处最少的一个方法来代替，" +
                "那就是window.prompt方法，其他的就是一些封装，这是核心原理,");
        WebView wv = (WebView)findViewById(R.id.webview);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.setWebChromeClient(
            new CustomChromeClient("HostApp", HostJsScope.class)
        );
        wv.loadUrl("file:///android_asset/test.html");
    }

    public class CustomChromeClient extends InjectedChromeClient {

        public CustomChromeClient (String injectedName, Class injectedCls) {
            super(injectedName, injectedCls);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            // to do your work
            // ...
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onProgressChanged (WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // to do your work
            // ...
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            // to do your work
            // ...
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }
}
