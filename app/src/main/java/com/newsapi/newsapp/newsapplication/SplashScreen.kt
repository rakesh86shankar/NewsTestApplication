package com.newsapi.newsapp.newsapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.newsapi.newsapp.newsapplication.model.Source
import com.newsapi.newsapp.newsapplication.model.SourceList
import com.newsapi.newsapp.newsapplication.viewModel.ArticleViewModel
import java.lang.Exception

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash)
        testMethod()
        /*new Handler().postDelayed(new Runnable() {

            */
        /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
        /*

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
        }
    }, SPLASH_TIME_OUT);*/
    }

    fun testMethod() {
        try {

            //ViewModelProviders.of(this).get(ClickCounterViewModel.class);
            val articleViewModel = ViewModelProviders.of(this@SplashScreen).get(ArticleViewModel::class.java)
            /**
             * Listened for data via default Observer
             */
//            articleViewModel.getNewsPaperSources()!!.observe(this@SplashScreen, Observer<SourceList> { (sources) ->
//                DataSingleton.getInstance().newsPaperSources = sources
//                val i = Intent(this@SplashScreen, NewAPIActivity::class.java)
//                startActivity(i)
//            })

            /*
            articleViewModel.getNewsPaperSources()?.observe(this, Observer {
                it?.let { response ->
                    if (!response.sources.isNullOrEmpty()) {
                        DataSingleton.getInstance().newsPaperSources = response.sources
                        val i = Intent(this@SplashScreen, NewAPIActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
                    }
                }
            })*/

//            articleViewModel.getNewsPaperSourcesSingle().subscribe {
//                t1: SourceList?, t2: Throwable? ->
//                if (t1 != null) {
//                    navigateToView(t1.sources ?: emptyList())
//                }
//                Log.v("Error",""+t2)
//            }
            navigateToView(emptyList())
        } catch (ex: Exception) {
            Log.v("Exception in downloading from model", ex.message!!)

        }
    }

    fun navigateToView(newsPaperSources: List<Source?>?) {
        DataSingleton.getInstance().newsPaperSources = newsPaperSources
        val i = Intent(this@SplashScreen, NewAPIActivity::class.java)
        startActivity(i)
    }

    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }
}

