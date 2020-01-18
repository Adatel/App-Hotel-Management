package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Reserva implements Serializable {

    // Atributos
    private int id, n_pessoas, num_quartos, quarto_s, quarto_d, quarto_f, quarto_c, id_cliente;
    private String dt_entrada, dt_saida;

    public Reserva(int idReserva, int num_pessoas, int num_quartos, int quarto_solteiro, int quarto_duplo, int quarto_familia, int quarto_casal, String data_entrada, String data_saida, int id_cliente){

        this.id = idReserva;
        this.n_pessoas = num_pessoas;
        this.num_quartos = num_quartos;
        this.quarto_s = quarto_solteiro;
        this.quarto_d = quarto_duplo;
        this.quarto_f = quarto_familia;
        this.quarto_c = quarto_casal;
        this.dt_entrada = data_entrada;
        this.dt_saida = data_saida;
        this.id_cliente = id_cliente;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPessoas() {
        return n_pessoas;
    }

    public void setNumPessoas(int n_pessoas) {
        this.n_pessoas = n_pessoas;
    }

    public int getQuartoSol() {
        return quarto_s;
    }

    public void setQuartoSol(int quarto_s) {
        this.quarto_s = quarto_s;
    }

    public int getQuartoD() {
        return quarto_d;
    }

    public void setQuartoD(int quarto_d) {
        this.quarto_d = quarto_d;
    }

    public int getQuartoF() {
        return quarto_f;
    }

    public void setQuartoF(int quarto_f) {
        this.quarto_f = quarto_f;
    }

    public int getQuartoC() {
        return quarto_c;
    }

    public void setQuartoC(int quarto_c) {
        this.quarto_c = quarto_c;
    }

    public String getDtEntrada() {
        return dt_entrada;
    }

    public void setDtEntrada(String dt_entrada) {
        this.dt_entrada = dt_entrada;
    }

    public String getDtSaida() {
        return dt_saida;
    }

    public void setDtSaida(String dt_saida) {
        this.dt_saida = dt_saida;
    }

    public int getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getNumQuartos() {
        return num_quartos = quarto_s + quarto_c + quarto_d + quarto_f;
    }

    public void setNumQuartos(int num_quartos) {
        this.num_quartos = num_quartos;
    }


}
