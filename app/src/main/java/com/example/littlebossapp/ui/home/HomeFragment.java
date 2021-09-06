package com.example.littlebossapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.littlebossapp.R;
import com.example.littlebossapp.principal_cliente_fragment;
import com.example.littlebossapp.producto_principal_fragment;
import com.example.littlebossapp.ui.pedidos.fragment_pedidos;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {


    private TextView txtagregar_pedido, txtagregarproducto, txtagregarcliente;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtagregar_pedido = view.findViewById(R.id.txtagregar_pedido);
        txtagregarproducto=view.findViewById(R.id.txtagregar_producto);
        txtagregarcliente=view.findViewById(R.id.txtagregar_cliente);

        txtagregar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new fragment_pedidos();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        txtagregarproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new producto_principal_fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        txtagregarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nuevoFragmento = new principal_cliente_fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}

//si quieren cambiar el de nombre que sale en la partede arriba de una activity
//getSupportActionBar().setTitle(R.string.app_agregarVivienda);