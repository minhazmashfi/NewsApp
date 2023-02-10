package com.minhaz_uddin.midtermproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "bookmark_table")
@Parcelize
data class BookmarkArticle (

    val author: String?,
    val content: String?,
    val category:String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?
):Parcelable