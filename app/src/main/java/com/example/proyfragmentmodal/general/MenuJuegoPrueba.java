package com.example.proyfragmentmodal.general;

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

import com.example.proyfragmentmodal.juego.FragmentCuentos;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.juego.DibujoGame;
import com.example.proyfragmentmodal.juego.JuegoDos;
import com.example.proyfragmentmodal.juego.JuegoUno;
import com.example.proyfragmentmodal.juego.UsoDeLaH;

@Deprecated
public class MenuJuegoPrueba extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View vista;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuJuegoPrueba() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment menu_juego_prueba.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuJuegoPrueba newInstance(String param1, String param2) {
        MenuJuegoPrueba fragment = new MenuJuegoPrueba();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_menu_juego_prueba, container, false);

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
                        .setItems(R.array.li_opciones_perfil_prueba, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0://salir
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