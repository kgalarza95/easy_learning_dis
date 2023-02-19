package com.example.proyfragmentmodal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.util.ListAdaptRecyChat;
import com.example.proyfragmentmodal.util.ListAdapterChatMensajes;
import com.example.proyfragmentmodal.util.ListAdapterIconText;
import com.example.proyfragmentmodal.util.ListAdapterParticipantes;

import java.util.ArrayList;
import java.util.List;

public class ChatGeneral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_general);


        ArrayList<EntityMap> litMensaje = new ArrayList<>();

        litMensaje.add(new EntityMap("S","S","YO HOLA"));
        litMensaje.add(new EntityMap("N","N","TÚ HOLA"));
        litMensaje.add(new EntityMap("S","S","YO HOLA 2"));



        //ListView listView = findViewById(R.id.lv_lista_mensajes);
        //ListAdapterChatMensajes listAdapter = new ListAdapterChatMensajes( this, litMensaje);
        //listView.setAdapter(listAdapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);*/

        // Inicializar el RecyclerView y el ChatAdapter
        RecyclerView  chatRecyclerView = findViewById(R.id.rv_lista_mensajes);
        ListAdaptRecyChat chatAdapter = new ListAdaptRecyChat(litMensaje);

        // Configurar el RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);


        // Notificar al adaptador que se han agregado nuevos mensajes
        chatAdapter.notifyDataSetChanged();


    }
}