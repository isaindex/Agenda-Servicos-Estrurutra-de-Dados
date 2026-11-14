package app;

import java.util.Scanner;
import modelo.Agendamento;
import modelo.Cliente;
import servico.AgendaService;

// TRABALHO FINAL - ESTRUTURA DE DADOS
// TEMA 10: AGENDA DE SERVIÇOS
// INTEGRANTES:
// Alice Maiara Santos Marques - 200040514
// Isabela Freitas Mota Lima - 200039557
public class Menu {
    private final AgendaService service = new AgendaService();
    private final Scanner entrada = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = lerInteiro("Escolha uma opcao: ");
            try {
                executar(opcao);
            } catch (IllegalArgumentException e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            }
        } while (opcao != 0);
        System.out.println("Sistema encerrado. Ate logo!");
    }

    private void exibirOpcoes() {
        System.out.println();
        System.out.println("========== AGENDA DE SERVICOS ==========");
        System.out.println("--- CLIENTES FIEIS (Arvore Binaria) ---");
        System.out.println(" 1 - Cadastrar cliente");
        System.out.println(" 2 - Buscar cliente por ID");
        System.out.println(" 3 - Buscar cliente por telefone");
        System.out.println(" 4 - Remover cliente");
        System.out.println(" 5 - Listar clientes (percursos)");
        System.out.println(" 6 - Desenhar arvore");
        System.out.println("--- AGENDA DO DIA (Fila) ---");
        System.out.println(" 7 - Novo agendamento");
        System.out.println(" 8 - Ver agenda do dia");
        System.out.println(" 9 - Ver proximo atendimento");
        System.out.println("10 - Atender proximo");
        System.out.println("11 - Buscar agendamento");
        System.out.println("12 - Cancelar agendamento");
        System.out.println("--- CANCELADOS (Pilha) ---");
        System.out.println("13 - Ver historico de cancelados");
        System.out.println("14 - Ver ultimo cancelado");
        System.out.println("15 - Analisar ultimo cancelado (retira do topo)");
        System.out.println("16 - Buscar cancelado");
        System.out.println("--- OUTROS ---");
        System.out.println("17 - Carregar dados de exemplo");
        System.out.println(" 0 - Sair");
    }

    private void executar(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                System.out.println("\n" + service.buscarCliente(lerInteiro("ID do cliente: ")));
                break;
            case 3:
                System.out.println("\n" + service.buscarClientePorTelefone(lerTexto("Telefone: ")));
                break;
            case 4:
                removerCliente();
                break;
            case 5:
                listarClientes();
                break;
            case 6:
                System.out.println("\nArvore deitada (raiz a esquerda, direita em cima):\n");
                System.out.println(service.desenharArvore());
                break;
            case 7:
                agendar();
                break;
            case 8:
                System.out.println("\nAGENDA DO DIA (inicio da fila primeiro):");
                System.out.println(service.listarAgenda());
                break;
            case 9:
                System.out.println("\nProximo: " + service.verProximo());
                break;
            case 10:
                Agendamento atendido = service.atenderProximo();
                System.out.println("\nAtendido: " + atendido);
                break;
            case 11:
                System.out.println("\n" + service.buscarAgendamento(lerInteiro("Numero do agendamento: ")));
                break;
            case 12:
                cancelar();
                break;
            case 13:
                System.out.println("\nHISTORICO DE CANCELADOS (mais recente no topo):");
                System.out.println(service.listarCancelados());
                break;
            case 14:
                System.out.println("\nUltimo cancelado: " + service.verUltimoCancelado());
                break;
            case 15:
                System.out.println("\nAnalisado e retirado do historico: " + service.analisarUltimoCancelado());
                break;
            case 16:
                System.out.println("\n" + service.buscarCancelado(lerInteiro("Numero do agendamento: ")));
                break;
            case 17:
                service.carregarExemplo();
                System.out.println("\nDados de exemplo carregados: 7 clientes e 5 agendamentos.");
                break;
            case 0:
                break;
            default:
                System.out.println("\nOpcao invalida.");
        }
    }

    private void cadastrarCliente() {
        int id = lerInteiro("ID: ");
        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone: ");
        Cliente cliente = service.cadastrarCliente(id, nome, telefone);
        System.out.println("\nCliente cadastrado: " + cliente);
    }

    private void removerCliente() {
        int id = lerInteiro("ID do cliente a remover: ");
        service.removerCliente(id);
        System.out.println("\nCliente " + id + " removido.");
    }

    private void listarClientes() {
        System.out.println("1 - Em ordem (ID crescente)");
        System.out.println("2 - Pre-ordem");
        System.out.println("3 - Pos-ordem");
        int tipo = lerInteiro("Percurso: ");
        if (tipo == 1) {
            System.out.println("\nEM ORDEM:\n" + service.clientesEmOrdem());
        } else if (tipo == 2) {
            System.out.println("\nPRE-ORDEM:\n" + service.clientesPreOrdem());
        } else if (tipo == 3) {
            System.out.println("\nPOS-ORDEM:\n" + service.clientesPosOrdem());
        } else {
            System.out.println("\nPercurso invalido.");
        }
    }

    private void agendar() {
        int idCliente = lerInteiro("ID do cliente: ");
        String servico = lerTexto("Servico: ");
        String horario = lerTexto("Horario (HH:MM): ");
        Agendamento agendamento = service.agendar(idCliente, servico, horario);
        System.out.println("\nAgendado: " + agendamento);
    }

    private void cancelar() {
        int id = lerInteiro("Numero do agendamento: ");
        String motivo = lerTexto("Motivo do cancelamento: ");
        Agendamento cancelado = service.cancelar(id, motivo);
        System.out.println("\nCancelado e enviado ao historico: " + cancelado);
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (!entrada.hasNextLine()) {
                return 0;
            }
            String linha = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.hasNextLine() ? entrada.nextLine() : "";
    }
}
