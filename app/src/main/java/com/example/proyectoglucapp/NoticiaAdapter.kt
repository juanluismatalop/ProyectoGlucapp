package com.example.proyectoglucapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoticiasAdapter(
    private val noticiasList: MutableList<Noticia>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_noticias, parent, false)
        return NoticiasViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        val noticia = noticiasList[position]
        holder.titulo.text = noticia.titulo
        holder.descripcion.text = noticia.descripcion

        holder.borrarButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = noticiasList.size

    fun removeItem(position: Int) {
        noticiasList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class NoticiasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.textView4)
        val descripcion: TextView = itemView.findViewById(R.id.textView5)
        val borrarButton: ImageButton = itemView.findViewById(R.id.imagebuttonDelete)
    }
}
