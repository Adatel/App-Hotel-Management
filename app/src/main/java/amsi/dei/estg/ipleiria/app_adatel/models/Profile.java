package amsi.dei.estg.ipleiria.app_adatel.models;

public class Profile {

    private int nif, telefone;
    private String nome, morada;

    private int is_admin, is_funcionario, is_cliente, id_user;

    public Profile(String nome, int nif, int telefone,  String morada, int is_admin, int is_funcionario, int is_cliente, int id_user) {
        this.nome = nome;
        this.nif = nif;
        this.telefone = telefone;
        this.morada = morada;
        this.is_admin = is_admin;
        this.is_funcionario = is_funcionario;
        this.is_cliente = is_cliente;
        this.id_user = id_user;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getIs_funcionario() {
        return is_funcionario;
    }

    public void setIs_funcionario(int is_funcionario) {
        this.is_funcionario = is_funcionario;
    }

    public int getIs_cliente() {
        return is_cliente;
    }

    public void setIs_cliente(int is_cliente) {
        this.is_cliente = is_cliente;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
