package view;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.Evento;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;

public class GUI {

    //TELA DE BOAS VINDAS
    public int menuBoasVindas(Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder("");

        m.append(evento)
                .append("\n\n1- Entrar como Administrador.")
                .append("\n2- Entrar como Convidado.")
                .append("\n3- Informações do evento.");
        //m.append("\n3- Informações.");
        m.append("\n\n0 - Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    //LOGINS
    public Usuario login(UsuarioDAO usuariodao) {
        Usuario usuario = null;
        String loginAtt, senhaAtt;

        while (usuario == null) {
            loginAtt = JOptionPane.showInputDialog("Informe seu login: ");
            senhaAtt = JOptionPane.showInputDialog("Informe sua senha: ");

            usuario = usuariodao.retornaUsuario(loginAtt, senhaAtt);

            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.");
            }
        }
        return usuario;
    }

    public ConvidadoFamilia loginConviteFamilia(ConvidadoFamiliaDAO convitefamiliadao) {
        ConvidadoFamilia convitefamilia = null;
        String acesso;

        while (convitefamilia == null) {
            acesso = JOptionPane.showInputDialog("Informe o acesso do seu Convite Família: ");

            convitefamilia = convitefamiliadao.retornaAcessoConviteFamilia(acesso);

            if (convitefamilia == null) {
                JOptionPane.showMessageDialog(null, "Acesso inválido. Tente novamente.");
            }
        }
        return convitefamilia;
    }

    //MENUS
    public int menuNoivos(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Menu dos Noivos -----");
        m.append("\n\n1- Menu do Evento.");        
        m.append("\n2- Menu de Convites Família e Convites Fornecedor.");
        m.append("\n3- Menu de Convites individuais.");        
        m.append("\n4- Menu de presentes.");
        m.append("\n5- Visualizar mensagens recebidas.");
        m.append("\n6- Visualizar pagamentos à fornecedores.");
        m.append("\n7- Incrementar dias.");
        m.append("\n8- Relatórios.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuAdmin(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Menu do Administrador -----");        
        m.append("\n\n1- CRUD Evento.");
        m.append("\n2- CRUD Convites Família e Convites Fornecedor.");
        m.append("\n3- CRUD Convites Individuais.");
        m.append("\n4- CRUD Usuários.");
        m.append("\n5- CRUD Presentes.");        
        m.append("\n6- CRUD Mensagens.");
        m.append("\n7- CRUD Fornecedores.");
        m.append("\n8- CRUD Pagamentos.");
        m.append("\n9- CRUD Calendário.");
        m.append("\n10- Relatórios.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuCerimonial(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Menu do Cerimonial -----");
        m.append("\n\n1- Ver Convites Família.");
        m.append("\n2- Enviar mensagem para os noivos.");
        m.append("\n3- Comprar presentes para os noivos.");
        m.append("\n4- Relatórios.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuConvidado() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Convidados -----");
        m.append("\n\n1- Enviar mensagem para os noivos.");
        m.append("\n2- Comprar presentes para os noivos.");
        m.append("\n3- Informar presença da família.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    //CRUDS
    public int crudConvFamilia(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Menu de Convites Famílias e Convites Fornecedor -----");
        m.append("\n\n1- Gerar Convite Família.");
        m.append("\n2- Gerar novos codigos de acesso para Convites Família.");
        m.append("\n3- Adicionar pessoas em Convites Família ou Convites Fornecedor.");
        m.append("\n4- Excluir pessoas em Convites Família ou Convites Fornecedor.");
        m.append("\n5- Editar Convites Família ou Convites Fornecedor.");
        m.append("\n6- Exibir Convites Família e Convites Fornecedor.");
        m.append("\n7- Desfazer Convites Famlília ou Convites Fornecedor.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudConvIndividual(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Menu de Convidados Individuais -----");
        m.append("\n\n1- Gerar convites individuais.");
        m.append("\n2- Editar convites individuais.");
        m.append("\n3- Exibir convites individuais.");
        m.append("\n4- Desfazer convites individuais.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudUsuario(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Usuários -----");
        m.append("\n\n1- Cadastrar usuários.");
        m.append("\n2- Editar usuários.");
        m.append("\n3- Exibir usuários.");
        m.append("\n4- Desfazer usuários.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudMensagem(Usuario usuarioLogado, Evento evento, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Mensagens -----");
        m.append("\n\n1- Exibir mensagens enviadas aos noivos.");
        m.append("\n2- Excluir mensagens enviadas aos noivos.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    /*
    public int crudMensagem(Usuario usuarioLogado, Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Mensagens -----");
        m.append("\n\n1- Editar mensagens.");
        m.append("\n2- Exibir mensagens.");
        m.append("\n3- Excluir mensagens.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }
     */
    public int crudPresentesNoivos(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Presentes -----");
        m.append("\n\n1- Ver presentes disponíveis.");
        m.append("\n2- Ver presentes comprados.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesAdmin(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Presentes -----");
        m.append("\n\n1- Registrar presente.");
        m.append("\n2- Editar presentes.");
        m.append("\n3- Exibir presentes disponíveis.");
        m.append("\n4- Exibir presentes comprados aos noivos.");
        m.append("\n5- Excluir presentes.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudFornecedorAdmin(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Fornecedores -----");
        m.append("\n\n1- Registrar fornecedor.");
        m.append("\n2- Editar fornecedores.");
        m.append("\n3- Exibir fornecedores.");
        m.append("\n4- Excluir fornecedores.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPagamento(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Fornecedores -----");
        m.append("\n\n1- Registrar pagamento.");
        m.append("\n2- Editar pagamentos.");
        m.append("\n3- Exibir pagamentos.");
        m.append("\n4- Excluir pagamentos.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudEvento(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Evento -----");
        m.append("\n\n1- Alterar nome dos noivos.");
        m.append("\n2- Alterar data do evento.");
        m.append("\n3- Alterar Cerimoniais.");
        m.append("\n4- Alterar Igreja.");
        m.append("\n5- Alterar Cartório.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudCerimonial(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- CRUD Cerimonial -----");
        m.append("\n\n1- Registrar cerimonial.");
        m.append("\n2- Editar cerimoniais.");
        m.append("\n3- Exibir cerimoniais.");
        m.append("\n4- Excluir cerimoniais.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesConvidado(PresentesDAO presentesdao) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("\nDigite o código do presente à venda que deseja comprar aos pombinhos!");
        m.append("\n\n\tPRESENTES: \n");
        m.append(presentesdao.verPresentesConvidado());
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int enviaMensagem(ConvidadoFamilia convitefamilia, Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Mensagens -----");
        m.append("\n\n1- Envie uma mensagem para ")
                .append(evento.getPessoaNoivo().getNome())
                .append(" e ")
                .append(evento.getPessoaNoiva().getNome());
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudData(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("\n\n----- Menu de Calendario -----");
        m.append("\n\n1- Adicionar dias ");
        m.append("\n2- Adicionar meses.");
        m.append("\n3- Adicionar anos");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudRelatorios(Usuario usuarioLogado, LocalDateTime calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado, calendario));

        m.append("----- Relatórios -----");
        m.append("\n\n1- Ver recados recebidos.");
        m.append("\n2- Imprima um Convite Família.");
        m.append("\n3- Imprima um Convite Individual.");
        m.append("\n4- Exibir o total de pagamentos à fornecedores.");
        m.append("\n5- Exibir a lista total de convidados.");
        m.append("\n\n0 - Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public StringBuilder headerAdmin(Usuario usuarioLogado, LocalDateTime calendario) {
        StringBuilder m = new StringBuilder("----------------------------------------------------------------------");
        m.append("\nUsuário: ").append(usuarioLogado.getPessoa().getNome());
        m.append("\nTipo: ").append(usuarioLogado.getTipo());
        m.append(String.format("%60s", calendarioFormatado(calendario)));
        m.append("\n----------------------------------------------------------------------\n\n");

        return m;
    }

    public StringBuilder headerConvidado(ConvidadoFamilia convitefamilia) {
        StringBuilder m = new StringBuilder("-------------------------------------------------------------------");
        m.append("\nConvite Família dos: ").append(convitefamilia.getNomeDaFamilia());
        m.append("\n-------------------------------------------------------------------\n\n");

        return m;
    }

    public String calendarioFormatado(LocalDateTime calendario) {
        String alteraDia = "";
        if (calendario.getDayOfMonth() < 10) {
            alteraDia += "0";
        }
        alteraDia += calendario.getDayOfMonth() + "/";
        if (calendario.getMonthValue() < 10) {
            alteraDia += "0";
        }
        alteraDia += calendario.getMonthValue() + "/" + calendario.getYear();
        return alteraDia;
    }
}
