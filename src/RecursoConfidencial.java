import java.util.Arrays;
import java.util.List;

public class RecursoConfidencial implements IRecurso {

    private Integer id;
    private String descricao;
    private String categoria;
    private Float valor;

    public RecursoConfidencial(int id) {
        this.id = id;
        RecursoConfidencial recurso = BD.getRecurso(id);
        this.descricao = recurso.descricao;
        this.categoria = recurso.categoria;
        this.valor = recurso.valor;
    }

    public RecursoConfidencial(Integer id, String descricao, String categoria, Float valor) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public List<String> obterDetalhes() {
        return Arrays.asList(this.descricao, this.categoria);
    }

    @Override
    public List<Float> obterValores(Usuario usuario) {
        return Arrays.asList(this.valor);
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Float getValor() {
        return valor;
    }
}
