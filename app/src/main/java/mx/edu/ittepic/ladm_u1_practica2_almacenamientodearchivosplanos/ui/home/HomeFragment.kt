package mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentHomeBinding
import java.io.OutputStreamWriter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fun guardarEnArchivo(mensaje:String) = try{
            var archivo = OutputStreamWriter(activity?.openFileOutput("archivo.txt", Context.MODE_PRIVATE))
            archivo.write(mensaje)
            archivo.flush()
            archivo.close()
        }catch(e:Exception){
            Toast.makeText(requireActivity(),e.message, Toast.LENGTH_LONG)
        }

        binding.insertar.setOnClickListener {
            var datosPaciente = arrayOf("Nombre del Paciente: "+binding.nombrecompleto.text,"Edad:" + binding.edad.text,
                "Dirección: " + binding.direccion.text, "Ocupación: " + binding.ocupacion.text,"Teléfono: " + binding.telefono.text)
            var mensaje = ""

            for(i in datosPaciente){
                mensaje = mensaje + i.toString() + " | "
            }

            guardarEnArchivo(mensaje)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}