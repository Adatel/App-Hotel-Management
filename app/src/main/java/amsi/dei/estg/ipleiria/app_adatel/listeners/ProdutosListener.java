package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Produto;

public interface ProdutosListener {
    void onRefreshListaProdutos(ArrayList<Produto> listaProdutos);
    void onUpdateListaProdutosBD(Produto produto, int operacao);
}
