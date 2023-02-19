package com.example.proyfragmentmodal.profesor;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.Filtro;
import com.example.proyfragmentmodal.util.ListAdapterParticipantes;

import java.util.ArrayList;


public class ParticipantesFragm extends Fragment {

    ListAdapterParticipantes adapter;

    public ParticipantesFragm() {
    }

    public static ParticipantesFragm newInstance(String param1, String param2) {
        ParticipantesFragm fragment = new ParticipantesFragm();
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
        View vista = inflater.inflate(R.layout.fragment_participantes, container, false);

        ArrayList<String> items = new ArrayList<>();
        items.add("Estudent uno");
        items.add("Estudent dos");
        items.add("Estudent tres");

        ListView listView = vista.findViewById(R.id.lv_lista_participantes_fr);
        adapter = new ListAdapterParticipantes((Context) getActivity(), (ArrayList<String>) items.clone());

        //filtroooooo====================================================================00
        //Filtro filtro = new Filtro(items, adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnTouchListener((v, event) -> {
            listView.clearFocus();
            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(listView.getWindowToken(), 0);
            return false;
        });

        ((EditText) vista.findViewById(R.id.search_field_de_lista)).addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                Log.i("=============================================>1. ", "onTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("=============================================>2. ", "beforeTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("=============================================>3. ", "afterTextChanged");
                Log.i("=============================================>3.s.length() ", String.valueOf(s.length()));
                if (s.length() == 0) {
                    Log.i("=============================================>3.Data ", String.valueOf(items));
                    // adapter.resetData((ArrayList<String>) items.clone());
                    //adapter.notifyDataSetChanged();
                    adapter = null;
                    adapter = new ListAdapterParticipantes((Context) getActivity(), (ArrayList<String>) items.clone());
                    listView.setAdapter(adapter);
                }
            }
        });
        //filtroooooo================================================


        listView.setAdapter(adapter);

        return vista;
    }
}