package amsi.dei.estg.ipleiria.app_adatel.vistas;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.adaptador.ListaReservaAdaptador;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PedidosReservasFragment extends Fragment {


    public PedidosReservasFragment() {
        // Required empty public constructor
    }

    private ArrayList<Reserva> listaReservas;
    private ListView lvlistaReservas;
    private Reserva idReserva;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_listas, container, false);

        listaReservas = SingletonGestaoHotel.getInstance().getReservas();
        lvlistaReservas = rootView.findViewById(R.id.lvLista);
        lvlistaReservas.setAdapter(new ListaReservaAdaptador(getContext(), listaReservas));
        lvlistaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reserva tempLivro = (Reserva) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "AQUI: " + tempLivro.getId(), Toast.LENGTH_SHORT).show();
                idReserva = SingletonGestaoHotel.getInstance().getReserva(tempLivro.getId());

                Intent intent = new Intent(getContext(), DetalhesReservaClienteActivity.class);
                intent.putExtra(DetalhesReservaClienteActivity.CHAVE_ID, idReserva.getId());
                startActivity(intent);
            }
        });
        return rootView;
    }
}
