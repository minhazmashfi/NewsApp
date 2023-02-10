package com.minhaz_uddin.midtermproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.minhaz_uddin.midtermproject.R
import com.minhaz_uddin.midtermproject.model.Constants


class DetailNews : Fragment() {
    private lateinit var webUrl:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       arguments?.let {
           webUrl=it.getString(Constants.NEWS_BASE).toString()
       }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView=view.findViewById<WebView>(R.id.web_view)
        webView.webViewClient= WebViewClient()
        webView.loadUrl(webUrl)
    }


}