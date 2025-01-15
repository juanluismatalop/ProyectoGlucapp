import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.proyectoglucapp.R

class CalculadoraFragment : Fragment() {

    private lateinit var valueManana: TextView
    private lateinit var valueMediodia: TextView
    private lateinit var valueTarde: TextView
    private lateinit var valueNoche: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculadora, container, false)

        valueManana = view.findViewById(R.id.valueManana)
        valueMediodia = view.findViewById(R.id.valueMediodia)
        valueTarde = view.findViewById(R.id.valueTarde)
        valueNoche = view.findViewById(R.id.valueNoche)

        val calcularButton: Button = view.findViewById(R.id.calcularButton)
        calcularButton.setOnClickListener { calcularResultados() }

        return view
    }

    private fun calcularResultados() {
        val nivelGlucosa = arguments?.getInt("nivelGlucosa")
        val racionesComida = arguments?.getFloat("racionesComida")
        val ratioManana = arguments?.getFloat("ratioManana")
        val ratioMediodia = arguments?.getFloat("ratioMediodia")
        val ratioTarde = arguments?.getFloat("ratioTarde")
        val ratioNoche = arguments?.getFloat("ratioNoche")

        if (nivelGlucosa == null || racionesComida == null || ratioManana == null ||
            ratioMediodia == null || ratioTarde == null || ratioNoche == null) {
            return
        }

        val factorSensibilidad = 4
        val ajusteSensibilidad = if (nivelGlucosa > 200) {
            ((nivelGlucosa - 200) / 100) * factorSensibilidad
        } else {
            0
        }

        val resultadoManana = (ratioManana * racionesComida) + ajusteSensibilidad
        val resultadoMediodia = (ratioMediodia * racionesComida) + ajusteSensibilidad
        val resultadoTarde = (ratioTarde * racionesComida) + ajusteSensibilidad
        val resultadoNoche = (ratioNoche * racionesComida) + ajusteSensibilidad

        valueManana.text = resultadoManana.toString()
        valueMediodia.text = resultadoMediodia.toString()
        valueTarde.text = resultadoTarde.toString()
        valueNoche.text = resultadoNoche.toString()
    }
}
