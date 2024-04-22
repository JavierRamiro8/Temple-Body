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

        tvTexto.setText("\n"+"Condiciones de Uso de Temple Body - Tu Compañero de Entrenamiento Ideal\n" +
                "\n" +
                "¡Bienvenido/a a Temple Body! Agradecemos tu interés y confianza en nuestra aplicación diseñada para brindarte una experiencia excepcional de entrenamiento y gimnasio. Antes de sumergirte en tu viaje de transformación física con nosotros, te pedimos que leas detenidamente nuestras condiciones de uso a continuación: \n" +
                "1. Uso Adecuado:\n" +
                "La aplicación Temple Body está destinada únicamente para fines de entrenamiento y acondicionamiento físico. Cualquier uso indebido, incluido el comportamiento fraudulento o malicioso, resultará en la terminación de tu cuenta.\n" +
                "2. Derechos de Propiedad:\n" +
                "Todos los derechos de propiedad intelectual relacionados con Temple Body, incluidos pero no limitados a logos, imágenes y contenido, son propiedad exclusiva de Temple Body. No está permitido copiar, modificar, distribuir o reproducir ningún elemento sin nuestro consentimiento explícito.\n" +
                "3. Terminación de Cuenta:\n" +
                "Nos reservamos el derecho de suspender o cerrar tu cuenta si violas estos términos de uso o participas en actividades que consideremos perjudiciales para la comunidad de Temple Body.\n" +
                "Al utilizar Temple Body, te unes a una comunidad dedicada al bienestar y la salud. ¡Estamos emocionados de ser parte de tu viaje de fitness! ¡Entrena con determinación y alcanza tus metas con Temple Body!\n" +
                "\n" +
                "Fecha de entrada en vigencia: [01/01/2024]"+"\n \n \n");

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