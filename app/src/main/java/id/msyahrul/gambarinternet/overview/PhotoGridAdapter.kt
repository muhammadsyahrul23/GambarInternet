package id.msyahrul.gambarinternet.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dwisetiyo.gambarinternet.databinding.GridViewItemBinding
import id.msyahrul.gambarinternet.network.MarsPhoto

/**
 * Kelas ini mengimplementasikan [RecyclerView] [ListAdapter]
 * yang menggunakan Data Binding untuk menyajikan data [Daftar],
 * termasuk menghitung perbedaan antara daftar.
 */
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    /**
     * Konstruktor MarsPhotosViewHolder mengambil variabel binding dari GridViewItem terkait,
     * yang dengan baik memberikannya akses ke informasi [MarsPhoto] lengkap.
     */
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // Ini penting, karena memaksa pengikatan data untuk segera dieksekusi,
            // yang memungkinkan RecyclerView membuat pengukuran ukuran tampilan yang benar
            binding.executePendingBindings()
        }
    }

    /**
     * Memungkinkan RecyclerView untuk menentukan item
     * mana yang telah berubah ketika [List] dari [MarsPhoto] telah diperbarui.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /**
     * Buat tampilan item [RecyclerView] baru (dipanggil oleh pengelola tata letak)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Mengganti konten tampilan (dipanggil oleh pengelola tata letak)
     */
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}