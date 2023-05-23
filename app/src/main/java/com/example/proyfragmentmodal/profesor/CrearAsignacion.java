package com.example.proyfragmentmodal.profesor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.CursoArrayAdapter;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.example.proyfragmentmodal.util.ListAdapterMisCursos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearAsignacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearAsignacion extends Fragment implements IDaoService.DAOCallbackServicio {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View vista;
    private Spinner spinnerCursos;
    private EditText tituloAsignacion;
    private EditText instrucciones;
    private DatePicker fechaVencimiento;
    private Button botonGuardar;
    private Gson gson = new Gson();
    private String opcion;

    private String mParam1;
    private String mParam2;

    public CrearAsignacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearAsignacion.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearAsignacion newInstance(String param1, String param2) {
        CrearAsignacion fragment = new CrearAsignacion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_crear_asignacion, container, false);

        spinnerCursos = vista.findViewById(R.id.spinner_cursos);
        tituloAsignacion = vista.findViewById(R.id.titulo_asignacion);
        instrucciones = vista.findViewById(R.id.instrucciones);
        fechaVencimiento = vista.findViewById(R.id.fecha_vencimiento);
        botonGuardar = vista.findViewById(R.id.boton_guardar);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntityMap cursoSeleccionado = (EntityMap) spinnerCursos.getSelectedItem();
                long idCursoSeleccionado = Long.parseLong(cursoSeleccionado.getID());
                Map<String, String> params = new HashMap<>();
                opcion = "IN";
                params.put("opcion", opcion);
                //params.put("id_curso", String.valueOf(spinnerCursos.getSelectedItemId()));
                params.put("id_curso", String.valueOf(idCursoSeleccionado));
                params.put("titulo", tituloAsignacion.getText().toString());
                params.put("instrucciones", instrucciones.getText().toString());
                params.put("fecha_vencimiento", String.format("%02d/%02d/%d", fechaVencimiento.getDayOfMonth(), fechaVencimiento.getMonth() + 1, fechaVencimiento.getYear()));

                Log.i("params send =================>", String.valueOf(params));
                IDaoService dao = new IDaoService(getActivity());
                dao.crudAsignacion(params, CrearAsignacion.this);
            }
        });


        requestCursos();
        return vista;
    }


    public void requestCursos() {
        GlobalAplicacion global = new GlobalAplicacion();
        opcion = "CC";

        Map<String, String> params = new HashMap<>();
        params.put("opcion", "CN");
        //params.put("id_profesor", String.valueOf(1));
        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");

        IDaoService dao = new IDaoService(getActivity());
        dao.crudCursosProf(params, CrearAsignacion.this);
    }

    @Override
    public void onSuccess(String response) {
        Log.d("Response==========>  ", response);
        Respuesta data = gson.fromJson(response, Respuesta.class);
        if (data.getCodResponse().equals("00")) {
            if (opcion.equals("IN")) {
                Toast.makeText(getActivity(), "TRANSACCIÓN OK", Toast.LENGTH_SHORT).show();

                spinnerCursos.setSelection(0);
                tituloAsignacion.setText("");
                instrucciones.setText("");
                fechaVencimiento.updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            } else if (opcion.equals("CN")) {
                // consulta de cursos
                Map<String, Object> listFilas = (Map<String, Object>) data.getData();

                spinnerCursos.setSelection(Integer.parseInt((String) listFilas.get("ID_CURSO")) - 1);
                tituloAsignacion.setText((String) listFilas.get("TITULO"));
                instrucciones.setText((String) listFilas.get("INSTRUCCIONES"));

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(Long.parseLong((String) listFilas.get("FECHA_VENCIMIENTO")));
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                fechaVencimiento.updateDate(year, month, day);
            } else if (opcion.equals("CC")) {
                //  List<String> listFilas = (List<String>) data.getData();
                String json = gson.toJson(data.getData());
                Type listType = new TypeToken<List<EntityMap>>() {
                }.getType();
                List<EntityMap> listaCursos = gson.fromJson(json, listType);
                Log.d("Respuesta:  ", String.valueOf(listaCursos));
                Log.d("Respuesta:  ", listaCursos.get(0).getNOMBRE());
                Log.d("Respuesta:  ", listaCursos.get(1).getNOMBRE());
                init(listaCursos);

            }

        } else {
            Toast.makeText(getActivity(), "NO SE PROCESARON LOS DATOS", Toast.LENGTH_SHORT).show();
        }
    }

    public void init(List<EntityMap> listaCursos) {

      /*  List<String> nombresCursos = new ArrayList<>(); // lista donde se almacenarán los nombres de los cursos

        for (EntityMap curso : listaCursos) {
            String nombreCurso = curso.getNOMBRE(); // obtener el nombre del curso y convertirlo a string
            nombresCursos.add(nombreCurso); // agregar el nombre del curso a la lista de nombres de cursos
        }


        //Adaptador con layout por defecto
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity(), nombresCursos,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //Diseño cuando aparezcan las opciones
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCursos.setAdapter(adaptador);*/

        //ArrayAdapter<EntityMap> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listaCursos);

        // Crear el adapter personalizado
        CursoArrayAdapter adapter = new CursoArrayAdapter(getContext(), listaCursos);

        spinnerCursos.setAdapter(adapter);

    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}