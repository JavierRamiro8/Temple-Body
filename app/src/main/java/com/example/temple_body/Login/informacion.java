package com.example.temple_body.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.R;
public class informacion extends Fragment {

    Button btInformacion, btCondiciones, btReglamento, btRegresar, btComunidad;
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_informacion, container, false);

        btInformacion = layout.findViewById(R.id.AIbtInformacionLegal);
        btReglamento = layout.findViewById(R.id.AIbtReglamento);
        btCondiciones = layout.findViewById(R.id.AIbtCondicionesUso);
        btRegresar = layout.findViewById(R.id.AIbtRegresar);
        btComunidad = layout.findViewById(R.id.AIbtTelegram);
        guardarLayoutLogin();

       btInformacion.setOnClickListener(v -> {
           viajarInformacion();
       });

        btComunidad.setOnClickListener(v -> {
            requestComunity();
        });

        btReglamento.setOnClickListener(v -> {
            viajarReglamento();
        });

        btCondiciones.setOnClickListener(v -> {
            viajarCondiciones();
        });

        btRegresar.setOnClickListener(v -> {
            viajarPerfil();
        });



        return layout;
    }
    private void requestComunity() {
        String enlace="https://discord.gg/khBQwgRJ";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(enlace));
        startActivity(intent);
    }
    private void viajarInformacion(){
        NavController nav= NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_fragPoliticasInformacion);
    }
    private void viajarCondiciones() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_fragCondicionesUso);
    }
    private void viajarReglamento() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_fragReglamento);
    }
    private void viajarPerfil() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_perfil);
    }
    private void guardarLayoutLogin() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("cargaLayoutLogin", 4);
        editor.apply();
    }
}