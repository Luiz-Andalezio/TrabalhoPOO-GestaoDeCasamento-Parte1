package model;

import java.time.LocalDateTime;

public class UsuarioDAO {

    Usuario[] usuarios = new Usuario[100];

    public UsuarioDAO(PessoaDAO pessoadao, LocalDateTime calendario) {
        Usuario u1 = new Usuario();
        u1.setPessoa(pessoadao.retornaPessoa(0));
        u1.setLogin("coxinho");
        u1.setSenha("123");
        u1.setTipo("Noivo");
        u1.setDataCriacao(calendario);
        usuarios[0] = u1;

        Usuario u2 = new Usuario();
        u2.setPessoa(pessoadao.retornaPessoa(1));
        u2.setLogin("coxinha");
        u2.setSenha("123");
        u2.setTipo("Noiva");
        u2.setDataCriacao(calendario);
        usuarios[1] = u2;

        Usuario u3 = new Usuario();
        u3.setPessoa(pessoadao.retornaPessoa(3));
        u3.setLogin("admin");
        u3.setSenha("123");
        u3.setTipo("Admin");
        u3.setDataCriacao(calendario);
        usuarios[2] = u3;
    }

    public UsuarioDAO(Pessoa pessoa, String t, String l, String s) {
        //Construtor automatizado
        Usuario u = new Usuario();
        u.setPessoa(pessoa);
        u.setTipo(t);
        u.setLogin(l);
        u.setSenha(s);

        for (int v = 0; v < usuarios.length; v++) {
            if (usuarios[v] == null) {
                usuarios[v] = u;
            }
        }
    }

    public boolean recebePessoa(LocalDateTime calendario, Pessoa novaPessoa, String tipo, String login, String senha) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = new Usuario();
                usuarios[i].setPessoa(novaPessoa);
                usuarios[i].setTipo(tipo);
                usuarios[i].setLogin(login);
                usuarios[i].setSenha(senha);
                usuarios[i].setDataCriacao(calendario);
                return true;
            }
        }
        return false;
    }

    public boolean atualizaPessoaUsuario(LocalDateTime calendario, String nome, String telefone, String nascimento, int id) {
        int i = 0;
        while (usuarios[i] != null && usuarios[i].getId() != id || usuarios[i] == null) {
            i++;
        }

        if (usuarios[i] != null && usuarios[i].getId() == id) {
            if (!nome.equals("")) {
                usuarios[i].getPessoa().setNome(nome);
            }
            if (!telefone.equals("")) {
                usuarios[i].getPessoa().setTelefone(telefone);
            }
            if (!nascimento.equals("")) {
                usuarios[i].getPessoa().setNascimento(nascimento);
            }            
            usuarios[i].getPessoa().setDataModificacao(calendario);
            usuarios[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public boolean atualizaUsuario(LocalDateTime calendario, String tipo, String login, String senha, int id) {
        int i = 0;
        while (usuarios[i] != null && usuarios[i].getId() != id || usuarios[i] == null) {
            i++;
        }

        if (usuarios[i] != null && usuarios[i].getId() == id) {
            if (!tipo.equals("")) {
                usuarios[i].setTipo(tipo);
            }
            if (!login.equals("")) {
                usuarios[i].setLogin(login);
            }
            if (!senha.equals("")) {
                usuarios[i].setSenha(senha);
            }
            usuarios[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public void excluirUsuario(int id) {
        int i = 0;
        while (usuarios[i] != null && usuarios[i].getId() != id || usuarios[i] == null) {
            i++;
        }

        if (usuarios[i] != null && usuarios[i].getId() == id) {
            usuarios[i] = null;
        }
    }

    public String verUsuarios() {
        String m = "";
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null) {
                m += usuarios[i].toString();
            }
        }
        return m;
    }

    public Usuario retornaUsuarioByID(int id) {
        int i = 0;
        while (usuarios[i] != null && usuarios[i].getId() != id || usuarios[i] == null) {
            i++;
        }

        if (usuarios[i] != null && usuarios[i].getId() == id) {
            return usuarios[i];
        }
        return null;
    }

    public Usuario retornaUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && login.equals(usuario.getLogin()) && senha.equals(usuario.getSenha())) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario retornaUsuario(int i) {
        return usuarios[i];
    }
}
