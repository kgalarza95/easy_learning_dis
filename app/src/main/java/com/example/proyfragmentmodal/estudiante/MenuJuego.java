package com.example.proyfragmentmodal.estudiante;

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

import com.example.proyfragmentmodal.FragmentCuentos;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.general.MenuProfEstud;
import com.example.proyfragmentmodal.general.Usuarios;
import com.example.proyfragmentmodal.profesor.MisCursosProfesor;

public class MenuJuego extends Fragment {

    public MenuJuego() {
        // Required empty public constructor
    }

    public static MenuJuego newInstance(String param1, String param2) {
        MenuJuego fragment = new MenuJuego();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_menu_juego, container, false);

        vista.findViewById(R.id.btn_game1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccion.replace(R.id.contenedor_fragment, new JuegoUno());
                miTrnsaccion.commit();
            }
        });

        vista.findViewById(R.id.btn_game2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                //remplazar fragmento actual con el nuevo indicado en el contenedor
                miTrnsaccion.replace(R.id.contenedor_fragment, new JuegoDos());
                miTrnsaccion.commit();
            }
        });

        vista.findViewById(R.id.btn_game3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                miTrnsaccion.commit();
            }
        });

        vista.findViewById(R.id.btn_game4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                FragmentTransaction miTrnsaccion = miManejador.beginTransaction();


                //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                miTrnsaccion.replace(R.id.contenedor_fragment, new UsoDeLaH());
                miTrnsaccion.commit();
            }
        });

        vista.findViewById(R.id.btn_game5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                FragmentTransaction miTrnsaccion = miManejador.beginTransaction();

                miTrnsaccion.replace(R.id.contenedor_fragment, new FragmentCuentos());
                miTrnsaccion.commit();
            }
        });

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
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new Usuarios(1));
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://mis cursos
                                        startActivity(new Intent(v.getContext(), MisCursosProfesor.class));
                                        break;
                                    case 99://historial academico
                                        FragmentManager miManejador_ = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion_ = miManejador_.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion_.replace(R.id.contenedor_fragment, new MisCursosEstudiante());
                                        miTrnsaccion_.commit();
                                        break;
                                    case 2://salir
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