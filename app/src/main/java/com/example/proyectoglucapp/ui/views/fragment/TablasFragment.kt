package com.example.proyectoglucapp.ui.views.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
            checkCameraPermission()
        }

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            adapter.setPhotos(photos)
        }

        viewModel.loadPhotos()
        return view
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val bitmap = result.data?.extras?.get("data") as? Bitmap
            bitmap?.let {
                viewModel.savePhoto(it)
            }
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(requireContext(), "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }
}
