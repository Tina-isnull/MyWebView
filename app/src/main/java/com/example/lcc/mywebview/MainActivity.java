package com.example.lcc.mywebview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private WebView myWebview;
    private Button web_button;
    private Button but_jump;
    private Button but_jump3;
    private Button but_jump4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebview = findViewById(R.id.main_webview_id);
        web_button = findViewById(R.id.web_button_id);
        but_jump = findViewById(R.id.but_jump);
        but_jump3 = findViewById(R.id.but_jump3);
        but_jump4 = findViewById(R.id.but_jump4);
        //配置类
        WebSettings webSettings = myWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        //设置允许弹框
        myWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        myWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        myWebview.loadUrl("file:///android_asset/javascript");
        but_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AndroidToJsActivity.class));
            }
        });
        but_jump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AndroidToJs2Activity.class));
            }
        });
        but_jump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AndroidToJs3Activity.class));
            }
        });
        web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebview.post(new Runnable() {
                    @Override
                    public void run() {
                        // Android版本变量
                        final int version = Build.VERSION.SDK_INT;
                        // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
                        if (version < 18) {
                            myWebview.loadUrl("javascript:callJS()");
                        } else {
                            myWebview.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String value) {
                                    //此处为 js 返回的结果
                                }
                            });
                        }
                    }
                });
            }
        });
        //原生
//        myWebview.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                return super.onJsAlert(view, url, message, result);
//            }
//        });
        //自定义
        myWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });

    }
}
