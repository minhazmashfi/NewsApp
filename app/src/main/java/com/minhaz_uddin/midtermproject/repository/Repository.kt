package com.minhaz_uddin.midtermproject.repository

import androidx.lifecycle.LiveData
import com.minhaz_uddin.midtermproject.model.BookmarkArticle
import com.minhaz_uddin.midtermproject.model.CustomArticles
import com.minhaz_uddin.midtermproject.newsDao.NewsDao

class Repository(private val newsDao: NewsDao) {
   suspend fun readNewsByCategory(category:String):List<CustomArticles> =newsDao.readArticlesByCategory(category)
   fun readAllBookmark():LiveData<List<BookmarkArticle>> =newsDao.readAllBookmarks()
   suspend fun readAllArticles():List<CustomArticles> =newsDao.readAllArticles()
    suspend fun addNewsArticle(articles: CustomArticles) {
        newsDao.addNewsArticle(articles)
    }
    suspend fun deleteAllNews() {
        newsDao.deleteAllArticles()
    }
    suspend fun updateBookmark(articles: CustomArticles){
        newsDao.updateBookmark(articles)
    }
    suspend fun addBookmark(articles: BookmarkArticle){
        newsDao.addBookmark(articles)
    }
    suspend fun deleteBookmark(articles: BookmarkArticle){
        newsDao.deleteBookmark(articles)
    }







}