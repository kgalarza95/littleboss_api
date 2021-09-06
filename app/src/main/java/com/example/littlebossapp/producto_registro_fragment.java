package com.example.littlebossapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class producto_registro_fragment extends Fragment {

    EditText EditTextedtNombreArticulo, EditTextedtCodigo, EditTextedtPrecio, EditTextedtCantidad, EditTextedtDescripcion;
    Button btnAgregar, btnLimpiar, btncancelar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.producto_registro_fragment, container, false);

        EditTextedtNombreArticulo = view.findViewById(R.id.edtNombreArticulo);
        EditTextedtCodigo = view.findViewById(R.id.edtCodigo);
        EditTextedtPrecio = view.findViewById(R.id.edtPrecio);
        EditTextedtCantidad = view.findViewById(R.id.edtCantidad);
        EditTextedtDescripcion = view.findViewById(R.id.edtDescripcion);
        //textInputEditTextDescripcion = findViewById(R.id.descripcion);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnLimpiar = view.findViewById(R.id.btnLimpiar);
        btncancelar = view.findViewById(R.id.btncancelar);
        //TextViewRegistroNuevo = findViewById(R.id.txtRegistroNuevo);
        //progressBar = findViewById(R.id.progress);

        /*
        TextViewRegistroNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarUsuario.class);
                startActivity(intent);
                finish();
            }
        });
*/

        btnAgregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String nombre, codigo, precio, cantidad, descripcion;
                nombre = String.valueOf(EditTextedtNombreArticulo.getText());
                codigo = String.valueOf(EditTextedtCodigo.getText());
                precio = String.valueOf(EditTextedtPrecio.getText());
                cantidad = String.valueOf(EditTextedtCantidad.getText());
                descripcion = String.valueOf(EditTextedtDescripcion.getText());


                if(!nombre.equals("") && !codigo.equals("") && !precio.equals("") && !cantidad.equals("") && !descripcion.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters

                            String[] field = new String[5];
                            field[0] = "nombre";
                            field[1] = "codigo";
                            field[2] = "precio";
                            field[3] = "cantidad";
                            field[4] = "descripcion";


                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = nombre;
                            data[1] = codigo;
                            data[2] = precio;
                            data[3] = cantidad;
                            data[4] = descripcion;



                            PutData putData = new PutData("http://192.168.100.14/mysql_littleboss6_1/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if(result.equals("Ingreso exitoso")){

                                        Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getContext(), littleboss.class);
                                        startActivity(intent);
                                        getActivity().finish();

                                    }
                                    else{
                                        Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarProducto();
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

        return  view;
    }

    private void limpiarProducto(){
        EditTextedtNombreArticulo.setText("");
        EditTextedtCodigo.setText("");
        EditTextedtPrecio.setText("");
        EditTextedtCantidad.setText("");
        EditTextedtDescripcion.setText("");
    }
}