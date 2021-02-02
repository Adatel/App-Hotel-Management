package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Tipoquarto;

public interface TipoQuartosListener {
    void onRefreshListaTipoquartos(ArrayList<Tipoquarto> lista);
    void onUpdateListaTipoquartosBD(Tipoquarto tipoqTipoQuartouarto, int operacao);
}
