package com.example.littlebossapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

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


public class fragment_lista_pedidos extends Fragment {

    EditText edtCodigop, edtDescripcionp, edtFecharegistrop, edtFechaEntregap, edtCantidadp, edtCostoEnviop, edtClientep, edtPagoTotalp;
    Button btnAgregar, btnEditar, btnEliminar, btnBuscar, btnLimpiar, btncancelar;
    private String IP = "192.168.100.14";//"192.168.100.14";

    RequestQueue requestQueue;
    //TextView textViewLogin;


    //ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_pedidos, container, false);

        edtCodigop=(EditText)view.findViewById(R.id.edtCodigop);
        edtDescripcionp=(EditText)view.findViewById(R.id.edtDescripcionp);
        edtFecharegistrop=(EditText)view.findViewById(R.id.edtFecharegistrop);
        edtFechaEntregap=(EditText)view.findViewById(R.id.edtFechaEntregap);
        edtCantidadp=(EditText)view.findViewById(R.id.edtCantidadp);
        edtCostoEnviop=(EditText)view.findViewById(R.id.edtCostoEnviop);
        edtClientep=(EditText)view.findViewById(R.id.edtClientep);
        edtPagoTotalp=(EditText)view.findViewById(R.id.edtPagoTotalp);
        //btnAgregar=(Button) findViewById(R.id.btnAgregar);
        btnBuscar=(Button) view.findViewById(R.id.btnBuscar);
        btnEditar=(Button) view.findViewById(R.id.btnEditar);
        btnEliminar=(Button) view.findViewById(R.id.btnEliminar);
        btnLimpiar=(Button) view.findViewById(R.id.btnLimpiar);
        btncancelar=(Button) view.findViewById(R.id.btncancelar);
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
                buscarProducto("http://192.168.100.14/mysql_littleboss2/buscar_pedido.php?codigo="+edtCodigop.getText()+"");
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
                ejecutarServicio("http://192.168.100.14/mysql_littleboss2/editar_pedido.php");

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarProducto("http://192.168.100.14/mysql_littleboss2/eliminar_pedido.php");

            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), littleboss.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        getParentFragmentManager().setFragmentResultListener("parametros", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String fecha = result.getString("fecha");
                        System.out.println("=============================================");
                        System.out.println("llega ==> "+fecha);
                        if(!fecha.isEmpty()){
                            Toast.makeText(getActivity(), "busqueda por fecha " + fecha, Toast.LENGTH_SHORT).show();
                            buscarProductoFecha("http://"+IP+":80/mysql_littleboss2/buscar_pedido_fecha.php?fecha="+fecha);
                        }
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
                parametros.put("codigo",edtCodigop.getText().toString());
                parametros.put("descripcion",edtDescripcionp.getText().toString());
                parametros.put("fecharegistrop",edtFecharegistrop.getText().toString());
                parametros.put("fechaEntrega",edtFechaEntregap.getText().toString());
                parametros.put("cantidad",edtCantidadp.getText().toString());
                parametros.put("costoEnvio",edtCostoEnviop.getText().toString());
                parametros.put("cliente",edtClientep.getText().toString());
                parametros.put("pagototal",edtPagoTotalp.getText().toString());
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
                        edtDescripcionp.setText(jsonObject.getString("descripcion"));
                        edtFecharegistrop.setText(jsonObject.getString("fecharegistrop"));
                        edtFechaEntregap.setText(jsonObject.getString("fechaEntrega"));
                        edtCantidadp.setText(jsonObject.getString("cantidad"));
                        edtCostoEnviop.setText(jsonObject.getString("costoEnvio"));
                        edtClientep.setText(jsonObject.getString("cliente"));
                        edtPagoTotalp.setText(jsonObject.getString("pagototal"));



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
                parametros.put("codigo",edtCodigop.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private void limpiarProducto(){
        edtCodigop.setText("");
        edtDescripcionp.setText("");
        edtFecharegistrop.setText("");
        edtFechaEntregap.setText("");
        edtCantidadp.setText("");
        edtCostoEnviop.setText("");
        edtClientep.setText("");
        edtPagoTotalp.setText("");
    }

    /**
     * Buscar un producto por fecha
     */
    private void buscarProductoFecha(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        System.out.println("========================================================");
                        System.out.println("LLamada por fecha");
                        System.out.println("repsuesta json PIN: " + jsonObject.getString("descripcion"));
                        if(!jsonObject.getString("fechaEntrega").isEmpty()){
                            edtCodigop.setText(jsonObject.getString("codigo"));
                            edtDescripcionp.setText(jsonObject.getString("descripcion"));
                            edtFecharegistrop.setText(jsonObject.getString("fecharegistrop"));
                            edtFechaEntregap.setText(jsonObject.getString("fechaEntrega"));
                            edtCantidadp.setText(jsonObject.getString("cantidad"));
                            edtCostoEnviop.setText(jsonObject.getString("costoEnvio"));
                            edtClientep.setText(jsonObject.getString("cliente"));
                            edtPagoTotalp.setText(jsonObject.getString("pagototal"));
                        }else{
                            System.out.println("No hay datos con la fecha seleccionada");
                            Toast.makeText(getActivity(), "No hay Datos", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        System.out.println("Ocurrio un error");
                        System.out.println("===> "+e);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error en la conexion");
                Toast.makeText(getActivity(), "Error de Conexión " + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

}