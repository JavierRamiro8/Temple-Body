package com.example.temple_body.Gimnasios;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

public class GimnasiosFragment extends Fragment {

    RecyclerView rcv;
    GimnasiosAdapter gimnasiosAdapter;
    public String nombre = "";
    private ActivityResultLauncher<String> requestPermissionLauncher;

    public GimnasiosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gimnasios, container, false);

        rcv = view.findViewById(R.id.listaContenedorGimnasios);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));

        gimnasiosAdapter = new GimnasiosAdapter(Gimnasios.generador());
        rcv.setAdapter(gimnasiosAdapter);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                requestMaps();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Error")
                        .setMessage("Error, sin el permiso no podrás usar la carecteristica")
                        .setPositiveButton("Salir", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        gimnasiosAdapter.setClickListener(new GimnasiosAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Gimnasios gimnasio) {
                Gimnasios miGimnasio = new Gimnasios(gimnasio.getNombre(), gimnasio.getPrecio(), gimnasio.getValoracion(), gimnasio.getMasInfo());
                nombre = miGimnasio.getNombre();
                if (ContextCompat.checkSelfPermission(
                        requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    requestMaps();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
        });

        return view;
    }

    private void requestMaps() {
        Uri uri = Uri.parse("geo:0,0?q=" + Uri.encode(nombre));
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.google.android.apps.maps");
        startActivity(i);
    }
}
