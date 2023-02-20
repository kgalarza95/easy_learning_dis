package com.example.proyfragmentmodal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.util.ListAdaptRecyChat;
import com.example.proyfragmentmodal.util.ListAdapterChatMensajes;
import com.example.proyfragmentmodal.util.ListAdapterIconText;
import com.example.proyfragmentmodal.util.ListAdapterParticipantes;

import java.util.ArrayList;
import java.util.List;

public class ChatGeneral extends AppCompatActivity {

    private EditText smsENVIAR;
    ListAdaptRecyChat chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_general);

        smsENVIAR = findViewById(R.id.message_input);

        ArrayList<EntityMap> litMensaje = new ArrayList<>();

        litMensaje.add(new EntityMap("S", "S", "YO HOLA"));
        litMensaje.add(new EntityMap("N", "N", "TÚ HOLA"));
        litMensaje.add(new EntityMap("S", "S", "YO HOLA 2"));


        //ListView listView = findViewById(R.id.lv_lista_mensajes);
        //ListAdapterChatMensajes listAdapter = new ListAdapterChatMensajes( this, litMensaje);
        //listView.setAdapter(listAdapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);*/

        // Inicializar el RecyclerView y el ChatAdapter
        RecyclerView chatRecyclerView = findViewById(R.id.rv_lista_mensajes);
        chatAdapter = new ListAdaptRecyChat((ArrayList<EntityMap>) litMensaje.clone());

        // Configurar el RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);


        // Notificar al adaptador que se han agregado nuevos mensajes
        chatAdapter.notifyDataSetChanged();

        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                litMensaje.add(new EntityMap("S", "S", smsENVIAR.getText().toString()));
                chatAdapter = null;
                chatAdapter = new ListAdaptRecyChat((ArrayList<EntityMap>) litMensaje.clone());
                chatRecyclerView.setAdapter(chatAdapter);
                chatAdapter.notifyDataSetChanged();
                smsENVIAR.setText("");
            }
        });

    }
}