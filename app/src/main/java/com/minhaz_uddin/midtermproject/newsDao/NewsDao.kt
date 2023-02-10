package com.minhaz_uddin.midtermproject.newsDao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.minhaz_uddin.midtermproject.model.Article
import com.minhaz_uddin.midtermproject.model.BookmarkArticle
import com.minhaz_uddin.midtermproject.model.CustomArticles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewsArticle(article: CustomArticles)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookmark(article: BookmarkArticle)

    @Query ("SELECT * FROM articlesDB ORDER BY publishedAt DESC")
    suspend fun readAllArticles():List<CustomArticles>

    @Query("SELECT * FROM bookmark_table")
     fun readAllBookmarks():LiveData<List<BookmarkArticle>>

    @Query("SELECT * FROM articlesDB WHERE category= :category ORDER BY publishedAt DESC")
    suspend fun readArticlesByCategory(category:String):List<CustomArticles>

    @Update
    suspend fun updateBookmark(article: CustomArticles)


    @Query("DELETE  FROM articlesDB")
    suspend fun deleteAllArticles()

    @Delete
    suspend fun deleteBookmark(article: BookmarkArticle)

}