package model;

public class Cartorio {
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;

    //GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCEP() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
