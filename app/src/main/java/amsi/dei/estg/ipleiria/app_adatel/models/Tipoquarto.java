package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Tipoquarto implements Serializable {

    private int id, preco_noite;
    private String designacao;

    public Tipoquarto(int id, int preco_noite, String designacao){

        this.id = id;
        this.preco_noite = preco_noite;
        this.designacao = designacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreco_noite() {
        return preco_noite;
    }

    public void setPreco_noite(int preco_noite) {
        this.preco_noite = preco_noite;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}

