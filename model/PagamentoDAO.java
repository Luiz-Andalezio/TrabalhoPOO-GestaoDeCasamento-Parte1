package model;

import java.time.LocalDateTime;

public class PagamentoDAO {

    Pagamento[] pagamentos = new Pagamento[100];

    public PagamentoDAO(PessoaDAO pessoadao, FornecedorDAO fornecedordao, ParcelaDAO parceladao, LocalDateTime calendario) {
        Pagamento p1 = new Pagamento();
        //p1.setData("01/11/2024");
        //p1.setPessoa(pessoadao.retornaPessoa(1));
        p1.setDescricao("Parcelas de DJ Alok.");
        p1.setFornecedor(fornecedordao.retornaFornecedorVetor(0));
        //p1.setValor(fornecedordao.calculaParcela(2));
        //p1.setEstadoPagamento(false);
        p1.setParcelasVetor(0, parceladao.retornaParcelaVetor(0));
        p1.setParcelasVetor(1, parceladao.retornaParcelaVetor(1));
        p1.setParcelasVetor(2, parceladao.retornaParcelaVetor(2));
        p1.setParcelasVetor(3, parceladao.retornaParcelaVetor(3));
        p1.setDataCriacao(calendario);
        pagamentos[0] = p1;

        Pagamento p2 = new Pagamento();
        p2.setDescricao("Bolo do casamento.");
        p2.setParcelasVetor(0, parceladao.retornaParcelaVetor(4));
        p2.setDataCriacao(calendario);
        pagamentos[1] = p2;
    }

