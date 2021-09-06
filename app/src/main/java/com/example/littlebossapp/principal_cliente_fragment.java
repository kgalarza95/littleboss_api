package com.example.littlebossapp;

import android.os.Bundle;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.littlebossapp.R;
import com.example.littlebossapp.fragment_lista_pedidos;
import com.example.littlebossapp.fragment_nuevo_pedido;

public class principal_cliente_fragment extends Fragment {

    private Button btnClienteR, btnClienteEd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pedidos, container, false);
        btnClienteR=(Button)view.findViewById(R.id.btn_nuevo_producto);
        btnClienteEd= (Button)view.findViewById(R.id.btn_lista_productos);



        btnClienteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new registrar_cliente_fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnClienteEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new editar_cliente_fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}