package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.TipoProduto;

public interface TipoProdutosListener {
    void onRefreshListaTipoProdutos(ArrayList<TipoProduto> listaTipoProduto);
    void onUpdateListaTipoProdutosBD(TipoProduto tipoProduto, int operacao);
}
