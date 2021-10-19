package com.example.sampleclassifiedsapp.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleclassifiedsapp.R
import com.example.sampleclassifiedsapp.adaptor.AdaptorList
import com.example.sampleclassifiedsapp.interfaces.ApiInterface
import com.example.sampleclassifiedsapp.model.GetResponseModel
import com.example.sampleclassifiedsapp.model.ResultItemModel
import com.example.sampleclassifiedsapp.utils.Helping
import com.example.sampleclassifiedsapp.utils.Helping.Companion.showRespectiveErrorMessages
import com.example.sampleclassifiedsapp.utils.RetrofitHelper
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.mtoolbar
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import kotlinx.android.synthetic.main.toolbar_layout.view.toolbar_title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeActivity : AppCompatActivity() {
    lateinit var dialog: AlertDialog
    var bookList = ArrayList<ResultItemModel>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter : AdaptorList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bindToolbar()
        dialog = Helping.setProgressDialog(this, "fetching data...")

        recyclerView=rvList
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = AdaptorList(bookList, this!!)
        recyclerView.adapter = adapter

        fetchProductList()

    }

    private fun bindToolbar() {
        setSupportActionBar(mtoolbar as Toolbar?)
        supportActionBar?.title = ""
        toolbar_title.setText("Home")

    }

    private fun fetchProductList(){
        dialog!!.show()
        val apiService = RetrofitHelper.getClient().create(ApiInterface::class.java)
        var call: Call<GetResponseModel> = apiService.getDynamoWriters()
        call.enqueue(object : Callback<GetResponseModel> {
            override fun onResponse(call: Call<GetResponseModel>, response: Response<GetResponseModel>) {
                dialog!!.dismiss()
                if (response.code() == 200) {
                    try {
                        if (response.body()!!.results.size > 0) {
                            for (data:ResultItemModel in response.body()!!.results) {
                                bookList.add(data)
                            }
                            LLNoItemsFound.visibility= View.GONE
                            recyclerView.adapter!!.notifyDataSetChanged()
                        }
                        else {
                            LLNoItemsFound.visibility= View.VISIBLE
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@HomeActivity, "Error while initializing data.", Toast.LENGTH_LONG).show()
                    }
                }
                else {
                    showRespectiveErrorMessages(this@HomeActivity, response.code())
                }
            }
            override fun onFailure(call: Call<GetResponseModel>, t: Throwable) {
                dialog!!.dismiss()
                Toast.makeText(this@HomeActivity, "Request Timeout",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}