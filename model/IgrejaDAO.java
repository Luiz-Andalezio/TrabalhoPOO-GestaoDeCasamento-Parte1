package model;

public class IgrejaDAO {

    Igreja[] igrejas = new Igreja[100];

    public IgrejaDAO() {
        Igreja i = new Igreja();
        i.setNome("Igreja Madre de Deus");
        i.setEndereco("Rua Madre de Deus, Recife, PE");
        i.setCEP("50030-110");
        igrejas[0] = i;
    }

    public boolean atualizaIgreja(String novoNome, String endereco, String CEP) {
        if (!novoNome.equals("")) {
            igrejas[0].setNome(novoNome);
        }
        if (!endereco.equals("")) {
            igrejas[0].setEndereco(endereco);
        }
        if (!CEP.equals("")) {
            igrejas[0].setCEP(CEP);
        }
        return true;
    }

    public Igreja retornaIgreja() {
        return igrejas[0];
    }
}
