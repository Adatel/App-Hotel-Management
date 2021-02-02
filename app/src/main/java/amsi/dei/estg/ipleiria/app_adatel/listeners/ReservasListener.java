package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public interface ReservasListener {

    void onRefreshListaReservas(ArrayList<Reserva> listaReservas);
    void onUpdateListaReservasBD(Reserva reserva, int operacao);
}
