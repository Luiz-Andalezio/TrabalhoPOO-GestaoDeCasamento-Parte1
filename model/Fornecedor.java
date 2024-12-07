package model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

public class Fornecedor {

    private long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private double valorAPagar;
    private int parcelas;
    private boolean estado;
    //private Pagamento[] pagamentos = new Pagamento[100];
    private String dataCriacao;
    private String dataModificacao;     
    private static long incrementaId = 0;   
    //final assegura que essa referência não será alterada durante a execução do programa
    private static final Locale localeBR = new Locale("pt", "BR");
    private static final NumberFormat formatador = NumberFormat.getCurrencyInstance(localeBR);

    //GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }   

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    /* 
    public Pagamento getPagamento(int id) {
        return pagamentos[id - 1];
    }

    public Pagamento getPagamentosVetor(int i) {
        return pagamentos[i];
    }

    public Pagamento getPagamentoByID(int id) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            return pagamentos[i];
        }
        return null;
    }

    public boolean getVetorPagamentos() {
        int existe = 0;
        for (int i = 0; i < pagamentos.length; ++i) {
            if (pagamentos[i] != null) {
                ++existe;
            }
        }
        if (existe != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setPagamento(int id, Pagamento pagamento) {
        this.pagamentos[id - 1] = pagamento;
    }

    public void setPagamentoByID(int id, Pagamento pagamento) {
        int i = 0;
        while (pagamentos[i] != null && pagamentos[i].getId() != id || pagamentos[i] == null) {
            i++;
        }

        if (pagamentos[i] != null && pagamentos[i].getId() == id) {
            this.pagamentos[i] = pagamento;
        }
    }

    public void setPagamentosVetor(int i, Pagamento pagamento) {
        this.pagamentos[i] = pagamento;
    }*/

    public String getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime calendario) {
        String concatenaDataHorario = "";

        if (calendario != null) {
            if (calendario.getDayOfMonth() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getDayOfMonth() + "/";

            if (calendario.getMonthValue() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getMonthValue() + "/";

            concatenaDataHorario += calendario.getYear() + " ";

            LocalDateTime horarioAtualizado = calendario.with(LocalTime.now());

            if (horarioAtualizado.getHour() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getHour() + ":";

            if (horarioAtualizado.getMinute() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getMinute() + ":";

            if (horarioAtualizado.getSecond() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getSecond();
        }        
        this.dataCriacao = concatenaDataHorario;
        this.id = ++Fornecedor.incrementaId;
    }

    public String getDataModificacao() {
        return this.dataModificacao;
    }

    public void setDataModificacao(LocalDateTime calendario) {
        String concatenaDataHorario = "";

        if (calendario != null) {
            if (calendario.getDayOfMonth() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getDayOfMonth() + "/";

            if (calendario.getMonthValue() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getMonthValue() + "/";

            concatenaDataHorario += calendario.getYear() + " ";

            LocalDateTime horarioAtualizado = calendario.with(LocalTime.now());

            if (horarioAtualizado.getHour() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getHour() + ":";

            if (horarioAtualizado.getMinute() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getMinute() + ":";

            if (horarioAtualizado.getSecond() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getSecond();
        }

        this.dataModificacao = concatenaDataHorario;
    }

    @Override
    public String toString() {
        String m = "";
        m += "--------------- Fornecedor de ID: " + this.id + " ---------------";
        m += "\nFornecedor: " + this.nome;
        m += "\nCnpj: " + this.cnpj;
        m += "\nTelefone: " + this.telefone;
        m += "\nValor: " + formatador.format(this.valorAPagar);
        m += "\nParcelas: " + this.parcelas;
        if (this.estado == false) {
            m += "\nNão pago!\n";
        } else {
            m += "\nPago!\n";
        }
            m += "\nRegistrado no dia: " + this.getDataCriacao();
            if (this.getDataModificacao() != null) {
                m += " e modificado no dia: " + this.getDataModificacao();
            }            
        m += "\n------------------------------------------------------------------\n\n";
        return m;
    }
}
