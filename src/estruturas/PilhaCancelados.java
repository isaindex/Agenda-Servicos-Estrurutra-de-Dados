package estruturas;

import modelo.Agendamento;

public class PilhaCancelados {
    private NoAgendamento topo;
    private int tamanho;

    public void inserir(Agendamento agendamento) {
        NoAgendamento novo = new NoAgendamento(agendamento);
        novo.setProximo(topo);
        topo = novo;
        tamanho++;
    }

    public Agendamento remover() {
        if (estaVazia()) {
            return null;
        }
        Agendamento removido = topo.getAgendamento();
        topo = topo.getProximo();
        tamanho--;
        return removido;
    }

    public Agendamento verTopo() {
        return estaVazia() ? null : topo.getAgendamento();
    }

    public Agendamento buscar(int id) {
        NoAgendamento atual = topo;
        while (atual != null) {
            if (atual.getAgendamento().getId() == id) {
                return atual.getAgendamento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public String listar() {
        if (estaVazia()) {
            return "Nenhum servico cancelado.";
        }
        StringBuilder sb = new StringBuilder();
        NoAgendamento atual = topo;
        boolean primeiro = true;
        while (atual != null) {
            sb.append(primeiro ? "TOPO -> " : "        ").append(atual.getAgendamento()).append('\n');
            primeiro = false;
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
