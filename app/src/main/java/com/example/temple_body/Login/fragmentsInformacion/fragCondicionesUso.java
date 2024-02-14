package com.example.temple_body.Login.fragmentsInformacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.temple_body.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragCondicionesUso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragCondicionesUso extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragCondicionesUso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragCondicionesUso.
     */
    // TODO: Rename and change types and number of parameters
    public static fragCondicionesUso newInstance(String param1, String param2) {
        fragCondicionesUso fragment = new fragCondicionesUso();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView tvTexto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_frag_condiciones_uso, container, false);
        tvTexto = layout.findViewById(R.id.fragmentCondicionesUso);

        tvTexto.setText("Condiciones de Uso de Temple Body - Tu Compañero de Entrenamiento Ideal\n" +
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
                "Fecha de entrada en vigencia: [01/01/2024]");
        return layout;
    }
}