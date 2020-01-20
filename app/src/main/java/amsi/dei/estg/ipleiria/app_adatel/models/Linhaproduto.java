package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Linhaproduto implements Serializable {

    private int id, quantidade, id_produto, id_pedido;

    public Linhaproduto(int id, int quantidade, int id_produto, int id_pedido){

        this.id = id;
        this.quantidade = quantidade;
        this. id_produto = id_produto;
        this.id_pedido = id_pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}
