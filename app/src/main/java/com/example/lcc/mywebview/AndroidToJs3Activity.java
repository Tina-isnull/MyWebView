package com.example.lcc.mywebview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Set;

/**
 * 拦截onJsPrompt 返回值方便拿取
 */

public class AndroidToJs3Activity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_to_js3);
        webView = findViewById(R.id.main_webview_id);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("file:///android_asset/androidToJs3");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("demo")) {
                        System.out.println("js调用了Android的方法");
                        //获得链接中传过来的参数
                        Set<String> collection = uri.getQueryParameterNames();
                        for (String str : collection) {
                            System.out.println("value=" + uri.getQueryParameter(str));
                        }
                        result.confirm("js调用了Android的方法成功啦");
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
            // 拦截JS的警告框
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            // 拦截JS的确认框
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });
    }
}
