package com.example.littlebossapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

//import com.google.android.material.textfield.TextInputEditText;
//import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class  activity_codigo_unico extends AppCompatActivity {

    EditText edtCodigop, edtDescripcionp, edtFecharegistrop, edtFechaEntregap, edtCantidadp, edtCostoEnviop, edtClientep, edtPagoTotalp;
    Button btnAgregar, btnEditar,btnEliminar, btnBuscar, btnLimpiar, btnRegresar;


    RequestQueue requestQueue;
    //TextView textViewLogin;


    //ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_unico);

        edtCodigop=(EditText)findViewById(R.id.edtCodigop);
        edtDescripcionp=(EditText)findViewById(R.id.edtDescripcionp);
        edtFecharegistrop=(EditText)findViewById(R.id.edtFecharegistrop);
        edtFechaEntregap=(EditText)findViewById(R.id.edtFechaEntregap);
        edtCantidadp=(EditText)findViewById(R.id.edtCantidadp);
        edtCostoEnviop=(EditText)findViewById(R.id.edtCostoEnviop);
        edtClientep=(EditText)findViewById(R.id.edtClientep);
        edtPagoTotalp=(EditText)findViewById(R.id.edtPagoTotalp);
        //btnAgregar=(Button) findViewById(R.id.btnAgregar);
        btnBuscar=(Button) findViewById(R.id.btnBuscar);
        btnRegresar=(Button)findViewById(R.id.btnRegresar);
        //btnEditar=(Button) findViewById(R.id.btnEditar);
        //btnEliminar=(Button) findViewById(R.id.btnEliminar);
        //btnLimpiar=(Button) findViewById(R.id.btnLimpiar);
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
                ejecutarServicio("http://192.168.100.14/mysql_littleboss/insertar_producto.php");

            }
        });
*/
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto("http://192.168.1.119/mysql_littleboss5/buscar_pedido.php?codigo="+edtCodigop.getText()+"");
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
/*
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
*/
    }
    /*
        private void ejecutarServicio(String URL){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<String, String>();
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

            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    */
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
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error de Conexión",Toast.LENGTH_SHORT).show();

            }
        }
        );

        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
/*
    private void eliminarProducto(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "EL PRODUCTO FUE ELIMINADO CON EXITO", Toast.LENGTH_SHORT).show();
                limpiarProducto();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("codigo",edtCodigop.getText().toString());
                return parametros;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
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

*/
}