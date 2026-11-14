package modelo;

public class Agendamento {
    private final int id;
    private final Cliente cliente;
    private final String servico;
    private final String horario;
    private String motivoCancelamento;

    public Agendamento(int id, Cliente cliente, String servico, String horario) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getServico() {
        return servico;
    }

    public String getHorario() {
        return horario;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    @Override
    public String toString() {
        String texto = String.format("#%d | %s | %s | Cliente: %s (ID %d)",
                id, horario, servico, cliente.getNome(), cliente.getId());
        if (motivoCancelamento != null) {
            texto += " | Motivo: " + motivoCancelamento;
        }
        return texto;
    }
}
