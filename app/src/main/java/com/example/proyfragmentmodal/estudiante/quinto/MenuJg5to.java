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
import com.example.proyfragmentmodal.profesor.MisCursosProfesor;


public class MenuJg5to extends Fragment {

    View vista;

    public MenuJg5to() {
    }


    public static MenuJg5to newInstance(String param1, String param2) {
        MenuJg5to fragment = new MenuJg5to();
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
                                    case 0: //
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new ArrastrarSaltar5to());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://
                                        FragmentManager useH = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction frmH = useH.beginTransaction();
                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        frmH.replace(R.id.contenedor_fragment, new PuntoComa5to());
                                        frmH.commit();
                                        break;
                                    case 2://
                                        FragmentManager useBD = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction frmBD = useBD.beginTransaction();
                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        frmBD.replace(R.id.contenedor_fragment, new Tilde5to());
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
                                    case 0: //
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new CompletaOracion());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://juego dos
                                        FragmentManager miManejadorP = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsac = miManejadorP.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsac.replace(R.id.contenedor_fragment, new SeleccionePalabraCorrecta());
                                        miTrnsac.commit();
                                        break;
                                    case 2:
                                        FragmentManager miManejadorQ = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsacQ = miManejadorQ.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsacQ.replace(R.id.contenedor_fragment, new Leyenda());
                                        miTrnsacQ.commit();
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
                                    case 0: //
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new AsociarImagen());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://
                                        FragmentManager miManejadorL = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorX = miManejadorL.beginTransaction();

                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new FragmentCuentos());
                                        miManejadorX.replace(R.id.contenedor_fragment, new UsoBV());
                                        miManejadorX.commit();
                                        break;
                                    case 2://
                                        FragmentManager miManejadorM = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorMX = miManejadorM.beginTransaction();

                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new FragmentCuentos());
                                        miManejadorMX.replace(R.id.contenedor_fragment, new RimaEscritaCorrectamente());
                                        miManejadorMX.commit();
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
                                        FragmentTransaction miTrnsaccion4 = miManejador2.beginTransaction();
                                        miTrnsaccion4.replace(R.id.contenedor_fragment, new Palabras_Homofonas());
                                        miTrnsaccion4.commit();
                                        break;
                                    case 2://Dibujo letras
                                        FragmentManager miManejador2X = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion2X = miManejador2X.beginTransaction();
                                        miTrnsaccion2X.replace(R.id.contenedor_fragment, new RimaEscritaCorrectamente());
                                        miTrnsaccion2X.commit();
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