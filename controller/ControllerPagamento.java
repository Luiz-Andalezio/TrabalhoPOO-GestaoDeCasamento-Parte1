package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.EventoDAO;
import model.Fornecedor;
import model.FornecedorDAO;
import model.Pagamento;
import model.PagamentoDAO;
import model.ParcelaDAO;
import model.Usuario;
import view.GUI;

public class ControllerPagamento {

    public void controllerCrudPagamentos(GUI gui, Usuario usuarioLogado, EventoDAO eventodao, FornecedorDAO fornecedordao, Pagamento pagamento, PagamentoDAO pagamentodao, ParcelaDAO parceladao, LocalDateTime calendario) {
        String s = fornecedordao.verFornecedores();
        if ("".equals(s)) {
            JOptionPane.showMessageDialog(null, "Ainda não há fornecedores registrados.");
        } else {
            StringBuilder m;
            int menuPagamentoOpc = 0;

            while (menuPagamentoOpc != -1) {
                menuPagamentoOpc = gui.crudPagamento(usuarioLogado, calendario);
                switch (menuPagamentoOpc) {
                    case 1:
                        //Gerar pagamento
                        int opc = Integer.parseInt(JOptionPane.showInputDialog("\nEscolha o tipo de pagamento que deseja registrar: \n\n1- Pagamento à um serviço qualquer.\n2- Pagamento à um fornecedor registrado.\n\n0 - Voltar"));
                        if (opc == 0) {

                        }
                        //Pagamento à um serviço qualquer
                        if (opc == 1) {
                            String descricao = JOptionPane.showInputDialog("Informe a descrição do novo pagamento: ");
                            pagamento = pagamentodao.registraDescricaoPagamento(descricao, calendario);
                            int opcParcela = Integer.parseInt(JOptionPane.showInputDialog("\nInsira quantas parcelas o pagamento terá: \n\n(OBS: Inserir '1' significa que o pagamento será à vista!)"));
                            //Pagamento à vista
                            if (opcParcela == 1) {
                                int parcela = 1;
                                String data = JOptionPane.showInputDialog("\nInsira a data de pagamento: ");
                                double valor = Integer.parseInt(JOptionPane.showInputDialog("\nInsira o valor do pagamento à vista:"));
                                pagamentodao.registraParcelaSemFornecedor(pagamento, parceladao, parcela, opcParcela, data, valor, eventodao.retornaEvento().getPessoaNoivo(), calendario);
                            }
                            //Pagamento parcelado
                            if (opcParcela == 2) {
                                int parcelas = opcParcela;
                                double valor = Integer.parseInt(JOptionPane.showInputDialog("\nInsira o valor total do pagamento que será dividido em " + parcelas + " parcelas: "));

                                int parcela = 1;
                                while (parcela <= parcelas) {
                                    String data = JOptionPane.showInputDialog("Informe a data da " + parcela + "° parcela: ");
                                    int selecionaPagante = 0;
                                    while (selecionaPagante < 1 || selecionaPagante > 2 || selecionaPagante == 0) {
                                        selecionaPagante = Integer.parseInt(JOptionPane.showInputDialog("Informe quem você deseja ser o pagante da " + parcela + "° parcela\n\n" + "1- Noivo: " + eventodao.retornaEvento().getPessoaNoivo().getNome() + "\n2- Noiva: " + eventodao.retornaEvento().getPessoaNoiva().getNome()));
                                        if (selecionaPagante == 1) {
                                            JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoivo().getNome() + ", o noivo, foi selecionado como o pagante desta " + parcela + "° parcela.");
                                            pagamentodao.registraParcelaSemFornecedor(pagamento, parceladao, parcela, parcelas, data, valor, eventodao.retornaEvento().getPessoaNoivo(), calendario);
                                        }
                                        if (selecionaPagante == 2) {
                                            JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoiva().getNome() + ", a noiva, foi selecionado como o pagante desta " + parcela + "° parcela.");
                                            pagamentodao.registraParcelaSemFornecedor(pagamento, parceladao, parcela, parcelas, data, valor, eventodao.retornaEvento().getPessoaNoiva(), calendario);
                                        }
                                    }
                                    parcela++;
                                }
                            }
                            parceladao.verificaDataPagamento(calendario);
                            pagamentodao.verificaEstadoFornecedor();
                            JOptionPane.showMessageDialog(null, "Pagamento registrado com sucesso!\n\n" + pagamentodao.retornaPagamentoByPagamento(pagamento));
                        }
                        //Pagamento à um fornecedor
                        if (opc == 2) {
                            int id = Integer.parseInt(JOptionPane.showInputDialog("\nInforme o ID do fornecedor que deseja registrar o pagamento: \n\n" + fornecedordao.verFornecedores()));
                            if (id != 0) {
                                Fornecedor fornecedor = fornecedordao.recebeFornecedorByID(id);
                                String descricao = JOptionPane.showInputDialog("Informe a descrição do novo pagamento: ");

                                pagamento = pagamentodao.registraPagamento(fornecedor, descricao, calendario);

                                int parcela = 1;
                                JOptionPane.showMessageDialog(null, "O fornecedor registrado neste pagamento possui " + fornecedor.getParcelas() + " para registrar");

                                while (parcela <= fornecedor.getParcelas()) {
                                    String data = JOptionPane.showInputDialog("Informe a data desta " + parcela + "° parcela: ");
                                    int selecionaPagante = 0;
                                    while (selecionaPagante < 1 || selecionaPagante > 2 || selecionaPagante == 0) {
                                        selecionaPagante = Integer.parseInt(JOptionPane.showInputDialog("Informe quem você deseja ser o pagante da " + parcela + "° parcela\n\n" + "1- Noivo: " + eventodao.retornaEvento().getPessoaNoivo().getNome() + "\n2- Noiva: " + eventodao.retornaEvento().getPessoaNoiva().getNome()));
                                        if (selecionaPagante == 1) {
                                            JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoivo().getNome() + ", o noivo, foi selecionado como o pagante desta " + parcela + "° parcela.");
                                            pagamentodao.registraParcela(pagamento, parceladao, parcela, data, eventodao.retornaEvento().getPessoaNoivo(), calendario);
                                        }
                                        if (selecionaPagante == 2) {
                                            JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoiva().getNome() + ", a noiva, foi selecionado como o pagante desta " + parcela + "° parcela.");
                                            pagamentodao.registraParcela(pagamento, parceladao, parcela, data, eventodao.retornaEvento().getPessoaNoiva(), calendario);
                                        }
                                    }
                                    parcela++;
                                }
                                JOptionPane.showMessageDialog(null, "Pagamento registrado com sucesso!\n\n" + pagamentodao.retornaPagamentoByPagamento(pagamento));
                            }
                        }
                        break;

