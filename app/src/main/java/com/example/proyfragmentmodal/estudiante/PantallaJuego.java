package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.principal.Usuarios;
import com.example.proyfragmentmodal.profesor.MenuProfesor;

public class PantallaJuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);


        //para el mnú
        /*FragmentManager miManejador = getSupportFragmentManager();
        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
        //remplazar fragmento actual con el nuevo indicado en el contenedor
        miTrnsaccion.remove(new MenuProfesor());
        miTrnsaccion.replace(R.id.menu, new MenuEstudiante());
        miTrnsaccion.commit();*/

        //poner fragment de juego uno en el centro
        FragmentManager miManejador_ = getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
        FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
        //remplazar fragmento actual con el nuevo indicado en el contenedor
        miTrnsaccion_.replace(R.id.contenedor_fragment, new JuegoUno());
        miTrnsaccion_.commit();
    }
}