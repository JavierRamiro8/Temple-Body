package com.example.temple_body.Gimnasios;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

public class ListaGimnasios extends AppCompatActivity {

    RecyclerView rcv;
    GimnasiosAdapter a;
    public String nombre="";
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gimnasios);

        rcv = findViewById(R.id.listaContenedorGimnasios);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        a = new GimnasiosAdapter(Gimnasios.generador());
        rcv.setAdapter(a);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                requestMaps();
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(ListaGimnasios.this, "Necesitamos permiso para obtener la localización.", Toast.LENGTH_SHORT).show();
            }
        });
        a.setClickListener(new GimnasiosAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Gimnasios gimnasio) {
                Gimnasios miGimnasio = new Gimnasios(gimnasio.getNombre(),gimnasio.getPrecio(),gimnasio.getValoracion(), gimnasio.getMasInfo());
                nombre = miGimnasio.getNombre();
                if (ContextCompat.checkSelfPermission(
                        ListaGimnasios.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                    requestMaps();
                } else if (false) {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected, and what
                    // features are disabled if it's declined. In this UI, include a
                    // "cancel" or "no thanks" button that lets the user continue
                    // using your app without granting the permission.

                    // Mostrar UI Dialog para explicar al usuarios la necesidad del permiso
                    // Vamos a usar la de por defecto de Android. Se ejecuta en el else

                }else {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
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
