import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {
    private String descricao;
    private boolean concluida;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private Prioridade prioridade;
    private Categoria categoria;

    public Tarefa() {
    }

    public Tarefa(String descricao, boolean concluida, LocalDate dataCriacao, LocalDate dataVencimento, Prioridade prioridade, Categoria categoria) {
        this.descricao = descricao;
        this.concluida = concluida;
        this.dataCriacao = dataCriacao;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return concluida == tarefa.concluida && Objects.equals(descricao, tarefa.descricao) && Objects.equals(dataCriacao, tarefa.dataCriacao) && Objects.equals(dataVencimento, tarefa.dataVencimento) && prioridade == tarefa.prioridade && Objects.equals(categoria, tarefa.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, concluida, dataCriacao, dataVencimento, prioridade, categoria);
    }
}
