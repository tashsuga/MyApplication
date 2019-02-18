package com.takeshisugai.rccapp1


import kotlinx.android.synthetic.main.activity_main.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView



import android.net.ConnectivityManager


import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

/*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
*/
//import androidx.lifecycle.Observer
import android.arch.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
import android.arch.lifecycle.ViewModelProviders

//import android.arch.lifecycle.ViewModelProvider


//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.DefaultItemAnimator
import android.support.v7.widget.DefaultItemAnimator


//import androidx.recyclerview.widget.LinearLayoutManager
//import android.support.v7.widget.LinearLayoutManager

//import com.google.android.material.snackbar.Snackbar

/*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
*/

/*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import android.arch.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import android.arch.lifecycle.ViewModelProvider



import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

// import android.support.v7.widget.RecyclerView.≈


import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*
*/

//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}



class MainActivity : AppCompatActivity() {


   private lateinit var adapter: ArticleAdapter
   private lateinit var viewModel: MainViewModel

    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // deleted by 18th/Feb.
        // setContentView(R.layout.activity_main)


        /*
        val urlString:String = "http://www.androidcentral.com/feed"
        val parser:Parser = Parser()
        parser.execute(urlString);
        */


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        //viewModel = listOf(ViewModelProvider).get(MainViewModel::class.java)
        //of(this@MainActivity).get(MainViewModel::class.java)

        //setSupportActionBar(toolbar)

        // 18th/Feb
        val layoutManager = LinearLayoutManager(this)
        val layoutAnimator = DefaultItemAnimator()

        //recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.itemAnimator = DefaultItemAnimator()
       // recycler_view.setHasFixedSize(true)

        viewModel.getArticleList().observe(this, Observer { articles ->

            if (articles != null) {
                val adapter = ArticleAdapter(articles)
                //adapter = adapter
                adapter.notifyDataSetChanged()
                 //これがいるかどうか不明。
                //progressBar.visibility = View.GONE
                //swipe_layout.isRefreshing = false
            }

        })


        //var value: Any = parser.onFinish(Parser.OnTaskCompleted() {
       //
        //}
            /*
            val data = listOf (
                "Alice", "Bob", "Charlie", "Dave", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy"
        )


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(this, data)

        */
    }



}

class RecyclerAdapter(context: Context, val data: List<String>) : RecyclerView.Adapter<ViewHolder>() {
    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // ここでViewHolderを作る
        return ViewHolder(inflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        // データの要素数を返す
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // ViewHolderを通してデータをViewに設定する
        holder.textView.text = data[position]
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.textView)
}

/*
class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val hoges: MutableList<String>
        // hoges = resources.getStringArray(arrayOf(R).hoges).toMutableList()

        //val hoges = resources.getStringArray(R.array.hoges).toMutableList()

        val hoges = resources.getStringArray(R.array.hoges).toMutableList()

        // Kotlin Android Extensionsを使っているので、つけたIDで直接指定できる（R.id.mainRecyclerView）
        // mainRecyclerView.adapter = RecyclerAdapter(this, this, hoges)
        mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext, "position $position was tapped", Toast.LENGTH_SHORT).show()
    }
}



import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
//import android.arch.lifecycle.LiveData
//import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager

//import android.arc
//import androidx.recyclerview.widget.LinearLayoutManager


import com.google.android.material.snackbar.Snackbar

import android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*



//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArticleAdapter
    private lateinit var viewModel: MainViewModel

    private val isNetworkAvailable: Boolean
    get() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.setHasFixedSize(true)

        viewModel.getArticleList().observe(this, Observer { articles ->

            if (articles != null) {
                adapter = ArticleAdapter(articles)
                recycler_view.adapter = adapter
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                swipe_layout.isRefreshing = false
            }

        })

        viewModel.snackbar.observe(this, Observer { value ->
            value?.let {
                Snackbar.make(root_layout, value, Snackbar.LENGTH_LONG).show()
                viewModel.onSnackbarShowed ()
            }

        })

        swipe_layout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipe_layout.canChildScrollUp()
        swipe_layout.setOnRefreshListener {
            adapter.articles.clear()
            adapter.notifyDataSetChanged()
            swipe_layout.isRefreshing = true
            viewModel.fetchFeed()
        }

        if (!isNetworkAvailable) {

            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.alert_message)
                .setTitle(R.string.alert_title)
                .setCancelable(false)
                .setPositiveButton(R.string.alert_positive
                ) { dialog, id -> finish() }

            val alert = builder.create()
            alert.show()

        } else if (isNetworkAvailable) {
            viewModel.fetchFeed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_settings) {
            val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this@MainActivity).create()
            alertDialog.setTitle(R.string.app_name)
            alertDialog.setMessage(Html.fromHtml(this@MainActivity.getString(R.string.info_text) +
                    " <a href='http://github.com/prof18/RSS-Parser'>GitHub.</a>" +
                    this@MainActivity.getString(R.string.author)))
            alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "OK"
            ) { dialog, which -> dialog.dismiss() }
            alertDialog.show()

            (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
        }

        return super.onOptionsItemSelected(item)
    }
}


*/