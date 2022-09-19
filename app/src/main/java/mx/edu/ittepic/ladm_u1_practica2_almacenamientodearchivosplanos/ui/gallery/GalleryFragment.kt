package mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.R
import mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentGalleryBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.databinding.FragmentHomeBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientodearchivosplanos.ui.home.HomeFragment
import java.io.BufferedReader
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




        var vector = ArrayList<String>()


        for(i in 1..5) {
            leerDeArchivo("paciente"+ i + ".txt")
            if (datosPaciente=="")
                break
            vector.add(datosPaciente)
            datosPaciente=""
        }

        binding.lista.adapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1, vector)

        binding.lista.setOnItemClickListener {
            parent, view, position, id ->

            leerDeArchivo("paciente"+(position+1)+".txt")
            Toast.makeText(requireActivity(),vector[0],Toast.LENGTH_LONG).show()
            FragmentHomeBinding.bind(requireActivity().findViewById(R.id.nav_home)).nombrecompleto.setText(vector[position].split("\n")[0].split(": ")[1])
            FragmentHomeBinding.bind(requireActivity().findViewById(R.id.nav_home)).edad.setText(vector[position].split("\n")[1].split(": ")[1])
            FragmentHomeBinding.bind(requireActivity().findViewById(R.id.nav_home)).direccion.setText(vector[position].split("\n")[2].split(": ")[1])
            FragmentHomeBinding.bind(requireActivity().findViewById(R.id.nav_home)).ocupacion.setText(vector[position].split("\n")[3].split(": ")[1])
            FragmentHomeBinding.bind(requireActivity().findViewById(R.id.nav_home)).telefono.setText(vector[position].split("\n")[4].split(": ")[1])
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.nav_home)

        }





        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}