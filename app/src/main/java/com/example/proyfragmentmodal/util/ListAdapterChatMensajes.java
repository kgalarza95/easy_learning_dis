package com.example.proyfragmentmodal.util;


import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.entity.EntityMap;

import java.util.ArrayList;

public class ListAdapterChatMensajes extends
        //RecyclerView.Adapter<ListAdapterChatMensajes.MyViewHolder>
       ArrayAdapter<EntityMap>
{

    Context context;
    ArrayList<EntityMap> litMensaje;

    public ListAdapterChatMensajes(Context context, ArrayList<EntityMap> entityMaps) {
        super(context, 0, entityMaps);
        this.context = context;
        this.litMensaje = entityMaps;
    }

    public ListAdapterChatMensajes(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public ListAdapterChatMensajes(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    // Constructor
  /*  public ListAdapterChatMensajes(ArrayList<EntityMap>  myDataset) {
        litMensaje = litMensaje;
    }

    // Crea nuevas vistas (invocadas por el layout manager)
    @Override
    public ListAdapterChatMensajes.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Crea una nueva vista
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_lista_mensajes, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    // Devuelve el tamaño de la lista (invocada por el layout manager)
    @Override
    public int getItemCount() {
        return litMensaje.size();
    }

    // Proporciona una referencia a las vistas para cada elemento de datos
    //obtener referencia a vistas por id
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMensaje;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMensaje = itemView.findViewById(R.id.mensaje_mensaje);

        }

    }

    // Reemplaza el contenido de la vista (invocada por el layout manager)
    //(llena las vistas referenciadas anteriormente con la data)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Obtiene el elemento de la lista en esta posición
        EntityMap obj = litMensaje.get(position);

        // Reemplaza el contenido de la vista con el elemento
        holder.txtMensaje.setText(obj.getMensaje());
    }
*/

    @Override
    public int getCount() {
        return litMensaje.size();
    }

    @Override
    public EntityMap getItem(int position) {
        return litMensaje.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        EntityMap message = (EntityMap) getItem(position);
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Si el mensaje es del emisor
            if (message.getEsEmisor().equals("S")) {
                rowView = inflater.inflate(R.layout.item_burbuja_remitente, parent, false);
            }
            // Si el mensaje es del remitente
            else {
                rowView = inflater.inflate(R.layout.item_burbuja_receptor, parent, false);
            }
        }

        // Rellenar la vista con el mensaje
        TextView messageText = rowView.findViewById(R.id.mensaje_mensaje);
        messageText.setText(message.getMensaje());

        return rowView;
    }

}
