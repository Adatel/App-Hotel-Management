package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Classificacao;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public interface ClassificacoesListener {

    void onRefreshListaClassificacoes(ArrayList<Classificacao> listaClassificacoes);
    void onUpdateListaClassificacoesBD(Classificacao classificacao, int operacao);
}
