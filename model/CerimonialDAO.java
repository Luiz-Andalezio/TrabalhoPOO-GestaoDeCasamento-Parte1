package model;

import java.time.LocalDateTime;

public class CerimonialDAO {

    Cerimonial[] cerimoniais = new Cerimonial[100];

    public CerimonialDAO(LocalDateTime calendario) {
        Cerimonial c1 = new Cerimonial();
        c1.setNome("Rodrigo Carvalho");
        c1.setFuncao("Mestre de Cerimônia");
        c1.setDataCriacao(calendario);

        Cerimonial c2 = new Cerimonial();
        c2.setNome("Kenedy Tavares");
        c2.setFuncao("Animador de Festas");
        c2.setDataCriacao(calendario);

        Cerimonial c3 = new Cerimonial();
        c3.setNome("Ana Silva");
        c3.setFuncao("Recepcionista");
        c3.setDataCriacao(calendario);

        Cerimonial c4 = new Cerimonial();
        c4.setNome("Leonardo Nihara");
        c4.setFuncao("Fotógrafo Profissional");
        c4.setDataCriacao(calendario);

        cerimoniais[0] = c1;
        cerimoniais[1] = c2;
        cerimoniais[2] = c3;
        cerimoniais[3] = c4;
    }

    public Cerimonial criarCerimonial(String nome, String funcao, LocalDateTime calendario) {
        Cerimonial c = new Cerimonial();
        c.setNome(nome);
        c.setFuncao(funcao);
        c.setDataCriacao(calendario);

        for (int v = 0; v < cerimoniais.length; v++) {
            if (cerimoniais[v] == null) {
                cerimoniais[v] = c;
                return c;
            }
        }
        return null;
    }

    public boolean atualizaCerimonial(int id, String nomeAtt, String funcaoAtt, LocalDateTime calendario) {
        int i = 0;
        while (cerimoniais[i] != null && cerimoniais[i].getId() != id || cerimoniais[i] == null) {
            i++;
        }
        if (cerimoniais[i] != null && cerimoniais[i].getId() == id) {
            if (!nomeAtt.equals("")) {
                cerimoniais[i].setNome(nomeAtt);
            }
            if (!funcaoAtt.equals("")) {
                cerimoniais[i].setFuncao(funcaoAtt);
            }
            cerimoniais[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public void excluirCerimonial(int id) {
        int i = 0;
        while (cerimoniais[i] != null && cerimoniais[i].getId() != id || cerimoniais[i] == null) {
            i++;
        }

        if (cerimoniais[i] != null && cerimoniais[i].getId() == id) {
            cerimoniais[i] = null;
        }
    }
    
    public String verCerimoniaisAdmin() {
        String m = "";
        for (int i = 0; i < cerimoniais.length; i++) {
            if (cerimoniais[i] != null) {
                m += cerimoniais[i].toStringAdmin();
            }
        }
        return m;
    }

    public String verCerimoniais() {
        String m = "";
        for (int i = 0; i < cerimoniais.length; i++) {
            if (cerimoniais[i] != null) {
                m += cerimoniais[i].toString();
            }
        }
        return m;
    }

    public Cerimonial retornaCerimonialByID(int id) {
        int i = 0;
        while (cerimoniais[i] != null && cerimoniais[i].getId() != id || cerimoniais[i] == null) {
            i++;
        }

        if (cerimoniais[i] != null && cerimoniais[i].getId() == id) {
            return cerimoniais[i];
        }
        return null;
    }
}
