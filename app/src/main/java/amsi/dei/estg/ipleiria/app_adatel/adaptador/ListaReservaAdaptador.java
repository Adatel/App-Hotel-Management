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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
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
        private TextView nomeCliente;
        private TextView numQuarto;
        private TextView dtEntrada;
        private TextView dtSaida;
        private TextView numPessoas;
        private TextView estado;

        public ViewHolderLista(View convertView){
            numQuarto = convertView.findViewById(R.id.tv_idQuarto);
            dtEntrada = convertView.findViewById(R.id.tv_dataEntrada);
            dtSaida = convertView.findViewById(R.id.tv_dataSaida);
            numPessoas = convertView.findViewById(R.id.tv_numeroPessoas);

        }

        public void update(Reserva reserva){
            numQuarto.setText(reserva.getId());
            dtEntrada.setText(reserva.getDt_entrada());
            dtSaida.setText(reserva.getDt_saida());
            numPessoas.setText(reserva.getN_pessoas());
        }
    }
}
