package com.example.proyfragmentmodal.principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.profesor.MenuProfesor;
import com.example.proyfragmentmodal.principal.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        establecerMenu(extras.getString("itOrigin"));
    }

    public void establecerMenu(String origin) {
        FragmentManager miManejador;//getFragmentManager(); //getParentFragmentManager();
        FragmentTransaction miTrnsaccion;
        switch (origin) {
            case "loginEstudiante":
                miManejador = getSupportFragmentManager();
                miTrnsaccion = miManejador.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccion.replace(R.id.menu, new Menu());
                miTrnsaccion.commit();
                break;
            case "loginProfesor":
                miManejador = getSupportFragmentManager();
                miTrnsaccion = miManejador.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccion.replace(R.id.menu, new MenuProfesor());
                miTrnsaccion.commit();
                break;
            default:
                break;
        }
    }
}