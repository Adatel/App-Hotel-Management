package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id, preco_unitario, id_tipoproduto;
    private String designacao;

    public Produto(int id, int preco_unitario, int id_tipoproduto, String designacao){

        this.id = id;
        this.preco_unitario = preco_unitario;
        this.id_tipoproduto = id_tipoproduto;
        this.designacao = designacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(int preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public int getId_tipoproduto() {
        return id_tipoproduto;
    }

    public void setId_tipoproduto(int id_tipoproduto) {
        this.id_tipoproduto = id_tipoproduto;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
