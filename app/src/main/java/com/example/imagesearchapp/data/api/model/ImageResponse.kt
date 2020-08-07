package com.example.imagesearchapp.data.api.model

import com.squareup.moshi.Json
data class ImageResponse (
	@Json(name ="data") val data : List<Data>,
	@Json(name ="success") val success : Boolean,
	@Json(name ="status") val status : Int
)