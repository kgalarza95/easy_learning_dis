package com.example.proyfragmentmodal.estudiante.septimo;

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
import com.example.proyfragmentmodal.estudiante.sexto.ANucleoSujeto;
import com.example.proyfragmentmodal.estudiante.sexto.AOrdernarEstructuraArticulo;
import com.example.proyfragmentmodal.estudiante.sexto.APuntoSuspensivo;
import com.example.proyfragmentmodal.estudiante.sexto.BOrdenarRelatos;
import com.example.proyfragmentmodal.estudiante.sexto.BSeleccioneRefran;
import com.example.proyfragmentmodal.estudiante.sexto.BVerboImpersonal;
import com.example.proyfragmentmodal.estudiante.sexto.CImagenConLaOracion;
import com.example.proyfragmentmodal.estudiante.sexto.CLetraRecuadro;
import com.example.proyfragmentmodal.estudiante.sexto.DPalabraBienEscrita;
import com.example.proyfragmentmodal.estudiante.sexto.DPalabraCorrecta;
import com.example.proyfragmentmodal.estudiante.sexto.DPalabraMalEscrita;
import com.example.proyfragmentmodal.general.MenuProfEstud;
import com.example.proyfragmentmodal.general.Usuarios;
import com.example.proyfragmentmodal.profesor.MisCursosProfesor;


public class MenuJg7mo extends Fragment {

    View vista;

    public MenuJg7mo() {
    }


    public static MenuJg7mo newInstance(String param1, String param2) {
        MenuJg7mo fragment = new MenuJg7mo();
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
        vista= inflater.inflate(R.layout.fragment_menu_jg7mo, container, false);

        // UNIDAD 1.
        vista.findViewById(R.id.btn_game1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Lista de Opciones")
                        .setItems(R.array.li_op_7to_unidad1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // juego uno
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new ALetraEnElRecuadro7());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://juego dos
                                        FragmentManager useH = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction frmH = useH.beginTransaction();
                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        frmH.replace(R.id.contenedor_fragment, new AOrdeneEstructuraFolleto7());
                                        frmH.commit();
                                        break;
                                    case 2://juegoo 3
                                        FragmentManager useBD = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction frmBD = useBD.beginTransaction();
                                        //miTrnsaccion.replace(R.id.contenedor_fragment, new DibujoGame());
                                        frmBD.replace(R.id.contenedor_fragment, new ASeleccioneLeyenda7());
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
                        .setItems(R.array.li_op_7to_unidad2, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // juego dos palabra mla escrita en frase
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new SeleccioneConceptoCorrecto());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://juego dos
                                        FragmentManager miManejadorP = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsac = miManejadorP.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsac.replace(R.id.contenedor_fragment, new ArrastrarSoltarOracion());
                                        miTrnsac.commit();
                                        break;
                                    case 2://juego tres
                                        FragmentManager miManejador3 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsa3 = miManejador3.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsa3.replace(R.id.contenedor_fragment, new AsociarGrafico7mo());
                                        miTrnsa3.commit();
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
                        .setItems(R.array.li_op_7to_unidad3, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: //
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new OrdenarEstructura());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://
                                        FragmentManager miManejadorL = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorX = miManejadorL.beginTransaction();
                                        miManejadorX.replace(R.id.contenedor_fragment, new TiempoVerbo());
                                        miManejadorX.commit();
                                        break;
                                    case 2://
                                        FragmentManager miManejadorO = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorM = miManejadorO.beginTransaction();
                                        miManejadorM.replace(R.id.contenedor_fragment, new TiempoVerbo());
                                        miManejadorM.commit();
                                        break;
                                    case 3://
                                        FragmentManager miManejadorP = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorN = miManejadorP.beginTransaction();
                                        miManejadorN.replace(R.id.contenedor_fragment, new CualEsPoesia());
                                        miManejadorN.commit();
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
                        .setItems(R.array.li_op_7to_unidad4, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // Dibujo Game
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new OracionEscritaCorrectamente());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://Dibujo letras
                                        FragmentManager miManejador2 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion2 = miManejador2.beginTransaction();
                                        miTrnsaccion2.replace(R.id.contenedor_fragment, new NumeroImagen());
                                        miTrnsaccion2.commit();
                                        break;
                                    case 2://juego tres
                                        FragmentManager miManejador3 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsa3 = miManejador3.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsa3.replace(R.id.contenedor_fragment, new TextoTeatral());
                                        miTrnsa3.commit();
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