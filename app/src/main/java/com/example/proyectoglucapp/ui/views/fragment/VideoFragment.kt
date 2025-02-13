package com.example.proyectoglucapp.ui.views.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.proyectoglucapp.R

class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        val videoView: VideoView = view.findViewById(R.id.videoView)

        val videoUri: Uri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.video_ejemplo1)

        videoView.setVideoURI(videoUri)

        videoView.start()

        videoView.setOnCompletionListener {
            println("El video ha terminado.")
        }

        return view
    }
}

