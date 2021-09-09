package com.example.littlebossapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.regex.Pattern;

public class activity_registrar_usuario extends AppCompatActivity {

    EditText edtNombresu, edtApellidosu, edtCodigou, edtEmailu, edtContrasenau;
    Button btnAgregar, btnCancelar;
    //TextView TextViewRegistroNuevo;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    //ProgressBar progressBar;
    private boolean validarEmail() {

        String emailInput = edtEmailu.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            edtEmailu.setError("Ingrese un Email Valido!");
            return false;
        } else {
            edtEmailu.setError(null);
            return true;
        }
    }

    private boolean validaCedula(){
        String ced = edtCodigou.getText().toString().trim();
        if (edtCodigou.length()>0 && edtCodigou.length()<=10) {
            edtCodigou.setError("Ingrese un numero de cedula Valido!");
            return false;
        } else {
            edtCodigou.setError(null);
            return true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        edtNombresu = findViewById(R.id.edtNombresu);
        edtApellidosu = findViewById(R.id.edtApellidosu);
        edtCodigou = findViewById(R.id.edtCodigou);
        edtEmailu = findViewById(R.id.edtEmailu);
        edtContrasenau = findViewById(R.id.edtContrasenau);
        //textInputEditTextDescripcion = findViewById(R.id.descripcion);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnCancelar = findViewById(R.id.btnCancelar);
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

                if (!validarEmail()){
                    return;
                }
                if (!validaCedula()){
                    return;
                }

                final String Nombres, Apellidos, codigo, username, password;
                Nombres = String.valueOf(edtNombresu.getText());
                Apellidos = String.valueOf(edtApellidosu.getText());
                codigo  = String.valueOf(edtCodigou.getText());
                username = String.valueOf(edtEmailu.getText());
                password = String.valueOf(edtContrasenau.getText());

                if(!Nombres.equals("") && !Apellidos.equals("") && !codigo.equals("") && !username.equals("") && !password.equals("")) {

                        //Start ProgressBar first (Set visibility VISIBLE)
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters

                            String[] field = new String[5];
                            field[0] = "Nombres";
                            field[1] = "Apellidos";
                            field[2] = "codigo";
                            field[3] = "username";
                            field[4] = "password";


                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = Nombres;
                            data[1] = Apellidos;
                            data[2] = codigo;
                            data[3] = username;
                            data[4] = password;

                            PutData putData = new PutData("http://192.168.1.119/mysql_littleboss6/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)

                                    if(codigo.length()>0&&codigo.length()<=10){


                                    if(result.equals("Ingreso exitoso")) {

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                        }

                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }}

                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }

        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
