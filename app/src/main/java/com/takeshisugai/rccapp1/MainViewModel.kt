 package com.takeshisugai.rccapp1

/*
 *   Copyright 2016 Marco Gomiero
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

//package com.prof.rssparser.sample.kotlin

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel



import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
// added 16th/Feb
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

 class MainViewModel : ViewModel() {


     private val url = "https://www.androidauthority.com/feed"

     private val viewModelJob = Job()
     private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

     private lateinit var articleListLive: MutableLiveData<MutableList<Article>>

     private val _snackbar = MutableLiveData<String>()

     val snackbar: LiveData<String>
         get() = _snackbar

     fun onSnackbarShowed() {
         _snackbar.value = null
     }

     fun getArticleList(): MutableLiveData<MutableList<Article>> {
         if (!::articleListLive.isInitialized) {
             articleListLive = MutableLiveData()
         }
         return articleListLive
     }

     private fun setArticleList(articleList: MutableList<Article>) {
         articleListLive.postValue(articleList)
     }

     override fun onCleared() {
         super.onCleared()
         viewModelJob.cancel()
     }

     fun fetchFeed() {
         coroutineScope.launch(Dispatchers.Main) {
             try {
                 val parser = Parser()
                 val articleList = parser.getArticles(url)
                 setArticleList(articleList)
             } catch (e: Exception) {
                 e.printStackTrace()
                 _snackbar.value = "An error has occurred. Please retry"
                 setArticleList(mutableListOf())
             }
         }
     }
 }