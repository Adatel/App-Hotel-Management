package amsi.dei.estg.ipleiria.app_adatel.models;

import android.provider.ContactsContract;

public class Pedido {

    // Atributos
    private int id, custo, id_reservaquarto;
    private String dt_hora;

    public Pedido(int idPedido, int Custo, int idReservaQuarto, String DataHora){

        this.id = idPedido;
        this.custo = Custo;
        this.id_reservaquarto = idReservaQuarto;
        this.dt_hora = DataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getId_reservaquarto() {
        return id_reservaquarto;
    }

    public void setId_reservaquarto(int id_reservaquarto) {
        this.id_reservaquarto = id_reservaquarto;
    }

    public String getDt_hora() {
        return dt_hora;
    }

    public void setDt_hora(String dt_hora) {
        this.dt_hora = dt_hora;
    }
}
