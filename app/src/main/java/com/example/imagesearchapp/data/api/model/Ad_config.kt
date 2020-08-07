package com.example.imagesearchapp.data.api.model
import com.squareup.moshi.Json
data class Ad_config (

	@Json(name ="safeFlags") val safeFlags : List<String>,
	@Json(name ="highRiskFlags") val highRiskFlags : List<String>,
	@Json(name ="unsafeFlags") val unsafeFlags : List<String>,
	@Json(name ="wallUnsafeFlags") val wallUnsafeFlags : List<String>,
	@Json(name ="showsAds") val showsAds : Boolean
)
