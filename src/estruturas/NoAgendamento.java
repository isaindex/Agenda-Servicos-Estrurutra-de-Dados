package estruturas;

import modelo.Agendamento;

public class NoAgendamento {
    private final Agendamento agendamento;
    private NoAgendamento proximo;

    public NoAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public NoAgendamento getProximo() {
        return proximo;
    }

    public void setProximo(NoAgendamento proximo) {
        this.proximo = proximo;
    }
}
