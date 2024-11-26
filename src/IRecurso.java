import java.util.List;

public interface IRecurso {
    List<String> obterDetalhes();
    List<Float> obterValores(Usuario usuario);
}
