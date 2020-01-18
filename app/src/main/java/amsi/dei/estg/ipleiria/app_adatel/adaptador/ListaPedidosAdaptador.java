package amsi.dei.estg.ipleiria.app_adatel.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.Pedido;

public class ListaPedidosAdaptador {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Pedido> pedido;

    public ListaPedidosAdaptador(Context context, ArrayList<Pedido> pedido){
        this.context = context;
        this.pedido = pedido;
    }

    public int getCount() {
        return pedido.size();
    }

    public Object getItem(int position) {
        return pedido.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_reserva, null);
        }

        ListaPedidosAdaptador.ViewHolderLista viewHolder = (ListaPedidosAdaptador.ViewHolderLista) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ListaPedidosAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.update(pedido.get(position));
        return convertView;
    }

    private class ViewHolderLista{

        private TextView idPedido;
        private TextView Custo;
        private TextView Dt_hora;

        // ID's do layout item_pedido
        public ViewHolderLista(View convertView){

            idPedido = convertView.findViewById(R.id.tvIdReserva);
            Custo = convertView.findViewById(R.id.tvCusto);
            Dt_hora = convertView.findViewById(R.id.tvDataHora);

        }

        public void update(Pedido pedido){
            idPedido.setText(" nº: " + pedido.getId());
            Dt_hora.setText(pedido.getDt_hora());
            Custo.setText(pedido.getCusto());
        }
    }
}
