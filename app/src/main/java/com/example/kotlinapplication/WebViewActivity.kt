package com.example.kotlinapplication

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.kotlinapplication.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding:ActivityWebViewBinding
    private lateinit var webView: WebView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_web_view)
        setContentView(binding.root)
        webView = binding.webViewBlog

        var s = intent.getStringExtra("blog")
        Log.i(ContentValues.TAG, "data: = $s")

        webView.webViewClient = WebViewClient().apply {
            webView.settings.javaScriptEnabled = true
            webView.settings.safeBrowsingEnabled = true
            webView.loadUrl("https://www.google.com/")
//            if (s != null) {
//                webView.loadUrl(s)
//            }
        }



    }
}