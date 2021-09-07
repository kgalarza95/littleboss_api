package com.example.littlebossapp.ui.home;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.littlebossapp.R;
import com.example.littlebossapp.fragment_lista_pedidos;
import com.example.littlebossapp.principal_cliente_fragment;
import com.example.littlebossapp.producto_principal_fragment;
import com.example.littlebossapp.ui.pedidos.fragment_pedidos;

import org.w3c.dom.Text;

import java.util.Calendar;

public class HomeFragment extends Fragment {


    private TextView txtagregar_pedido, txtagregarproducto, txtagregarcliente;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtagregar_pedido = view.findViewById(R.id.txtagregar_pedido);
        txtagregarproducto=view.findViewById(R.id.txtagregar_producto);
        txtagregarcliente=view.findViewById(R.id.txtagregar_cliente);
        TextView txtbtn_fecha = view.findViewById(R.id.txtcalendario);

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

        txtbtn_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha =
                        abrirCalendario(v);
            }
        });

        return view;
    }

    public String  abrirCalendario(View view) {
        String fecha_ ="";
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new
                DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month+1) + "/" + year;
                //tv.setText(fecha);
                fecha = completarCeros(fecha);
                System.out.println("fecha es: " + completarCeros(fecha));

                if(!fecha.equalsIgnoreCase("")){
                    Bundle extras = new Bundle();

                    extras.putString("fecha", fecha); // se obtiene el valor mediante getString(...)

                    /*Intent intent = new Intent(getActivity(), ListaPedidosFragment.class);
                    intent.putExtras(extras);
                    startActivity(intent);*/

                    Fragment nuevoFragmento = new fragment_lista_pedidos();
                    //nuevoFragmento.
                            getParentFragmentManager().
                            setFragmentResult("parametros",extras);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                    transaction.addToBackStack(null);
                    transaction.commit();

                   /* fragment_lista_pedidos fr=new fragment_lista_pedidos();
                    fr.setArguments(bn);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment,fr)
                            .addToBackStack(null)
                            .commit();*/
                }else{
                    Toast.makeText(getActivity(), "Error al obtener fecha", Toast.LENGTH_SHORT).show();
                }
            }
        }, anio, mes, dia);
        dpd.show();
        return fecha_;
    }

    private String completarCeros(String fecha) {
        String[] split = fecha.split("/");
        String dia = split[0];
        String mes = split[1];
        String anio = split[2];
        String newFecha = "";

        if (dia.length() == 1) {
            dia = "0" + dia;
        }
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        return dia + "/" + mes + "/" + anio;
    }
}

//si quieren cambiar el de nombre que sale en la partede arriba de una activity
//getSupportActionBar().setTitle(R.string.app_agregarVivienda);