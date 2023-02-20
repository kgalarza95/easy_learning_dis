package com.example.proyfragmentmodal.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.entity.EntityMap;

import java.util.ArrayList;

public class ListAdaptRecyChat extends RecyclerView.Adapter<ListAdaptRecyChat.ChatViewHolder> {

    ArrayList<EntityMap> litMensaje;

    public ListAdaptRecyChat(ArrayList<EntityMap>  messages) {
        this.litMensaje = messages;
    }

    @Override
    public int getItemViewType(int position) {
        EntityMap message = litMensaje.get(position);
        if (message.getEsEmisor().equals("S")) {
            // El mensaje fue enviado por el usuario actual
            return 0;
        } else {
            // El mensaje fue recibido
            return 1;
        }
    }


    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int idLayout = viewType ==0 ? R.layout.item_burbuja_receptor:R.layout.item_burbuja_remitente;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(idLayout, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        EntityMap message = litMensaje.get(position);
        holder.messageTextView.setText(message.getMensaje());
    }

    @Override
    public int getItemCount() {
        return litMensaje.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.mensaje_mensaje);
        }
    }
}
