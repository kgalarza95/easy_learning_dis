package com.example.proyfragmentmodal.profesor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.ListAdapterParticipantes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Participantes extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private String opcion;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

     /*   ArrayList<ListItem> items = new ArrayList<>();
        items.add(new ListItem("Item 1", R.drawable.image1));
        items.add(new ListItem("Item 2", R.drawable.image2));
        items.add(new ListItem("Item 3", R.drawable.image3));
        */
        ArrayList<String> items = new ArrayList<>();
        items.add("Estudent uno");
        items.add("Estudent dos");
        items.add("Estudent tres");

        ListView listView = findViewById(R.id.lv_lista_participantes);
        ListAdapterParticipantes adapter = new ListAdapterParticipantes(this, items);
        listView.setAdapter(adapter);

        fab = findViewById(R.id.fab_new_estudiante);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Participantes.this, "Clic en float button", Toast.LENGTH_SHORT).show();
            }
        });

        // Obtener el Intent que inició esta actividad y extraer el dato de tipo String
        Intent intent = getIntent();
        String dato = intent.getStringExtra("idCurso");

        Log.d("===============================================================> ", dato);
        Log.d("", dato);
    }

}