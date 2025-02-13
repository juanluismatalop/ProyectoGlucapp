package com.example.proyectoglucapp.ui.views.fragment.noticias


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.data.local.noticia.Noticia

class NoticiaAdapter(
    private val onEditClick: (Noticia) -> Unit,
    private val onDeleteClick: (Noticia) -> Unit
) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    private var noticias = emptyList<Noticia>()

    class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        val descripcion: TextView = itemView.findViewById(R.id.textViewDescripcion)
        val botonEditar: ImageButton = itemView.findViewById(R.id.imageButtonEditar)
        val botonBorrar: ImageButton = itemView.findViewById(R.id.imageButtonBorrar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.titulo.text = noticia.titulo
        holder.descripcion.text = noticia.descripcion
        holder.botonEditar.setOnClickListener { onEditClick(noticia) }
        holder.botonBorrar.setOnClickListener { onDeleteClick(noticia) }
    }

    override fun getItemCount() = noticias.size

    fun setNoticias(nuevasNoticias: List<Noticia>) {
        this.noticias = nuevasNoticias
        notifyDataSetChanged()
    }
}
