package servico;

import estruturas.ArvoreClientes;
import estruturas.FilaAgendamentos;
import estruturas.PilhaCancelados;
import modelo.Agendamento;
import modelo.Cliente;

public class AgendaService {
    private final ArvoreClientes clientes = new ArvoreClientes();
    private final FilaAgendamentos agenda = new FilaAgendamentos();
    private final PilhaCancelados cancelados = new PilhaCancelados();
    private int proximoIdAgendamento = 1;

    public Cliente cadastrarCliente(int id, String nome, String telefone) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }
        if (vazio(nome)) {
            throw new IllegalArgumentException("Informe o nome do cliente.");
        }
        if (vazio(telefone)) {
            throw new IllegalArgumentException("Informe o telefone do cliente.");
        }
        if (clientes.buscarPorTelefone(telefone.trim()) != null) {
            throw new IllegalArgumentException("Ja existe um cliente com esse telefone.");
        }
        Cliente cliente = new Cliente(id, nome.trim(), telefone.trim());
        if (!clientes.inserir(cliente)) {
            throw new IllegalArgumentException("Ja existe um cliente com o ID " + id + ".");
        }
        return cliente;
    }

    public Cliente buscarCliente(int id) {
        Cliente cliente = clientes.buscar(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente com ID " + id + " nao encontrado.");
        }
        return cliente;
    }

    public Cliente buscarClientePorTelefone(String telefone) {
        Cliente cliente = clientes.buscarPorTelefone(telefone == null ? "" : telefone.trim());
        if (cliente == null) {
            throw new IllegalArgumentException("Nenhum cliente com o telefone " + telefone + ".");
        }
        return cliente;
    }

    public void removerCliente(int id) {
        buscarCliente(id);
        if (agenda.contemCliente(id)) {
            throw new IllegalArgumentException("Cliente tem agendamento pendente. Atenda ou cancele antes de remover.");
        }
        clientes.remover(id);
    }

    public String clientesEmOrdem() {
        return clientes.emOrdem();
    }

    public String clientesPreOrdem() {
        return clientes.preOrdem();
    }

    public String clientesPosOrdem() {
        return clientes.posOrdem();
    }

    public String desenharArvore() {
        return clientes.desenhar()
                + "\nTotal de clientes: " + clientes.getTamanho()
                + " | Altura da arvore: " + clientes.altura();
    }

    public Agendamento agendar(int idCliente, String servico, String horario) {
        Cliente cliente = buscarCliente(idCliente);
        if (vazio(servico)) {
            throw new IllegalArgumentException("Informe o servico.");
        }
        int minutos = Horario.paraMinutos(horario);
        Agendamento ultimo = agenda.ultimo();
        if (ultimo != null && minutos <= Horario.paraMinutos(ultimo.getHorario())) {
            throw new IllegalArgumentException("O horario precisa ser depois do ultimo da agenda (" + ultimo.getHorario() + ").");
        }
        Agendamento agendamento = new Agendamento(proximoIdAgendamento++, cliente, servico.trim(), horario);
        agenda.inserir(agendamento);
        return agendamento;
    }

    public String listarAgenda() {
        return agenda.listar();
    }

    public Agendamento verProximo() {
        Agendamento proximo = agenda.primeiro();
        if (proximo == null) {
            throw new IllegalArgumentException("Nao ha agendamentos na fila.");
        }
        return proximo;
    }

    public Agendamento atenderProximo() {
        Agendamento atendido = agenda.remover();
        if (atendido == null) {
            throw new IllegalArgumentException("Nao ha agendamentos na fila.");
        }
        atendido.getCliente().registrarAtendimento();
        return atendido;
    }

    public Agendamento buscarAgendamento(int id) {
        Agendamento agendamento = agenda.buscar(id);
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento #" + id + " nao esta na fila.");
        }
        return agendamento;
    }

    public Agendamento cancelar(int idAgendamento, String motivo) {
        Agendamento agendamento = agenda.removerPorId(idAgendamento);
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento #" + idAgendamento + " nao esta na fila.");
        }
        agendamento.setMotivoCancelamento(vazio(motivo) ? "Nao informado" : motivo.trim());
        cancelados.inserir(agendamento);
        return agendamento;
    }

    public String listarCancelados() {
        return cancelados.listar() + "\nTotal no historico: " + cancelados.getTamanho();
    }

    public Agendamento verUltimoCancelado() {
        Agendamento ultimo = cancelados.verTopo();
        if (ultimo == null) {
            throw new IllegalArgumentException("Historico de cancelados vazio.");
        }
        return ultimo;
    }

    public Agendamento analisarUltimoCancelado() {
        Agendamento analisado = cancelados.remover();
        if (analisado == null) {
            throw new IllegalArgumentException("Historico de cancelados vazio.");
        }
        return analisado;
    }

    public Agendamento buscarCancelado(int id) {
        Agendamento agendamento = cancelados.buscar(id);
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento #" + id + " nao esta no historico de cancelados.");
        }
        return agendamento;
    }

    public void carregarExemplo() {
        if (!clientes.estaVazia()) {
            throw new IllegalArgumentException("Os exemplos so podem ser carregados com o sistema vazio.");
        }
        cadastrarCliente(50, "Ana Souza", "71990000001");
        cadastrarCliente(30, "Bruno Lima", "71990000002");
        cadastrarCliente(70, "Carla Dias", "71990000003");
        cadastrarCliente(20, "Diego Alves", "71990000004");
        cadastrarCliente(40, "Elisa Rocha", "71990000005");
        cadastrarCliente(60, "Fabio Nunes", "71990000006");
        cadastrarCliente(80, "Gabi Torres", "71990000007");
        agendar(30, "Corte de cabelo", "08:00");
        agendar(70, "Manicure", "09:00");
        agendar(20, "Barba", "10:30");
        agendar(50, "Escova", "13:00");
        agendar(80, "Hidratacao", "15:00");
    }

    private boolean vazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
