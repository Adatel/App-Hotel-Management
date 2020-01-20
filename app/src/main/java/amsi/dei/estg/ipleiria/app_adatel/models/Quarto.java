package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Quarto implements Serializable {

    private int num_quarto, estado, id_tipo;

    public Quarto(int num_quarto, int estado, int id_tipo){

        this.num_quarto = num_quarto;
        this.estado = estado;
        this.id_tipo = id_tipo;
    }

    public int getNum_quarto() {
        return num_quarto;
    }

    public void setNum_quarto(int num_quarto) {
        this.num_quarto = num_quarto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }
}
