package com.example.lcc.mywebview;

import android.webkit.JavascriptInterface;

public class AndroidToJs extends Object {
    @JavascriptInterface
    public void hello(String msg){
        System.out.println(msg);
    }
}
