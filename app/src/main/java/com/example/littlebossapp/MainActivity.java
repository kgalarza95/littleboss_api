package com.example.littlebossapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {

    EditText EditTextedtUsuario, EditTextedtPassword, txtRegistroNuevo;
    Button btnLogin, btnCodigoUnico;
    TextView textViewtxtRegistroNuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditTextedtUsuario = findViewById(R.id.edtUsuario);
        EditTextedtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCodigoUnico = findViewById(R.id.btnCodigoUnico);
        textViewtxtRegistroNuevo = findViewById(R.id.txtRegistroNuevo);

        textViewtxtRegistroNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registrar_cliente_fragment.class);
                startActivity(intent);
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String username, password;
                username = String.valueOf(EditTextedtUsuario.getText());
                password = String.valueOf(EditTextedtPassword.getText());


                if(!username.equals("") && !password.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    //progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters

                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";


                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;



                            PutData putData = new PutData("http://192.168.100.14/mysql_littleboss6/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if(result.equals("Ingreso exitoso")){

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), littleboss.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
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

        btnCodigoUnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_codigo_unico.class);
                startActivity(intent);
                finish();
            }
        });
    }
}