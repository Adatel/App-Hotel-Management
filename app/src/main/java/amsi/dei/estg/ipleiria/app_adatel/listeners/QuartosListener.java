package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Quarto;

public interface QuartosListener {
    void onRefreshListaQuartos(ArrayList<Quarto> listaQuartos);
    void onUpdateListaQuartosBD(Quarto quarto, int operacao);
}
