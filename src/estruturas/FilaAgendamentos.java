package estruturas;

import modelo.Agendamento;

public class FilaAgendamentos {
    private NoAgendamento inicio;
    private NoAgendamento fim;
    private int tamanho;

    public void inserir(Agendamento agendamento) {
        NoAgendamento novo = new NoAgendamento(agendamento);
        if (estaVazia()) {
            inicio = novo;
        } else {
            fim.setProximo(novo);
        }
        fim = novo;
        tamanho++;
    }

    public Agendamento remover() {
        if (estaVazia()) {
            return null;
        }
        Agendamento removido = inicio.getAgendamento();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return removido;
    }

    public Agendamento removerPorId(int id) {
        NoAgendamento anterior = null;
        NoAgendamento atual = inicio;
        while (atual != null && atual.getAgendamento().getId() != id) {
            anterior = atual;
            atual = atual.getProximo();
        }
        if (atual == null) {
            return null;
        }
        if (anterior == null) {
            inicio = atual.getProximo();
        } else {
            anterior.setProximo(atual.getProximo());
        }
        if (atual == fim) {
            fim = anterior;
        }
        tamanho--;
        return atual.getAgendamento();
    }

    public Agendamento buscar(int id) {
        NoAgendamento atual = inicio;
        while (atual != null) {
            if (atual.getAgendamento().getId() == id) {
                return atual.getAgendamento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public boolean contemCliente(int idCliente) {
        NoAgendamento atual = inicio;
        while (atual != null) {
            if (atual.getAgendamento().getCliente().getId() == idCliente) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public Agendamento primeiro() {
        return estaVazia() ? null : inicio.getAgendamento();
    }

    public Agendamento ultimo() {
        return estaVazia() ? null : fim.getAgendamento();
    }

    public String listar() {
        if (estaVazia()) {
            return "Nenhum agendamento na fila.";
        }
        StringBuilder sb = new StringBuilder();
        NoAgendamento atual = inicio;
        int posicao = 1;
        while (atual != null) {
            sb.append(posicao).append(". ").append(atual.getAgendamento()).append('\n');
            atual = atual.getProximo();
            posicao++;
        }
        return sb.toString();
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
