package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class TipoProduto implements Serializable {

    private int id;
    private String descricao;

    public TipoProduto(int id, String descricao){

        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
