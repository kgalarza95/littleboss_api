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


public class fragment_nuevo_pedido extends Fragment {

    EditText EditTextedtCodigop, EditTextedtDescripcionp, EditTextedtFecharegistrop, EditTextedtFechaEntregap, EditTextedtCantidadp, EditTextedtCostoEnviop, EditTextedtClientep, EditTextedtPagoTotalp;
    Button btnAgregar, btnLimpiar, btn_calcularpagop;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_nuevo_pedido, container, false);

        EditTextedtCodigop = view.findViewById(R.id.edtCodigop);
        EditTextedtDescripcionp = view.findViewById(R.id.edtDescripcionp);
        EditTextedtFecharegistrop = view.findViewById(R.id.edtFecharegistrop);
        EditTextedtFechaEntregap = view.findViewById(R.id.edtFechaEntregap);
        EditTextedtCantidadp = view.findViewById(R.id.edtCantidadp);
        EditTextedtCostoEnviop = view.findViewById(R.id.edtCostoEnviop);
        EditTextedtClientep = view.findViewById(R.id.edtClientep);
        EditTextedtPagoTotalp = view.findViewById(R.id.edtPagoTotalp);
        //textInputEditTextDescripcion = findViewById(R.id.descripcion);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnLimpiar = view.findViewById(R.id.btnLimpiar);
        btn_calcularpagop = view.findViewById(R.id.btn_calcularpagop);
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

                final String codigo, descripcion, fecharegistrop, fechaEntrega, cantidad, costoEnvio, cliente, pagototal;

                codigo = String.valueOf(EditTextedtCodigop.getText());
                descripcion = String.valueOf(EditTextedtDescripcionp.getText());
                fecharegistrop = String.valueOf(EditTextedtFecharegistrop.getText());
                fechaEntrega = String.valueOf(EditTextedtFechaEntregap.getText());
                cantidad = String.valueOf(EditTextedtCantidadp.getText());
                costoEnvio = String.valueOf(EditTextedtCostoEnviop.getText());
                cliente = String.valueOf(EditTextedtClientep.getText());
                pagototal = String.valueOf(EditTextedtPagoTotalp.getText());


                if(!codigo.equals("") && !descripcion.equals("") && !fecharegistrop.equals("") && !fechaEntrega.equals("") && !cantidad.equals("") && !costoEnvio.equals("") && !cliente.equals("") && !pagototal.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters

                            String[] field = new String[8];
                            field[0] = "codigo";
                            field[1] = "descripcion";
                            field[2] = "fecharegistrop";
                            field[3] = "fechaEntrega";
                            field[4] = "cantidad";
                            field[5] = "costoEnvio";
                            field[6] = "cliente";
                            field[7] = "pagototal";


                            //Creating array for data
                            String[] data = new String[8];
                            data[0] = codigo;
                            data[1] = descripcion;
                            data[2] = fecharegistrop;
                            data[3] = fechaEntrega;
                            data[4] = cantidad;
                            data[5] = costoEnvio;
                            data[6] = cliente;
                            data[7] = pagototal;



                            PutData putData = new PutData("http://192.168.100.14/mysql_littleboss6_2/signup.php", "POST", field, data);
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


        return  view;
    }

    private void limpiarProducto(){
        EditTextedtCodigop.setText("");
        EditTextedtDescripcionp.setText("");
        EditTextedtFecharegistrop.setText("");
        EditTextedtFechaEntregap.setText("");
        EditTextedtCantidadp.setText("");
        EditTextedtCostoEnviop.setText("");
        EditTextedtClientep.setText("");
        EditTextedtPagoTotalp.setText("");
    }
}