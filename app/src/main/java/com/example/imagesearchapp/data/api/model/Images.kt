
package com.example.imagesearchapp.data.api.model
import com.squareup.moshi.Json

data class Images (

	@Json(name ="id") val id : String,
	@Json(name ="title") val title : String,
	@Json(name ="description") val description : String,
	@Json(name ="datetime") val datetime : Int,
	@Json(name ="type") val type : String,
	@Json(name ="animated") val animated : Boolean,
	@Json(name ="width") val width : Int,
	@Json(name ="height") val height : Int,
	@Json(name ="size") val size : Int,
	@Json(name ="views") val views : Int,
	@Json(name ="vote") val vote : String,
	@Json(name ="favorite") val favorite : Boolean,
	@Json(name ="nsfw") val nsfw : String,
	@Json(name ="section") val section : String,
	@Json(name ="account_url") val account_url : String,
	@Json(name ="account_id") val account_id : String,
	@Json(name ="is_ad") val is_ad : Boolean,
	@Json(name ="in_most_viral") val in_most_viral : Boolean,
	@Json(name ="has_sound") val has_sound : Boolean,
	@Json(name ="tags") val tags : List<String>,
	@Json(name ="ad_type") val ad_type : Int,
	@Json(name ="ad_url") val ad_url : String,
	@Json(name ="edited") val edited : Int,
	@Json(name ="in_gallery") val in_gallery : Boolean,
	@Json(name ="link") val link : String,
	@Json(name ="comment_count") val comment_count : String,
	@Json(name ="favorite_count") val favorite_count : String,
	@Json(name ="ups") val ups : String,
	@Json(name ="downs") val downs : String,
	@Json(name ="points") val points : String,
	@Json(name ="score") val score : String
)