package id.msyahrul.gambarinternet.network

import com.squareup.moshi.Json

class MarsPhoto (
    val id: String,
    // digunakan untuk memetakan img_src dari JSON ke imgSrcUrl
    @Json(name = "img_src") val imgSrcUrl: String
)