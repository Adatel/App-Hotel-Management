package amsi.dei.estg.ipleiria.app_adatel.models;

import android.provider.ContactsContract;

public class Pedido {

    // Atributos
    private int id, custo, id_reservaquarto, id_funcionario;
    private String dt_hora;

    public Pedido(int idPedido, int Custo, int idReservaQuarto, int idFuncionario, String DataHora){

        this.id = idPedido;
        this.custo = Custo;
        this.id_reservaquarto = idReservaQuarto;
        this.id_funcionario = idFuncionario;
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

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getDt_hora() {
        return dt_hora;
    }

    public void setDt_hora(String dt_hora) {
        this.dt_hora = dt_hora;
    }
}
