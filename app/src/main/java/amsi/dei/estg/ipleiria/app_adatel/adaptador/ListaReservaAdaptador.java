package amsi.dei.estg.ipleiria.app_adatel.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class ListaReservaAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Reserva> reserva;

    public ListaReservaAdaptador(Context context, ArrayList<Reserva> reserva){
        this.context = context;
        this.reserva = reserva;
    }

    @Override
    public int getCount() {
        return reserva.size();
    }

    @Override
    public Object getItem(int position) {
        return reserva.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_reserva, null);
        }

        ViewHolderLista viewHolder = (ViewHolderLista) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(reserva.get(position));
        return convertView;
    }

    private class ViewHolderLista{

        private TextView idReserva;
        private TextView numQuarto;
        private TextView dtEntrada;
        private TextView dtSaida;
        private TextView numPessoas;
        private TextView estado;

        // ID's do layout item_reserva
        public ViewHolderLista(View convertView){

            idReserva = convertView.findViewById(R.id.tvIdReserva);
            numQuarto = convertView.findViewById(R.id.tvNumQuartos);
            dtEntrada = convertView.findViewById(R.id.tvDataEntrada);
            dtSaida = convertView.findViewById(R.id.tvDataSaida);
            numPessoas = convertView.findViewById(R.id.tvNumPessoas);

        }

        public void update(Reserva reserva){
            idReserva.setText(" nº: " + reserva.getId());
            dtEntrada.setText(reserva.getDtEntrada());
            dtSaida.setText(reserva.getDtSaida());
            numPessoas.setText("" + reserva.getNumPessoas());
            numQuarto.setText("" + reserva.getNumQuartos());
        }
    }
}
