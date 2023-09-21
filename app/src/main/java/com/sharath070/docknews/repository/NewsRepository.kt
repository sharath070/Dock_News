package com.sharath070.docknews.repository

import com.sharath070.docknews.api.RetrofitInstance
import com.sharath070.docknews.db.ArticleDatabase
import com.sharath070.docknews.models.Article

class NewsRepository(private val db: ArticleDatabase) {


    suspend fun getBreakingNews(countryCode: String, pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)


}
