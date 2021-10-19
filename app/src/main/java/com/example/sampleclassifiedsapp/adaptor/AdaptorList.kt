package com.example.sampleclassifiedsapp.adaptor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleclassifiedsapp.R
import com.example.sampleclassifiedsapp.activity.ListDetailActivity
import com.example.sampleclassifiedsapp.model.ResultItemModel
import com.example.sampleclassifiedsapp.utils.Helping
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_list_item.view.*

class AdaptorList(var list: ArrayList<ResultItemModel>, val mcontext: Context) :
    RecyclerView.Adapter<AdaptorList.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj: ResultItemModel = list.get(position)

        holder.tvName.text = "Name : "+obj.name
        holder.tvPrice.text = "Price : "+obj.price
        holder.tvPublishOn.text = "Published On : "+Helping.convertStringDateToRequiredFormat12Hour(obj.created_at)

        try {
            if (obj.image_urls_thumbnails.size != 0) {
                if (obj.image_urls_thumbnails.get(0) != "") {
                    Picasso.get().load(obj.image_urls_thumbnails.get(0))
                        .placeholder( R.color.noTextColor )
                        .into(holder.ivImage)
                }
            }
        }
        catch (e:Exception){

        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val tvName = view.tvName
        val ivImage = view.ivImage
        val tvPrice = view.tvPrice
        val tvPublishOn = view.tvPublishOn

        init {
            view.setOnClickListener {
                val intent = Intent(mcontext, ListDetailActivity::class.java)
                intent.putExtra("data", Gson().toJson(list.get(adapterPosition)))
                (mcontext as Activity).startActivity(intent)
            }
        }
    }






}