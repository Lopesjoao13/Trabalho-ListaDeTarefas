import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String NOME_ARQUIVO = "tarefas.csv";

    // Salva a lista inteira no arquivo
    public static void salvarTarefas(List<Tarefa> tarefas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Tarefa t : tarefas) {
                // Formatado
                String linha = String.format("%s;%s;%s;%s;%s;%s",
                        t.getDescricao(),
                        t.getPrioridade(),
                        t.getCategoria().getNome(),
                        t.isConcluida(),
                        t.getDataCriacao(),
                        (t.getDataConclusao() != null ? t.getDataConclusao() : "null") // Trata nulo
                );
                writer.write(linha);
                writer.newLine();
            }
        }
    }

    // Lê o arquivo e recria a lista de tarefas
    public static List<Tarefa> carregarTarefas() {
        List<Tarefa> listaRecuperada = new ArrayList<>();
        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return listaRecuperada; // Retorna lista vazia se não tiver arquivo
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    // Extrair dados
                    String descricao = partes[0];
                    Prioridade prioridade = Prioridade.valueOf(partes[1]);
                    Categoria categoria = new Categoria(partes[2], "#FFFFFF");
                    boolean concluida = Boolean.parseBoolean(partes[3]);
                    LocalDate dataCriacao = LocalDate.parse(partes[4]);
                    
                    // Criar objeto
                    Tarefa t = new Tarefa(descricao, prioridade, categoria);
                    t.setDataCriacao(dataCriacao); 
                    
                    if (concluida) {
                        t.setConcluida(true);
                        if (!partes[5].equals("null")) {
                            t.setDataConclusao(LocalDate.parse(partes[5]));
                        }
                    }
                    
                    listaRecuperada.add(t);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
        return listaRecuperada;
    }
}
