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

import com.example.proyfragmentmodal.estudiante.septimo.u4.J7U41OracionEscrita;
import com.example.proyfragmentmodal.estudiante.septimo.u4.J7U42NumeroImagen;
import com.example.proyfragmentmodal.estudiante.septimo.u4.J7U43TextoTeatral;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.MisCursosEstudiante;
import com.example.proyfragmentmodal.estudiante.septimo.u2.J7U21ConceptoCorrecto;
import com.example.proyfragmentmodal.estudiante.septimo.u2.J7U22ComplementoCircunstancial;
import com.example.proyfragmentmodal.estudiante.septimo.u2.J7U23AsociarGrafico;
import com.example.proyfragmentmodal.estudiante.septimo.u3.J7U31OrdernarEstructura;
import com.example.proyfragmentmodal.estudiante.septimo.u3.J7U32TiempoVerbo;
import com.example.proyfragmentmodal.estudiante.septimo.u3.J7U33PPoesia;
import com.example.proyfragmentmodal.general.MainActivity;
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
                builder.setTitle("UNIDAD 1")
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
                builder.setTitle("UNIDAD 2")
                        .setItems(R.array.li_op_7to_unidad2, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // juego dos palabra mla escrita en frase
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new J7U21ConceptoCorrecto());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://juego dos
                                        FragmentManager miManejadorP = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsac = miManejadorP.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsac.replace(R.id.contenedor_fragment, new J7U22ComplementoCircunstancial());
                                        miTrnsac.commit();
                                        break;
                                    case 2://juego tres
                                        FragmentManager miManejador3 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsa3 = miManejador3.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsa3.replace(R.id.contenedor_fragment, new J7U23AsociarGrafico());
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
                builder.setTitle("UNIDAD 3")
                        .setItems(R.array.li_op_7to_unidad3, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: //
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new J7U31OrdernarEstructura());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://
                                        FragmentManager miManejadorL = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miManejadorX = miManejadorL.beginTransaction();
                                        miManejadorX.replace(R.id.contenedor_fragment, new J7U32TiempoVerbo());
                                        miManejadorX.commit();
                                        break;
                                    case 2:
                                        FragmentManager miManejadory = getActivity().getSupportFragmentManager();
                                        FragmentTransaction miManejadorX3 = miManejadory.beginTransaction();
                                        miManejadorX3.replace(R.id.contenedor_fragment, new J7U33PPoesia());
                                        miManejadorX3.commit();
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
                builder.setTitle("UNIDAD 4")
                        .setItems(R.array.li_op_7to_unidad4, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0: // Dibujo Game
                                        FragmentManager miManejador = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion = miManejador.beginTransaction();
                                        miTrnsaccion.replace(R.id.contenedor_fragment, new J7U41OracionEscrita());
                                        miTrnsaccion.commit();
                                        break;
                                    case 1://Dibujo letras
                                        FragmentManager miManejador2 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsaccion2 = miManejador2.beginTransaction();
                                        miTrnsaccion2.replace(R.id.contenedor_fragment, new J7U42NumeroImagen());
                                        miTrnsaccion2.commit();
                                        break;
                                    case 2://juego tres
                                        FragmentManager miManejador3 = getActivity().getSupportFragmentManager();//getFragmentManager(); //getParentFragmentManager();
                                        FragmentTransaction miTrnsa3 = miManejador3.beginTransaction();
                                        //remplazar fragmento actual con el nuevo indicado en el contenedor
                                        miTrnsa3.replace(R.id.contenedor_fragment, new J7U43TextoTeatral());
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
                builder.setTitle("OPCIONES")
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

        vista.findViewById(R.id.btn_mn_aula_v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("itOrigin", "loginEstudiante");
                startActivity(intent);
            }
        });

        return vista;
    }
}