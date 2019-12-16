package amsi.dei.estg.ipleiria.app_adatel.vistas;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.adaptador.ListaReservaAdaptador;
import amsi.dei.estg.ipleiria.app_adatel.listeners.ReservasListener;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaReservasFragment extends Fragment implements ReservasListener {

    private ArrayList<Reserva> listaReservas;
    private ListView lvlistaReservas;
    private SearchView searchView;
    private Reserva idReserva;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_listas, container, false);

        lvlistaReservas = rootView.findViewById(R.id.lvLista);
        listaReservas = SingletonGestaoHotel.getInstance().getReservas();
        lvlistaReservas.setAdapter(new ListaReservaAdaptador(getContext(), listaReservas));

        //  <----------- Floating Button ----------->

        FloatingActionButton fab = rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), DetalhesReservaClienteActivity.class);
                startActivity(intent);
            }
        });


        // <--------------------- Ao selecionar um item da List View --------------------->

        lvlistaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reserva tempReserva = (Reserva) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), "AQUI: " + tempReserva.getId(), Toast.LENGTH_SHORT).show();
                idReserva = SingletonGestaoHotel.getInstance().getReserva(tempReserva.getId());

                Intent intent = new Intent(getContext(), DetalhesReservaClienteActivity.class);
                intent.putExtra(DetalhesReservaClienteActivity.CHAVE_ID, idReserva.getId());
                startActivity(intent);
            }
        });
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        // Para carregar o menu usa-se o Inflater
        inflater.inflate(R.menu.menu_pesquisa, menu);
        // Vai buscar aquele item
        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        searchView = (SearchView)itemPesquisa.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // Método chamado a cada letra que se insere
            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Reserva> tempListaLivros = new ArrayList<>();

                for (Reserva tempReserva : SingletonGestaoHotel.getInstance().getReservas()) {
                    if(tempReserva.getDtEntrada().toLowerCase().contains(newText.toLowerCase())){
                        tempListaLivros.add(tempReserva);
                    }
                }
                lvlistaReservas.setAdapter(new ListaReservaAdaptador(getContext(), tempListaLivros));
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.itemPesquisa){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {

        if(searchView != null){
            searchView.onActionViewCollapsed();
        }
        super.onResume();
        SingletonGestaoHotel.getInstance().getReservas();
    }

    @Override
    public void onRefreshListaReservas(ArrayList<Reserva> listaReservas) {

        System.out.println("--> onRefreshListaReservas: " + listaReservas);
/*
        ListaReservaAdaptador = new ListaReservaAdaptador(getContext(), listaReservas);
        lvlistaReservas.setAdapter(listaReservaAdaptador);
        ListaReservaAdaptador.refresh(listaReservas); */
    }

    @Override
    public void onUpdateListaReservasBD(Reserva livro, int operacao) {

    }
}
