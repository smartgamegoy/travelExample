package com.example.interviewapplication.ui.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.interviewapplication.databinding.FragmentWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private var _binding: FragmentWebviewBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: WebViemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[WebViemViewModel::class.java]
        setFragmentResultListener("webView") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val url = bundle.getString("urlString")
            url?.let {
                //載入網頁
                binding.webView.loadUrl(it)
                viewModel.urlText.set(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            if(binding.webView.canGoBack())
                binding.webView.goBack()
            else
                parentFragmentManager.popBackStack()
        }

        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        val webSetting = binding.webView.settings
        //啟用JavaScript
        webSetting.javaScriptEnabled = true
        //啟用自動載入圖片
        webSetting.loadsImagesAutomatically = true

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.webView.reload()
        }

        // 當網頁載入完成後，停止下拉刷新動畫
        binding.webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                viewModel.urlText.set(url)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}