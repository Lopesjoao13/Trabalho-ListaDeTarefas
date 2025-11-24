import java.util.*;
import java.io.IOException;

public class PrincipalColecoes {

    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private Set<Categoria> categorias = new HashSet<>();
    //private Map<Prioridade, List<Tarefa>> tarefasPorPrioridade = new HashMap<>();
    private Map<Prioridade, List<Tarefa>> tarefasPorPrioridade = new EnumMap<>(Prioridade.class);

    public PrincipalColecoes() {
        for (Prioridade prioridade : Prioridade.values()) {
            tarefasPorPrioridade.put(prioridade, new ArrayList<>());
        }
    }

    public void exibirMenu(){
        int opcao;
        Scanner leitor = new Scanner(System.in);
        do {
            System.out.println("---GERENCIADOR DE TAREFAS---");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas Não Concluídas");
            System.out.println("3. Listar Tarefas Concluídas");
            System.out.println("4. Listar Todas as Tarefas");
            System.out.println("5. Filtrar por Prioridade");
            System.out.println("6. Marcar Tarefa Como Concluida");
            System.out.println("7. Salvar e Sair");
            System.out.println("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao){
                case 1 -> {
                    System.out.println("Digite a descricao da tarefa: ");
                    String descricao = leitor.nextLine();
                    Prioridade prioridadeEscolhida = null;
                    while (prioridadeEscolhida == null) {
                        System.out.println("Defina a prioridade (BAIXA, MEDIA, ALTA, URGENTE):");
                        String entradaPrioridade = leitor.nextLine().toUpperCase().trim();
                        
                        try {
                            // Tenta converter. Se digitar errado, vai pular pro erro
                            prioridadeEscolhida = Prioridade.valueOf(entradaPrioridade);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: Prioridade inválida! Digite exatamente uma das opções acima.");
                        }
                    }
                    
                    System.out.println("Defina a categoria para a tarefa: ");
                    String categoria = leitor.nextLine();
                    adicionarTarefa(descricao, prioridadeEscolhida, categoria);
                }
                case 2 -> exibirTarefasNaoConcluidas();
                case 3 -> exibirTarefasConcluidas();
                case 4 -> exibirTodasTarefas();
                case 5 -> exibirTarefasPorPrioridade();
                case 6 -> marcarConcluida();
                case 7 -> salvarESair();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    public void adicionarTarefa(String descricao, Prioridade prioridade, String categoria){
        Tarefa tarefa = new Tarefa(descricao, prioridade, new Categoria(categoria, "#FFFFFF"));
        
        listaDeTarefas.add(tarefa);
        categorias.add(tarefa.getCategoria());
        tarefasPorPrioridade.get(tarefa.getPrioridade()).add(tarefa);
        
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void exibirTodasTarefas(){
        System.out.println("---TODAS TAREFAS---");
        listaDeTarefas.forEach(System.out::println);
    }


    public void exibirTarefasPorPrioridade() {
        System.out.println("=== TAREFAS POR PRIORIDADE ===");
        for (Map.Entry<Prioridade, List<Tarefa>> entry : tarefasPorPrioridade.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " tarefas");
            entry.getValue().forEach(t -> System.out.println("  - " + t));
        }
    }

    public void exibirCategorias() {
        System.out.println("=== CATEGORIAS ===");
        categorias.forEach(categoria -> System.out.println("• " + categoria.getNome()));
    }

    public void carregarDados() {
        List<Tarefa> recuperadas = Persistencia.carregarTarefas();
        for (Tarefa t : recuperadas) {
            // Adiciona nas coleções
            listaDeTarefas.add(t);
            categorias.add(t.getCategoria());
            tarefasPorPrioridade.get(t.getPrioridade()).add(t);
        }
        if (!recuperadas.isEmpty()) {
            System.out.println("Dados carregados com sucesso! (" + recuperadas.size() + " tarefas)");
        }
    }

    public void marcarConcluida() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("--- MARCAR TAREFA COMO CONCLUÍDA ---");
        
        // Lista tarefas com índice para o usuário escolher
        for (int i = 0; i < listaDeTarefas.size(); i++) {
            Tarefa t = listaDeTarefas.get(i);
            String status = t.isConcluida() ? "[X]" : "[ ]";
            System.out.println(i + ". " + status + " " + t.getDescricao());
        }

        System.out.print("Digite o número da tarefa para concluir: ");
        int index = leitor.nextInt();

        if (index >= 0 && index < listaDeTarefas.size()) {
            Tarefa t = listaDeTarefas.get(index);
            t.marcarComoConcluida();
            System.out.println("Tarefa '" + t.getDescricao() + "' marcada como concluída!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void salvarESair() {
        try {
            Persistencia.salvarTarefas(listaDeTarefas);
            System.out.println("Dados salvos com sucesso. Saindo...");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public List<Tarefa> getListaDeTarefas() {
        return listaDeTarefas;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public Map<Prioridade, List<Tarefa>> getTarefasPorPrioridade() {
        return tarefasPorPrioridade;
    }


    public void exibirTarefasConcluidas() {
        System.out.println("--- TAREFAS CONCLUÍDAS ---");

        listaDeTarefas.stream() // 1. Cria um Stream para processamento sequencial
                .filter(Tarefa::isConcluida) // 2. Filtra, mantendo apenas tarefas onde isConcluida() retorna true
                .forEach(System.out::println); // 3. Itera sobre o resultado e imprime cada tarefa
    }


    public void exibirTarefasNaoConcluidas() {
        System.out.println("--- TAREFAS PENDENTES ---");

        listaDeTarefas.stream() // 1. Inicia o fluxo de dados
                .filter(t -> !t.isConcluida()) // 2. Filtra: mantém apenas tarefas onde isConcluida() é FALSO
                .forEach(System.out::println); // 3. Imprime as tarefas restantes
    }
}
