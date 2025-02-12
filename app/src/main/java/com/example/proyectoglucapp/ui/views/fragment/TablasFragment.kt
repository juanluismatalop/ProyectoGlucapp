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
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.proyectoglucapp.R
import com.example.proyectoglucapp.ui.viewModel.ViewModelFactory
import com.example.proyectoglucapp.ui.viewmodel.TablasViewModel


class TablasFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var btnTakePhoto: ImageButton
    private val REQUEST_IMAGE_CAPTURE = 1

    private val viewModel: TablasViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_tablas, container, false)

        imageView = view.findViewById(R.id.imageView)
        btnTakePhoto = view.findViewById(R.id.btnTakePhoto)

        btnTakePhoto.setOnClickListener {
            dispatchTakePictureIntent()
        }

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            if (photos.isNotEmpty()) {
                imageView.setImageBitmap(photos.last().bitmap)
            }
        }

        viewModel.loadPhotos()

        return view
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            viewModel.savePhoto(imageBitmap)
        }
    }
}
