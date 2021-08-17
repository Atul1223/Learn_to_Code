package com.example.learntocode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

public class DSAfragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View v;
        v=inflater.inflate(R.layout.dsa_fagment,container,false);
        WebView webView=v.findViewById(R.id.webViewDsa);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.geeksforgeeks.org/data-structures/");
        return v;
    }
}
