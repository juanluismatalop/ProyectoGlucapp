import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.domain.models.Noticia

class EditarNoticiaFragment : Fragment() {

    private lateinit var tituloEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var editarButton: Button
    private var onNoticiaEdited: ((Noticia) -> Unit)? = null

    private var noticia: Noticia? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_editar_noticia, container, false)

        tituloEditText = view.findViewById(R.id.editTextTitulo)
        descripcionEditText = view.findViewById(R.id.editTextDescripcion)
        editarButton = view.findViewById(R.id.buttonEditarNoticia)

        // Cargar datos de la noticia si existe
        noticia?.let {
            tituloEditText.setText(it.titulo)
            descripcionEditText.setText(it.descripcion)
        }

        editarButton.setOnClickListener {
            val titulo = tituloEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                noticia?.let {
                    val updatedNoticia = it.copy(titulo = titulo, descripcion = descripcion)
                    onNoticiaEdited?.invoke(updatedNoticia)
                    parentFragmentManager.popBackStack()
                }
            }
        }

        return view
    }

    fun setOnNoticiaEditedListener(listener: (Noticia) -> Unit) {
        onNoticiaEdited = listener
    }

    fun setNoticia(noticia: Noticia) {
        this.noticia = noticia
    }
}
