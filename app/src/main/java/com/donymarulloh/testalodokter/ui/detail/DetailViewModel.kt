package com.donymarulloh.testalodokter.ui.detail

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.donymarulloh.testalodokter.data.repository.Repository
import com.donymarulloh.testalodokter.ui.adapter.viewholder.ErrorStateItem
import com.donymarulloh.testalodokter.ui.adapter.viewholder.LoadingStateItem
import com.donymarulloh.testalodokter.ui.base.BaseViewItem
import com.donymarulloh.testalodokter.ui.base.BaseViewModel
import com.donymarulloh.testalodokter.util.SingleLiveEvent
import com.donymarulloh.testalodokter.util.rx.SchedulerProvider

class DetailViewModel(
    private val appRepository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    fun webView(webView: WebView, url:String, view: View){

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(url)

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                //For now only check if there is a throwable
                view!!.visibility = View.VISIBLE
                webView!!.visibility = View.GONE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                view!!.visibility = View.GONE
                webView!!.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }
        }

    }
}

