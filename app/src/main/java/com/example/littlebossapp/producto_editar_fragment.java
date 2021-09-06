package com.example.littlebossapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class producto_editar_fragment extends Fragment {


    EditText edtNombreArticulo, edtCodigo, edtPrecio, edtCantidad, edtDescripcion;
    Button btnAgregar, btnEditar,btnEliminar, btnBuscar, btnLimpiar,btRMcancelar;


    RequestQueue requestQueue;
    //TextView textViewLogin;


    //ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.producto_editar_fragment, container, false);

        edtNombreArticulo=(EditText)view.findViewById(R.id.edtNombreArticulo);
        edtCodigo=(EditText)view.findViewById(R.id.edtCodigo);
        edtPrecio=(EditText)view.findViewById(R.id.edtPrecio);
        edtCantidad=(EditText)view.findViewById(R.id.edtCantidad);
        edtDescripcion=(EditText)view.findViewById(R.id.edtDescripcion);
        //btnAgregar=(Button) findViewById(R.id.btnAgregar);
        btnBuscar=(Button) view.findViewById(R.id.btnBuscar);
        btnEditar=(Button) view.findViewById(R.id.btnEditar);
        btnEliminar=(Button) view.findViewById(R.id.btnEliminar);
        btnLimpiar=(Button) view.findViewById(R.id.btnLimpiar);
        btRMcancelar = view.findViewById(R.id.btRMcancelar);
        //progressBar = findViewById(R.id.progress);

        /*textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });*/
/*
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.31.142/mysql_littleboss/insertar_producto.php");

            }
        });

 */

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto("http://192.168.100.14/mysql_littleboss/buscar_producto.php?codigo="+edtCodigo.getText()+"");
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarProducto();
            }
        });


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.100.14/mysql_littleboss/editar_producto.php");

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarProducto("http://192.168.100.14/mysql_littleboss/eliminar_producto.php");

            }
        });

        btRMcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), littleboss.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("nombre",edtNombreArticulo.getText().toString());
                parametros.put("codigo",edtCodigo.getText().toString());
                parametros.put("precio",edtPrecio.getText().toString());
                parametros.put("cantidad",edtCantidad.getText().toString());
                parametros.put("descripcion",edtDescripcion.getText().toString());
                return parametros;
            }
        };


        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        edtNombreArticulo.setText(jsonObject.getString("nombre"));
                        edtPrecio.setText(jsonObject.getString("precio"));
                        edtCantidad.setText(jsonObject.getString("cantidad"));
                        edtDescripcion.setText(jsonObject.getString("descripcion"));


                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error de Conexión",Toast.LENGTH_SHORT).show();

            }
        }
        );

        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }

    private void eliminarProducto(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "EL PRODUCTO FUE ELIMINADO CON EXITO", Toast.LENGTH_SHORT).show();
                //limpiarProducto();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("codigo",edtCodigo.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private void limpiarProducto(){
        edtNombreArticulo.setText("");
        edtCodigo.setText("");
        edtPrecio.setText("");
        edtCantidad.setText("");
        edtDescripcion.setText("");
    }


}
