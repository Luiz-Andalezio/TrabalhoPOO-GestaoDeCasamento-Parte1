package model;

import java.time.LocalDateTime;

public class PessoaDAO {

    Pessoa[] pessoas = new Pessoa[100];

    public PessoaDAO(LocalDateTime calendario) {
        Pessoa p1 = new Pessoa();
        p1.setNome("João");
        p1.setTelefone("+55 (71) ####-####");
        p1.setNascimento("23/02/2000");
        p1.setDataCriacao(calendario);
        pessoas[0] = p1;

        Pessoa p2 = new Pessoa();
        p2.setNome("Maria");
        p2.setTelefone("+55 (68) ####-####");
        p2.setNascimento("01/12/2003");
        p2.setDataCriacao(calendario);
        pessoas[1] = p2;

        Pessoa p3 = new Pessoa();
        p3.setNome("Rodolfo");
        p3.setTelefone("+55 (34) ####-####");
        p3.setNascimento("02/12/1993");
        p3.setDataCriacao(calendario);
        pessoas[2] = p3;

        Pessoa p4 = new Pessoa();
        p4.setNome("Administrador");
        p4.setTelefone("+55 (34) 99713-6908");
        p4.setNascimento("19/04/2004");
        p4.setDataCriacao(calendario);
        pessoas[3] = p4;

        Pessoa p5 = new Pessoa();
        p5.setNome("Gabriel");
        p5.setTelefone("+55 (34) ####-####");
        p5.setNascimento("10/11/2004");
        p5.setDataCriacao(calendario);
        pessoas[4] = p5;

        Pessoa p6 = new Pessoa();
        p6.setNome("Ana");
        p6.setTelefone("+55 (71) ####-####");
        p6.setNascimento("22/09/2013");
        p6.setDataCriacao(calendario);
        pessoas[5] = p6;

        Pessoa p7 = new Pessoa();
        p7.setNome("Dario");
        p7.setTelefone("+55 (21) ####-####");
        p7.setNascimento("13/06/1992");
        p7.setDataCriacao(calendario);
        pessoas[6] = p7;

        Pessoa p8 = new Pessoa();
        p8.setNome("Eustáquio");
        p8.setTelefone("+55 (71) ####-####");
        p8.setNascimento("07/01/1949");
        p8.setDataCriacao(calendario);
        pessoas[7] = p8;
        /*
        p1.setId(1);
        p1.setNome("Luiz");
        p1.setIdade(20);
        p1.setTelefone("+55 34 99713-6908");
        p1.setNascimento("19/04/2004");
        p1.setDataCriacao(calendario);
        pessoa[0] = p1;*/
    }

    public PessoaDAO(String n, String m, String t, LocalDateTime calendario) {
        Pessoa p = new Pessoa();
        p.setNome(n);
        p.setTelefone(m);
        p.setNascimento(t);
        p.setDataCriacao(calendario);
        p.setDataModificacao(calendario);
        for (int v = 0; v < pessoas.length; v++) {
            if (pessoas[v] == null) {
                pessoas[v] = p;
            }
        }
    }

    public Pessoa criarPessoa(String nome, String telefone, String nascimento, LocalDateTime calendario) {
        Pessoa p = new Pessoa();
        p.setNome(nome);
        p.setTelefone(telefone);
        p.setNascimento(nascimento);
        p.setDataCriacao(calendario);

        for (int v = 0; v < pessoas.length; v++) {
            if (pessoas[v] == null) {
                pessoas[v] = p;
                return p;
            }
        }
        return null;
    }

    public boolean atualizaPessoa(String nomeAtt, String telefone, String nascimento, int id, LocalDateTime calendario) {
        int i = 0;
        while (pessoas[i] != null && pessoas[i].getId() != id || pessoas[i] == null) {
            i++;
        }
        //equals() with null check (temary) - Same shit of: (pessoas[i].getNome() != nome)
        if (pessoas[i] != null && pessoas[i].getId() == id) {
            if (!nomeAtt.equals("")) {
                pessoas[i].setNome(nomeAtt);
            }
            if (!telefone.equals("")) {
                pessoas[i].setTelefone(telefone);
            }
            if (!nascimento.equals("")) {
                pessoas[i].setNascimento(nascimento);
            }
            pessoas[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public void excluirPessoa(Pessoa pessoa) {
        int i = 0;
        while (pessoas[i] != null && pessoas[i] != pessoa || pessoas[i] == null) {
            i++;
        }

        if (pessoas[i] != null && pessoas[i] == pessoa) {
            pessoas[i] = null;
        }
    }

    /* 
    public String verConvidados() {
        String m = "";
        
        //for (int i = 0; i < pessoas.length; i++) {
        //if (pessoas[i] != null) {
        //m += pessoas[i].toString() + "\n";
        //}
        //} 

        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                m += pessoa.toString() + "\n";
            }
        }
        return m;
    }*/
    
    public String verPessoa() {
        String m = "";
        /*
        for (int i = 0; i < pessoas.length; i++) {
        if (pessoas[i] != null) {
        m = pessoas[i].toString() + "\n";
        }
        }
         */
        for (Pessoa pessoa : pessoas) {
            if (pessoa != null) {
                m = pessoa.toString() + "\n";
            }
        }
        return m;
    }

    public Pessoa retornaPessoa(int i) {
        return pessoas[i];
    }

    /*
    public Pessoa retornaPessoa1() {
        return pessoas[0];
    }

    public Pessoa retornaPessoa2() {
        return pessoas[1];
    }*/
}
