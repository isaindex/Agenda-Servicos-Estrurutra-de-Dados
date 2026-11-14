package estruturas;

import modelo.Cliente;

public class ArvoreClientes {
    private NoCliente raiz;
    private int tamanho;

    public boolean inserir(Cliente cliente) {
        if (buscar(cliente.getId()) != null) {
            return false;
        }
        raiz = inserir(raiz, cliente);
        tamanho++;
        return true;
    }

    private NoCliente inserir(NoCliente no, Cliente cliente) {
        if (no == null) {
            return new NoCliente(cliente);
        }
        if (cliente.getId() < no.getCliente().getId()) {
            no.setEsquerda(inserir(no.getEsquerda(), cliente));
        } else {
            no.setDireita(inserir(no.getDireita(), cliente));
        }
        return no;
    }

    public Cliente buscar(int id) {
        NoCliente atual = raiz;
        while (atual != null) {
            int idAtual = atual.getCliente().getId();
            if (id == idAtual) {
                return atual.getCliente();
            }
            atual = id < idAtual ? atual.getEsquerda() : atual.getDireita();
        }
        return null;
    }

    public Cliente buscarPorTelefone(String telefone) {
        return buscarPorTelefone(raiz, telefone);
    }

    private Cliente buscarPorTelefone(NoCliente no, String telefone) {
        if (no == null) {
            return null;
        }
        if (no.getCliente().getTelefone().equals(telefone)) {
            return no.getCliente();
        }
        Cliente encontrado = buscarPorTelefone(no.getEsquerda(), telefone);
        if (encontrado != null) {
            return encontrado;
        }
        return buscarPorTelefone(no.getDireita(), telefone);
    }

    public boolean remover(int id) {
        if (buscar(id) == null) {
            return false;
        }
        raiz = remover(raiz, id);
        tamanho--;
        return true;
    }

    private NoCliente remover(NoCliente no, int id) {
        if (no == null) {
            return null;
        }
        int idAtual = no.getCliente().getId();
        if (id < idAtual) {
            no.setEsquerda(remover(no.getEsquerda(), id));
            return no;
        }
        if (id > idAtual) {
            no.setDireita(remover(no.getDireita(), id));
            return no;
        }
        if (no.getEsquerda() == null) {
            return no.getDireita();
        }
        if (no.getDireita() == null) {
            return no.getEsquerda();
        }
        NoCliente sucessor = menor(no.getDireita());
        no.setCliente(sucessor.getCliente());
        no.setDireita(remover(no.getDireita(), sucessor.getCliente().getId()));
        return no;
    }

    private NoCliente menor(NoCliente no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    public String emOrdem() {
        StringBuilder sb = new StringBuilder();
        emOrdem(raiz, sb);
        return resultado(sb);
    }

    private void emOrdem(NoCliente no, StringBuilder sb) {
        if (no == null) {
            return;
        }
        emOrdem(no.getEsquerda(), sb);
        sb.append(no.getCliente()).append('\n');
        emOrdem(no.getDireita(), sb);
    }

    public String preOrdem() {
        StringBuilder sb = new StringBuilder();
        preOrdem(raiz, sb);
        return resultado(sb);
    }

    private void preOrdem(NoCliente no, StringBuilder sb) {
        if (no == null) {
            return;
        }
        sb.append(no.getCliente()).append('\n');
        preOrdem(no.getEsquerda(), sb);
        preOrdem(no.getDireita(), sb);
    }

    public String posOrdem() {
        StringBuilder sb = new StringBuilder();
        posOrdem(raiz, sb);
        return resultado(sb);
    }

    private void posOrdem(NoCliente no, StringBuilder sb) {
        if (no == null) {
            return;
        }
        posOrdem(no.getEsquerda(), sb);
        posOrdem(no.getDireita(), sb);
        sb.append(no.getCliente()).append('\n');
    }

    public String desenhar() {
        StringBuilder sb = new StringBuilder();
        desenhar(raiz, 0, sb);
        return resultado(sb);
    }

    private void desenhar(NoCliente no, int nivel, StringBuilder sb) {
        if (no == null) {
            return;
        }
        desenhar(no.getDireita(), nivel + 1, sb);
        for (int i = 0; i < nivel; i++) {
            sb.append("        ");
        }
        sb.append(no.getCliente().getId()).append('\n');
        desenhar(no.getEsquerda(), nivel + 1, sb);
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(NoCliente no) {
        if (no == null) {
            return 0;
        }
        return 1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita()));
    }

    private String resultado(StringBuilder sb) {
        return sb.length() == 0 ? "Nenhum cliente cadastrado." : sb.toString();
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
