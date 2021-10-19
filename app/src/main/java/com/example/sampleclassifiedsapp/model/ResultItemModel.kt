package com.example.sampleclassifiedsapp.model

class ResultItemModel(var created_at: String,
                      var price: String,
                      var name: String,
                      var uid: String,
                      var image_ids: ArrayList<String>,
                      var image_urls: ArrayList<String>,
                      var image_urls_thumbnails: ArrayList<String>)  {
}