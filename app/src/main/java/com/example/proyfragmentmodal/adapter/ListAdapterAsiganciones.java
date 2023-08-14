package com.example.proyfragmentmodal.adapter;


import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Environment;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListAdapterAsiganciones
        extends RecyclerView.Adapter<ListAdapterAsiganciones.ViewHolder>
        implements IDaoService.DAOCallbackServicio {

    public List<EntityMap> listUsuarios;
    public LayoutInflater layoutInflater;
    public Context context;
    private Gson gson = new Gson();

    private TextView txtCalificacionG;
    private ProgressDialog progressDialog;
    private Map<String, String> params = new HashMap<>();


    public ListAdapterAsiganciones() {
    }

    public ListAdapterAsiganciones(List<EntityMap> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view
        View view = layoutInflater.inflate(R.layout.cv_item_asignacion, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(listUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return listUsuarios.size();
    }

    public void setItem(List<EntityMap> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    FileOutputStream fos = null;
    String filename;

    @Override
    public void onSuccess(String response) {
        progressDialog.dismiss();
        try {
            Log.i("response============>:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);

            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("DE")) {//descargar PDF
                    try {
                        Log.d("data:  ", (String) data.getData());
                        String base64String = (String) data.getData(); // Cadena de texto en base64
                        byte[] dataB64 = Base64.decode(base64String, Base64.DEFAULT); // Decodificar la cadena base64

                        //String filename = "archivo_123.pdf";
                        // Crear un archivo en el directorio de descargas del dispositivo
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

                        // Escribir los datos decodificados en el archivo


                        fos = new FileOutputStream(file);
                        fos.write(dataB64);
                        fos.close();

                        //showNotificationWithAttachment(context, file);

                        // Primero, crea un canal de notificación
                        String channelId = "my_channel_id";
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel(channelId, "My Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
                            notificationManager.createNotificationChannel(channel);
                        }

                        // Crea una notificación
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                                .setSmallIcon(R.drawable.ic_op1) // Icono de la notificación
                                .setContentTitle("Notificación de Archivo Descargado") // Título de la notificación
                                .setContentText("El archivo PDF se ha guardado en la carpeta de descargas") // Contenido de la notificación
                                .setPriority(NotificationCompat.PRIORITY_HIGH) // Prioridad de la notificación
                                .setAutoCancel(true); // Cancela la notificación al tocarla

                        // Muestra la notificación
                        notificationManager.notify(/* ID de la notificación */ 1, builder.build());

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (opcion.equals("DE")) {//descargar PDF
                    Toast.makeText(context, "NO HAY ARCHIVOS CARGADOS", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

    @Override
    public void onError(VolleyError error) {
        progressDialog.dismiss();
        try {
            Log.e("response============>:  ", String.valueOf(error));
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View manejoVista;
        TextView txtTitulo;
        TextView txtEstudiante;
        TextView txtContenido;
        TextView txtCalificacion;
        TextView txtFechaEntrega;
        Button btnDescargar;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            txtTitulo = itemView.findViewById(R.id.txt_titulo);
            txtEstudiante = itemView.findViewById(R.id.txt_estudiante);
            txtContenido = itemView.findViewById(R.id.txt_contenido);
            txtCalificacion = itemView.findViewById(R.id.txt_calificacion);
            txtFechaEntrega = itemView.findViewById(R.id.txt_fch_entrega);
            txtCalificacionG = txtCalificacion;
            btnDescargar = itemView.findViewById(R.id.btnAdjunto);
        }

        void bindData(final EntityMap itemCv) {
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtTitulo.setText(itemCv.getTITULO());
            txtEstudiante.setText(itemCv.getNOMBRES());
            txtContenido.setText(itemCv.getENTREGA());
            txtCalificacion.setText(itemCv.getCALIFICACION());
            txtFechaEntrega.setText(itemCv.getFECHA_ENTREGA());

            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    abrirCuadroDialogo(view, itemCv.getID());
                }
            });

            btnDescargar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    opcionDescarga(itemCv);
                }
            });
        }
    }


    public void abrirCuadroDialogo(View view, String idTarea) {
        // Crea un objeto AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Calificar");

        // Crea un EditText para que el usuario ingrese la calificación
        final EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);

        // Agrega botones para "Aceptar" y "Cancelar"
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtiene el valor ingresado por el usuario
                String calificacion = editText.getText().toString();

                txtCalificacionG.setText(calificacion);

                Map<String, String> params = new HashMap<>();
                // opcion = "CN";
                params.put("opcion", "CALIF");
                params.put("calificacion", calificacion);
                params.put("id_tarea", idTarea);

                Log.i("Parámetros===>", String.valueOf(params));
                IDaoService dao = new IDaoService(context);
                dao.crudAsignacion(params, ListAdapterAsiganciones.this);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cierra el cuadro de diálogo sin hacer nada
            }
        });

        // Muestra el cuadro de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    String opcion;

    private void opcionDescarga(EntityMap itemCv) {
        //descargarPDF();
        IDaoService dao = new IDaoService(context);
        opcion = "DE";
        params.put("opcion", "DE");
        params.put("ruta_file", itemCv.getRUTA_FILE_TAREA());


        filename = "";

        String filePath = itemCv.getRUTA_FILE_TAREA();
        System.out.println("=====> filePath "+filePath);

        // Obtener el índice de la última barra invertida o barra diagonal en la ruta
        int lastIndex = filePath.lastIndexOf("\\");
        if (lastIndex == -1) {
            lastIndex = filePath.lastIndexOf("/");
        }

        // Extraer el nombre del archivo con extensión
        String fileNameWithExtension = filePath.substring(lastIndex + 1);

        System.out.println("Nombre del archivo con extensión: " + fileNameWithExtension);

        // Obtener solo el nombre del archivo (sin la ruta)
        int lastDotIndex = fileNameWithExtension.lastIndexOf(".");
        String fileNameWithoutPath = "";
        if (lastDotIndex != -1) {
             fileNameWithoutPath = fileNameWithExtension.substring(0, lastDotIndex);
            System.out.println("Nombre del archivo sin la ruta y extensión: " + fileNameWithoutPath);
        } else {
            System.out.println("No se encontró extensión en el nombre del archivo");
        }

        System.out.println("=====> fileNameWithExtension "+fileNameWithExtension);
        System.out.println("=====> fileNameWithoutPath "+fileNameWithoutPath);

        filename = "tarea_alumno.pdf";

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Descargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.i("send params:   ", params.toString());
        dao.manejoPDF(params, ListAdapterAsiganciones.this);
    }

}
