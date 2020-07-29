package com.example.apitesting1.newsList

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.apitesting1.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.viewpageritem.view.*

class newsDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        setSupportActionBar(tool_bar_id_new_details)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)




        val title=intent.getStringExtra("title")
        val body=intent.getStringExtra("body")
        val imageUrl=intent.getStringExtra("image")
        val created=intent.getStringExtra("created")
        val createdArray=created.split("T")



        textView_news_details_title.text=title
        textView_news_detials_body.text= Html.fromHtml(body)
        date_id_newsdetail.text= Html.fromHtml("Published At <b>:"+createdArray[0]+"</b>")





        Picasso.get().load("http://128.199.112.232:4400/$imageUrl")
            .fit()
            .centerCrop()
            .into(imageView_news_details)
        imageView_news_details.clipToOutline=true

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}