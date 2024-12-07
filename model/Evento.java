package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    private long id;
    private String data;
    private Igreja igreja;
    private Cartorio cartorio;
    private Pessoa pessoaNoivo;
    private Pessoa pessoaNoiva;
    private String dataCriacao;
    private String dataModificacao;
    private static long incrementaId = 0;

    //GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {        
        if(data == null){
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } else {
            this.data = data;
        }
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public Cartorio getCartorio() {
        return cartorio;
    }

    public void setCartorio(Cartorio cartorio) {
        this.cartorio = cartorio;
    }

    public Pessoa getPessoaNoivo() {
        return pessoaNoivo;
    }

    public void setPessoaNoivo(Pessoa pessoaNoivo) {
        this.pessoaNoivo = pessoaNoivo;
    }

    public Pessoa getPessoaNoiva() {
        return pessoaNoiva;
    }

    public void setPessoaNoiva(Pessoa pessoaNoiva) {
        this.pessoaNoiva = pessoaNoiva;
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
        this.id = ++Evento.incrementaId;
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

    public String eventoInfos(CerimonialDAO cerimonialDAO) {
        String m = "";

        m += "-------------------------- Informações do Casamento de " + this.pessoaNoivo.getNome() + " e " + this.pessoaNoiva.getNome() + " --------------------------";
        m += "\n\nData e Horário: " + this.getData();
        m += "\n\n--- Cerimoniais ---\n";
        m += cerimonialDAO.verCerimoniais();
        m += "--- Informações da igreja do evento ---";
        m += "\nNome: " + this.igreja.getNome();
        m += "\nEndereço: " + this.igreja.getEndereco();
        m += "\nCEP: " + this.igreja.getCEP();
        m += "\n\n--- Informações do cartório responsável ---";
        m += "\nNome: " + this.cartorio.getNome();
        m += "\nEndereço: " + this.cartorio.getEndereco();
        m += "\nCEP: " + this.cartorio.getCEP();
        m += "\nTelefone: " + this.cartorio.getTelefone();        
        m += "\n--------------------------------------------------------------------------------------------------------------------";

        return m;
    }
    
    @Override
    public String toString(){
        String m = "";

        m += "---------- Boas Vindas ao Casamento de " + this.pessoaNoivo.getNome() + " e " + this.pessoaNoiva.getNome() + " ----------";
        m += "\n------------------------   Data: " + this.getData() + "   -----------------------";

        return m;
    }
}
