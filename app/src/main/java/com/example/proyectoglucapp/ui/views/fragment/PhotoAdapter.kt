package com.example.proyectoglucapp.ui.views.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.data.local.photo.PhotoEntity


class PhotoAdapter(private var photos: List<PhotoEntity>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.imageView.setImageBitmap(photos[position].toBitmap())
    }

    override fun getItemCount() = photos.size

    fun setPhotos(newPhotos: List<PhotoEntity>) {
        photos = newPhotos
        notifyDataSetChanged()
    }
}
