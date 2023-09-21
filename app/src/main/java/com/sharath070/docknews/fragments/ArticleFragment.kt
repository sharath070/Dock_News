package com.sharath070.docknews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sharath070.docknews.R
import com.sharath070.docknews.databinding.FragmentArticleBinding
import com.sharath070.docknews.databinding.FragmentSearchNewsBinding
import com.sharath070.docknews.models.Article
import com.sharath070.docknews.ui.NewsActivity
import com.sharath070.docknews.ui.NewsViewModel

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT)
                .show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}