package controller;

import java.time.LocalDateTime;
import model.CalendarioDAO;
import model.CartorioDAO;
import model.CerimonialDAO;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.EventoDAO;
import model.Fornecedor;
import model.FornecedorDAO;
import model.IgrejaDAO;
import model.Mensagens;
import model.MensagensDAO;
import model.Pagamento;
import model.PagamentoDAO;
import model.ParcelaDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Presentes;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class MainController {

    //Calendário
    LocalDateTime calendario = LocalDateTime.of(2024, 10, 20, 04, 20, 07);
    CalendarioDAO calendarioDAO = new CalendarioDAO();

    //controller
    ControllerCalendario cc = new ControllerCalendario();
    ControllerConviteFamilia ccf = new ControllerConviteFamilia();
    ControllerConvidadoIndividual cci = new ControllerConvidadoIndividual();
    ControllerPresentes cp = new ControllerPresentes();
    ControllerMensagens cm = new ControllerMensagens();
    ControllerUsuario cu = new ControllerUsuario();
    ControllerFornecedor cf = new ControllerFornecedor();
    ControllerPagamento cpm = new ControllerPagamento();
    ControllerRelatorios cr = new ControllerRelatorios();
    ControllerEvento ce = new ControllerEvento();

    //model
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoadao = new PessoaDAO(calendario);
    UsuarioDAO usuariodao = new UsuarioDAO(pessoadao, calendario);
    Usuario usuarioLogado = null;
    CerimonialDAO cerimonialdao = new CerimonialDAO(calendario);
    IgrejaDAO igrejadao = new IgrejaDAO();
    CartorioDAO cartoriodao = new CartorioDAO();
    EventoDAO eventodao = new EventoDAO(pessoadao, igrejadao, cartoriodao, calendario);
    Evento evento = eventodao.retornaEvento();
    Fornecedor fornecedor = new Fornecedor();
    FornecedorDAO fornecedordao = new FornecedorDAO(calendario);
    ParcelaDAO parceladao = new ParcelaDAO(pessoadao, fornecedordao, calendario);
    Pagamento pagamento = new Pagamento();
    PagamentoDAO pagamentodao = new PagamentoDAO(pessoadao, fornecedordao, parceladao, calendario);
    Mensagens mensagens = new Mensagens();
    MensagensDAO mensagensdao = new MensagensDAO(calendario);
    Presentes presentes = new Presentes();
    PresentesDAO presentesdao = new PresentesDAO(calendario);
    ConvidadoIndividual conviteindividual = new ConvidadoIndividual();
    ConvidadoIndividualDAO conviteindividualdao = new ConvidadoIndividualDAO(pessoadao, calendario);
    ConvidadoFamilia convitefamilia = new ConvidadoFamilia();
    ConvidadoFamiliaDAO convitefamiliadao = new ConvidadoFamiliaDAO(conviteindividualdao, evento, calendario);

    //view
    GUI gui = new GUI();

    //Main
    public MainController() {   mainOpc = gui.menuBoasVindas(evento);

            switch (mainOpc) {
                //Entrar como Administrador
                case 1:
                    usuarioLogado = gui.login(usuariodao);

                    int opc = 0;
                    while (opc != -1) {
                        //Login Noiva
                        if (usuarioLogado.getTipo().equals("Noivo") || usuarioLogado.getTipo().equals("Noiva")) {
                            opc = gui.menuNoivos(usuarioLogado, calendario);
                            switch (opc) {
                                case 1:
                                    //ce = Controller Evento
                                    ce.controllerCrudEvento(gui, usuarioLogado, eventodao, cerimonialdao, igrejadao, cartoriodao, calendario);
                                    break;

                                case 2:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, pessoa, evento, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao, calendario);
                                    break;

                                case 3:
                                    //cci = Controller Convidado Individual
                                    cci.controllerCrudConvidado(gui, usuarioLogado, pessoadao, pessoa, conviteindividualdao, calendario);
                                    break;

                                case 4:
                                    //cp = Controller Presentes
                                    cp.controllerVerPresentesNoivos(gui, usuarioLogado, presentesdao, calendario);
                                    break;

                                case 5:
                                    //cm = Controller Mensagens
                                    cm.controllerVerMensagens(mensagensdao);
                                    break;

                                case 6:
                                    //cpm = Controller Pagamentos
                                    cpm.controllerCrudPagamentos(gui, usuarioLogado, eventodao, fornecedordao, pagamento, pagamentodao, parceladao, calendario);
                                    break;

                                case 7:
                                    //cc = Controller Calendario
                                    this.calendario = cc.controllerCrudIncrementaData(gui, usuarioLogado, fornecedordao, pagamentodao, calendarioDAO, parceladao, calendario);
                                    break;

                                case 8:
                                    //cr = Controller Relatorios
                                    cr.controllerRelatorios(gui, usuarioLogado, fornecedordao, mensagensdao, conviteindividualdao, convitefamiliadao, pagamentodao, calendario);
                                    break;

                                case 0:
                                    //Voltar
                                    opc = -1;
                                    break;

                                default:
                                    opc = 0;
                                    break;
                            }
                        }
                        //Login Administrador
                        if (usuarioLogado.getTipo().equals("Admin")) {
                            opc = gui.menuAdmin(usuarioLogado, calendario);
                            switch (opc) {
                                case 1:
                                    //ce = Controller Evento
                                    ce.controllerCrudEvento(gui, usuarioLogado, eventodao, cerimonialdao, igrejadao, cartoriodao, calendario);
                                    break;

                                case 2:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, pessoa, evento, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao, calendario);
                                    break;

                                case 3:
                                    //cci = Controller Convidado Individual
                                    cci.controllerCrudConvidado(gui, usuarioLogado, pessoadao, pessoa, conviteindividualdao, calendario);
                                    break;

                                case 4:
                                    //cu = Controller Usuarios
                                    cu.controllerCrudUsuarios(gui, usuarioLogado, usuariodao, pessoadao, pessoa, calendario);
                                    break;

                                case 5:
                                    //cp = Controller Presentes
                                    cp.controllerCrudPresentes(gui, usuarioLogado, presentes, presentesdao, calendario);
                                    break;

                                case 6:
                                    //cm = Controller Mensagens
                                    cm.controllerCrudMensagens(gui, usuarioLogado, usuariodao, pessoadao, pessoa, evento, mensagens, mensagensdao, calendario);
                                    break;

                                case 7:
                                    //cf = Controller Fornecedores
                                    cf.controllerCrudFornecedores(gui, usuarioLogado, fornecedor, fornecedordao, calendario);
                                    break;

                                case 8:
                                    //cpm = Controller Pagamentos
                                    cpm.controllerCrudPagamentos(gui, usuarioLogado, eventodao, fornecedordao, pagamento, pagamentodao, parceladao, calendario);
                                    break;

                                case 9:
                                    //cc = Controller Calendario
                                    this.calendario = cc.controllerCrudIncrementaData(gui, usuarioLogado, fornecedordao, pagamentodao, calendarioDAO, parceladao, calendario);
                                    break;

                                case 10:
                                    //cr = Controller Relatorios
                                    cr.controllerRelatorios(gui, usuarioLogado, fornecedordao, mensagensdao, conviteindividualdao, convitefamiliadao, pagamentodao, calendario);
                                    break;

                                case 0:
                                    //Voltar
                                    opc = -1;
                                    break;

                                default:
                                    opc = 0;
                                    break;
                            }
                        }
                        //Login Cerimonial
                        if (usuarioLogado.getTipo().equals("Cerimonial")) {
                            opc = gui.menuCerimonial(usuarioLogado, calendario);
                            switch (opc) {
                                case 1:
                                    //Ver Convites Família
                                    ccf.controllerVerConvitesFamiliaEFornecedor(convitefamiliadao);
                                    break;

                                case 2:
                                    //cm = Controller Mensagens
                                    cm.controllerEnviarMensagens(evento, mensagens, mensagensdao, calendario);
                                    break;

                                case 3:
                                    //cp = Controller Presentes
                                    cp.controllerDarPresentes(gui, presentesdao, calendario);
                                    break;

                                case 4:
                                    //cr = Controller Relatorios
                                    cr.controllerRelatorios(gui, usuarioLogado, fornecedordao, mensagensdao, conviteindividualdao, convitefamiliadao, pagamentodao, calendario);
                                    break;

                                case 0:
                                    //Voltar
                                    opc = -1;
                                    break;

                                default:
                                    opc = 0;
                                    break;
                            }
                        }
                    }
                    break;

                case 2:
                    //Entrar como Convidado
                    opc = 0;
                    while (opc != -1) {
                        opc = gui.menuConvidado();
                        switch (opc) {
                            case 1:
                                //cm = Controller Mensagens
                                cm.controllerEnviarMensagens(evento, mensagens, mensagensdao, calendario);
                                break;

                            case 2:
                                //cp = Controller Presentes
                                cp.controllerDarPresentes(gui, presentesdao, calendario);
                                break;

                            case 3:
                                //ccf = Controller Convite Familia
                                ccf.controllerConfirmarFamiliares(gui, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao, calendario);
                                break;

                            case 0:
                                //Voltar
                                opc = -1;
                                break;

                            default:
                                opc = 0;
                                break;
                        }
                    }
                    break;

                //Informações do Evento
                case 3:
                    //ce = Controller Evento
                    ce.controllerInfoEvento(eventodao, cerimonialdao);
                    break;

                //Sair
                case 0:
                    mainOpc = -1;
                    break;

                default:
                    mainOpc = -1;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new MainController();
    }
}
