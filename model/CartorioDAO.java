package model;

public class CartorioDAO {

    Cartorio[] cartorios = new Cartorio[100];

    public CartorioDAO() {
        Cartorio c = new Cartorio();
        c.setNome("Cartório Primeiro Distrito da Capital");
        c.setEndereco("Av. Marquês de Olinda, 296 - Recife Antigo, Recife, PE");
        c.setTelefone("(81) 3037-3240");
        c.setCep("50030-000");
        cartorios[0] = c;
    }

    public boolean atualizaCartorio(String nomeAtt, String enderecoAtt, String telefoneAtt, String cepAtt) {
        if (!nomeAtt.equals("")) {
            cartorios[0].setNome(nomeAtt);
        }
        if (!enderecoAtt.equals("")) {
            cartorios[0].setEndereco(enderecoAtt);
        }
        if (!telefoneAtt.equals("")) {
            cartorios[0].setTelefone(telefoneAtt);
        }
        if (!cepAtt.equals("")) {
            cartorios[0].setCep(cepAtt);
        } 
        return true;
    }

    public Cartorio retornaCartorio() {
        return cartorios[0];
    }
}
