package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConviteFamilia {

    public void controllerCrudFamilia(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao, Pessoa pessoa, Evento evento, ConvidadoIndividual conviteIndividual, ConvidadoIndividualDAO conviteIndividualDAO, ConvidadoFamilia convidadoFamilia, ConvidadoFamiliaDAO convidadoFamiliaDAO, LocalDateTime calendario) {
        StringBuilder m;
        int menuFamiliaOpc = 0;

        while (menuFamiliaOpc != -1) {
            menuFamiliaOpc = gui.crudConvFamilia(usuarioLogado, calendario);
            switch (menuFamiliaOpc) {
                case 1:
                    //Gerar Convite Família
                    String novoNomeDaFamilia = JOptionPane.showInputDialog("Digite o nome da família a ser convidada: \n\n0 - Voltar");

                    if ("".equals(novoNomeDaFamilia)) {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: Convite Família não gerado...");
                    } else if ("0".equals(novoNomeDaFamilia)) {
                    } else if ("Fornecedores".equals(novoNomeDaFamilia)) {
                        JOptionPane.showMessageDialog(null, "Você não pode colocar este nome em um Convite Família!");
                    } else {
                        m = new StringBuilder("Convite Família gerado para a família: ")
                                .append(novoNomeDaFamilia)
                                .append("\n\n")
                                .append(convidadoFamiliaDAO.convidaFamilia(novoNomeDaFamilia, evento, calendario));
                        JOptionPane.showMessageDialog(null, m);
                    }
                    break;

                case 2:
                    //Gerar novos códigos de acesso para Convites Família
                    String s = convidadoFamiliaDAO.verConvitesFamilia();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família gerados.");
                    } else {
                        m = new StringBuilder("Informe o ID do Convite Família a receber um novo código de acesso: \n\n")
                                .append(convidadoFamiliaDAO.verConvitesFamilia())
                                .append("0 - Voltar");

                        int id = Integer.parseInt(JOptionPane.showInputDialog(m));

                        if (id == 0) {
                            JOptionPane.showMessageDialog(null, "Atualização de código de acesso não sucedida.");
                        } else {
                            if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                m = new StringBuilder("Deseja mesmo atualizar o código de acesso desta família? \n\n")
                                        .append(convidadoFamiliaDAO.retornaConviteByID(id));

                                int veredito = JOptionPane.showConfirmDialog(null, m.append(""), "Confirmar Atualização", JOptionPane.YES_NO_OPTION);

                                if (veredito == JOptionPane.YES_OPTION) {
                                    m = new StringBuilder("Código de acesso da família ")
                                            .append(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())
                                            .append(" atualizado com sucesso!\n\nAntes: \n")
                                            .append(convidadoFamiliaDAO.retornaConviteByID(id).getAcesso());

                                    convidadoFamilia = convidadoFamiliaDAO.retornaConviteByID(id);
                                    convidadoFamiliaDAO.atualizaAcesso(id, evento, convidadoFamilia, calendario);

                                    m.append("\n\nAgora: \n").append(convidadoFamiliaDAO.retornaConviteByID(id).getAcesso());
                                    JOptionPane.showMessageDialog(null, m);
                                } else {
                                    m = new StringBuilder("Adição de pessoas no convite da família ")
                                            .append(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())
                                            .append(" não sucedida.");
                                    JOptionPane.showMessageDialog(null, m);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "ID inserido inválido.");
                            }
                        }
                    }
                    break;

                case 3:
                    //Adicionar pessoas em Convites Família ou Convites Fornecedor
                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família e Convites Fornecedor gerados.");
                    } else {
                        m = new StringBuilder("Informe o ID do Convite Família ou Convite Fornecedor a receber pessoas: \n\n")
                                .append(convidadoFamiliaDAO.verConvitesFamiliaEFornecedor())
                                .append("0 - Voltar");

                        int id = Integer.parseInt(JOptionPane.showInputDialog(m));

                        if (id == 0) {
                            JOptionPane.showMessageDialog(null, "Adição de pessoas não sucedida.");
                        } else {
                            m = new StringBuilder("Deseja mesmo acrescentar Convites Individuais no convite abaixo? \n\n")
                                    .append(convidadoFamiliaDAO.retornaConviteByID(id));

                            int veredito = JOptionPane.showConfirmDialog(null, m.append(""), "Confirmar Adição", JOptionPane.YES_NO_OPTION);

                            if (veredito == JOptionPane.YES_OPTION) {
                                if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                    m = new StringBuilder("Informe o ID do Convite Individual a entrar no convite da família: ")
                                            .append(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())
                                            .append("\n\n-- CONVITES INDIVIDUAIS DE PARENTES --\n\n")
                                            .append(conviteIndividualDAO.verParentesConvidados())
                                            .append("\n0 - Voltar");
                                } else {
                                    m = new StringBuilder("Informe o ID do Convite Individual a entrar no Convite Fornecedor: ")
                                            .append("\n\n-- CONVITES INDIVIDUAIS DE FORNECEDORES --\n\n")
                                            .append(conviteIndividualDAO.verFornecedoresConvidados())
                                            .append("\n0 - Voltar");
                                }

                                int id2 = Integer.parseInt(JOptionPane.showInputDialog(m));

                                if (id2 == 0) {
                                    JOptionPane.showMessageDialog(null, "Operação não sucedida.");
                                } else {
                                    if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia()) && conviteIndividualDAO.retornaConviteIndividualByID(id2).getParentesco() == null) {
                                        JOptionPane.showMessageDialog(null, "ID inserido inválido.");
                                    } else if ("Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia()) && conviteIndividualDAO.retornaConviteIndividualByID(id2).getParentesco() != null) {
                                        JOptionPane.showMessageDialog(null, "ID inserido inválido.");
                                    } else {
                                        if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                            veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo adicionar " + conviteIndividualDAO.retornaConviteIndividualByID(id2).getPessoa().getNome() + " no Convite Família abaixo?\n\n" + convidadoFamiliaDAO.retornaConviteByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                                        } else {
                                            veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo adicionar o fornecedor " + conviteIndividualDAO.retornaConviteIndividualByID(id2).getPessoa().getNome() + " no Convite Fornecedor abaixo?\n\n" + convidadoFamiliaDAO.retornaConviteByID(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                                        }
                                        if (veredito == JOptionPane.YES_OPTION) {
                                            conviteIndividual = conviteIndividualDAO.retornaConviteIndividualByID(id2);
                                            convidadoFamiliaDAO.recebeConviteIndividual(id, conviteIndividual);

                                            if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                                m = new StringBuilder("Convite Individual de: ")
                                                        .append(conviteIndividual.getPessoa().getNome())
                                                        .append(" adicionado ao Convite Família da família: ")
                                                        .append(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())
                                                        .append("\n\nNovo estado do convite: \n\n")
                                                        .append(convidadoFamiliaDAO.retornaConviteByID(id));
                                            } else {
                                                m = new StringBuilder("Convite Individual do fornecedor: ")
                                                        .append(conviteIndividual.getPessoa().getNome())
                                                        .append(" adicionado ao Convite Fornecedor!")
                                                        .append("\n\nNovo estado do convite: \n\n")
                                                        .append(convidadoFamiliaDAO.retornaConviteByID(id));
                                            }

                                            JOptionPane.showMessageDialog(null, m);
                                        } else {
                                            if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                                m = new StringBuilder("Adição de pessoas no convite da família '")
                                                        .append(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())
                                                        .append("' não sucedida.");
                                            } else {
                                                m = new StringBuilder("Adição de pessoas no Convite Fornecedor não sucedida.");
                                            }

                                            JOptionPane.showMessageDialog(null, m);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 4:
                    //Excluir pessoas em Convites Família ou Convites Fornecedor
                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();
                    String s2 = conviteIndividualDAO.verConvidados();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família e Convites Fornecedor gerados.");
                    } else if ("".equals(s2)) {
                        JOptionPane.showMessageDialog(null, "Não há convidados em nenhum dos Convites Família e Convites Fornecedor. Impossível realizar exclusões.\n\n" + convidadoFamiliaDAO.verConvitesFamiliaEFornecedor());
                    } else {
                        int continuarAtt = 0;
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do Convite Família ou Convite Fornecedor a ter Convites Individuais excluídos.\n\n" + s + "\n0 - Voltar"));
                        if (id == 0) {
                        } else if (convidadoFamiliaDAO.retornaConviteByID(id) == null || !convidadoFamiliaDAO.retornaConviteByID(id).getVetorConvidadosIndividuais()) {
                            if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                JOptionPane.showMessageDialog(null, "Não há convidados neste Convite Família para serem excluídos.\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                            } else {
                                JOptionPane.showMessageDialog(null, "Não há convidados neste Convite Fornecedor para serem excluídos.\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                            }
                        } else {
                            while (continuarAtt != -1) {
                                convidadoFamilia = convidadoFamiliaDAO.retornaConviteByID(id);
                                m = new StringBuilder("Deseja continuar para excluir convidados deste Convite Família?\n\n" + convidadoFamilia);
                                int continuar = JOptionPane.showConfirmDialog(null, m.toString(), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                                if (continuar == JOptionPane.YES_OPTION) {
                                    int conviteID = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Convite Individual da pessoa a ser excluída do convite abaixo:\n\n" + convidadoFamilia + "\n\n0 - Voltar"));

                                    if (conviteID == 0) {
                                        JOptionPane.showMessageDialog(null, "Exclusão de convidados não sucedida...");
                                        continuarAtt = -1;
                                    } else if (convidadoFamiliaDAO.retornaConviteIndividualByIDifNotNull(id, conviteID) == null) {
                                        JOptionPane.showMessageDialog(null, "O ID de número " + conviteID + " inserido não corresponde ao de um convite individual deste do convite abaixo:\n\n" + convidadoFamiliaDAO.retornaConviteByID(id) + "Tente outro!");
                                    } else {
                                        int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a pessoa abaixo?\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(conviteID), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                                        if (veredito == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "Convidado abaixo excluído com sucesso!\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(conviteID));
                                            convidadoFamiliaDAO.excluirPessoaConviteFamilia(id, conviteID);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Exclusão não realizada...");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Exclusão de convidados não continuada...");
                                    continuarAtt = -1;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Exclusões finalizadas!\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                        }
                    }
                    break;

                case 5:
                    //Editar Convites Família
                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();

                    if ("".equals(s)) {
                        s = "Ainda não há Convites Família e Convites Fornecedor gerados.";
                        JOptionPane.showMessageDialog(null, s);
                    } else {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do Convite Família ou Convite Fornecedor a ser atualizado.\n\n" + s + "\n0 - Voltar"));

                        if (id == 0) {
                        } else {
                            if (!"Fornecedores".equals(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())) {
                                JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                m = new StringBuilder("Insira o novo nome a ser atualizado para a família ")
                                        .append(convidadoFamiliaDAO.retornaConviteByID(id).getNomeDaFamilia())
                                        .append(" de ID ")
                                        .append(convidadoFamiliaDAO.retornaConviteByID(id).getId())
                                        .append(":\n\n0 - Voltar");

                                String nomeDaFamiliaAtt = JOptionPane.showInputDialog(m);

                                if (!"".equals(nomeDaFamiliaAtt)) {
                                    convidadoFamiliaDAO.atualizaNomeConviteFamilia(id, nomeDaFamiliaAtt, calendario);
                                    JOptionPane.showMessageDialog(null, "Nome da família atualizado com sucesso!\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                } else {
                                    JOptionPane.showMessageDialog(null, "Atualização do nome da família não realizada.");
                                }

                                s2 = conviteIndividualDAO.verConvidados();
                                if ("".equals(s2)) {
                                    s2 = "Não há convidados em nenhum dos Convites Família. Impossível realizar edições.\n\n" + convidadoFamiliaDAO.verConvitesFamilia();
                                    JOptionPane.showMessageDialog(null, s2);
                                } else if (!convidadoFamiliaDAO.retornaConviteByID(id).getVetorConvidadosIndividuais()) {
                                    JOptionPane.showMessageDialog(null, "Não há convidados neste Convite Família para serem atualizados.\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                } else {
                                    int edicoes = 0;
                                    int continuarAtt = 0;
                                    while (continuarAtt != -1) {
                                        m = new StringBuilder("Deseja continuar para atualizar os convidados deste Convite Família?\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                        int continuar = JOptionPane.showConfirmDialog(null, m, "Confirmar Atualização", JOptionPane.YES_NO_OPTION);

                                        if (continuar == JOptionPane.YES_OPTION) {
                                            int conviteID = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do convite individual da pessoa a ser atualizada deste Convite Família:\n\n" + convidadoFamiliaDAO.retornaConviteByID(id) + "\n\n0 - Voltar"));

                                            if (conviteID == 0) {
                                                JOptionPane.showMessageDialog(null, "Edição de convidados não sucedida...");
                                                continuarAtt = -1;
                                            } else if (convidadoFamiliaDAO.retornaConviteIndividualByIDifNotNull(id, conviteID) == null) {
                                                JOptionPane.showMessageDialog(null, "O ID de número " + conviteID + " inserido não corresponde ao de um convite individual deste Convite Família:\n\n" + convidadoFamiliaDAO.retornaConviteByID(id) + "Tente outro!");
                                            } else {
                                                int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a pessoa abaixo?\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(conviteID), "Confirmar Edição", JOptionPane.YES_NO_OPTION);

                                                if (veredito == JOptionPane.YES_OPTION) {
                                                    String nomeAtt = JOptionPane.showInputDialog("Informe o nome a ser atualizado: ");
                                                    String parentescoAtt = JOptionPane.showInputDialog("\nInforme o parentesco a ser atualizado: ");
                                                    String telefoneAtt = JOptionPane.showInputDialog("\nInforme o telefone a ser atualizado: ");
                                                    String nascimentoAtt = JOptionPane.showInputDialog("\nInforme a data de nascimento a ser atualizada: ");

                                                    if (!"".equals(nomeAtt) || !"".equals(parentescoAtt) || !"".equals(telefoneAtt) || !"".equals(nascimentoAtt)) {
                                                        convidadoFamiliaDAO.atualizaPessoasConviteFamilia(id, conviteID, nomeAtt, telefoneAtt, nascimentoAtt, parentescoAtt, calendario);
                                                        JOptionPane.showMessageDialog(null, "Convidado atualizado com sucesso!\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(conviteID));
                                                        edicoes++;
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Edição do convite de " + conviteIndividualDAO.retornaConviteIndividualByID(conviteID).getPessoa().getNome() + " não sucedida...");
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Edição de convidados não continuada...");
                                            continuarAtt = -1;
                                        }
                                    }
                                    if (edicoes != 0 && "".equals(nomeDaFamiliaAtt)) {
                                        JOptionPane.showMessageDialog(null, "Edições finalizadas!\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                    } else {

                                    }
                                }
                            } else {
                                s2 = conviteIndividualDAO.verFornecedoresConvidados();
                                if ("".equals(s2)) {
                                    s2 = "Não há convidados em nenhum dos Convites Fornecedor. Impossível realizar edições.\n\n" + convidadoFamiliaDAO.verConvitesFamilia();
                                    JOptionPane.showMessageDialog(null, s2);
                                } else if (!convidadoFamiliaDAO.retornaConviteByID(id).getVetorConvidadosIndividuais()) {
                                    JOptionPane.showMessageDialog(null, "Não há convidados neste Convite Fornecedor para serem atualizados.\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                } else {
                                    int edicoes = 0;
                                    int continuarAtt = 0;
                                    while (continuarAtt != -1) {
                                        m = new StringBuilder("Deseja continuar para atualizar os convidados deste Convite Fornecedor?\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                        int continuar = JOptionPane.showConfirmDialog(null, m, "Confirmar Atualização", JOptionPane.YES_NO_OPTION);

                                        if (continuar == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione ENTER.");
                                            int conviteID = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do convite individual do fornecedor a ser atualizado deste Convite Fornecedor:\n\n" + convidadoFamiliaDAO.retornaConviteByID(id) + "\n\n0 - Voltar"));

                                            if (conviteID == 0) {
                                                JOptionPane.showMessageDialog(null, "Edição de convidados não sucedida...");
                                                continuarAtt = -1;
                                            } else if (convidadoFamiliaDAO.retornaConviteIndividualByIDifNotNull(id, conviteID) == null) {
                                                JOptionPane.showMessageDialog(null, "O ID de número " + conviteID + " inserido não corresponde ao de um convite individual deste Convite Família:\n\n" + convidadoFamiliaDAO.retornaConviteByID(id) + "Tente outro!");
                                            } else {
                                                int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar o fornecedor abaixo?\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(conviteID), "Confirmar Edição", JOptionPane.YES_NO_OPTION);

                                                if (veredito == JOptionPane.YES_OPTION) {
                                                    String nomeAtt = JOptionPane.showInputDialog("Informe o nome a ser atualizado: ");
                                                    String telefoneAtt = JOptionPane.showInputDialog("\nInforme o telefone a ser atualizado: ");

                                                    if (!"".equals(nomeAtt) || !"".equals(telefoneAtt)) {
                                                        convidadoFamiliaDAO.atualizaFornecedorConviteFamilia(id, conviteID, nomeAtt, telefoneAtt, calendario);
                                                        JOptionPane.showMessageDialog(null, "Fornecedor convidado atualizado com sucesso!\n\n" + conviteIndividualDAO.retornaConviteIndividualByID(conviteID));
                                                        edicoes++;
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Edição do convite de " + conviteIndividualDAO.retornaConviteIndividualByID(conviteID).getPessoa().getNome() + " não sucedida...");
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Edição de fornecedores convidados não continuada...");
                                            continuarAtt = -1;
                                        }
                                    }
                                    if (edicoes != 0) {
                                        JOptionPane.showMessageDialog(null, "Edições finalizadas!\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                    } else {

                                    }
                                }
                            }
                        }
                    }
                    break;

                case 6:
                    //Mostrar Convites Família e Convites Fornecedor
                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();
                    if ("".equals(s)) {
                        s += "Ainda não há Convites Família e Convites Fornecedor feitos.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 7:
                    //Excluir Convites Família ou Convites Fornecedor
                    s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família ou Convites Fornecedor gerados.");
                    } else {
                        m = new StringBuilder("Insira o ID do Convite Família ou Convite Fornecedor que deseja excluir:\n\n").append(s).append("0 - Voltar");
                        int id = Integer.parseInt(JOptionPane.showInputDialog(m));

                        if (id == 0) {
                        } else {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo desfazer o convite abaixo?\n\n" + convidadoFamiliaDAO.retornaConviteByID(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Convite abaixo desfeito com sucesso!\n\n" + convidadoFamiliaDAO.retornaConviteByID(id));
                                convidadoFamiliaDAO.desfazerConviteFamilia(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não continuada...");
                            }
                        }
                    }
                    break;

                case 0:
                    //Voltar
                    menuFamiliaOpc = -1;
                    break;

                default:
                    menuFamiliaOpc = -1;
                    break;
            }
        }
    }

    public void controllerVerConvitesFamiliaEFornecedor(ConvidadoFamiliaDAO convidadoFamiliaDAO) {
        String s = convidadoFamiliaDAO.verConvitesFamiliaEFornecedor();
        if ("".equals(s)) {
            JOptionPane.showMessageDialog(null, "Ainda não há Convites Família e Convites Fornecedor feitos.");
        } else {
            JOptionPane.showMessageDialog(null, s);
        }
    }

    public void controllerConfirmarFamiliares(GUI gui, ConvidadoIndividual conviteIndividual, ConvidadoIndividualDAO conviteIndividualDAO, ConvidadoFamilia convidadoFamilia, ConvidadoFamiliaDAO convidadoFamiliaDAO, LocalDateTime calendario) {
        String s = convidadoFamiliaDAO.verConvitesFamilia();
        if ("".equals(s)) {
            JOptionPane.showMessageDialog(null, "Ainda não há Convites Família feitos.");
        } else {
            convidadoFamilia = gui.loginConviteFamilia(convidadoFamiliaDAO);
            int parar = 0;

            while (parar != -1) {
                int conviteID = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do convidado que deseja informar a presença:\n\n " + convidadoFamiliaDAO.retornaConviteFamiliaByFamilia(convidadoFamilia) + "\n0 - Voltar"));
                if (conviteID == 0) {
                    parar = -1;
                } else if (convidadoFamiliaDAO.retornaConviteIndividualByIDifNotNull(convidadoFamilia, conviteID) == null) {
                    JOptionPane.showMessageDialog(null, "O ID de número " + conviteID + " inserido não corresponde ao de um convite individual deste Convite Família:\n\n" + convidadoFamiliaDAO.retornaConviteFamiliaByFamilia(convidadoFamilia) + "Tente outro!");
                } else {
                    StringBuilder m;
                    int veredito = JOptionPane.showConfirmDialog(null, "Deseja confirmar a presença da pessoa abaixo?\n\n" + convidadoFamilia.getConviteIndividualByID(conviteID), "Confirmar Presença", JOptionPane.YES_NO_OPTION);
                    if (veredito == JOptionPane.YES_OPTION) {
                        boolean registro = true;
                        convidadoFamiliaDAO.registrarPresenca(convidadoFamilia, conviteID, registro, calendario);
                        m = new StringBuilder("Presença de ")
                                .append(conviteIndividualDAO.retornaConviteIndividualByID(conviteID).getPessoa().getNome())
                                .append(" da família ")
                                .append(convidadoFamiliaDAO.retornaConviteFamiliaByFamilia(convidadoFamilia).getNomeDaFamilia())
                                .append(" confirmada!\n\nNos vemos no casamento!");
                        JOptionPane.showMessageDialog(null, m);
                    } else {
                        m = new StringBuilder("Presença de ")
                                .append(conviteIndividualDAO.retornaConviteIndividualByID(conviteID).getPessoa().getNome())
                                .append(" da família ")
                                .append(convidadoFamiliaDAO.retornaConviteFamiliaByFamilia(convidadoFamilia).getNomeDaFamilia())
                                .append(" não confirmada!\n\nA presença pode ser confirmada novamente a qualquer momento!");
                        JOptionPane.showMessageDialog(null, m);
                    }
                }
            }
        }
    }
}
