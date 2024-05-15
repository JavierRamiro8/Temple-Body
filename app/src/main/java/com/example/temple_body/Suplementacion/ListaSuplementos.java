package com.example.temple_body.Suplementacion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

public class ListaSuplementos extends AppCompatActivity {

    RecyclerView rcv;
    SuplementosAdapter a;
    public String nombre="";
    ActivityResultLauncher<String> requestPermissionLauncher;
    Spinner spinnerSuplementos;
    TextView proe,hsn,prozis,big_supps,marcasRecomendadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_suplementos);

        spinnerSuplementos = findViewById(R.id.spinnerEjercicios);

        rcv = findViewById(R.id.listaContenedorSuplementos);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        marcasRecomendadas = findViewById(R.id.tvMarcasRecomendadas);
        proe = findViewById(R.id.tvProe);
        hsn = findViewById(R.id.tvHsn);
        prozis = findViewById(R.id.tvProzis);
        big_supps = findViewById(R.id.tvBig);

        proe.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        proe.setText("Proe-Nutrition");

        hsn.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        hsn.setText("HSN");

        prozis.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        prozis.setText("Prozis");

        big_supps.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        big_supps.setText("Big-Supps");

        marcasRecomendadas.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        marcasRecomendadas.setText("Marcas Recomendadas");

        String opcion = spinnerSuplementos.getSelectedItem().toString();

        spinnerSuplementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    a = new SuplementosAdapter(Suplementos.generador(item.toString()));
                    rcv.setAdapter(a);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

            a = new SuplementosAdapter(Suplementos.generador(opcion));
            rcv.setAdapter(a);


        proe.setOnClickListener((View v)->{
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realizar la acción
                abrirWeb("https://proenutrition.es/module/iqitsearch/searchiqit?s=" + opcion);
            } else {
                // Solicitar permiso al usuario
                requestPermissionLauncher.launch(Manifest.permission.INTERNET);
            }
        });
        hsn.setOnClickListener((View v)->{
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realizar la acción
                abrirWeb("https://www.hsnstore.com/?q=" + opcion);
            } else {
                // Solicitar permiso al usuario
                requestPermissionLauncher.launch(Manifest.permission.INTERNET);
            }
        });
        prozis.setOnClickListener((View v)->{
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realizar la acción
                abrirWeb("https://www.prozis.com/es/es/search?text=" + opcion);
            } else {
                // Solicitar permiso al usuario
                requestPermissionLauncher.launch(Manifest.permission.INTERNET);
            }
        });
        big_supps.setOnClickListener((View v)->{
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.INTERNET) ==
                    PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realizar la acción
                abrirWeb("https://bigsupps.site/search?type=product,article,page&q=*" + opcion + "*");
            } else {
                // Solicitar permiso al usuario
                requestPermissionLauncher.launch(Manifest.permission.INTERNET);
            }
        });
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                abrirWeb("");
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Toast.makeText(ListaSuplementos.this, "Necesitamos permiso para abrir la web.", Toast.LENGTH_SHORT).show();
            }
        });
        a.setClickListener(new SuplementosAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Suplementos suplemento) {
            }
        });
    }
    private void abrirWeb(String webSolicitada) {
        Uri uri=Uri.parse(webSolicitada);
        Intent webIntent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(webIntent);
    }

}
