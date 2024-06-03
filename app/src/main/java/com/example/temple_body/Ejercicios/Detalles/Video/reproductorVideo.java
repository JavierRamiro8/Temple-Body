package com.example.temple_body.Ejercicios.Detalles.Video;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.temple_body.R;
import com.example.temple_body.Settings.EnlacesVideos;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class reproductorVideo extends Fragment {
    WebView reproducirVideo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout= inflater.inflate(R.layout.fragment_reproductor_video, container, false);
        Bundle args= getArguments();
        String nombreEjercicio=args.getString("nombreEjercicio");
        reproducirVideo =layout.findViewById(R.id.reproductor);
        reproducirVideo.clearCache(true);
        reproducirVideo.getSettings().setJavaScriptEnabled(true);
        reproducirVideo.getSettings().getAllowContentAccess();
        reproducirVideo.getSettings().getAllowFileAccessFromFileURLs();
        reproducirVideo.getSettings().getAllowUniversalAccessFromFileURLs();
        String youtubeURL = new EnlacesVideos().enlaceReproductorVideo(nombreEjercicio);
        if(youtubeURL.isEmpty()){
            new MaterialAlertDialogBuilder(requireActivity())
                    .setTitle("Error")
                    .setMessage("El video no esta disponible, vuelve a intentarlo en otro momento")
                    .setPositiveButton("OK", (dialog, which) -> {
                    })
                    .show();
        }
        reproducirVideo.loadUrl(youtubeURL);

        return layout;
    }
}