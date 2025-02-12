package com.example.proyectoglucapp.ui.views.fragment.noticias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.domain.models.Noticia

class NoticiasAdapter(
    private val onDeleteClick: (Noticia) -> Unit,
    private val onEditClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder>() {

    private val noticiasList = mutableListOf<Noticia>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_noticias, parent, false)
        return NoticiasViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        val noticia = noticiasList[position]
        holder.bind(noticia, onDeleteClick, onEditClick)
    }

    override fun getItemCount(): Int = noticiasList.size

    fun updateNoticias(noticias: List<Noticia>) {
        val diffCallback = NoticiasDiffCallback(noticiasList, noticias)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        noticiasList.clear()
        noticiasList.addAll(noticias)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NoticiasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        private val descripcion: TextView = itemView.findViewById(R.id.textViewDescripcion)
        private val borrarButton: ImageButton = itemView.findViewById(R.id.imageButtonBorrar)
        private val editarButton: ImageButton = itemView.findViewById(R.id.imageButtonEditar)

        fun bind(noticia: Noticia, onDeleteClick: (Noticia) -> Unit, onEditClick: (Noticia) -> Unit) {
            titulo.text = noticia.titulo
            descripcion.text = noticia.descripcion

            borrarButton.setOnClickListener { onDeleteClick(noticia) }
            editarButton.setOnClickListener { onEditClick(noticia) }
        }
    }
}

class NoticiasDiffCallback(
    private val oldList: List<Noticia>,
    private val newList: List<Noticia>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
