package com.minhaz_uddin.midtermproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.minhaz_uddin.midtermproject.NewsDatabase.NewsDatabase
import com.minhaz_uddin.midtermproject.model.Article
import com.minhaz_uddin.midtermproject.model.BookmarkArticle
import com.minhaz_uddin.midtermproject.model.CustomArticles
import com.minhaz_uddin.midtermproject.network.NewsApi
import com.minhaz_uddin.midtermproject.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val _newsList = MutableLiveData<List<Article>>()
    val newsRepository: Repository
    val _readAllArticles=MutableLiveData<List<CustomArticles>>()
    val readAllArticles=_readAllArticles
    val _readNewsByCategory=MutableLiveData<List<CustomArticles>>()
    val readNewsBycategory=_readNewsByCategory
    val _readBusinessNews=MutableLiveData<List<CustomArticles>>()
    val readbusinessNews=_readBusinessNews
    val _sportsNews=MutableLiveData<List<CustomArticles>>()
    val sportsNews=_sportsNews
    val _scienceNews=MutableLiveData<List<CustomArticles>>()
    val scienceNews=_scienceNews
    val _entertainmentNews=MutableLiveData<List<CustomArticles>>()
    val entertainmentNews=_entertainmentNews
    val _healthNews=MutableLiveData<List<CustomArticles>>()
    val healthnews=_healthNews






    init {
        val newsDao = NewsDatabase.getDatabaseInstance(application).newsDao()
        newsRepository = Repository(newsDao)
        getAllNews()
        readAllNews()
        rnbc("business")
        rnbc("sports")
        rnbc("science")
        rnbc("entertainment")
        rnbc("health")

    }

    fun getReadAllBookmarks():LiveData<List<BookmarkArticle>>{
        return newsRepository.readAllBookmark()
    }

     fun getAllNews() {

        val categories = listOf<String>(
            "sports",
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "technology"
        )

        viewModelScope.launch {

            for (category in categories) {
                _newsList.value = NewsApi.retrofitService.getAllNews(category).articles
                viewModelScope.launch(Dispatchers.IO) {
                    articleToCustom(category, _newsList)
                }
            }

        }
    }

    fun articleToCustom(category: String, articlesList: MutableLiveData<List<Article>>) {
        if (articlesList.value != null) {
            for (news in articlesList.value!!) {
                val articles = CustomArticles(
                    news.author, news.content, category,
                    news.description, news.publishedAt, news.title, news.url, news.urlToImage
                )
                addArticle(articles)
            }

        }

    }

    fun addArticle(articles: CustomArticles) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.addNewsArticle(articles)
        }

    }


    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteAllNews()
        }
    }
   fun readAllNews(){
       viewModelScope.launch {
           _readAllArticles.postValue(newsRepository.readAllArticles())
           Log.d("tag", "readAllNews:${readAllArticles.value} ")
       }
   }
    fun updateBookmark(articles: CustomArticles){
        viewModelScope.launch {
            newsRepository.updateBookmark(articles)
        }
    }
    fun addBookmark(article: BookmarkArticle){
       viewModelScope.launch {
           newsRepository.addBookmark(article)
       }

    }

    fun deleteBookmark(article: BookmarkArticle){
        viewModelScope.launch {
            newsRepository.deleteBookmark(article)
        }
    }
    fun rnbc(category: String){
        if (category=="business"){
            viewModelScope.launch {
                _readBusinessNews.postValue(newsRepository.readNewsByCategory(category))
            }
        }
        if(category=="sports"){
            viewModelScope.launch {
                _sportsNews.postValue(newsRepository.readNewsByCategory(category))
            }
        }
        if (category=="science"){
            viewModelScope.launch {
                _scienceNews.postValue(newsRepository.readNewsByCategory(category))
            }
        }
        if (category=="entertainment"){
            viewModelScope.launch {
                _entertainmentNews.postValue(newsRepository.readNewsByCategory(category))
            }
        }
        if (category=="health"){
            viewModelScope.launch {
                _healthNews.postValue(newsRepository.readNewsByCategory(category))
            }
        }
    }



}