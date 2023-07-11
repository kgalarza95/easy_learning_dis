package com.example.proyfragmentmodal.estudiante.quinto;

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
import com.example.proyfragmentmodal.estudiante.MisCursosEstudiante;
import com.example.proyfragmentmodal.general.MenuProfEstud;
import com.example.proyfragmentmodal.general.Usuarios;
import com.example.proyfragmentmodal.juego.CuentosDos;
import com.example.proyfragmentmodal.juego.DibujoGame;
import com.example.proyfragmentmodal.juego.FragmentDibujoLetras;
import com.example.proyfragmentmodal.juego.JuegoDos;
import com.example.proyfragmentmodal.juego.PalabraCorrecta;
import com.example.proyfragmentmodal.juego.PalabrasConBD;
import com.example.proyfragmentmodal.profesor.MisCursosProfesor;


public class MenuJg5to extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    View vista;

    public MenuJg5to() {
    }


    public static MenuJg5to newInstance(String param1, String param2) {
        MenuJg5to fragment = new MenuJg5to();
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
        vista =  inflater.inflate(R.layout.fragment_menu_jg5to, container, false);
        // UNIDAD 1.
        vista.findViewById(R.id.btn_game1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_op_5to_unidad1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // juego uno
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new JuArrastrarSoltar());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://juego dos
                                        FragmentManager useH = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction frmH = useH.beginTransaction();
                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        frmH.replace(R.id.contenedor_fragment, new PuntoComa());
                                        frmH.commit();
                                        break;
                                    case 2://juegoo 3
                                        FragmentManager useBD = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction frmBD = useBD.beginTransaction();
                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        frmBD.replace(R.id.contenedor_fragment, new PalabrasConBD());
                                        frmBD.commit();
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

        // UNIDAD 2.
        vista.findViewById(R.id.btn_game2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_op_5to_unidad2, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // juego dos palabra mla escrita en frase
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new JuegoDos());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://juego dos
                                        FragmentManager miManejadorP = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsac = miManejadorP.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsac.replace(R.id.contenedor_fragment, new PalabraCorrecta());
                                        miTrnsac.commit();
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

        // UNIDAD 3.
        vista.findViewById(R.id.btn_game3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_op_5to_unidad3, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // Dibujo Game
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://Dibujo letras
                                        FragmentManager miManejadorL = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorX = miManejadorL.beginTransaction();

                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new FragmentCuentos());
                                        miManejadorX.replace(R.id.contenedor_fragment, new FragmentDibujoLetras());
                                        miManejadorX.commit();
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

        // UNIDAD 4.
        vista.findViewById(R.id.btn_game4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_op_5to_unidad4, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // Dibujo Game
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new UsoDeLaH());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://Dibujo letras
                                        FragmentManager miManejador2 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion2 = miManejador2.beginTransaction();
                                        miTrnsaccion2.replace(R.id.contenedor_fragment, new CuentosDos());
                                        miTrnsaccion2.commit();
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

        vista.findViewById(R.id.btn_mn_perfil_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_opciones_perfil_juego, new DialogInterface.OnClickListener() {
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