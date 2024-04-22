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


public class fragReglamento extends Fragment {
    public fragReglamento() {}
    TextView tvTexto;
    Button btRegresar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_frag_reglamento, container, false);

        btRegresar = layout.findViewById(R.id.ARbtRegreso);
        tvTexto = layout.findViewById(R.id.fragmentReglamento);

        tvTexto.setText("\n"+"Reglamento (UE) 2021/1232 \n"+"Temple Body - Tu Compañero de Entrenamiento Ideal\n" +
                "\n" +
                "¡Bienvenido/a a Temple Body! Agradecemos tu interés y confianza en nuestra aplicación diseñada para brindarte" +
                " una experiencia excepcional de entrenamiento y gimnasio. Antes de sumergirte en tu viaje de transformación física " +
                "con nosotros, te pedimos que leas detenidamente el Reglamento (UE) 2021/1232 a continuación: \n"
                +"Reglamento (UE) 2021/1232 del Parlamento Europeo y del Consejo de 14 de julio de " +
                        "2021 por el que se establece una excepción temporal a determinadas disposiciones de la Directiva " +
                        "2002/58/CE en lo que respecta al uso de tecnologías por proveedores de servicios de comunicaciones interpersonales"+
                "independientes de la numeración para el tratamiento de datos personales y de otro tipo con fines de lucha contra los abusos sexuales de menores en línea."
        +"\n \n \n");

        btRegresar.setOnClickListener(v -> {
            viajarInformacion();
        });

        return layout;
    }
    private void viajarInformacion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_fragReglamento_to_informacion);
    }
}