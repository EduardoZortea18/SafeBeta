package com.example.safe_v02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoogleTradutor extends AppCompatActivity {
WebView webView;
Toolbar toolbarTradutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_tradutor);

        toolbarTradutor = (Toolbar) findViewById(R.id.toolbarTradutor);
        setSupportActionBar(toolbarTradutor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        webView = (WebView)findViewById(R.id.webViewTradutor);
        webView.loadUrl("https://translate.google.com.br/?hl=pt-BR");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
