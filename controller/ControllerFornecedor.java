package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.Fornecedor;
import model.FornecedorDAO;
import model.Usuario;
import view.GUI;

public class ControllerFornecedor {

    public void controllerCrudFornecedores(GUI gui, Usuario usuarioLogado, Fornecedor fornecedor, FornecedorDAO fornecedordao, LocalDateTime calendario) {
        StringBuilder m;
        int menuFornecedorOpc = 0;

        while (menuFornecedorOpc != -1) {
            menuFornecedorOpc = gui.crudFornecedorAdmin(usuarioLogado, calendario);
            switch (menuFornecedorOpc) {
                case 1:
                    //Gerar fornecedor
                    String nome = JOptionPane.showInputDialog("Informe o nome do novo fornecedor: ");
                    String cnpj = JOptionPane.showInputDialog("\nInforme o cnpj do fornecedor: " + nome);
                    String telefone = JOptionPane.showInputDialog("\nInforme o telefone do fornecedor: " + nome);
                    double valorAPagar = Integer.parseInt(JOptionPane.showInputDialog("\nInforme o valor a pagar do fornecedor: " + nome));
                    int parcelas = Integer.parseInt(JOptionPane.showInputDialog("\nInforme a parcela para o valor de R$ " + valorAPagar + " do fornecedor: " + nome));

                    if (!"".equals(nome) || !"".equals(cnpj) || !"".equals(telefone)) {
                        fornecedordao.registraFornecedor(nome, cnpj, telefone, valorAPagar, parcelas, calendario);
                        JOptionPane.showMessageDialog(null, fornecedordao.verFornecedores());
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: fornecedor não criado...");
                    }
                    break;

                case 2:
                    //Editar fornecedores
                    String s = fornecedordao.verFornecedores();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do fornecedor que deseja editar: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o fornecedor abaixo?\n\n" + fornecedordao.recebeFornecedorByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                String nomeAtt = JOptionPane.showInputDialog("Informe o novo nome a ser atualizado do fornecedor: " + fornecedordao.recebeFornecedorByID(id).getNome());
                                if (!"".equals(nomeAtt)) {
                                    JOptionPane.showMessageDialog(null, "Nome atualizado parcialmente até o fim dos procedimentos.\n\nAntes: " + fornecedordao.recebeFornecedorByID(id).getNome() + "\n\nDepois: " + nomeAtt);
                                }
                                String cnpjAtt = JOptionPane.showInputDialog("\nInforme o novo cnpj a ser atualizado do fornecedor: " + nomeAtt);
                                String telefoneAtt = JOptionPane.showInputDialog("\nInforme o novo telefone a ser atualizado do fornecedor: " + nomeAtt);
                                double valorAPagarAtt = Integer.parseInt(JOptionPane.showInputDialog("\nInforme o novo valor a ser atualizado do fornecedor: " + nomeAtt));
                                int parcelasAtt = Integer.parseInt(JOptionPane.showInputDialog("\nInforme a nova parcela a ser atualizada do fornecedor: " + nomeAtt));

                                if (!"".equals(nomeAtt) || !"".equals(cnpjAtt) || !"".equals(telefoneAtt)) {
                                    m = new StringBuilder("Fornecedor atualizado com sucesso!\n\nAntes: \n" + fornecedordao.recebeFornecedorByID(id));
                                    fornecedordao.atualizaFornecedor(nomeAtt, cnpjAtt, telefoneAtt, valorAPagarAtt, parcelasAtt, id, calendario);
                                    m.append("\nAgora: \n").append(fornecedordao.recebeFornecedorByID(id));
                                    JOptionPane.showMessageDialog(null, m);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há fornecedors cadastrados.");
                    }
                    break;

                case 3:
                    //Exibir fornecedores
                    s = fornecedordao.verFornecedores();
                    if ("".equals(s)) {
                        s = "Ainda não há fornecedores registrados.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 4:
                    //Excluir fornecedores
                    s = fornecedordao.verFornecedores();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do fornecedor a ser excluído: \n\n0 - Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este fornecedor abaixo?\n\n" + fornecedordao.recebeFornecedorByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso!\n\n" + fornecedordao.recebeFornecedorByID(id));
                                fornecedordao.excluirFornecedor(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há fornecedores cadastrados.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuFornecedorOpc = -1;
                    break;

                default:
                    menuFornecedorOpc = -1;
                    break;
            }
        }
    }
}
