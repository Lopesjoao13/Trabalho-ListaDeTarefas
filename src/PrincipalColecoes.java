import java.util.*;

public class PrincipalColecoes {

    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private Set<Categoria> categorias = new HashSet<>();
    private Map<Prioridade, List<Tarefa>> tarefasPorPrioridade = new HashMap<>();

    public PrincipalColecoes() {
        // Inicializar o mapa com listas vazias para cada prioridade
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
            System.out.println("2. Listar Todas as Tarefas");
            System.out.println("3. Marcar Tarefa Como Concluida");
            System.out.println("4. Filtrar por Prioridade");
            System.out.println("5. Salvar e Sair");
            System.out.println("Escolha uma opção: ");

            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao){
                case 1 -> {
                    System.out.println("Digite a descricao da tarefa: ");
                    String descricao = leitor.nextLine();
                    System.out.println("Defina a prioridade como BAIXA, MÉDIA, ALTA ou URGENTE:");
                    String prioridade = leitor.nextLine().toUpperCase();
                    System.out.println("Defina a categoria para a tarefa: ");
                    String categoria = leitor.nextLine();
                    adicionarTarefa(descricao, prioridade, categoria);
                }
                case 2 -> exibirTodasTarefas();
                case 3 -> System.out.println(); //marcarConcluida();
                case 4 -> exibirTarefasPorPrioridade();
                case 5 -> System.out.println();//salvarESair();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    public void adicionarTarefa(String descricao, String prioridade, String categoria){
        Tarefa tarefa = new Tarefa(descricao, Prioridade.valueOf(prioridade), new Categoria(categoria,"#hhhhh"));
        listaDeTarefas.add(tarefa);
        categorias.add(tarefa.getCategoria());
        tarefasPorPrioridade.get(tarefa.getPrioridade()).add(tarefa);
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

    public List<Tarefa> getListaDeTarefas() {
        return listaDeTarefas;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public Map<Prioridade, List<Tarefa>> getTarefasPorPrioridade() {
        return tarefasPorPrioridade;
    }
}
