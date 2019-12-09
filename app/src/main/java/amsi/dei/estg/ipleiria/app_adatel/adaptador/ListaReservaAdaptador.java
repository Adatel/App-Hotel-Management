package amsi.dei.estg.ipleiria.app_adatel.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class ListaReservaAdaptador extends BaseAdapter {

    private Context context;
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
        return null;
    }

    public View ViewHolder(){
        return null;
    }
}
