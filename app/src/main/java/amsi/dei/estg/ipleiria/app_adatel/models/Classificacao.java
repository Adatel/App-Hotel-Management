package amsi.dei.estg.ipleiria.app_adatel.models;

public class Classificacao {

    private int id, id_cliente;
    private float quarto, comida, staff, servicos, geral;

    public Classificacao(int id, float quarto, float comida, float staff, float servicos, float geral, int id_cliente){

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

    public float getQuarto() {
        return quarto;
    }

    public void setQuarto(float quarto) {
        this.quarto = quarto;
    }

    public float getComida() {
        return comida;
    }

    public void setComida(float comida) {
        this.comida = comida;
    }

    public float getStaff() {
        return staff;
    }

    public void setStaff(float staff) {
        this.staff = staff;
    }

    public float getServicos() {
        return servicos;
    }

    public void setServicos(float servicos) {
        this.servicos = servicos;
    }

    public float getGeral() {
        return geral;
    }

    public void setGeral(float geral) {
        this.geral = geral;
    }
}
