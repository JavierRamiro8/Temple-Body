package com.example.temple_body.Login;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.temple_body.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Registro extends Fragment {
    public Registro() {}

    EditText usuario,email,contrasena;

    CheckBox checkTerminos;

    Button registro;
    FirebaseAuth mauth;

    DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout= inflater.inflate(R.layout.fragment_registro, container, false);
        usuario=layout.findViewById(R.id.reg_et_usuario);
        contrasena=layout.findViewById(R.id.reg_et_password);
        email=layout.findViewById(R.id.reg_et_email);
        checkTerminos=layout.findViewById(R.id.cb_terminosYcondiciones);
        registro=layout.findViewById(R.id.btRegistro);


        mauth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();


        registro.setOnClickListener(v -> {
            if(usuario.getText().toString().isEmpty()){
                usuario.setError("Introduce nombre de usuario");
            }else if(contrasena.getText().toString().isEmpty()){
                contrasena.setError("Introduce contraseña");
            }else if (email.getText().toString().isEmpty()) {
                email.setError("El correo esta vacio o no es valido");
            }else if(!checkTerminos.isChecked()){
                checkTerminos.setError("Lee las condiciones y terminos");
            }else{
                registrarUsuario();
            }
        });

        return layout;
    }

    private void registrarUsuario() {
        mauth.createUserWithEmailAndPassword(email.getText().toString(),contrasena.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = mauth.getCurrentUser().getUid(); // Obtener el ID de usuario generado por Firebase

                    Map<String, Object> mapa=new HashMap<>();
                    mapa.put("nombreUsuario",usuario.getText().toString());
                    mapa.put("email",email.getText().toString());
                    mapa.put("contraseña",contrasena.getText().toString());

                    // Guardar los datos en la base de datos con el ID de usuario
                    mDatabase.child("Users").child(userId).setValue(mapa);

                    // Pasar argumentos al fragmento loginPrincipal
                    Bundle args = new Bundle();
                    args.putString("userId", userId);
                    args.putString("email", email.getText().toString());
                    args.putString("contraseña", contrasena.getText().toString());
                    args.putString("nombreUsuario", usuario.getText().toString());

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragmentRegistro, new Perfil());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
    }

}