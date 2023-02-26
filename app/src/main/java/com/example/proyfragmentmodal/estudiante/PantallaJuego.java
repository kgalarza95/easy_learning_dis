package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.general.MenuJuegoPrueba;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

public class PantallaJuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);


        Intent intent = getIntent();
        long tiempoDifi = intent.getLongExtra("nivel",0);
        String tipoNivel = intent.getStringExtra("tipoNivel");

        Bundle args = new Bundle();
        args.putInt("nivelFrg", (int) tiempoDifi);
        args.putString("tipoNivel", tipoNivel);

        //para el mnú
        FragmentManager miManejador = getSupportFragmentManager();
        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
        //remplazar fragmento actual con el nuevo indicado en el contenedor
        if(GlobalAplicacion.esEstudiante.equalsIgnoreCase("N")){
            miTrnsaccion.remove(new MenuJuego());
            miTrnsaccion.replace(R.id.menu, new MenuJuegoPrueba());
        }else{
            miTrnsaccion.remove(new MenuJuegoPrueba());
            miTrnsaccion.replace(R.id.menu, new MenuJuego());
        }

        miTrnsaccion.commit();

        //poner fragment de juego uno en el centro
        FragmentManager miManejador_ = getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
        FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
        //remplazar fragmento actual con el nuevo indicado en el contenedor
        JuegoUno juegoUno = new JuegoUno();
        juegoUno.setArguments(args);
        miTrnsaccion_.replace(R.id.contenedor_fragment, juegoUno);
        miTrnsaccion_.commit();
    }
}