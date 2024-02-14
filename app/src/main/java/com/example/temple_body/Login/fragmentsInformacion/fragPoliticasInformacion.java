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
 * Use the {@link fragPoliticasInformacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragPoliticasInformacion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragPoliticasInformacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragPoliticasInformacion.
     */
    // TODO: Rename and change types and number of parameters
    public static fragPoliticasInformacion newInstance(String param1, String param2) {
        fragPoliticasInformacion fragment = new fragPoliticasInformacion();
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
        View layout = inflater.inflate(R.layout.fragment_frag_politicas_informacion, container, false);
        tvTexto = layout.findViewById(R.id.fragmentInformacionLegal);

        tvTexto.setText("Informacion y Politicas de privacidad de Temple Body - Tu Compañero de Entrenamiento Ideal\n" +
                "\n" +
                "¡Bienvenido/a a Temple Body! Agradecemos tu interés y confianza en nuestra aplicación diseñada para brindarte una experiencia excepcional de entrenamiento y gimnasio. Antes de sumergirte en tu viaje de transformación física con nosotros, te pedimos que leas detenidamente nuestra informacion y politica de privacidad a continuación: \n" +"1. Aceptación de los Términos:\n" +
                "Al acceder y utilizar la aplicación Temple Body, aceptas cumplir con estos términos de uso. Si no estás de acuerdo con alguna parte de estos términos, te recomendamos que no utilices nuestra aplicación.\n" +
                "\n" +
                "2. Responsabilidad del Usuario:\n" +
                "Eres responsable de la información que compartes, los datos que ingreses y las actividades que realices dentro de Temple Body. Te comprometes a proporcionar información precisa y a utilizar la aplicación de manera ética y legal.\n" +
                "3. Soporte Técnico:\n" +
                "Ofrecemos soporte técnico para resolver problemas relacionados con la aplicación. Si experimentas algún problema, contáctanos a través de los canales de soporte proporcionados en la aplicación.\n" +
                "\n" + "4. Privacidad y Datos Personales:\n" +
                "Nos tomamos en serio la privacidad de tus datos. Consulta nuestra política de privacidad para comprender cómo recopilamos, utilizamos y protegemos tu información personal.\n" +
                "\n" +
                "5. Derechos de Propiedad:\n" +
                "Todos los derechos de propiedad intelectual relacionados con Temple Body, incluidos pero no limitados a logos, imágenes y contenido, son propiedad exclusiva de Temple Body. No está permitido copiar, modificar, distribuir o reproducir ningún elemento sin nuestro consentimiento explícito." +
                "Al utilizar Temple Body, te unes a una comunidad dedicada al bienestar y la salud. ¡Estamos emocionados de ser parte de tu viaje de fitness! ¡Entrena con determinación y alcanza tus metas con Temple Body!\n" +
                "\n" +
                "Fecha de entrada en vigencia: [01/01/2024]");

        return layout;
    }
}