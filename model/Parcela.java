package model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Parcela {
    private long id;    
    private int parcela;
    private LocalDate data;
    private Pessoa pagante;
    private double valorDaParcela;
    private boolean estadoPagamento;    
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

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }
    
    public String getData() {
        String alteraDia = "";
        if (this.data.getDayOfMonth() < 10){
            alteraDia += "0";
        }
        alteraDia += this.data.getDayOfMonth() + "/";
        if (this.data.getMonthValue() < 10){
            alteraDia += "0";
        }
        alteraDia += this.data.getMonthValue() + "/" + this.data.getYear();
        return alteraDia;
    }

    public void setData(String data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if ("".equals(data)) {
            this.data = LocalDate.now();
        } else {
            this.data = LocalDate.parse(data, dtf);
        }
    }

    public void setDataByFunction(String data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if ("".equals(data)) {
            this.data = LocalDate.now();
        } else {
            this.data = LocalDate.parse(data, dtf);
        }
    }

    public Pessoa getPagante() {
        return pagante;
    }

    public void setPagante(Pessoa pessoa) {
        this.pagante = pessoa;
    }

    public double getValorDaParcela() {
        return valorDaParcela;
    }

    public void setValorDaParcela(double valor) {
        this.valorDaParcela = valor;
    }

    public boolean getEstadoPagamento() {
        return this.estadoPagamento;
    }

    public void setEstadoPagamento(boolean estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }
    
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
        this.id = ++Parcela.incrementaId;
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

    public String toStringParcelaUnica() {
        String m = "";
        m += "Data de pagamento: " + this.getData();
        m += "\nPagante: " + this.pagante.getNome();
        if (this.estadoPagamento == true) {
            m += "\nPago!\n";
        } else {
            m += "\nAinda não pago.\n";
        }
        return m;
    }

    @Override
    public String toString() {
        String m = "";
        m += "-- Dados da " + this.getParcela() + "° parcela: --";
        //m += "ID: " + this.id;
        m += "\nData de pagamento: " + this.getData();
        m += "\nPagante: " + this.pagante.getNome();
        m += "\nValor a pagar: " + formatador.format(this.valorDaParcela);
        if (this.estadoPagamento == true) {
            m += "\nPaga!\n";
        } else {
            m += "\nAinda não paga.\n";
        }
        /*m += "\nRegistrada no dia: " + this.getDataCriacao();
        if (this.getDataModificacao() != null) {
            m += " e modificada no dia: " + this.getDataModificacao();
        }*/
        return m;
    }
}
