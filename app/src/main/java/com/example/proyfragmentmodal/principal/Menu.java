package com.example.proyfragmentmodal.principal;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.Foro;
import com.example.proyfragmentmodal.estudiante.MaterialEstudio;
import com.example.proyfragmentmodal.mini_pantalla_dos;
import com.example.proyfragmentmodal.mini_pantalla_uno;

import java.util.ArrayList;
import java.util.List;


public class Menu extends Fragment{



    public Menu() {
    }


    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
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
        View vista = inflater.inflate(R.layout.fragment_menu, container, false);

        //identificar en que actividad me enceuntro
        Activity miActivity = getActivity();//obtener actividad actual

        //obtener los botones de la vista y agregarles eventos a cada uno.
        ImageButton btnMenu = (ImageButton) vista.findViewById(R.id.btn_mn_comunicacion);
        ImageButton btnNivel = (ImageButton) vista.findViewById(R.id.btn_mn_eval);
        ImageButton btnPerfil = (ImageButton) vista.findViewById(R.id.btn_mn_perfil);


/*
        fragment = new lunes_gestionar();

        FragmentTransaction ft =  getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();*/

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(miActivity, "Botón presionado ", Toast.LENGTH_SHORT).show();

               /* */


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_comunicacion, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                //Toast.makeText(getActivity(), "which: "+which, Toast.LENGTH_SHORT).show();
                                switch (which){
                                    case  0: // Foro
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
                //builder.create();

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        btnNivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_evaluacion, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getActivity(), "which: "+which, Toast.LENGTH_SHORT).show();
                                switch (which){
                                    case  0:
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new mini_pantalla_uno());
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

        vista.findViewById(R.id.btn_mn_aprendizaje).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_aprendizaje, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getActivity(), "which: "+which, Toast.LENGTH_SHORT).show();
                                switch (which){
                                    case  0:
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

        vista.findViewById(R.id.btn_mn_perfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_perfil, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(getActivity(), "which: "+which, Toast.LENGTH_SHORT).show();
                                switch (which){
                                    case  0:
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new mini_pantalla_uno());
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

        vista.findViewById(R.id.btn_mn_notificacion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Notificaciones")
                        .setItems(listNotificaciones, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                switch (which){
                                    case  0:
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
        return vista;
    }


}