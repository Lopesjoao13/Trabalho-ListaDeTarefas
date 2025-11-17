import java.util.Objects;

public class Categoria {
    private String nome;
    private String cor;

     public Categoria(String nome, String cor) {
         this.nome = nome;
         this.cor = cor;
     }

    public Categoria() {
    }

    public String getNome() {
         return nome;
     }

     public void setNome(String nome) {
         this.nome = nome;
     }


     public String getCor() {
         return cor;
     }

     public void setCor(String cor) {
         this.cor = cor;
     }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nome, categoria.nome) && Objects.equals(cor, categoria.cor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cor);
    }

}
