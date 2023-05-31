package com.example.proyfragmentmodal.adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.profesor.MisCursosProfesor;
import com.example.proyfragmentmodal.profesor.Participantes;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//importante que se llame "ListAdapter" para que funcione el adpatador
//no es necesario llamarle ListAdapter
public class ListAdapterMisCursos
        extends RecyclerView.Adapter<ListAdapterMisCursos.ViewHolder>
        implements IDaoService.DAOCallbackServicio {

    ProgressDialog progressDialog;
    private List<EntityMap> listaCursos;
    private LayoutInflater layoutInflater;
    private Context context;
    private Gson gson = new Gson();
    private int posicionItem;
    private int idCurso;
    private View vista;
    private String opcion;
    private TextView txtNomCurso;
    private TextView txtDescCurso;
    private ListAdapterMisCursos listAdapter;
    private EntityMap objeMoidificadoEl;

    public ListAdapterMisCursos() {
    }

    public ListAdapterMisCursos(List<EntityMap> listaCursos, Context context) {
        this.listaCursos = listaCursos;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public ListAdapterMisCursos getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(ListAdapterMisCursos listAdapter) {
        this.listAdapter = listAdapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view (layout creado)
        View view = layoutInflater.inflate(R.layout.cv_item_cursos, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(listaCursos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public void setItem(List<EntityMap> listUsuarios) {
        this.listaCursos = listUsuarios;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View manejoVista;
        ImageView iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            vista = itemView;
            //iv = itemView.findViewById(R.id.ic);
            txtNomCurso = itemView.findViewById(R.id.txt_titulo_cr);
            txtDescCurso = itemView.findViewById(R.id.txt_descripcion_cr);
            progressDialog = new ProgressDialog(context);
        }

        void bindData(final EntityMap objeto) {
        //void bindData(final EntityMap objeto, int pos) {
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtNomCurso.setText(objeto.getNOMBRE());
            txtDescCurso.setText(objeto.getDESCRIPCION());
            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //posicionItem = holder.getAdapterPosition();
                   // posicionItem = pos;
                   /* int clicPosition = getAdapterPosition(); // Obtener la posición del CardView al que se le hizo clic
                    if (clicPosition != RecyclerView.NO_POSITION) {
                        posicionItem = clicPosition;
                    }*/
                    //Toast.makeText(context, "ID Clic.. "+objeto.getID(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Participantes.class);
                    intent.putExtra("idCurso", objeto.getID());
                    idCurso = Integer.parseInt(objeto.getID().toString().replace(".00", ""));
                    if (context instanceof MisCursosProfesor) {
                        mostrarDialogOption(objeto);
                    } else {
                        intent.putExtra("origenCall", "estud");
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    private void mostrarDialogOption(EntityMap itemCv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Lista de Opciones")
                .setItems(R.array.li_opciones_cursos, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://Ver Estudiantes del curso
                                Intent intent = new Intent(context, Participantes.class);
                                intent.putExtra("idCurso", itemCv.getID());
                                intent.putExtra("origenCall", "prof");
                                context.startActivity(intent);
                                break;
                            case 1: //Editar Curso
                                requestCursos();
                                break;
                            case 2: //Elimnar Curso
                                modalConfirmacionEliminacionCurso(itemCv.getID(), itemCv);
                                break;
                            default:
                                break;
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void modalConfirmacionEliminacionCurso(String idCrso, EntityMap itemCv) {
        // Dentro de tu método onClick o donde deseas mostrar el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarCursos(idCrso, itemCv);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }

    public void eliminarCursos(String idCurso, EntityMap itemCv) {
        objeMoidificadoEl = itemCv;
        progressDialog.setMessage("Eliminando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        opcion = "EL_CURSO";
        Map<String, String> params = new HashMap<>();
        params.put("opcion", "EL_CURSO");//eliminar curso
        params.put("id_profesor", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("id_curso", idCurso);

        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");

        IDaoService dao = new IDaoService(context);
        dao.crudCursosProf(params, ListAdapterMisCursos.this);
    }

    public void removeItem(int position) {
        listaCursos.remove(position);
        notifyItemRemoved(position);
    }


    public void requestCursos() {
        GlobalAplicacion global = new GlobalAplicacion();
        opcion = "CN_CURSO";

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
        params.put("id_curso", String.valueOf(idCurso));
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");

        IDaoService dao = new IDaoService(context);
        dao.crudCursosProf(params, ListAdapterMisCursos.this);
    }

    private String nuevoNombre, nuevaDescripcion;

    public void alertDialogEditarCurso(String nombreCurso, String Descripcion) {
        GlobalAplicacion global = new GlobalAplicacion();


        // Crear una instancia de la vista personalizada
        //@SuppressLint("ResourceType") View customView = vista.findViewById(R.layout.dialog_frm_cursos_prof);
        // Inflar el diseño del diálogo
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.dialog_frm_cursos_prof, null);

        // Obtener los objetos EditText en la vista personalizada
        EditText txtNombCurso = customView.findViewById(R.id.txt_nom_curso);
        EditText txtDescripcionCurso = customView.findViewById(R.id.txt_descripcion_curso);
        EditText txtAnioLectivo = customView.findViewById(R.id.txt_anio_lectivo);

        txtNombCurso.setText(nombreCurso);
        txtDescripcionCurso.setText(Descripcion);
        // Crear el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Actualizar Curso")
                .setView(customView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Obtener los valores ingresados en los EditText

                        opcion = "AC_CURSO";

                        nuevoNombre = txtNombCurso.getText().toString();
                        nuevaDescripcion = txtDescripcionCurso.getText().toString();

                        Map<String, String> params = new HashMap<>();
                        params.put("opcion", opcion);
                        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
                        params.put("id_curso", String.valueOf(idCurso));
                        params.put("nombre", txtNombCurso.getText().toString());
                        params.put("descripcion", txtDescripcionCurso.getText().toString());
                        params.put("anio", txtAnioLectivo.getText().toString());
                        params.put("estado", "S");


                        IDaoService dao = new IDaoService(context);
                        dao.crudCursosProf(params, ListAdapterMisCursos.this);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Haz algo cuando se haga clic en Cancelar
                    }
                });

        // Mostrar el AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void requestReloadPantalla() {
        GlobalAplicacion global = new GlobalAplicacion();
        opcion = "CN";

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");

        IDaoService dao = new IDaoService(context);
        dao.crudCursosProf(params, ListAdapterMisCursos.this);
    }

   /* public void init(List<EntityMap> listaCursos) {
        ListAdapterMisCursos listAdapter = new ListAdapterMisCursos(listaCursos, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }*/
    public void updateData(List<EntityMap> nuevaListaCursos) {
        listaCursos = nuevaListaCursos;
        notifyDataSetChanged();
    }

    public void updateDataCh(List<EntityMap> nuevaListaCursos) {
        Log.i("old data ", String.valueOf(listAdapter.listaCursos));
        listAdapter.listaCursos.clear();
        listAdapter.listaCursos.addAll(nuevaListaCursos);
        Log.i("nueva data ", String.valueOf(listAdapter.listaCursos));
        Log.i("recibida data ", String.valueOf(nuevaListaCursos));
        notifyDataSetChanged();
    }

    @Override
    public void onSuccess(String response) {
        try {
            progressDialog.dismiss();
            Log.i("response============>:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);

            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CN_CURSO")) {//consulta de curss por ID del profesor
                    Map<String, Object> listFilas = (Map<String, Object>) data.getData();
                    alertDialogEditarCurso(listFilas.get("NOMBRE").toString(), listFilas.get("DESCRIPCION").toString());

                } else if (opcion.equals("AC_CURSO")) {//actualizar curso
                    Toast.makeText(context, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
                    //txtNomCurso.setText(nuevoNombre);
                    //txtDescCurso.setText(nuevaDescripcion);
/*
                    EntityMap objeto = listaCursos.get(posicionItem);
                    objeto.setNOMBRE(nuevoNombre); // Actualiza el valor en el objeto correspondiente
                    objeto.setDESCRIPCION(nuevaDescripcion); // Actualiza el valor en el objeto correspondiente
                    notifyItemChanged(posicionItem);*/
                    requestReloadPantalla();
                } else if (opcion.equals("CN")) {
                    //  List<String> listFilas = (List<String>) data.getData();
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaCursos = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaCursos));
                    Log.d("Respuesta:  ", listaCursos.get(0).getNOMBRE());
                    Log.d("Respuesta:  ", listaCursos.get(1).getNOMBRE());
                  //updateData(listaCursos);
                    //listAdapter.setItem(listaCursos);
                    //listAdapter.notifyDataSetChanged();
                    updateDataCh( listaCursos);
                }else {
                    //removeItem(posicionItem);
                    //requestReloadPantalla();
                    Log.i("objeto a eliminar ", objeMoidificadoEl.toString());
                    listaCursos.remove(objeMoidificadoEl);
                    Log.i("nueva lista  ", String.valueOf(listaCursos));
                    // Notifica al Adapter que el conjunto de datos ha cambiado
                    notifyDataSetChanged();
                }
            } else {
                Toast.makeText(context, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        try {
            progressDialog.dismiss();
            Log.e("response============>:  ", String.valueOf(error));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
