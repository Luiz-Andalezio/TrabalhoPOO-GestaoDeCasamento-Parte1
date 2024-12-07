package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParcelaDAO {

    Parcela[] parcelas = new Parcela[100];

    public ParcelaDAO(PessoaDAO pessoadao, FornecedorDAO fornecedordao, LocalDateTime calendario) {
        Parcela p1 = new Parcela();
        p1.setParcela(1);
        p1.setData("01/11/2024");
        p1.setPagante(pessoadao.retornaPessoa(0));
        p1.setValorDaParcela(calculaValor(1, fornecedordao));
        p1.setEstadoPagamento(verificaDataPagamentoConstrutor(calendario));
        p1.setDataCriacao(calendario);
        parcelas[0] = p1;

        Parcela p2 = new Parcela();
        p2.setParcela(2);
        p2.setData("01/12/2024");
        p2.setPagante(pessoadao.retornaPessoa(1));        
        p2.setValorDaParcela(calculaValor(1, fornecedordao));
        p2.setEstadoPagamento(verificaDataPagamentoConstrutor(calendario));
        p2.setDataCriacao(calendario);
        parcelas[1] = p2;

        Parcela p3 = new Parcela();        
        p3.setParcela(3);
        p3.setData("01/01/2025");
        p3.setPagante(pessoadao.retornaPessoa(0));        
        p3.setValorDaParcela(calculaValor(1, fornecedordao));
        p3.setEstadoPagamento(verificaDataPagamentoConstrutor(calendario));
        p3.setDataCriacao(calendario);
        parcelas[2] = p3;

        Parcela p4 = new Parcela();        
        p4.setParcela(4);
        p4.setData("01/02/2025");
        p4.setPagante(pessoadao.retornaPessoa(1));        
        p4.setValorDaParcela(calculaValor(1, fornecedordao));
        p4.setEstadoPagamento(verificaDataPagamentoConstrutor(calendario));
        p4.setDataCriacao(calendario);
        parcelas[3] = p4;

        Parcela p5 = new Parcela();        
        p5.setParcela(1);
        p5.setData("31/10/2024");
        p5.setPagante(pessoadao.retornaPessoa(1));        
        p5.setValorDaParcela(350);
        p5.setEstadoPagamento(verificaDataPagamentoConstrutor(calendario));
        p5.setDataCriacao(calendario);
        parcelas[4] = p5;
    }

    private double calculaValor(int id, FornecedorDAO fornecedordao) {
        double valorDaParcela = fornecedordao.recebeFornecedorByID(id).getValorAPagar() / fornecedordao.recebeFornecedorByID(id).getParcelas();
        return valorDaParcela;
    }

    public Parcela registraParcela(LocalDateTime calendario, String data, Pessoa pessoa, double valor, int parcela) {
        for (int i = 0; i < parcelas.length; i++) {
            if (parcelas[i] == null) {
                parcelas[i] = new Parcela();
                parcelas[i].setData(data);
                parcelas[i].setPagante(pessoa);
                parcelas[i].setValorDaParcela(valor);
                parcelas[i].setParcela(parcela);
                parcelas[i].setEstadoPagamento(verificaDataPagamento(calendario));
                parcelas[i].setDataCriacao(calendario);
                return parcelas[i];
            }
        }
        return null;
    }

    /*
    public boolean verificaDataPagamento(LocalDateTime calendario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < parcelas.length; i++) {
            if (parcelas[i] != null) {
                LocalDate dataParcela = LocalDate.parse(parcelas[i].getData(), formatter);
                if (calendario.toLocalDate().isAfter(dataParcela)) {
                    parcelas[i].setEstadoPagamento(true);
                    return true;
                } else {
                    parcelas[i].setEstadoPagamento(false);
                    return false;
                }
            }
        }
        return false;
    }*/

    public boolean verificaDataPagamento(LocalDateTime calendario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int i = 0;
    
        while (i < parcelas.length) {
            if (parcelas[i] != null) {
                LocalDate dataParcela = LocalDate.parse(parcelas[i].getData(), formatter);
                if (calendario.toLocalDate().isAfter(dataParcela) || calendario.toLocalDate() == dataParcela) {
                    parcelas[i].setEstadoPagamento(true);
                } else {
                    parcelas[i].setEstadoPagamento(false);
                }
            }
            i++;
        }
    
        return false;
    }    

    private boolean verificaDataPagamentoConstrutor(LocalDateTime calendario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int i = 0;
    
        while (i < parcelas.length) {
            if (parcelas[i] != null) {
                LocalDate dataParcela = LocalDate.parse(parcelas[i].getData(), formatter);
                if (calendario.toLocalDate().isAfter(dataParcela) || calendario.toLocalDate() == dataParcela) {
                    return true;
                } else {
                    return false;
                }
            }
            i++;
        }
    
        return false;
    }   

    public Parcela retornaParcelaVetor(int i) {
        return parcelas[i];
    }
}
