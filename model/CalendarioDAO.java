package model;

import java.time.LocalDateTime;

public class CalendarioDAO {

    public CalendarioDAO() {

    }

    public LocalDateTime adicionaDia(LocalDateTime calendario, int dias) {
        calendario = calendario.plusDays(dias);
        return calendario;
    }

    public LocalDateTime adicionaMeses(LocalDateTime calendario, int meses) {
        calendario = calendario.plusMonths(meses);
        return calendario;
    }

    public LocalDateTime adicionaAnos(LocalDateTime calendario, int anos) {
        calendario = calendario.plusYears(anos);
        return calendario;
    }
}
