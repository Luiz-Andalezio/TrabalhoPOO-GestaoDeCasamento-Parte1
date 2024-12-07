package model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

public class Pagamento {

    private long id;
    //private String data;
    //private Pessoa pessoa;
    private String descricao;
    private Fornecedor fornecedor;
    //private double valor;
    private Parcela[] parcelas = new Parcela[100];
    //private int parcela;
    //private boolean estadoPagamento;    
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

    /* 
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }*/
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /* 
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public boolean getEstadoPagamento() {
        return this.estadoPagamento;
    }

    public void setEstadoPagamento(boolean estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }*/
    public Parcela getParcelaVetor(int i) {
        return parcelas[i];
    }

    public Parcela getParcelaByID(int id) {
        int i = 0;
        while (parcelas[i] != null && parcelas[i].getId() != id || parcelas[i] == null) {
            i++;
        }

        if (parcelas[i] != null && parcelas[i].getId() == id) {
            return parcelas[i];
        }
        return null;
    }

    public Parcela getParcelaByNmr(int parcela) {
        int i = 0;
        while (parcelas[i] != null && parcelas[i].getParcela() != parcela || parcelas[i] == null) {
            i++;
        }

        if (parcelas[i] != null && parcelas[i].getParcela() == parcela) {
            return parcelas[i];
        }
        return null;
    }

    public String getParcelas() {
        String m = "";
        for (int i = 0; i < parcelas.length; i++) {
            if (parcelas[i] != null) {
                m += parcelas[i].toString() + "\n";
            }
        }
        return m;
    }

    public int getQtdParcelas() {
        int existe = 0;
        for (int i = 0; i < parcelas.length; ++i) {
            if (parcelas[i] != null) {
                ++existe;
            }
        }
        return existe;
    }

    public int getTamanhoVetorParcelas() {
        int i;
        for (i = 0; i < parcelas.length; ++i) {
        }
        return i;
    }

    public boolean getVetorParcelas() {
        int existe = 0;
        for (int i = 0; i < parcelas.length; ++i) {
            if (parcelas[i] != null) {
                ++existe;
            }
        }
        if (existe != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean setParcela(Parcela parcela) {
        for (int i = 0; i < parcelas.length; i++) {
            if (parcelas[i] == null) {
                this.parcelas[i] = parcela;
                return true;
            }
        }
        return false;
    }

    public boolean setParcelaByID(int id, Parcela parcela) {
        int i = 0;
        while (parcelas[i] != null && parcelas[i].getId() != id || parcelas[i] == null) {
            i++;
        }

        if (parcelas[i] != null && parcelas[i].getId() == id) {
            this.parcelas[i] = parcela;
            return true;
        }
        return false;
    }

    public void setParcelasVetor(int i, Parcela parcela) {
        this.parcelas[i] = parcela;
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
        this.id = ++Pagamento.incrementaId;
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

    /* 
    @Override
    public String toString() {
        String m = "";
        m += "--------------- Fornecedor de Código: " + this.id + " ---------------";
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
        m += "\n------------------------------------------------------------------\n";
        return m;
    }*/
    @Override
    public String toString() {
        String m = "";
        m += "--------------- Pagamento de ID: " + this.id + " ---------------";

        m += "\nDescrição: " + this.descricao;

        if (this.fornecedor != null) {
            m += "\n\nDados do Fornecedor:";
            m += "\nNome: " + this.fornecedor.getNome();
            m += "\nCnpj: " + this.fornecedor.getCnpj();
            m += "\nTelefone: " + this.fornecedor.getTelefone();
            m += "\nValor total: " + formatador.format(this.fornecedor.getValorAPagar());
            m += "\nEstado: ";
            if (this.fornecedor.getEstado() == true) {
                m += "Todas as parcelas pagas!";
            } else {
                m += "Faltam parcelas à pagar.";
            }

            int existe = 0;
            for (int i = 0; i < parcelas.length; ++i) {
                if (parcelas[i] != null) {
                    ++existe;
                }
            }
            if (existe == 0) {
                m += "\n\nAinda não há parcelas.\n\n";
            }
            if (existe == 1) {
                for (int i = 0; i < parcelas.length; ++i) {
                    if (parcelas[i] != null) {
                        m += parcelas[i].toStringParcelaUnica();
                        m += "\n";
                    }
                }
            }
            if (existe > 1) {
                m += "\n\nParcelas:\n";
                for (int i = 0; i < parcelas.length; ++i) {
                    if (parcelas[i] != null) {
                        m += parcelas[i].toString();
                        m += "\n";
                    }
                }
            }
        } else {
            double valorTotal = 0;
            for (int i = 0; i < parcelas.length; ++i) {
                if (parcelas[i] != null) {
                    valorTotal = valorTotal + parcelas[i].getValorDaParcela();
                }
            }
            m += "\nValor total: " + formatador.format(valorTotal) + "\n";
            int existe = 0;
            for (int i = 0; i < parcelas.length; ++i) {
                if (parcelas[i] != null) {
                    ++existe;
                }
            }
            if (existe == 0) {
                m += "\nAinda não há parcelas.\n\n";
            }
            if (existe == 1) {
                for (int i = 0; i < parcelas.length; ++i) {
                    if (parcelas[i] != null) {
                        m += parcelas[i].toStringParcelaUnica();
                        m += "\n";
                    }
                }
            }
            if (existe > 1) {
                m += "\nParcelas:\n";
                for (int i = 0; i < parcelas.length; ++i) {
                    if (parcelas[i] != null) {
                        m += parcelas[i].toString();
                        m += "\n";
                    }
                }
            }
        }
        m += "Registrado no dia: " + this.getDataCriacao();
        if (this.getDataModificacao() != null) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n------------------------------------------------------------------\n\n";
        return m;
    }
}
