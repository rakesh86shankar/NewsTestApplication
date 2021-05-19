package com.newsapi.newsapp.newsapplication.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.newsapi.newsapp.newsapplication.model.NewsPaperList
import com.newsapi.newsapp.newsapplication.model.SourceList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rakesh sankar on 9/12/2017.
 */
class APIClient {
    fun downloadArticleList(paperName: String?) {
        try {
            val apiService = client!!.create(APIInterface::class.java)
            val call = apiService.getArticleList("associated-press", APIKey)
            call.enqueue(object : Callback<NewsPaperList> {
                override fun onResponse(call: Call<NewsPaperList>, response: Response<NewsPaperList>) {
                    val articleList = response.body() ?: NewsPaperList.empty()
                     articleListMutableLiveData.value = articleList
                }

                override fun onFailure(call: Call<NewsPaperList>, t: Throwable) {
                    Log.v("OnFailure Issues>>>.", t.message!!)
                    articleListMutableLiveData.value = NewsPaperList.empty()
                }
            })
        } catch (e: Exception) {
            Log.v("Exception in getting Article Information", e.message!!)
        }
    }

    fun getNewsPaperResources(): MutableLiveData<SourceList?> {
        try {
            val apiService = client!!.create(APIInterface::class.java)
            val call = apiService.getNewsPaperSources(APIKey)
            call?.enqueue(object : Callback<SourceList> {
                override fun onResponse(call: Call<SourceList>, response: Response<SourceList>) {
                    Log.v("API Response", response.body().toString())
                    val sourceList = response.body()
                    newsPaperListMutableLiveData.value = sourceList
                }

                override fun onFailure(url: Call<SourceList>, t: Throwable) {
                    Log.v("API Response Url", url.toString())
                    Log.v("API Response Error", t.toString())
                }
            })
        } catch (e: Exception) {
            Log.v("Exception in getting newsPaper Information", e.message!!)
        }
        return newsPaperListMutableLiveData
    }

    companion object {
        const val ROOT_URL = "https://newsapi.org/v2/"
        var APIKey = "a3b94d1e4cab435d8096cc0f20060b96" //"a3b94d1e4cab435d8096cc0f20060b96";//a3b94d1e4cab435d8096cc0f20060b96
        private var retrofit: Retrofit? = null
        private val articleListMutableLiveData = MutableLiveData<NewsPaperList?>()
        private val newsPaperListMutableLiveData = MutableLiveData<SourceList?>()
        val client: Retrofit?
            get() {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                retrofit = Retrofit.Builder()
                        .baseUrl(ROOT_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .build()
                return retrofit
            }
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<SourceList>) {

}
