package com.example.proyectoglucapp.ui.views.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.ui.viewmodel.TablasViewModel

class TablasFragment : Fragment() {

    private val viewModel: TablasViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_tablas, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewPhotos)
        val btnTakePhoto: ImageButton = view.findViewById(R.id.btnTakePhoto)

        adapter = PhotoAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        btnTakePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
        }

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.setPhotos(photos)
        }

        viewModel.loadPhotos()
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            viewModel.savePhoto(bitmap)
        }
    }
}
