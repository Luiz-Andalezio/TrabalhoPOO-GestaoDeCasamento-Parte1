package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.ConvidadoIndividualDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConvidadoIndividual {

    public void controllerCrudConvidado(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao, Pessoa pessoa, ConvidadoIndividualDAO conviteindividualdao, LocalDateTime calendario) {
        StringBuilder m;
        int menuPessoaOpc = 0;

        while (menuPessoaOpc != -1) {
            menuPessoaOpc = gui.crudConvIndividual(usuarioLogado, calendario);
            switch (menuPessoaOpc) {
                case 1:
                    //Gerar convites
                    int escolheTipo = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo de Convite Individual que deseja:\n\n1- Parente ou Amigo.\n2- Fornecedor.\n\n0 - Voltar."));
                    if (escolheTipo != 0) {
                    }
                    if (escolheTipo == 1) {
                        String novoNome = JOptionPane.showInputDialog("\n Informe o primeiro nome do(a) convidado(a): ");
                        String novoParentesco = JOptionPane.showInputDialog("\nInforme o parentesco de " + novoNome);
                        String novoTelefone = JOptionPane.showInputDialog("\nInforme o telefone de " + novoNome);
                        String novoNascimento = JOptionPane.showInputDialog("\nInforme a data de nascimento de " + novoNome);

                        if (!"".equals(novoNome) || !"".equals(novoParentesco) || !"".equals(novoTelefone) || !"".equals(novoNascimento)) {
                            pessoa = pessoadao.criarPessoa(novoNome, novoTelefone, novoNascimento, calendario);
                            conviteindividualdao.recebePessoa(pessoa, novoParentesco, calendario);
                            JOptionPane.showMessageDialog(null, conviteindividualdao.verConvidados());
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum dado enviado: convite não gerado...");
                        }
                    }
                    if (escolheTipo == 2) {
                        String novoNome = JOptionPane.showInputDialog("\n Informe o nome do(a) fornecedor(a): ");
                        String novoTelefone = JOptionPane.showInputDialog("\nInforme o telefone de " + novoNome);

                        if (!"".equals(novoNome) || !"".equals(novoTelefone)) {
                            pessoa = pessoadao.criarPessoa(novoNome, novoTelefone, null, calendario);
                            conviteindividualdao.recebePessoa(pessoa, null, calendario);
                            JOptionPane.showMessageDialog(null, conviteindividualdao.verConvidados());
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum dado enviado: convite não gerado...");
                        }
                    }
                    break;

                case 2:
                    //Editar convites individuais
                    String s = conviteindividualdao.verConvidados();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do convite da pessoa que deseja editar: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a pessoa do convite abaixo?\n\n" + conviteindividualdao.retornaConviteIndividualByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                String nomeAtt;
                                String parentescoAtt = "";
                                String nascimentoAtt = "";
                                String telefoneAtt;

                                JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                nomeAtt = JOptionPane.showInputDialog("Informe o nome a ser atualizado: ");
                                if (conviteindividualdao.retornaConviteIndividualByID(id).getParentesco() != null) {
                                    parentescoAtt = JOptionPane.showInputDialog("\nInforme o parentesco a ser atualizado: ");
                                    nascimentoAtt = JOptionPane.showInputDialog("\nInforme a data de nascimento a ser atualizada: ");
                                }
                                telefoneAtt = JOptionPane.showInputDialog("\nInforme o telefone a ser atualizado: ");

                                if (conviteindividualdao.retornaConviteIndividualByID(id).getParentesco() != null) {
                                    if (!"".equals(nomeAtt) || !"".equals(parentescoAtt) || !"".equals(telefoneAtt) || !"".equals(nascimentoAtt)) {
                                        m = new StringBuilder("Convite atualizado com sucesso!\n\nAntes: \n" + conviteindividualdao.retornaConviteIndividualByID(id));
                                        conviteindividualdao.atualizaConviteIndividual(nomeAtt, telefoneAtt, nascimentoAtt, parentescoAtt, id, calendario);
                                        m.append("\nAgora: \n").append(conviteindividualdao.retornaConviteIndividualByID(id));
                                        JOptionPane.showMessageDialog(null, m);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                    }
                                } else {
                                    if (!"".equals(nomeAtt) || !"".equals(telefoneAtt)) {
                                        m = new StringBuilder("Convite atualizado com sucesso!\n\nAntes: \n" + conviteindividualdao.retornaConviteIndividualByID(id));
                                        conviteindividualdao.atualizaConviteIndividual(nomeAtt, telefoneAtt, null, null, id, calendario);
                                        m.append("\nAgora: \n").append(conviteindividualdao.retornaConviteIndividualByID(id));
                                        JOptionPane.showMessageDialog(null, m);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há convites feitos.");
                    }
                    break;

                case 3:
                    //Exibir convites individuais
                    s = conviteindividualdao.verConvidados();
                    if ("".equals(s)) {
                        s = "Ainda não há convites feitos.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 4:
                    //Desfazer convites
                    s = conviteindividualdao.verConvidados();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do convite a ser desfeito: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo desconvidar a pessoa do convite abaixo?\n\n" + conviteindividualdao.retornaConviteIndividualByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Convite desfeito com sucesso!\n\n" + conviteindividualdao.retornaConviteIndividualByID(id));
                                pessoadao.excluirPessoa(conviteindividualdao.retornaConviteIndividualByID(id).getPessoa());
                                conviteindividualdao.desfazerConviteIndividual(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há convites feitos.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuPessoaOpc = -1;
                    break;

                default:
                    menuPessoaOpc = -1;
                    break;
            }
        }
    }
}
