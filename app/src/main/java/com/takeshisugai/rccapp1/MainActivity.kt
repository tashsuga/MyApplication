package com.takeshisugai.rccapp1

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


/*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
*/

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


