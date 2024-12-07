package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.Presentes;
import model.PresentesDAO;
import model.Usuario;
import view.GUI;

public class ControllerPresentes {

    public void controllerCrudPresentes(GUI gui, Usuario usuarioLogado, Presentes presente, PresentesDAO presentesdao, LocalDateTime calendario) {
        StringBuilder m;
        int menuPresentesOpc = 0;

        while (menuPresentesOpc != -1) {
            menuPresentesOpc = gui.crudPresentesAdmin(usuarioLogado, calendario);
            switch (menuPresentesOpc) {
                case 1:
                    //Registrar presente
                    String novoNome = JOptionPane.showInputDialog("\nO que é o presente?: ");
                    String novoTipo = JOptionPane.showInputDialog("\nInforme o tipo do presente: " + novoNome);
                    double novoValor = Integer.parseInt(JOptionPane.showInputDialog("\nInforme o valor em reais do presente: " + novoNome));

                    if (!"".equals(novoNome) || !"".equals(novoTipo) || !"".equals(novoValor)) {
                        presentesdao.registrarPresente(calendario, novoNome, novoTipo, novoValor);
                        JOptionPane.showMessageDialog(null, presentesdao.verPresentesConvidado());
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: presente não registrado...");
                    }
                    break;

                case 2:
                    //Editar presentes
                    String s = presentesdao.verPresentesConvidado();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do presente que deseja editar: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o presente abaixo?\n\n" + presentesdao.retornaPresenteByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                String nomeAtt = JOptionPane.showInputDialog("Informe o novo nome a ser atualizado: ");
                                String tipoAtt = JOptionPane.showInputDialog("\nInforme o novo tipo de presente a ser atualizado: ");
                                double valorAtt = Integer.parseInt(JOptionPane.showInputDialog("\nInforme o novo valor em reais a ser atualizado: "));

                                if (!"".equals(nomeAtt) || !"".equals(tipoAtt)) {
                                    m = new StringBuilder("Presente atualizado com sucesso!\n\nAntes: \n" + presentesdao.retornaPresenteByID(id));
                                    presentesdao.atualizaPresente(calendario, nomeAtt, tipoAtt, valorAtt, id);
                                    m.append("\nAgora: \n").append(presentesdao.retornaPresenteByID(id));
                                    JOptionPane.showMessageDialog(null, m);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há usuarios cadastrados.");
                    }
                    break;

                case 3:
                    //Exibir presentes disponíveis
                    s = presentesdao.verPresentesAdmin(usuarioLogado);
                    if ("".equals(s)) {
                        s = "Ainda não há presentes cadastrados.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 4:
                    //Exibir presentes comprados aos noivos
                    s = presentesdao.verPresentesComprados();
                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há presentes comprados.");
                    } else {
                        JOptionPane.showMessageDialog(null, s);
                    }
                    break;

                case 5:
                    //Desfazer presentes
                    s = presentesdao.verPresentesConvidado();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do presente a ser desfeito: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este presente abaixo?\n\n" + presentesdao.retornaPresenteByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Presente excluido com sucesso!\n\n" + presentesdao.retornaPresenteByID(id));
                                presentesdao.excluirPresente(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há presentes cadastrados.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuPresentesOpc = -1;
                    break;

                default:
                    menuPresentesOpc = -1;
                    break;
            }
        }
    }

    public void controllerVerPresentesNoivos(GUI gui, Usuario usuarioLogado, PresentesDAO presentesdao, LocalDateTime calendario) {
        int menuPresentesOpc = 0;

        while (menuPresentesOpc != -1) {
            menuPresentesOpc = gui.crudPresentesNoivos(usuarioLogado, calendario);
            switch (menuPresentesOpc) {
                case 1:
                    String s = presentesdao.verPresentesAdmin(usuarioLogado);
                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há presentes registrados.");
                    } else {
                        JOptionPane.showMessageDialog(null, s);
                    }
                    break;

                case 2:
                    s = presentesdao.verPresentesCompradosAdmin(usuarioLogado);
                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há presentes comprados.");
                    } else {
                        JOptionPane.showMessageDialog(null, s);
                    }
                    break;

                case 0:
                    //Voltar
                    menuPresentesOpc = -1;
                    break;

                default:
                    menuPresentesOpc = -1;
                    break;
            }
        }
    }

    public void controllerDarPresentes(GUI gui, PresentesDAO presentesdao, LocalDateTime calendario) {
        int menuDarPresentes = 0;

        while (menuDarPresentes != -1) {
            String s = presentesdao.verPresentesConvidado();
            if ("".equals(s)) {
                JOptionPane.showMessageDialog(null, "Ainda não há presentes disponíveis.");
            } else {
                int id = gui.crudPresentesConvidado(presentesdao);
                if (id != 0) {
                    boolean verifica = presentesdao.verificaPresente(id);
                    if (verifica == true) {
                        JOptionPane.showMessageDialog(null, "Presente já comprado! Escolha outro.");
                    } else {
                        int veredito = JOptionPane.showConfirmDialog(null, "Deseja iniciar a compra do presente abaixo?\n\n" + presentesdao.retornaPresenteByID(id), "Confirmar Compra", JOptionPane.YES_NO_OPTION);

                        if (veredito == JOptionPane.YES_OPTION) {
                            String nomeComprador = JOptionPane.showInputDialog("Insira o seu nome completo: ");
                            String nomeCartao = JOptionPane.showInputDialog("Insira o nome informado no seu cartão de crédito: ");
                            String numeroCartao = JOptionPane.showInputDialog("Insira o número informado no seu cartão de crédito: ");
                            String dataVencimento = JOptionPane.showInputDialog("Insira a data de vencimento informada no seu cartão de crédito (formato MM/YY): ");
                            String cvv = JOptionPane.showInputDialog("Insira o CVV informado no seu cartão de crédito: ");
                            presentesdao.compraPresente(calendario, nomeComprador, id);

                            veredito = JOptionPane.showConfirmDialog(null, "Deseja finalizar a compra do presente abaixo?\n\n" + presentesdao.retornaPresenteByID(id), "Confirmar Compra", JOptionPane.YES_NO_OPTION);

                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Compra finalizada!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Compra não sucedida");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Compra não sucedida");
                        }
                    }
                } else {
                    menuDarPresentes = -1;
                }
            }
        }
    }
}
