package model;

public class Igreja {
    private String nome;
    private String endereço;
    private String CEP;

    //GETTERS E SETTERS
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereço;
    }

    public void setEndereco(String endereço) {
        this.endereço = endereço;
    }

    public String getCEP() {
        return this.CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
}
