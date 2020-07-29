package com.example.apitesting1.viewPager

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apitesting1.R
import com.example.apitesting1.newsList.Home
import com.example.apitesting1.newsList.newsDetails
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.addlistrecycler.view.*
import kotlinx.android.synthetic.main.viewpageritem.view.*

class ViewPagerAdapter(val home:Array<Home>):RecyclerView.Adapter<ViewPagerAdapter.viewPagerHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.viewPagerHolder {
        val layoutinflator= LayoutInflater.from(parent.context)
        val cellRow=layoutinflator.inflate(R.layout.viewpageritem,parent,false)
        return viewPagerHolder(cellRow)

    }

    override fun getItemCount(): Int {
        return home.size

    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.viewPagerHolder, position: Int) {
        val hometitle=home.get(position).title  //getTitle
        val image=home.get(position).newsImage //getImage
        val createdAt=home.get(position).createdAt

        val itemPosition=home.get(position)

        holder.itemView.textView_View_Pager_title.text=hometitle
        holder.itemView.date_textView_viewpager.text=createdAt

        val imageUrl="http://128.199.112.232:4400/$image"
//        Log.d("OnBindViewHolder",imageUrl)

        Picasso.get().load(imageUrl)
            .transform(RoundedCornersTransformation(25,10))
            .resize(1200,700)
            .into(holder?.itemView?.imageView_View_Pager)
        holder?.itemView?.imageView_View_Pager.cropToPadding=true





        holder?.pHome=itemPosition
    }

    class viewPagerHolder(v: View, var pHome: Home?=null):RecyclerView.ViewHolder(v){
        init {
            v.setOnClickListener{
                val intent= Intent(v.context,
                    newsDetails::class.java)
                intent.putExtra("title",pHome?.title)
                intent.putExtra("body",pHome?.body)
                intent.putExtra("image",pHome?.newsImage)
                intent.putExtra("created",pHome?.createdAt)
                v.context.startActivity(intent)


            }
        }

    }


}