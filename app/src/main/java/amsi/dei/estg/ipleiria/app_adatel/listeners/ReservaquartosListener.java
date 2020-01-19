package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Reservaquarto;

public interface ReservaquartosListener {

    void onRefreshListaReservaquartos(ArrayList<Reservaquarto> listaReservaquartos);
    void onUpdateListaReservaquartosBD(Reservaquarto reservaquarto, int operacao);
}
