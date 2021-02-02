package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Reservaquarto implements Serializable {

    private int id, id_reserva, id_quarto;

    public Reservaquarto(int id, int id_reserva, int id_quarto){

        this.id = id;
        this.id_reserva = id_reserva;
        this.id_quarto = id_quarto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdReserva() {
        return id_reserva;
    }

    public void setIdReserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getIdQuarto() {
        return id_quarto;
    }

    public void setIdQuarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }
}
