package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Linhaproduto;

public interface LinhaProdutosListener {
    void onRefreshListaLinhaProdutos(ArrayList<Linhaproduto> listaLinhaproduto);
    void onUpdateListaLinhaProdutosBD(Linhaproduto linhaproduto, int operacao);
}
