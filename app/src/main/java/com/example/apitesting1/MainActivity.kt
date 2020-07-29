package com.example.apitesting1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.apitesting1.newsList.Home
import com.example.apitesting1.newsList.MainAdapter
import com.example.apitesting1.viewPager.ViewPagerAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var backPressedTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(tool_bar_id)

        fetchJson()
        reshlayout()
        recycler_view_main.layoutManager = LinearLayoutManager(this)
        

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fitler_search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_menu -> {
                Log.d("MainActivity", "menu action")
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun reshlayout() {
        swipe_Resh_Layout.setOnRefreshListener {
            fetchJson()
            swipe_Resh_Layout.isRefreshing = false
        }
    }

    private fun fetchJson() {

        val url =
            "http://128.199.112.232:4400/api/news?fbclid=IwAR3OkrLHN0B0mRNjnWollKLEUmNiObuqi6vBhjSjVe1Q-gCOKSbFSo6HfTQ"

        val request = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("MainActivityJson", e.toString())
                runOnUiThread {
                    Toast.makeText(
                        application,
                        "Conneciton Failed And Something wrong",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val home = gson.fromJson(body, Array<Home>::class.java)
                runOnUiThread {
                    recycler_view_main.adapter = MainAdapter(home) //recyclerAdapter

                    viewPager2_id.adapter = ViewPagerAdapter(home) //viewPagerAdapter

                    viewPager2_id.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    circleIndicator3_id.setViewPager(viewPager2_id)

                    viewPager2_id.clipToPadding = false
                    viewPager2_id.clipChildren = false
                    viewPager2_id.offscreenPageLimit = 3
                    viewPager2_id.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


                    val compositePageee = CompositePageTransformer()
                    compositePageee.addTransformer(MarginPageTransformer(40))
                    compositePageee.addTransformer(ViewPager2.PageTransformer { page, position ->
                        val r = 1 - Math.abs(position)
                        page.scaleY = 0.85f + r * 0.15f




                    })
//
                    viewPager2_id.setPageTransformer(compositePageee)
//

//                    Log.d("MainActivity", home.size.toString())
                }

            }

        })

    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else Toast.makeText(application, "Please press again to exit", Toast.LENGTH_LONG).show()
        backPressedTime = System.currentTimeMillis()
    }


}



