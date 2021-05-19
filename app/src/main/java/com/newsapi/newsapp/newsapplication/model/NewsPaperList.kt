package com.newsapi.newsapp.newsapplication.model

data class NewsPaperList(
        val articles: List<Article>? = emptyList(),
        val status: String,
        val totalResults: Int,
        val id: String = ""
) {
        companion object {
                fun empty() = NewsPaperList(emptyList(),"Error", 0)
        }
}

data class Article(
        val author: String,
        val content: String,
        val description: String,
        val publishedAt: String,
        val source: Source,
        val title: String,
        val url: String,
        val urlToImage: String
)

data class SourceList(
        val sources: List<Source>,
        val status: String

){
        companion object {
                fun empty() = SourceList(emptyList(),"Error")
        }
}

data class Source(
        val id: String,
        val name: String,
        val description: String,
        val url: String,
        val category: String,
        val language: String,
        val country: String
)