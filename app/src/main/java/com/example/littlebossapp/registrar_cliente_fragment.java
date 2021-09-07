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


public class registrar_cliente_fragment extends Fragment {

    EditText EditTextedtCodigoc, EditTextedtNombrec, EditTextedtApellidoc, EditTextedtCorreoc, EditTextedtCelularc, EditTextedtCiudadc, EditTextedtUbicacionc;
    Button btnAgregar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_registrar_cliente, container, false);

        EditTextedtCodigoc = view.findViewById(R.id.edtCodigoc);
        EditTextedtNombrec = view.findViewById(R.id.edtNombrec);
        EditTextedtApellidoc = view.findViewById(R.id.edtApellidoc);
        EditTextedtCorreoc = view.findViewById(R.id.edtCorreoc);
        EditTextedtCelularc = view.findViewById(R.id.edtCelularc);
        EditTextedtCiudadc = view.findViewById(R.id.edtCiudadc);
        EditTextedtUbicacionc = view.findViewById(R.id.edtUbicacionc);

        //textInputEditTextDescripcion = findViewById(R.id.descripcion);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        //btnLimpiar = view.findViewById(R.id.btnLimpiar);

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

                final String codigo, nombreCliente, apellidoCliente, correoCliente, celularCliente, ciudadCliente, ubicacionCliente;

                codigo = String.valueOf(EditTextedtCodigoc.getText());
                nombreCliente = String.valueOf(EditTextedtNombrec.getText());
                apellidoCliente = String.valueOf(EditTextedtApellidoc.getText());
                correoCliente = String.valueOf(EditTextedtCorreoc.getText());
                celularCliente = String.valueOf(EditTextedtCelularc.getText());
                ciudadCliente = String.valueOf(EditTextedtCiudadc.getText());
                ubicacionCliente = String.valueOf(EditTextedtUbicacionc.getText());



                if(!codigo.equals("") && !nombreCliente.equals("") && !apellidoCliente.equals("") && !correoCliente.equals("") && !celularCliente.equals("") && !ciudadCliente.equals("") && !ubicacionCliente.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters

                            String[] field = new String[7];
                            field[0] = "codigo";
                            field[1] = "nombreCliente";
                            field[2] = "apellidoCliente";
                            field[3] = "correoCliente";
                            field[4] = "celularCliente";
                            field[5] = "ciudadCliente";
                            field[6] = "ubicacionCliente";



                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = codigo;
                            data[1] = nombreCliente;
                            data[2] = apellidoCliente;
                            data[3] = correoCliente;
                            data[4] = celularCliente;
                            data[5] = ciudadCliente;
                            data[6] = ubicacionCliente;




                            PutData putData = new PutData("http://192.168.100.14/mysql_littleboss6_3/signup.php", "POST", field, data);
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




        return  view;
    }

    /*private void limpiarProducto(){
        EditTextedtCodigoc.setText("");
        EditTextedtNombrec.setText("");
        EditTextedtApellidoc.setText("");
        EditTextedtCorreoc.setText("");
        EditTextedtCelularc.setText("");
        EditTextedtCiudadc.setText("");
        EditTextedtUbicacionc.setText("");

    }*/


}
