package com.example.apitesting1.newsList

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apitesting1.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.addlistrecycler.view.*

class MainAdapter(val home:Array<Home>): RecyclerView.Adapter<CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutinflator=LayoutInflater.from(parent.context)
        val cellRow=layoutinflator.inflate(R.layout.addlistrecycler,parent,false)
        return CustomViewHolder(cellRow)
    }

    override fun getItemCount(): Int {
        return home.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val hometitle=home.get(position).title//getTitle
        val image=home.get(position).newsImage//getImage
        val createdAt=home.get(position).createdAt//getdate

        val itemPosition=home.get(position)

        holder.itemView.textView_title.text=hometitle

        val imageUrl="http://128.199.112.232:4400/$image"
//        Log.d("OnBindViewHolder",imageUrl)

        Picasso.get().load(imageUrl)
            .transform(RoundedCornersTransformation(10,4))
            .resize(88,71)
            .into(holder?.itemView?.imageview_image);

        holder?.pHome=itemPosition
    }
    

}

class CustomViewHolder(v: View,var pHome: Home?=null):RecyclerView.ViewHolder(v){
    init {
        v.setOnClickListener{
            val intent=Intent(v.context,
                newsDetails::class.java)
            intent.putExtra("title",pHome?.title)
            intent.putExtra("body",pHome?.body)
            intent.putExtra("image",pHome?.newsImage)
            intent.putExtra("created",pHome?.createdAt)
            v.context.startActivity(intent)


        }
    }

}