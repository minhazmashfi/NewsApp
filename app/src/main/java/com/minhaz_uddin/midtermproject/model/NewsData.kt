package com.minhaz_uddin.midtermproject.model

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)