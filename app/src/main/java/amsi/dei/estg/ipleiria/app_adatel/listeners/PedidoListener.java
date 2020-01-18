package amsi.dei.estg.ipleiria.app_adatel.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Pedido;

public interface PedidoListener {
    void onRefreshListaPedidos(ArrayList<Pedido> listaPedido);
    void onUpdateListaPedidosBD(Pedido pedido, int operacao);
}
