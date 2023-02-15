package com.example.proyfragmentmodal.profesor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.Foro;
import com.example.proyfragmentmodal.estudiante.MaterialEstudio;
import com.example.proyfragmentmodal.mini_pantalla_dos;
import com.example.proyfragmentmodal.mini_pantalla_uno;
import com.example.proyfragmentmodal.principal.MenuProfEstud;


public class MenuProfesor extends Fragment {


    public MenuProfesor() {
        // Required empty public constructor
    }

    public static MenuProfesor newInstance(String param1, String param2) {
        MenuProfesor fragment = new MenuProfesor();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_menu_profesor, container, false);


        //boton comunicacion
        vista.findViewById(R.id.btn_mn_comunicacion_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_comunicacion_prof, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // Foro
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new Foro());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1:
                                        FragmentManager miManejador_ = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion_.replace(R.id.contenedor_fragment, new mini_pantalla_dos());
                                        miTrnsaccion_.commit();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        //menu aprendizaje
        vista.findViewById(R.id.btn_mn_aprendizaje_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_aprendizaje_prof, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getActivity(), "which: "+which, Toast.LENGTH_SHORT).show();
                                switch (which) {
                                    case 0:
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new mini_pantalla_uno());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1:// MATERIAL DE ESTUDIO
                                        FragmentManager miManejador_ = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion_.replace(R.id.contenedor_fragment, new MaterialEstudio());
                                        miTrnsaccion_.commit();
                                        break;
                                    default:
                                        Toast.makeText(getActivity(), "Pantalla no configurada aún", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                //builder.create();

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        String[] listNotificaciones =
                {"Notificacion_Uno", "Notificacion_Dos", "Notificacion_Tres"};
        //boton notificacion
        vista.findViewById(R.id.btn_mn_notificacion_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Notificaciones")
                        .setItems(listNotificaciones, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0:
                                        break;
                                    case 1:
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                //builder.create();

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //boton perfil
        vista.findViewById(R.id.btn_mn_perfil_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_perfil_prof, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0://datos personales
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new mini_pantalla_uno());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://mis cursos
                                        startActivity(new Intent(v.getContext(), MisCursosProfesor.class));
                                        break;
                                    case 2://historial academico
                                        FragmentManager miManejador_ = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion_.replace(R.id.contenedor_fragment, new mini_pantalla_dos());
                                        miTrnsaccion_.commit();
                                        break;
                                    case 3://salir
                                        startActivity(new Intent(v.getContext(), MenuProfEstud.class));
                                        break;
                                    default:
                                        Toast.makeText(getActivity(), "Pantalla no configurada aún", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                //builder.create();

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return vista;
    }
}