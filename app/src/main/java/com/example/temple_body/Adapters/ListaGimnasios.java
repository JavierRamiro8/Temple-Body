package com.example.temple_body.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

public class ListaGimnasios extends AppCompatActivity {

    RecyclerView rcv;
    GimnasiosAdapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gimnasios);

        rcv = findViewById(R.id.listaContenedorGimnasios);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        a = new GimnasiosAdapter(Gimnasios.generador());
        rcv.setAdapter(a);

        a.setClickListener(new GimnasiosAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, Gimnasios gimnasio) {
                Gimnasios miGimnasio = new Gimnasios(gimnasio.getNombre(),gimnasio.getPrecio(),gimnasio.getValoracion());
                String nombre = miGimnasio.getNombre();
                Uri uri = Uri.parse("geo:0,0?q="+ Uri.encode(nombre));
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });
    }
}
