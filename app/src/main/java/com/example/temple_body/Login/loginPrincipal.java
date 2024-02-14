package com.example.temple_body.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.temple_body.R;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 * Use the {@link loginPrincipal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginPrincipal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public loginPrincipal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginPrincipal.
     */
    // TODO: Rename and change types and number of parameters
    public static loginPrincipal newInstance(String param1, String param2) {
        loginPrincipal fragment = new loginPrincipal();
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
    public static  final  String User = "prueba";
    public static  final  String Pass = "prueba";
    EditText etUsuario;
    EditText etPassword;
    Button btIniciarSesion;
    TextView tvRegistrarse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login_principal, container, false);

        etUsuario = layout.findViewById(R.id.ALetUsuario);
        etPassword = layout.findViewById(R.id.ALetPassword);
        btIniciarSesion = layout.findViewById(R.id.ALbtAccept);
        tvRegistrarse = layout.findViewById(R.id.ALtvRegisterlink);

        btIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (usuario.equals(User) && password.equals(Pass)) {
                    // Las credenciales son correctas, navegar al perfil Fragment
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragmentLogin, new Perfil());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    // Las credenciales son incorrectas, mostrar AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Error")
                            .setMessage("Usuario o contraseña incorrectos, Es prueba, prueba.")
                            .setPositiveButton("Aceptar", null);

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new Registro());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return layout;
    }
}