package com.example.sampleclassifiedsapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sampleclassifiedsapp.R
import com.example.sampleclassifiedsapp.model.ResultItemModel
import com.example.sampleclassifiedsapp.utils.Helping
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.mtoolbar
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import kotlinx.android.synthetic.main.toolbar_layout.view.mtoolbar
import kotlinx.android.synthetic.main.toolbar_layout.view.toolbar_title

class ListDetailActivity : AppCompatActivity() {
    var data:ResultItemModel? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindToolbar()

        if(intent.extras!!.getString("data")!=null) {
            data = Gson().fromJson(intent.extras!!.getString("data"), ResultItemModel::class.java)
            initializingData(data!!)
        }
        else
            finish()


    }

    private fun initializingData(obj:ResultItemModel){
        tvName.text = obj.name
        tvPrice.text = obj.price
        tvCreatedAt.text = Helping.convertStringDateToRequiredFormat12Hour(obj.created_at)

        try {
            if (obj.image_urls.size != 0) {
                if (obj.image_urls.get(0) != "") {
                    Picasso.get().load(obj.image_urls.get(0))
                        .placeholder( R.color.noTextColor )
                        .into(ivImage)
                }
            }
        }
        catch (e:Exception){

        }
    }

    private fun bindToolbar() {
        setSupportActionBar(mtoolbar as Toolbar?)
        supportActionBar?.title = ""
        toolbar_title.setText("Detail")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (mtoolbar as Toolbar?)!!.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}