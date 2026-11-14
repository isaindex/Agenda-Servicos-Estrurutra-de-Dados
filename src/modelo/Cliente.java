package modelo;

public class Cliente {
    private final int id;
    private String nome;
    private String telefone;
    private int totalAtendimentos;

    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.totalAtendimentos = 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTotalAtendimentos() {
        return totalAtendimentos;
    }

    public void registrarAtendimento() {
        totalAtendimentos++;
    }

    @Override
    public String toString() {
        return String.format("[ID %d] %s | Tel: %s | Atendimentos: %d", id, nome, telefone, totalAtendimentos);
    }
}
