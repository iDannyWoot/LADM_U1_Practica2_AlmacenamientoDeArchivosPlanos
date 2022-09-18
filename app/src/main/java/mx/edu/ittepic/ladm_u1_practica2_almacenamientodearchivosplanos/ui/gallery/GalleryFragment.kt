package mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentGalleryBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var datosPaciente = ""
        fun leerDeArchivo(nombreArchivo: String) {
            try{
                var archivo = BufferedReader(InputStreamReader(requireActivity().openFileInput(nombreArchivo)))
                datosPaciente = archivo.readLine()
                archivo.close()
            }catch (e:Exception){  }
        }

        var arrayDatos = datosPaciente.split("|")

        var vector = ArrayList<String>()


        for(i in arrayDatos) {
            vector.add(i)
        }

        binding.lista.adapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1, vector)
        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}