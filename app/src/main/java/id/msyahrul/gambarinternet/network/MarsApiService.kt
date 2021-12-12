package id.msyahrul.gambarinternet.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * membangun objek moshi yang digunakan untuk retrofit,
 * dan pastikan menambahkan adaptor kotlin untuk kompatibilitas penuh kotlin
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Gunakan pembuat Retrofit untuk membuat objek retrofit
 * menggunakan konverter Moshi dengan objek Moshi.
 */

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Antarmuka publik yang memperlihatkan metode [getPhotos]
 */
interface MarsApiService {
    /**
     *Antarmuka publik yang mengekspos metode [getPhotos] Mengembalikan [Daftar] [MarsPhoto]
     * dan metode ini dapat dipanggil dari Coroutine.
     * Anotasi @GET menunjukkan bahwa titik akhir "foto" akan diminta dengan metode GET HTTP
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

/**
 * Objek Api publik yang mengekspos layanan Retrofit yang diinisialisasi dengan lazy
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}