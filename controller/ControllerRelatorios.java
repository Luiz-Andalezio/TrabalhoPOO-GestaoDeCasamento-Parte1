package controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividualDAO;
import model.FornecedorDAO;
import model.MensagensDAO;
import model.PagamentoDAO;
import model.Usuario;
import view.GUI;

public class ControllerRelatorios {

    private static final Locale localeBR = new Locale("pt", "BR");
    private static final NumberFormat formatador = NumberFormat.getCurrencyInstance(localeBR);

    public void controllerRelatorios(GUI gui, Usuario usuarioLogado, FornecedorDAO fornecedorDAO, MensagensDAO mensagensDAO, ConvidadoIndividualDAO conviteIndividualDAO, ConvidadoFamiliaDAO convidadoFamiliaDAO, PagamentoDAO pagamentodao, LocalDateTime calendario) {
        StringBuilder m;
        int menuRelatorioOpc = 0;

        while (menuRelatorioOpc != -1) {
            menuRelatorioOpc = gui.crudRelatorios(usuarioLogado, calendario);
            switch (menuRelatorioOpc) {
                case 1:
                    // Ver recados recebidos
                    String s = mensagensDAO.verMensagens();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID da mensagem que deseja imprimir: \n\n0 - Voltar"));
                        if (id != 0) {
                            JOptionPane.showMessageDialog(null, "Mensagem imprimida com sucesso!\n\n" + mensagensDAO.retornaMensagemByID(id));
                        } else {
                            JOptionPane.showMessageDialog(null, "Impressão não sucedida...");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há mensagens enviadas.");
                    }
                    break;

                case 2:
                    //Imprima um Convite Família ou Convite Fornecedor            
                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do Convite Família ou Convite Fornecedor que deseja imprimir: \n\n0 - Voltar"));
                        if (id != 0) {
                            JOptionPane.showMessageDialog(null, "Convite imprimido com sucesso!\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                        } else {
                            JOptionPane.showMessageDialog(null, "Impressão não sucedida...");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família ou Convites Fornecedor enviados.");
                    }
                    break;

                case 3:
                    //Imprima um Convite Individual             
                    s = conviteIndividualDAO.verConvidados();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do Convite Individual que deseja imprimir: \n\n0 - Voltar"));
                        if (id != 0) {
                            JOptionPane.showMessageDialog(null, "Convite Individual imprimido com sucesso!\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(id));
                        } else {
                            JOptionPane.showMessageDialog(null, "Impressão não sucedida...");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Individuais enviados.");
                    }
                    break;

                case 4:
                    //Exibir o total de pagamentos realizados
                    s = pagamentodao.verPagamentos();
                    if (!"".equals(s)) {
                        m = new StringBuilder();
                        double valorTotal = pagamentodao.calculaTotalPagamentos();
                        if (valorTotal == 0) {
                            m.append("Nenhuma parcela paga: ")
                                    .append(formatador.format(valorTotal))
                                    .append("\n\n")
                                    .append(pagamentodao.verPagamentos());
                        } else {
                            m.append("Valor total de pagamentos realizados: ")
                                    .append(formatador.format(valorTotal))
                                    .append("\n\n")
                                    .append(pagamentodao.verPagamentos());
                        }
                        JOptionPane.showMessageDialog(null, m);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum pagamento registrado.");
                    }
                    break;

                case 5:
                    //Exibir a lista total de convidados

                    /*
                    =>Exiba um relatório formatado com a lista total de convidados confirmados.
                    Some o total de pessoas/pontos.
                    Esta lista pode ser usada na portaria da festa para o controle dos convidados permitidos.
                    Siga os critérios a seguir:
                    1 - Crianças até 8 anos não contam.
                    2 - Crianças de 9 a 13 anos contam como 50% do valor do adulto.
                    3 – Pessoas com 14 anos ou mais contam como adulto.
                    4 - Fornecedores contam como 50% do valor do adulto.
                     */

                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();
                    if (!"".equals(s)) {
                        if (convidadoFamiliaDAO.verificaListaConfirmados() != false) {
                            int i = 0;
                            String lista = "------------------ LISTA TOTAL DE CONVIDADOS CONFIRMADOS ------------------";
                            lista += "\nPontuação total de convidados: " + convidadoFamiliaDAO.listaPontosTotais(calendario) + "\n\n";
                            while (i < convidadoFamiliaDAO.retornaTamanhoVetorConvidadoFamilia()) {
                                if (convidadoFamiliaDAO.retornaConviteFamiliaVetor(i) != null) {
                                    int j = 0;
                                    while (j < convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getTamanhoVetorConvidadoIndividual()) {
                                        if (convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j) != null && convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getConfirmacao() != false) {                                            
                                            int ponto = 10;
                                            if ("Fornecedores".equals(convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getNomeDaFamilia())) {
                                                ponto = ponto / 2;
                                                lista += " - Fornecedor";                                                
                                                lista += "\nNome: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getPessoa().getNome();
                                                lista += "\nPeso de pontuação: " + ponto + "\n\n";
                                            } else {
                                                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                                int idade = Period.between(LocalDate.parse(convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getPessoa().getNascimento(), formatador), calendario.toLocalDate()).getYears();
                                                if (idade <= 8) {
                                                    ponto = 0;
                                                    lista += "Nome: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getPessoa().getNome() + " " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getNomeDaFamilia();
                                                    lista += "\nParentesco: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getParentesco();                                         
                                                    lista += "\nIdade: " + idade; 
                                                    lista += "\nPeso de pontuação: " + ponto + "\n\n";
                                                } else if (idade >= 9 && idade <= 13) {
                                                    ponto = ponto / 2;
                                                    lista += "Nome: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getPessoa().getNome() + " " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getNomeDaFamilia();
                                                    lista += "\nParentesco: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getParentesco();                                         
                                                    lista += "\nIdade: " + idade; 
                                                    lista += "\nPeso de pontuação: " + ponto + "\n\n";
                                                } else if (idade >= 14) {
                                                    lista += "Nome: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getPessoa().getNome() + " " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getNomeDaFamilia();
                                                    lista += "\nParentesco: " + convidadoFamiliaDAO.retornaConviteFamiliaVetor(i).getConvidadoIndividualVetor(j).getParentesco();                                         
                                                    lista += "\nIdade: " + idade; 
                                                    lista += "\nPeso de pontuação: " + ponto + "\n\n";
                                                }
                                            }
                                        }
                                        j++;
                                    }
                                }
                                i++;
                            }
                            lista += "------------------------------------------------------";
                            JOptionPane.showMessageDialog(null, lista);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ainda não há convidados confirmados.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família e Convites Fornecedor enviados.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuRelatorioOpc = -1;
                    break;

                default:
                    menuRelatorioOpc = -1;
                    break;
            }
        }
    }
}