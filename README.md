# Agenda de Servicos (Tema 10)

Aplicação em Java que gerencia um salão/estúdio de serviços usando estruturas de dados feitas à mão, sem nenhuma Collection do Java.

| Estrutura | Papel no negócio |
|---|---|
| Árvore Binária de Busca | Cadastro de clientes fiéis, com busca por ID (e por telefone) |
| Fila | Agendamentos do dia, em ordem cronológica de atendimento |
| Pilha | Histórico de serviços cancelados, para análise |

## Como rodar

Você precisa do **JDK 8 ou mais novo** (para conferir, rode `java -version` e `javac -version` no terminal).

### Pelo IntelliJ / Eclipse / VS Code
1. Abra a pasta `agenda-servicos` como projeto.
2. Marque a pasta `src` como *Sources Root* (no IntelliJ: botão direito → Mark Directory as → Sources Root).
3. Abra `src/app/Main.java` e clique em ▶ Run.

### Pelo terminal

**Windows (PowerShell):**
```powershell
cd agenda-servicos
mkdir out -Force
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out app.Main
```

**Linux / macOS / Git Bash:**
```bash
cd agenda-servicos
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Main
```

### Primeiro uso
Escolha a opção **17** para carregar 7 clientes e 5 agendamentos de exemplo, depois teste à vontade (a opção **6** desenha a árvore).

## Estrutura de pastas
```
src/
├── app/          Main e Menu (interface de console)
├── modelo/       Cliente e Agendamento
├── estruturas/   ArvoreClientes, FilaAgendamentos, PilhaCancelados e os nós
└── servico/      AgendaService (regras de negócio) e Horario
```
