package model;

import java.time.LocalDateTime;

public class EventoDAO {
    Evento[] eventos = new Evento[100];

    public EventoDAO(PessoaDAO pessoadao, IgrejaDAO igrejadao, CartorioDAO cartoriodao, LocalDateTime calendario) {
        Evento e = new Evento();
        e.setId(1);
        e.setData("25/12/2024 18:30");
        //e1.setCerimonial();
        e.setIgreja(igrejadao.retornaIgreja());
        e.setCartorio(cartoriodao.retornaCartorio());
        e.setPessoaNoivo(pessoadao.retornaPessoa(0));
        e.setPessoaNoiva(pessoadao.retornaPessoa(1));
        e.setDataCriacao(calendario);
        eventos[0] = e;
        }  
        
    public boolean atualizaNomeNoivo(String novoNomeNoivo, LocalDateTime calendario) {
        eventos[0].getPessoaNoivo().setNome(novoNomeNoivo);
        eventos[0].setDataModificacao(calendario);
        return true;
    }

    public boolean atualizaNomeNoiva(String novoNomeNoiva, LocalDateTime calendario) {
        eventos[0].getPessoaNoiva().setNome(novoNomeNoiva);
        eventos[0].setDataModificacao(calendario);
        return true;
    }

    public boolean atualizaDataEvento(String dataAtt, LocalDateTime calendario) {
        eventos[0].setData(dataAtt);
        eventos[0].setDataModificacao(calendario);
        return true;
    }

    public Evento retornaEvento(){
        return eventos[0];
    }
}
