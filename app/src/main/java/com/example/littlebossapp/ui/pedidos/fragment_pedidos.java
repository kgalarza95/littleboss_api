package com.example.littlebossapp.ui.pedidos;

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

public class fragment_pedidos extends Fragment {
    private Button btnnuevop, btnlistap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pedidos, container, false);
        btnnuevop=(Button)view.findViewById(R.id.btn_nuevo_producto);
        btnlistap= (Button)view.findViewById(R.id.btn_lista_productos);



        btnnuevop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new fragment_nuevo_pedido();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnlistap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new fragment_lista_pedidos();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}