package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Pedido;

public interface PedidosListener {

    void onRefreshListaPedidos(ArrayList<Pedido> listaPedidos);
    void onUpdateListaPedidosBD(Pedido pedido, int operacao);
}
