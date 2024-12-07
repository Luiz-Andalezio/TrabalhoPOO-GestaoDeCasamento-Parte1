package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.CalendarioDAO;
import model.FornecedorDAO;
import model.PagamentoDAO;
import model.ParcelaDAO;
import model.Usuario;
import view.GUI;

public class ControllerCalendario {

    public LocalDateTime controllerCrudIncrementaData(GUI gui, Usuario usuarioLogado, FornecedorDAO fornecedordao, PagamentoDAO pagamentodao, CalendarioDAO calendarioDAO, ParcelaDAO parceladao, LocalDateTime calendario) {
        int menuCalendarioOpc = 0;

        while (menuCalendarioOpc != -1) {
            menuCalendarioOpc = gui.crudData(usuarioLogado, calendario);
            switch (menuCalendarioOpc) {
                case 1:
                    int dias = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de dias que deseja incrementar: \n\n0 - Voltar."));
                    if (0 == dias) {

                    } else {
                        calendario = calendarioDAO.adicionaDia(calendario, dias);
                        parceladao.verificaDataPagamento(calendario);                        
                        pagamentodao.verificaEstadoFornecedor();
                    }
                    break;

                case 2:
                    int meses = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de meses que deseja incrementar: \n\n0 - Voltar."));
                    if (0 == meses) {

                    } else {
                        calendario = calendarioDAO.adicionaMeses(calendario, meses);
                        parceladao.verificaDataPagamento(calendario);                        
                        pagamentodao.verificaEstadoFornecedor();
                    }
                    break;

                case 3:
                    int anos = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de anos que deseja incrementar: \n\n0 - Voltar."));
                    if (0 == anos) {

                    } else {
                        calendario = calendarioDAO.adicionaAnos(calendario, anos);
                        parceladao.verificaDataPagamento(calendario);                        
                        pagamentodao.verificaEstadoFornecedor();
                    }
                    break;

                case 0:
                    menuCalendarioOpc = -1;
                    break;

                default:
                    menuCalendarioOpc = -1;
                    break;
            }
        }
        return calendario;
    }
}
