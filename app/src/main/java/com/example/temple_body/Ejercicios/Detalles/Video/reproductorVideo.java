package com.example.temple_body.Ejercicios.Detalles.Video;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.VideoView;

import com.example.temple_body.R;
public class reproductorVideo extends Fragment {
    WebView reproductorVideo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout= inflater.inflate(R.layout.fragment_reproductor_video, container, false);
        reproductorVideo=layout.findViewById(R.id.reproductor);
        reproductorVideo.clearCache(true);
        reproductorVideo.getSettings().setJavaScriptEnabled(true);
        reproductorVideo.getSettings().getAllowContentAccess();
        reproductorVideo.getSettings().getAllowFileAccessFromFileURLs();
        reproductorVideo.getSettings().getAllowUniversalAccessFromFileURLs();
        String youtubeURL = "https://www.youtube.com/embed/wWoQ7PFSYlk?si=6LKOWPquxoEMb4wF";
        reproductorVideo.loadUrl(youtubeURL);

        return layout;
    }
}