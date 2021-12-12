package com.dwisetiyo.gambarinternet.overview

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dwisetiyo.gambarinternet.R
import id.msyahrul.gambarinternet.network.MarsPhoto
import id.msyahrul.gambarinternet.overview.MarsApiStatus
import id.msyahrul.gambarinternet.overview.PhotoGridAdapter

/**
 * Memperbarui data yang ditampilkan di [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
 * Menggunakan perpustakaan Coil untuk memuat gambar dengan URL ke dalam [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Adaptor pengikat ini menampilkan [MarsApiStatus] dari permintaan jaringan dalam tampilan gambar.
 * Saat permintaan sedang dimuat, ini akan menampilkan file loading_animation.
 * Jika permintaan memiliki kesalahan, ini akan menampilkan gambar yang rusak untuk mencerminkan kesalahan koneksi.
 * Ketika permintaan selesai, itu menyembunyikan tampilan gambar.
 */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
