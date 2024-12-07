package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.Evento;
import model.Mensagens;
import model.MensagensDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class ControllerMensagens {

    public void controllerCrudMensagens(GUI gui, Usuario usuarioLogado, UsuarioDAO usuariodao, PessoaDAO pessoadao, Pessoa pessoa, Evento evento, Mensagens mensagens, MensagensDAO mensagensdao, LocalDateTime calendario) {
        int menuCrudMensagemOpc = 0;

        while (menuCrudMensagemOpc != -1) {
            menuCrudMensagemOpc = gui.crudMensagem(usuarioLogado, evento, calendario);
            switch (menuCrudMensagemOpc) {
                case 1:
                    //Editar mensagem
                    /*
                    String s = mensagensdao.verMensagens();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID da mensagem a ser editada: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a mensagem do convite abaixo?\n\n" + mensagensdao.retornaMensagemByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                String nomeDoMensageiroAtt = JOptionPane.showInputDialog("Informe o novo nome do mensageiro: ");
                                String mensagemAtt = JOptionPane.showInputDialog("\nDigite a mensagem a ser subtituida: ");

                                if (!"".equals(nomeDoMensageiroAtt) || !"".equals(mensagemAtt)) {
                                    m = new StringBuilder("Convite atualizado com sucesso!\n\nAntes: \n" + mensagensdao.retornaMensagemByID(id));
                                    mensagensdao.atualizaMensagem(nomeDoMensageiroAtt, mensagemAtt, id);
                                    m.append("\nAgora: \n").append(mensagensdao.retornaMensagemByID(id));
                                    JOptionPane.showMessageDialog(null, m);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há convites feitos.");
                    }
                    break;
                    */

                    //case 2:
                    //Exibir mensagens
                    String s = mensagensdao.verMensagens();
                    if ("".equals(s)) {
                        s = "Ainda não há mensagens enviadas.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                //case 3:
                case 2:
                    //Excluir mensagens
                    s = mensagensdao.verMensagens();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID da mensagem a ser excluida: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a mensagem abaixo?\n\n" + mensagensdao.retornaMensagemByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Mensagem excluída com sucesso!\n\n" + mensagensdao.retornaMensagemByID(id));
                                mensagensdao.excluiMensagem(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há mensagens enviadas.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuCrudMensagemOpc = -1;
                    break;

                default:
                    menuCrudMensagemOpc = -1;
                    break;
            }
        }
    }

    public void controllerVerMensagens(MensagensDAO mensagensdao) {
        String s = mensagensdao.verMensagens();
        if ("".equals(s)) {
            s = "Ainda não há mensagens enviadas.";
        }
        JOptionPane.showMessageDialog(null, s);
    }

    public void controllerEnviarMensagens(Evento evento, Mensagens mensagens, MensagensDAO mensagensdao, LocalDateTime calendario) {
        String mensagemTeste = JOptionPane.showInputDialog("Digite a mensagem que deseja enviar para " + evento.getPessoaNoivo().getNome() + " e " + evento.getPessoaNoiva().getNome() + ":");
        if (!"0".equals(mensagemTeste)) {
            String nomeDoMensageiro = JOptionPane.showInputDialog("Informe o seu nome: ");
            if (!"0".equals(nomeDoMensageiro)) {
                JOptionPane.showMessageDialog(null, "Sua mensagem foi enviada para " + evento.getPessoaNoivo().getNome() + " e " + evento.getPessoaNoiva().getNome() + "\n\nSeu nome: " + nomeDoMensageiro + "\n\nMensagem: " + mensagemTeste);
                mensagensdao.criaMensagem(nomeDoMensageiro, mensagemTeste, calendario);
            } else if ("".equals(nomeDoMensageiro)) {
                JOptionPane.showMessageDialog(null, "Nome não enviado: nenhuma mensagem enviada");
            } else {
            }
        } else if (!"".equals(mensagemTeste)) {

        } else {
            JOptionPane.showMessageDialog(null, "Dado não enviado: nenhuma mensagem enviada");
        }
    }
}
