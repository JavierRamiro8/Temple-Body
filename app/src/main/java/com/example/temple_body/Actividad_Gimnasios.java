package com.example.temple_body;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Actividad_Gimnasios extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    private FragmentContainerView fragmentSuplementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_gimnasios);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.settings));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.settings));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.settings));
        fragmentSuplementos=findViewById(R.id.FragmentSuplementos);
        fragmentSuplementos.setVisibility(View.INVISIBLE);
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Intent intent;

                switch(model.getId()){
                    case 1:
                        fragmentSuplementos.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        fragmentSuplementos.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        fragmentSuplementos.setVisibility(View.INVISIBLE);

                        break;
                    case 4:
                        fragmentSuplementos.setVisibility(View.INVISIBLE);
                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 1:
                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 2:
                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 3:
                        break;
                }
                return null;
            }
        });bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 4:
                        break;
                }
                return null;
            }
        });

    }
}