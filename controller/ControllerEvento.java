package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.CartorioDAO;
import model.CerimonialDAO;
import model.EventoDAO;
import model.IgrejaDAO;
import model.Usuario;
import view.GUI;

public class ControllerEvento {

    public void controllerCrudEvento(GUI gui, Usuario usuarioLogado, EventoDAO eventodao, CerimonialDAO cerimonialdao, IgrejaDAO igrejadao, CartorioDAO cartoriodao, LocalDateTime calendario) {
        StringBuilder m;
        int menuEventoOpc = 0;

        while (menuEventoOpc != -1) {
            menuEventoOpc = gui.crudEvento(usuarioLogado, calendario);
            switch (menuEventoOpc) {
                case 1:
                    //Alterar nome dos noivos
                    String novoNomeNoivo = JOptionPane.showInputDialog("Informe o novo nome do noivo: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String novoNomeNoiva = JOptionPane.showInputDialog("Informe o novo nome da noiva: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    if (!"".equals(novoNomeNoivo) || !"".equals(novoNomeNoiva)) {
                        if (!"".equals(novoNomeNoivo) && !"".equals(novoNomeNoiva)) {
                            JOptionPane.showMessageDialog(null, "Nomes atualizados com sucesso!\n\nNovo nome do noivo: " + novoNomeNoivo + "\nNovo nome da noiva: " + novoNomeNoiva);
                        } else if (!"".equals(novoNomeNoivo)) {
                            eventodao.atualizaNomeNoivo(novoNomeNoivo, calendario);
                            JOptionPane.showMessageDialog(null, "Nome do noivo atualizado com sucesso!");
                        } else if (!"".equals(novoNomeNoiva)) {
                            eventodao.atualizaNomeNoiva(novoNomeNoiva, calendario);
                            JOptionPane.showMessageDialog(null, "Nome da noiva atualizado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum dado enviado: nomes dos noivos não alterados...");
                        }
                    }
                    break;

                case 2:
                    //Alterar data e horario do evento
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");

                    String dataAtt = JOptionPane.showInputDialog("\nInforme a nova data e horario do evento (##/##/#### ##:##): ");
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDate.parse(dataAtt, formatador);

                    if (!"".equals(dataAtt)) {
                        eventodao.atualizaDataEvento(dataAtt, calendario);
                        JOptionPane.showMessageDialog(null, "Data atualizada com sucesso!");
                    } else if ("".equals(dataAtt)) {
                        JOptionPane.showMessageDialog(null,
                                "Nenhum dado enviado: atualizações não foram realizadas...");
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Formato inválido! Use o formato (##/##/#### ##:##).");
                    }
                    break;

                case 3:
                    int menuCerimonialOpc = 0;

                    while (menuCerimonialOpc != -1) {
                        menuCerimonialOpc = gui.crudCerimonial(usuarioLogado, calendario);
                        switch (menuCerimonialOpc) {
                            case 1:
                                //Registrar Cerimonial
                                String novoNome = JOptionPane.showInputDialog("Informe o novo nome do cerimonial: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                                String novaFuncao = JOptionPane.showInputDialog("Informe o funcao nome da cerimonial: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                                if (!"".equals(novoNome) || !"".equals(novaFuncao)) {
                                    if (!"".equals(novoNome) && !"".equals(novaFuncao)) {
                                        cerimonialdao.criarCerimonial(novoNome, novaFuncao, calendario);
                                        JOptionPane.showMessageDialog(null, cerimonialdao.verCerimoniaisAdmin());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: cerimonial não criado...");
                                    }
                                }
                                break;

                            case 2:
                                //Editar Cerimonial
                                String s = cerimonialdao.verCerimoniaisAdmin();
                                if ("".equals(s)) {
                                    JOptionPane.showMessageDialog(null, "Ainda não há cerimoniais registrados.");
                                } else {
                                    int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do cerimonial a ser atualizado: \n" + s + "\n0 - Voltar"));
                                    if (id != 0) {
                                        int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o cerimonial abaixo?\n\n" + cerimonialdao.retornaCerimonialByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                                        if (veredito == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                            String nomeAtt = JOptionPane.showInputDialog("Informe o novo nome do cerimonial abaixo:\n\n" + cerimonialdao.retornaCerimonialByID(id) + "\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                                            String funcaoAtt = JOptionPane.showInputDialog("Informe a nova função do cerimonial abaixo:\n\n" + cerimonialdao.retornaCerimonialByID(id) + "\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                                            if (!"".equals(nomeAtt) || !"".equals(funcaoAtt)) {
                                                cerimonialdao.atualizaCerimonial(id, nomeAtt, funcaoAtt, calendario);
                                                JOptionPane.showMessageDialog(null, "Cerimonial atualizada com sucesso!");
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Nenhum dado enviado: cerimonial não criado...");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                                        }
                                    }
                                }
                                break;

                            case 3:
                                //Exibir Cerimoniais
                                s = cerimonialdao.verCerimoniaisAdmin();
                                if ("".equals(s)) {
                                    s = "Ainda não há cerimoniais registrados.";
                                }
                                JOptionPane.showMessageDialog(null, s);
                                break;

                            case 4:
                                //Excluir Cerimoniais
                                s = cerimonialdao.verCerimoniaisAdmin();
                                if (!"".equals(s)) {
                                    int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do cerimonial a ser desfeito: \n\n0 - Voltar"));
                                    if (id != 0) {
                                        int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este cerimonial abaixo?\n\n" + cerimonialdao.retornaCerimonialByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                                        if (veredito == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "Cerimonial excluido com sucesso!\n\n" + cerimonialdao.retornaCerimonialByID(id));
                                            cerimonialdao.excluirCerimonial(id);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Ainda não há cerimoniais registrados.");
                                }
                                break;

                            case 0:
                                //Voltar
                                menuCerimonialOpc = -1;
                                break;

                            default:
                                menuCerimonialOpc = -1;
                                break;
                        }
                    }
                    break;

                case 4:
                    //Alterar Igreja
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                    String nomeAtt = JOptionPane.showInputDialog("Informe o novo nome da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String enderecoAtt = JOptionPane.showInputDialog("Informe o novo endereço da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String cepAtt = JOptionPane.showInputDialog("Informe o novo cep da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    if (!"".equals(nomeAtt) || !"".equals(enderecoAtt) || !"".equals(cepAtt)) {
                        igrejadao.atualizaIgreja(nomeAtt, enderecoAtt, cepAtt);
                        JOptionPane.showMessageDialog(null, "Igreja atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: igreja não editada...");
                    }
                    break;

                case 5:
                    //Alterar Cartório
                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                    nomeAtt = JOptionPane.showInputDialog("Informe o novo nome do cartório: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    enderecoAtt = JOptionPane.showInputDialog("Informe o novo endereço do cartório: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    String telefoneAtt = JOptionPane.showInputDialog("Informe o novo telefone do cartório: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    cepAtt = JOptionPane.showInputDialog("Informe o novo cep da igreja: \n\n(OBS: deixe a caixa vazia e pressione ENTER para pular)");
                    if (!"".equals(nomeAtt) || !"".equals(enderecoAtt) || !"".equals(cepAtt) || !"".equals(telefoneAtt)) {
                        cartoriodao.atualizaCartorio(nomeAtt, enderecoAtt, telefoneAtt, cepAtt);
                        JOptionPane.showMessageDialog(null, "Cartório atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: cartório não editado...");
                    }
                    break;

                case 0:
                    //Voltar
                    menuEventoOpc = -1;
                    break;

                default:
                    menuEventoOpc = -1;
                    break;
            }
        }
    }

    public void controllerInfoEvento(EventoDAO eventodao, CerimonialDAO cerimonialdao) {
        JOptionPane.showMessageDialog(null, eventodao.retornaEvento().eventoInfos(cerimonialdao));
    }
}
