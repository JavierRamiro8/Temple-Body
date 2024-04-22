package com.example.temple_body.Login.fragmentsInformacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.temple_body.Login.informacion;
import com.example.temple_body.R;
import com.example.temple_body.Settings.Textos;

public class fragCondicionesUso extends Fragment {

    public fragCondicionesUso() {}
    TextView tvTexto;
    Button btRegresar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_frag_condiciones_uso, container, false);

        btRegresar = layout.findViewById(R.id.ACUbtRegreso);
        tvTexto = layout.findViewById(R.id.fragmentCondicionesUso);
        Textos textoCondiciones=new Textos();
        tvTexto.setText(textoCondiciones.getCONDICIONES());

        btRegresar.setOnClickListener(v -> {
            viajarInformacion();
        });

        return layout;
    }
    private void viajarInformacion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_fragCondicionesUso_to_informacion);
    }
}