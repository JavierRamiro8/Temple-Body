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
 * Use the {@link fragReglamento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragReglamento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragReglamento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragReglamento.
     */
    // TODO: Rename and change types and number of parameters
    public static fragReglamento newInstance(String param1, String param2) {
        fragReglamento fragment = new fragReglamento();
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
        View layout = inflater.inflate(R.layout.fragment_frag_reglamento, container, false);

        tvTexto = layout.findViewById(R.id.fragmentReglamento);
        tvTexto.setText("Reglamento (UE) 2021/1232 \n"+"Temple Body - Tu Compañero de Entrenamiento Ideal\n" +
                "\n" +
                "¡Bienvenido/a a Temple Body! Agradecemos tu interés y confianza en nuestra aplicación diseñada para brindarte una experiencia excepcional de entrenamiento y gimnasio. Antes de sumergirte en tu viaje de transformación física con nosotros, te pedimos que leas detenidamente el Reglamento (UE) 2021/1232 a continuación: \n" +"Reglamento (UE) 2021/1232 del Parlamento Europeo y del Consejo de 14 de julio de " +
                        "2021 por el que se establece una excepción temporal a determinadas disposiciones de la Directiva " +
                        "2002/58/CE en lo que respecta al uso de tecnologías por proveedores de servicios de comunicaciones interpersonales"+
                "independientes de la numeración para el tratamiento de datos personales y de otro tipo con fines de lucha contra los abusos sexuales de menores en línea.");

        return layout;
    }
}