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
    Button btRegresar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_frag_politicas_informacion, container, false);

        btRegresar = layout.findViewById(R.id.AIPbtRegreso);
        tvTexto = layout.findViewById(R.id.fragmentInformacionLegal);
        Textos textoPoliticas=new Textos();
        tvTexto.setText(textoPoliticas.getPOLITICAS());

        btRegresar.setOnClickListener(v -> {
            viajarInformacion();
        });

        return layout;
    }
    private void viajarInformacion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_fragPoliticasInformacion_to_informacion);
    }
}