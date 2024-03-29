package com.example.proyfragmentmodal.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.profesor.MenuProfesor;
import com.example.proyfragmentmodal.estudiante.MenuEstudiante;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        establecerMenu(extras.getString("itOrigin"));
    }

    public void establecerMenu(String origin) {
        GlobalAplicacion global = new GlobalAplicacion();

        global.setGlobalUsuario("PREUBA.............");
        //FragmentManager miManejador;//getFragmentManager(); //getParentFragmentManager();
        switch (origin) {
            case "loginEstudiante":
                FragmentManager miManejador = getSupportFragmentManager();
                FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccion.remove(new MenuProfesor());
                miTrnsaccion.replace(R.id.menu, new MenuEstudiante());
                miTrnsaccion.commit();
                break;
            case "loginProfesor":
                FragmentManager miManejador_ = getSupportFragmentManager();
                FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccion_.remove(new MenuEstudiante());
                miTrnsaccion_.replace(R.id.menu, new MenuProfesor());
                miTrnsaccion_.commit();
                break;
            case "loginAdmin":
                FragmentManager miManejadorAdmin = getSupportFragmentManager();
                FragmentTransaction miTrnsaccionAdmin = miManejadorAdmin.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccionAdmin.remove(new MenuEstudiante());
                miTrnsaccionAdmin.remove(new MenuProfesor());
                miTrnsaccionAdmin.replace(R.id.menu, new MenuAdmin());
                miTrnsaccionAdmin.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed(){
        //En caso de querer permitir volver atrás usa esta llamada:
        //super.onBackPressed();
    }
}