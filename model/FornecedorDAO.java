package model;

import java.time.LocalDateTime;

public class FornecedorDAO {

    private Fornecedor[] fornecedores = new Fornecedor[100];

    public FornecedorDAO(/*PagamentoDAO pagamentodao,*/ LocalDateTime calendario) {
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setNome("DJ Alok");
        fornecedor1.setCnpj("12.345.678/0001-99");
        fornecedor1.setTelefone("(11) 99735-4261");
        fornecedor1.setValorAPagar(680000.00); 
        fornecedor1.setParcelas(4); 
        fornecedor1.setEstado(false); 
        fornecedor1.setDataCriacao(calendario); 

        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setNome("Três Marias: Decoração Elegante");
        fornecedor2.setCnpj("98.765.432/0001-88");
        fornecedor2.setTelefone("(11) 9876-5432");
        fornecedor2.setValorAPagar(8000.00);
        fornecedor2.setParcelas(2);
        fornecedor2.setEstado(false);
        fornecedor2.setDataCriacao(calendario);

        Fornecedor fornecedor3 = new Fornecedor();
        fornecedor3.setNome("Buffet Margaux");
        fornecedor3.setCnpj("22.746.258/0001-12");
        fornecedor3.setTelefone("(21) 9876-5432");
        fornecedor3.setValorAPagar(20000.00);
        fornecedor3.setParcelas(2);
        fornecedor3.setEstado(false);
        fornecedor3.setDataCriacao(calendario);

        fornecedores[0] = fornecedor1;
        fornecedores[1] = fornecedor2;
        fornecedores[2] = fornecedor3;
    }     

    public double calculaParcela(int i) {
        double valorDaParcela = fornecedores[i].getValorAPagar() / fornecedores[i].getParcelas();
        return valorDaParcela;
    }

    public boolean registraFornecedor(String nome, String cnpj, String telefone, double valorAPagar, int parcelas, LocalDateTime calendario) {
        for (int i = 0; i < fornecedores.length; i++) {
            if (fornecedores[i] == null) {
                fornecedores[i] = new Fornecedor();
                fornecedores[i].setNome(nome);
                fornecedores[i].setCnpj(cnpj);
                fornecedores[i].setTelefone(telefone);
                fornecedores[i].setValorAPagar(valorAPagar);
                fornecedores[i].setParcelas(parcelas);
                fornecedores[i].setDataCriacao(calendario);
                return true;
            }
        }
        return false;
    }

    public boolean atualizaFornecedor(String nome, String cnpj, String telefone, double valorAPagar, int parcelas, int id, LocalDateTime calendario) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            if (!nome.equals("")) {
                fornecedores[i].setNome(nome);
            }
            if (!cnpj.equals("")) {
                fornecedores[i].setCnpj(cnpj);
            }
            if (!telefone.equals("")) {
                fornecedores[i].setTelefone(telefone);
            }
            fornecedores[i].setValorAPagar(valorAPagar);
            fornecedores[i].setParcelas(parcelas);
            fornecedores[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public void excluirFornecedor(int id) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            fornecedores[i] = null;
        }
    }

    public boolean pagarFornecedor(int id, boolean registro, LocalDateTime calendario) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            if (registro != false) {
                fornecedores[i].setEstado(registro);
                fornecedores[i].setDataModificacao(calendario);
            }
            return true;
        }
        return false;
    }

    public String verFornecedores() {
        String m = "";
        for (int i = 0; i < fornecedores.length; i++) {
            if (fornecedores[i] != null) {
                m += fornecedores[i].toString();
            }
        }
        return m;
    }

    public Fornecedor recebeFornecedorByID(int id) {
        int i = 0;
        while (fornecedores[i] != null && fornecedores[i].getId() != id || fornecedores[i] == null) {
            i++;
        }

        if (fornecedores[i] != null && fornecedores[i].getId() == id) {
            return fornecedores[i];
        }
        return null;
    }

    public Fornecedor retornaFornecedorVetor(int i) {
        return fornecedores[i];
    }
}
