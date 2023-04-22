package com.example.proyfragmentmodal.util;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.estudiante.Foro;
import com.example.proyfragmentmodal.estudiante.MaterialEstudio;
import com.example.proyfragmentmodal.estudiante.ParticipantesFragmEstud;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//importante que se llame "ListAdapter" para que funcione el adpatador
//no es necesario llamarle ListAdapter
public class ListAdapterIconTextObject
        extends RecyclerView.Adapter<ListAdapterIconTextObject.ViewHolder>
        implements IDaoService.DAOCallbackServicio {

    public List<EntityMap> listUsuarios;
    public LayoutInflater layoutInflater;
    public Context context;
    private Gson gson = new Gson();
    ProgressDialog progressDialog;

    public ListAdapterIconTextObject() {
    }

    public ListAdapterIconTextObject(List<EntityMap> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view
        View view = layoutInflater.inflate(R.layout.cv_item_icon_text, null);
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
        try {
            progressDialog.dismiss();
            Log.i("response============>:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);

            if (data.getCodResponse().equals("00")) {
                if (opcion.equalsIgnoreCase("EL")) {
                    consultarPdfsPorCurso();
                } else if (opcion.equals("CN")) {
                    //  List<String> listFilas = (List<String>) data.getData();
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaCursos = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaCursos));
                    Log.d("Respuesta:  ", listaCursos.get(0).getRUTA());
                    Log.d("Respuesta:  ", listaCursos.get(0).getNOMBRE());
                    // init(listaCursos);

                    initConsulta(listaCursos);

                } else {
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

          /*      // Show notification with progress bar
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context, "download_channel")
                        .setSmallIcon(R.drawable.btn_descargar)
                        .setContentTitle("Downloading " + filename)
                        .setContentText("Download in progress")
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .setAutoCancel(true)
                        .setOngoing(true)
                        .setProgress(0, 0, true);

                notificationManager.notify(1, builder.build());

                //int progress = (int) (totalBytesRead * 100 / contentLength);
               // builder.setProgress(100, progress, false);
                //notificationManager.notify(1, builder.build());

                // Show notification that download is complete
                builder.setContentText("Download complete")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                notificationManager.notify(1, builder.build());*/

                        // Download the file using a background thread
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Open a connection to the server and download the file
                            URL url = new URL("http://example.com/file.pdf");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            InputStream inputStream = connection.getInputStream();
                            long contentLength = connection.getContentLength();
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            long totalBytesRead = 0;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                fos.write(buffer, 0, bytesRead);
                                totalBytesRead += bytesRead;
                                int progress = (int) (totalBytesRead * 100 / contentLength);
                                builder.setProgress(100, progress, false);
                                notificationManager.notify(1, builder.build());
                            }
                            fos.flush();
                            fos.close();
                            inputStream.close();

                            // Show notification that download is complete
                            builder.setContentText("Download complete")
                                    .setProgress(0, 0, false)
                                    .setOngoing(false);
                            notificationManager.notify(1, builder.build());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
*/
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        View manejoVista;
        TextView txtNomPDF;
        TextView txtRutaPDF;
        EditText txtIDPdf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            iv = itemView.findViewById(R.id.ic_descargar);
            txtNomPDF = itemView.findViewById(R.id.txt_titulo_cr);
            txtRutaPDF = itemView.findViewById(R.id.txt_ruta_fl);
            txtIDPdf = itemView.findViewById(R.id.txt_gone_id);
        }

        void bindData(final EntityMap itemCv) {
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtIDPdf.setText(itemCv.getID());
            txtNomPDF.setText(itemCv.getNOMBRE());
            txtRutaPDF.setText(itemCv.getRUTA());

            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mostrarDialogOption(itemCv);
                }
            });

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

            manejoVista.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    vibrator.vibrate(500);
                    mostrarDialogOption(itemCv);
                    return false;
                }
            });
        }
    }

    public void descargarPDF() {
        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://192.168.1.44/php_api_dislexia/reapldos_archivos/Geometria-uniciencias.pdf";

            //4.Crea una instancia de File y de FileOutputStream para guardar el archivo PDF en el almacenamiento interno de tu aplicación
            File file = new File(context.getExternalFilesDir(null), "mi-archivo.pdf");

            FileOutputStream outputStream = new FileOutputStream(file);

            //5.Crea una instancia de la clase InputStreamRequest, que extiende la clase Request de Volley, para obtener la respuesta de la solicitud HTTP y escribir los datos en el archivo PDF:
            InputStreamRequest request = new InputStreamRequest(Request.Method.GET, url, new Response.Listener<InputStream>() {
                @Override
                public void onResponse(InputStream response) {
                    try {
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = response.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error", "Error al descargar el archivo PDF");
                }
            });

            queue.add(request);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    //notificaicones de archivos descargado
    public void showNotificationWithAttachment(Context context, File file) {

      /*  String fileName = "nombre_del_archivo.pdf";
        File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(downloadsFolder, fileName);
        Uri fileUri = FileProvider.getUriForFile(context, "com.example.myapp.fileprovider", file);
*/

        // Obtener el URI del archivo descargado
        Uri fileUri = FileProvider.getUriForFile(context, "com.example.myapp.fileprovider", file);

        // Crear un intent para abrir el archivo adjunto
        Intent openFileIntent = new Intent(Intent.ACTION_VIEW);
        openFileIntent.setDataAndType(fileUri, getMimeType(file.getAbsolutePath()));
        openFileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Crear una notificación con el archivo adjunto
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId")
                .setSmallIcon(R.drawable.ic_op1)
                .setContentTitle("Archivo descargado")
                .setContentText("Haz clic para abrir el archivo")
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(context, 0, openFileIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(R.drawable.comunicacion_48, "Abrir", PendingIntent.getActivity(context, 0, openFileIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        // Mostrar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }

    private String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    private void mostrarDialogOption(EntityMap itemCv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Lista de Opciones")
                .setItems(R.array.li_optiones_crud, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://
                                opcionDescarga(itemCv);
                                break;
                            case 1: //
                                opcionEliminar(itemCv);
                                break;
                            default:
                                break;
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    Map<String, String> params = new HashMap<>();

    private String opcion;

    private void opcionDescarga(EntityMap itemCv) {
        //descargarPDF();
        IDaoService dao = new IDaoService(context);
        opcion = "DE";
        params.put("opcion", "DE");
        params.put("ruta_file", itemCv.getRUTA() + itemCv.getNOMBRE());


        filename = itemCv.getNOMBRE();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Descargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Log.i("send params:   ", params.toString());
        dao.manejoPDF(params, ListAdapterIconTextObject.this);
    }

    private void opcionEliminar(EntityMap itemCv) {
        IDaoService dao = new IDaoService(context);
        opcion = "EL";
        params.put("opcion", opcion);
        params.put("nombre_archivo", itemCv.getNOMBRE());
        params.put("id_registro", itemCv.getID());

        filename = itemCv.getNOMBRE();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Eliminando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Log.i("send params:   ", params.toString());
        dao.manejoPDF(params, ListAdapterIconTextObject.this);

        //Toast.makeText(context, "Presionado por mucho tiempo " + itemCv.getID(), Toast.LENGTH_SHORT).show();

    }

    public void consultarPdfsPorCurso() {
        try {
            opcion = "CN";
            params.put("opcion", opcion);
            params.put("pdf", "");
            params.put("nombre_pdf", "");
            IDaoService dao = new IDaoService(context);
            Log.i("send params:   ", params.toString());
            dao.manejoPDF(params, ListAdapterIconTextObject.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    RecyclerView recyclerView;
    public void initConsulta(List<EntityMap> litUsuarios) {
        try {
            ListAdapterIconTextObject listAdapter = new ListAdapterIconTextObject(litUsuarios, context);
            recyclerView.setAdapter(listAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
