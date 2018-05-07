package com.example.lcc.mywebview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Set;

/**
 * 返回值需要利用android调js的方法传递上去
 */
public class AndroidToJs2Activity extends AppCompatActivity {

    private WebView myWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_to_js2);
        myWebview = findViewById(R.id.main_webview_id);
        WebSettings webSettings = myWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        myWebview.loadUrl("file:///android_asset/androidToJs2");
        myWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("js")) {
                  if(uri.getAuthority().equals("webview")){
                      System.out.println("js调用了Android的方法");
                      //获得链接中传过来的参数
                      Set<String> collection=uri.getQueryParameterNames();
                      for (String str : collection) {
                          System.out.println("value="+uri.getQueryParameter(str));
                      }
                    }
                }
                return true;
            }
        });

    }
}