                    case 2:
                        //Editar pagamentos
                        s = pagamentodao.verPagamentos();
                        if (!"".equals(s)) {                                       
                            int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do pagamento que deseja editar: \n\n0 - Voltar"));
                            if (id != 0) {
                                int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o pagamento abaixo?\n\n" + pagamentodao.retornaPagamentoByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                                if (veredito == JOptionPane.YES_OPTION) {
                                    JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                    if (pagamentodao.retornaPagamentoByID(id).getFornecedor() != null) {
                                        veredito = JOptionPane.showConfirmDialog(null, "Deseja alterar o fornecedor do o pagamento abaixo?\n\n" + pagamentodao.retornaPagamentoByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                                        if (veredito == JOptionPane.YES_OPTION) {
                                            m = new StringBuilder("\nInforme o ID do fornecedor que deseja para mudar para o pagamento: \n\n")
                                                    .append(fornecedordao.verFornecedores())
                                                    .append("\n0 - Manter fonecedor do pagamento atual: ")
                                                    .append(pagamentodao.retornaPagamentoByID(id).getFornecedor().getNome())
                                                    .append("\n");
                                            int idF = Integer.parseInt(JOptionPane.showInputDialog(m));
                                            if (idF == 0) {
                                                JOptionPane.showMessageDialog(null, "Fornecedor do pagamento abaixo mantido:\n\n " + pagamentodao.retornaPagamentoByID(id));
                                            } else {
                                                Fornecedor fornecedorAtt = fornecedordao.recebeFornecedorByID(idF);
                                                pagamentodao.atualizaFornecedorByPagamento(id, fornecedorAtt, calendario);
                                                JOptionPane.showMessageDialog(null, "Fornecedor do pagamento abaixo atualizado com sucesso!\n\n " + pagamentodao.retornaPagamentoByID(id));
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Fornecedor do pagamento abaixo mantido:\n\n " + pagamentodao.retornaPagamentoByID(id));
                                        }
                                    }

                                    String descricaoAtt = JOptionPane.showInputDialog("Informe a nova descricao a ser atualizada: ");
                                    if (!"".equals(descricaoAtt)) {
                                        pagamentodao.atualizaDescricao(id, descricaoAtt, calendario);
                                        m = new StringBuilder("\nPagamento parcialmente atualizado: \n").append(pagamentodao.retornaPagamentoByID(id));
                                        JOptionPane.showMessageDialog(null, m);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Nenhuma descricao enviadas...");
                                    }

                                    if (pagamentodao.retornaPagamentoByID(id).getQtdParcelas() != 1) {
                                        veredito = JOptionPane.showConfirmDialog(null, "Deseja editar as parcelas do pagamento abaixo?\n\n" + pagamentodao.retornaPagamentoByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                                        if (veredito == JOptionPane.YES_OPTION) {
                                            int continua = 0;
                                            while (continua != -1) {
                                                int nmrParcela = Integer.parseInt(JOptionPane.showInputDialog("\nInforme o número da parcela que deseja editar do pagamento abaixo: \n\n" + pagamentodao.retornaPagamentoByID(id).getParcelas() + "\n\n0 - Voltar"));
                                                if (nmrParcela == 0) {
                                                    continua = -1;
                                                } else {
                                                    String dataAtt = JOptionPane.showInputDialog("Digite a data que deseja atualizar da parcela abaixo: \n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela));
                                                    int i = 0;
                                                    while (i < 1 || i > 2 || i == 0) {
                                                        i = Integer.parseInt(JOptionPane.showInputDialog("Informe quem você deseja ser o pagante da parcela abaixo: \n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela) + "\n1- Noivo: " + eventodao.retornaEvento().getPessoaNoivo().getNome() + "\n2- Noiva: " + eventodao.retornaEvento().getPessoaNoiva().getNome()));
                                                        if (i == 1) {
                                                            JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoivo().getNome() + ", o noivo, foi selecionado como o pagante da parcela abaixo:\n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela));
                                                            pagamentodao.atualizaParcela(id, nmrParcela, dataAtt, eventodao.retornaEvento().getPessoaNoivo(), calendario);
                                                        }
                                                        if (i == 2) {
                                                            JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoiva().getNome() + ", a noiva, foi selecionado como o pagante da parcela abaixo:\n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela));
                                                            pagamentodao.atualizaParcela(id, nmrParcela, dataAtt, eventodao.retornaEvento().getPessoaNoiva(), calendario);
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Edições finalizadas no pagamento abaixo!\n\n" + pagamentodao.retornaPagamentoByID(id));
                                        }
                                    } else {
                                        int nmrParcela = 1;
                                        String dataAtt = JOptionPane.showInputDialog("Digite a data que deseja atualizar da parcela abaixo: \n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela));

                                        int i = 0;
                                        while (i < 1 || i > 2 || i == 0) {
                                            i = Integer.parseInt(JOptionPane.showInputDialog("Informe quem você deseja ser o pagante da parcela abaixo: \n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela) + "\n1- Noivo: " + eventodao.retornaEvento().getPessoaNoivo().getNome() + "\n2- Noiva: " + eventodao.retornaEvento().getPessoaNoiva().getNome()));
                                            if (i == 1) {
                                                JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoivo().getNome() + ", o noivo, foi selecionado como o pagante da parcela abaixo:\n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela));
                                                pagamentodao.atualizaParcela(id, nmrParcela, dataAtt, eventodao.retornaEvento().getPessoaNoivo(), calendario);
                                            }
                                            if (i == 2) {
                                                JOptionPane.showMessageDialog(null, eventodao.retornaEvento().getPessoaNoiva().getNome() + ", a noiva, foi selecionado como o pagante da parcela abaixo:\n\n" + pagamentodao.retornaPagamentoByID(id).getParcelaByNmr(nmrParcela));
                                                pagamentodao.atualizaParcela(id, nmrParcela, dataAtt, eventodao.retornaEvento().getPessoaNoiva(), calendario);
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ainda não há pagamentos registrados.");
                        }
                        break;

                    case 3:
                        //Exibir pagamentos        
                        s = pagamentodao.verPagamentos();
                        if ("".equals(s)) {
                            JOptionPane.showMessageDialog(null, "Ainda não há pagamentos registrados.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Data de hoje: " + calendarioFormatado(calendario) + "\n\n" + pagamentodao.verPagamentos());
                        }
                        break;
                        /*
                        s = pagamentodao.verPagamentos();
                        if ("".equals(s)) {
                            JOptionPane.showMessageDialog(null, "Ainda não há pagamentos registrados.");
                        } else {
                            parceladao.verificaDataPagamento(calendario);
                            pagamentodao.verificaEstadoFornecedor(fornecedordao);

                            JTextArea areaPagamentos = new JTextArea(15, 30);
                            areaPagamentos.setText("Data de hoje: " + calendario + "\n\n" + s);
                            areaPagamentos.setEditable(false);

                            JScrollPane scrollPane = new JScrollPane(areaPagamentos);
                            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                            JOptionPane.showMessageDialog(null, scrollPane, "Lista de Pagamentos", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                        */

                    case 4:
                        //Desfazer pagamentos
                        s = pagamentodao.verPagamentos();
                        if (!"".equals(s)) {                            
                            int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do pagamento a ser excluído: \n\n0 - Voltar"));
                            if (id != 0) {
                                int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este pagamento abaixo?\n\n" + pagamentodao.retornaPagamentoByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                                if (veredito == JOptionPane.YES_OPTION) {
                                    JOptionPane.showMessageDialog(null, "Pagamento excluido com sucesso!\n\n" + pagamentodao.retornaPagamentoByID(id));
                                    pagamentodao.excluirPagamento(id);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Ainda não há pagamentos cadastrados.");
                        }
                        break;

                    case 0:
                        //Voltar
                        menuPagamentoOpc = -1;
                        break;

                    default:
                        menuPagamentoOpc = -1;
                        break;
                }
            }
        }
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