    public Pagamento registraPagamento(Fornecedor fornecedor, String descricao, LocalDateTime calendario) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] == null) {
                pagamentos[i] = new Pagamento();
                pagamentos[i].setFornecedor(fornecedor);
                pagamentos[i].setDescricao(descricao);
                pagamentos[i].setDataCriacao(calendario);
                return pagamentos[i];
            }
        }
        return null;
    }

    public Pagamento registraDescricaoPagamento(String descricao, LocalDateTime calendario) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] == null) {
                pagamentos[i] = new Pagamento();
                pagamentos[i].setDescricao(descricao);
                pagamentos[i].setDataCriacao(calendario);
                return pagamentos[i];
            }
        }
        return null;
    }

    public boolean registraParcela(Pagamento pagamento, ParcelaDAO parceladao, int nmrParcela, String data, Pessoa pessoa, LocalDateTime calendario) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] == pagamento) {
                Parcela parcela = parceladao.registraParcela(calendario, data, pessoa, calculaParcelaByFornecedorDoPagamento(i), nmrParcela);
                pagamentos[i].setParcela(parcela);
                return true;
            }
        }
        return false;
    }

    public boolean registraParcelaSemFornecedor(Pagamento pagamento, ParcelaDAO parceladao, int nmrParcela, int qtdParcelas, String data, double valor, Pessoa pessoa, LocalDateTime calendario) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] == pagamento) {
                Parcela parcela = parceladao.registraParcela(calendario, data, pessoa, calculaValor(valor, qtdParcelas), nmrParcela);
                pagamentos[i].setParcela(parcela);
                return true;
            }
        }
        return false;
    }

    public boolean atualizaFornecedorByPagamento(int id, Fornecedor fornecedor, LocalDateTime calendario) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            pagamentos[i].setFornecedor(fornecedor);
            pagamentos[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public boolean atualizaDescricao(int id, String descricao, LocalDateTime calendario) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            pagamentos[i].setDescricao(descricao);
            pagamentos[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public boolean atualizaParcela(int id, int parcela, String data, Pessoa pessoa, LocalDateTime calendario) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            pagamentos[i].getParcelaByNmr(parcela).setValorDaParcela(calculaParcelaByFornecedorDoPagamento(id));
            pagamentos[i].getParcelaByNmr(parcela).setData(data);
            pagamentos[i].getParcelaByNmr(parcela).setPagante(pessoa);
            pagamentos[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public double calculaParcelaByFornecedorDoPagamento(int i) {
        double valor = pagamentos[i].getFornecedor().getValorAPagar() / pagamentos[i].getFornecedor().getParcelas();
        return valor;
    }

    public double calculaValor(double valor, int qtdParcelas) {
        valor = valor / qtdParcelas;
        return valor;
    }

    public void excluirPagamento(int id) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            pagamentos[i] = null;
        }
    }

    /*
    public boolean verificaEstadoFornecedor(int i, FornecedorDAO fornecedordao, Pagamento pagamento) {
            int parcelasPagas = 0;
            for (int j = 0; j < pagamentos.length; j++) {
                if (pagamentos[j] != null && pagamentos[j].getFornecedor() == pagamentos[i].getFornecedor()) {
                    for (int k = 0; k < 99; k++) {
                        if (pagamentos[j].getParcelaVetor(k) != null && pagamentos[j].getParcelaVetor(k).getEstadoPagamento() == true) {
                            parcelasPagas++;
                        }
                    }
                }
            }
            if (parcelasPagas == pagamentos[i].getFornecedor().getParcelas()) {
                pagamentos[i].getFornecedor().setEstado(true);
            }
        return false;
    }

    public boolean verificaEstadoFornecedor1(int i, FornecedorDAO fornecedordao) {
        int parcelasPagas = 0;
        int j = 0;

        while (j < pagamentos.length) {
            if (pagamentos[j] != null && pagamentos[j].getFornecedor() == pagamentos[i].getFornecedor() /*|| pagamentos[j] == null/) {
                int k = 0;
                while (k < 99) {
                    if (pagamentos[j].getParcelaVetor(k) != null && pagamentos[j].getParcelaVetor(k).getEstadoPagamento() == true) {
                        parcelasPagas++;
                    }
                    k++;
                }
            }
            j++;
        }

        if (parcelasPagas == pagamentos[i].getFornecedor().getParcelas()) {
            pagamentos[i].getFornecedor().setEstado(true);
            return true;
        }
        return false;
    }
    public boolean verificaEstadoFornecedor2(int id, FornecedorDAO fornecedordao, Pagamento pagamento) {
        int parcelasPagas = 0;
        int k = 0;
        pagamento = retornaPagamentoVetor(id);
        while (k < pagamento.getTamanhoVetorParcelas()) {
            if (pagamento.getParcelaVetor(k) != null && pagamento.getParcelaVetor(k).getEstadoPagamento() == true) {
                parcelasPagas++;
            }
            k++;
        }

        if (parcelasPagas == pagamento.getFornecedor().getParcelas()) {
            pagamento.getFornecedor().setEstado(true);
            return true;
        }
        return false;
    }*/

    public boolean verificaEstadoFornecedor() {
        int parcelasPagas = 0;
        int j = 0;

        while (j < pagamentos.length) {
            if (pagamentos[j] != null && pagamentos[j].getFornecedor() != null /*|| pagamentos[j] == null*/) {
                int k = 0;
                while (k < pagamentos[j].getTamanhoVetorParcelas()) {
                    if (pagamentos[j].getParcelaVetor(k) != null && pagamentos[j].getParcelaVetor(k).getEstadoPagamento() == true) {
                        parcelasPagas++;
                    }
                    k++;
                } 
                if (parcelasPagas == pagamentos[j].getFornecedor().getParcelas()) {
                    pagamentos[j].getFornecedor().setEstado(true);
                    return true;
                }
            }
            j++;
        }
        return false;
    }

    public String verPagamentos() {
        String m = "";
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] != null) {
                m += pagamentos[i].toString();
            }
        }
        return m;
    }

    public double calculaTotalPagamentos() {
        double totalPagamentos = 0;
        int i = 0;

        while (i < pagamentos.length) {
            if (pagamentos[i] != null) {
                int k = 0;
                while (k < pagamentos[i].getTamanhoVetorParcelas()) {
                    if (pagamentos[i].getParcelaVetor(k) != null && pagamentos[i].getParcelaVetor(k).getEstadoPagamento() == true) {
                        totalPagamentos = totalPagamentos + pagamentos[i].getParcelaVetor(k).getValorDaParcela();
                    }
                    k++;
                }
            }
            i++;
        }
        return totalPagamentos;
    }

    /*
    public String verPagamentosComParcelas() {
        String m = "";
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] != null) {
                m += pagamentos[i] + "\n";
            }
        }
        return m;
    } */
    public Pagamento retornaPagamentoByID(int id) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            return pagamentos[i];
        }
        return null;
    }

    public Pagamento retornaPagamentoByPagamento(Pagamento pagamento) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i] != pagamento || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i] == pagamento) {
            return pagamentos[i];
        }
        return null;
    }

    public Pagamento retornaPagamentoVetor(int i) {
        return pagamentos[i];
    }
}
