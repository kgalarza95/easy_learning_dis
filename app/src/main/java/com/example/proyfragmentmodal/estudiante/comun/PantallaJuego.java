package com.example.proyfragmentmodal.estudiante.comun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.MenuJuego;
import com.example.proyfragmentmodal.estudiante.quinto.ArrastrarSaltar5to;
import com.example.proyfragmentmodal.estudiante.quinto.MenuJg5to;
import com.example.proyfragmentmodal.estudiante.septimo.ALetraEnElRecuadro7;
import com.example.proyfragmentmodal.estudiante.septimo.MenuJg7mo;
import com.example.proyfragmentmodal.estudiante.sexto.MenuJg6to;
import com.example.proyfragmentmodal.general.MenuJuegoPrueba;
import com.example.proyfragmentmodal.juego.JuegoUno;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

public class PantallaJuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);


        Intent intent = getIntent();
      /*  long tiempoDifi = intent.getLongExtra("nivel",0);
        String tipoNivel = intent.getStringExtra("tipoNivel");
*/
      /*  Bundle args = new Bundle();
        args.putInt("nivelFrg", (int) tiempoDifi);
        args.putString("tipoNivel", tipoNivel);*/

        //para el mnú
        FragmentManager miManejador = getSupportFragmentManager();
        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();

        //poner fragment de juego uno en el centro (central o fragment de juegos)
        FragmentManager fgManejadorContenedorJuego = getSupportFragmentManager();
        FragmentTransaction miTrnContenedor = fgManejadorContenedorJuego.beginTransaction();


        //remplazar fragmento actual con el nuevo indicado en el contenedor
        if(GlobalAplicacion.esEstudiante.equalsIgnoreCase("N")){
            miTrnsaccion.remove(new MenuJuego());
            miTrnsaccion.replace(R.id.menu, new MenuJuegoPrueba());
        }else{
            miTrnsaccion.remove(new MenuJuegoPrueba());
            //miTrnsaccion.replace(R.id.menu, new MenuJuego());

            Log.i("número de curso", String.valueOf(GlobalAplicacion.getGlobalNumCurso()));
            switch (GlobalAplicacion.getGlobalNumCurso()){
                case 5:
                    miTrnsaccion.replace(R.id.menu, new MenuJg5to());
                    //Inicializar el juego uno de cada unidad
                    ArrastrarSaltar5to arrastrarSaltar5to = new ArrastrarSaltar5to();
                    miTrnContenedor.replace(R.id.contenedor_fragment, arrastrarSaltar5to);
                    break;
                case 6:
                    miTrnsaccion.replace(R.id.menu, new MenuJg6to());
                    //Inicializar el juego uno de cada unidad
                    JuegoUno juegoUnoxx = new JuegoUno();
                    miTrnContenedor.replace(R.id.contenedor_fragment, juegoUnoxx);
                    break;
                case 7:
                    miTrnsaccion.replace(R.id.menu, new MenuJg7mo());
                    //Inicializar el juego uno de cada unidad
                    ALetraEnElRecuadro7 aLetraEnElRecuadro7 = new ALetraEnElRecuadro7();
                    miTrnContenedor.replace(R.id.contenedor_fragment, aLetraEnElRecuadro7);
                    break;
                default:
            }

        }
        //miTrnsaccion.replace(R.id.menu, new MenuJuego());
        miTrnsaccion.commit();
        miTrnContenedor.commit();



    }
}