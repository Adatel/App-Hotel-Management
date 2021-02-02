package amsi.dei.estg.ipleiria.app_adatel.models;

public class Classificacao {

    private int id, id_cliente;
    private double quarto, comida, staff, servicos, geral;

    public Classificacao(int id, double quarto, double comida, double staff, double servicos, double geral, int id_cliente){

        this.id = id;
        this.quarto = quarto;
        this.comida = comida;
        this.staff = staff;
        this.servicos = servicos;
        this.geral = geral;
        this.id_cliente = id_cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getQuarto() {
        return quarto;
    }

    public void setQuarto(double quarto) {
        this.quarto = quarto;
    }

    public double getComida() {
        return comida;
    }

    public void setComida(double comida) {
        this.comida = comida;
    }

    public double getStaff() {
        return staff;
    }

    public void setStaff(double staff) {
        this.staff = staff;
    }

    public double getServicos() {
        return servicos;
    }

    public void setServicos(double servicos) {
        this.servicos = servicos;
    }

    public double getGeral() {
        return geral;
    }

    public void setGeral(double geral) {
        this.geral = geral;
    }
}
