package com.example.temple_body.Gimnasios;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ListaGimnasios extends AppCompatActivity {

    RecyclerView rcv;
    GimnasiosAdapter gimnasiosAdapter;
    public String nombre="";
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gimnasios);

        rcv = findViewById(R.id.listaContenedorGimnasios);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        gimnasiosAdapter = new GimnasiosAdapter(Gimnasios.generador());
        rcv.setAdapter(gimnasiosAdapter);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                requestMaps();
            } else {
                new MaterialAlertDialogBuilder(ListaGimnasios.this)
                        .setTitle("ERROR")
                        .setMessage("Necesitamos el permiso para usar esta característica")
                        .setPositiveButton("OK", (dialog, which) -> {})
                        .show();
            }
        });
        gimnasiosAdapter.setClickListener(new GimnasiosAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Gimnasios gimnasio) {
                Gimnasios miGimnasio = new Gimnasios(gimnasio.getNombre(),gimnasio.getPrecio(),gimnasio.getValoracion(), gimnasio.getMasInfo());
                nombre = miGimnasio.getNombre();
                if (ContextCompat.checkSelfPermission(
                        ListaGimnasios.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    requestMaps();
                } else if (false) {
                    new MaterialAlertDialogBuilder(ListaGimnasios.this)
                            .setTitle("ERROR")
                            .setMessage("Necesitamos el permiso para usar esta característica")
                            .setPositiveButton("OK", (dialog, which) -> {})
                            .show();
                }else {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }
        });
    }
    private void requestMaps() {
        Uri uri = Uri.parse("geo:0,0?q="+ Uri.encode(nombre));
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.google.android.apps.maps");
        startActivity(i);
    }

}
