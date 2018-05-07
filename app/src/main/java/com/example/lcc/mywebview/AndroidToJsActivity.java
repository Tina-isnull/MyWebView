package com.example.lcc.mywebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 4.2以上相对简单的调用方法
 */
public class AndroidToJsActivity extends AppCompatActivity {
    private WebView myWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_to_js);
        myWebview = findViewById(R.id.main_webview_id);
        WebSettings webSettings=myWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebview.addJavascriptInterface(new AndroidToJs(),"android");
        myWebview.loadUrl("file:///android_asset/androidToJs");
    }
}
