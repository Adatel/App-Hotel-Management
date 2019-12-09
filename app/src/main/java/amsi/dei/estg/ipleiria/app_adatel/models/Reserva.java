package amsi.dei.estg.ipleiria.app_adatel.models;

import java.io.Serializable;

public class Reserva implements Serializable {

    // Atributos
    private int id, n_pessoas, n_quartos, quarto_s, quarto_d, quarto_f, quarto_c;
    private String dt_entrada, dt_saida;

    public Reserva(int idReserva, int num_pessoas, int num_quartos, int quarto_solteiro, int quarto_duplo, int quarto_familia, int quarto_casal, String data_entrada, String data_saida){

        this.id = idReserva;
        this.n_pessoas = num_pessoas;
        this.n_quartos = num_quartos;
        this.quarto_s = quarto_solteiro;
        this.quarto_d = quarto_duplo;
        this.quarto_f = quarto_familia;
        this.quarto_c = quarto_casal;
        this.dt_entrada = data_entrada;
        this.dt_saida = data_saida;
    }

    // Getters e Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getN_pessoas() {
        return n_pessoas;
    }

    public void setN_pessoas(int n_pessoas) {
        this.n_pessoas = n_pessoas;
    }

    public int getN_quartos() {
        return n_quartos;
    }

    public void setN_quartos(int n_quartos) {
        this.n_quartos = n_quartos;
    }

    public int getQuarto_s() {
        return quarto_s;
    }

    public void setQuarto_s(int quarto_s) {
        this.quarto_s = quarto_s;
    }

    public int getQuarto_d() {
        return quarto_d;
    }

    public void setQuarto_d(int quarto_d) {
        this.quarto_d = quarto_d;
    }

    public int getQuarto_f() {
        return quarto_f;
    }

    public void setQuarto_f(int quarto_f) {
        this.quarto_f = quarto_f;
    }

    public int getQuarto_c() {
        return quarto_c;
    }

    public void setQuarto_c(int quarto_c) {
        this.quarto_c = quarto_c;
    }

    public String getDt_entrada() {
        return dt_entrada;
    }

    public void setDt_entrada(String dt_entrada) {
        this.dt_entrada = dt_entrada;
    }

    public String getDt_saida() {
        return dt_saida;
    }

    public void setDt_saida(String dt_saida) {
        this.dt_saida = dt_saida;
    }
}
