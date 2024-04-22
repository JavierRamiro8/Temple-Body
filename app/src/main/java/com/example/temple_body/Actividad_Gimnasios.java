package com.example.temple_body;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Actividad_Gimnasios extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_gimnasios);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.peso));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.manzana));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.mapa));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.usuario));

        bottomNavigation.show(4, false);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // Update the selected item in the bottom navigation view based on the current destination
                if (navController != null) {
                    bottomNavigation.show(navController.getCurrentDestination().getId(), true);
                }
            }
        });

        // Set listener for bottom navigation item clicks
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // Navigate to the corresponding fragment when a bottom navigation item is clicked
                if (navController != null) {
                    switch (model.getId()) {
                        case 1:
                            navController.navigate(R.id.ejerciciosFragment);
                            break;
                        case 2:
                            navController.navigate(R.id.suplementosFragment);
                            break;
                        case 3:
                            navController.navigate(R.id.gimnasiosFragment);
                            break;
                        case 4:
                            navController.navigate(R.id.loginPrincipal);
                            break;
                    }
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
    @Override
    protected void onStart() {
        super.onStart();
        // Initialize the NavController when the activity is started
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
    }
}