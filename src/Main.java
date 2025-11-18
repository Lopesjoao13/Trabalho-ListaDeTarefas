import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        carregarDados();
        exibirMenu();
    }

    public static void exibirMenu(){
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

            switch (opcao){
                case 1 -> adicionarTarefa();
                case 2 -> listarTarefas();
                case 3 -> marcarConcluida();
                case 4 -> filtrarPorPrioridade();
                case 5 -> salvarESair();
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    public void adicionarTarefa()
}