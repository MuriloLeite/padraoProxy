import java.util.List;

public class RecursoProxy implements IRecurso {

    private RecursoConfidencial recursoConfidencial;

    private Integer id;

    public RecursoProxy(Integer id) {
        this.id = id;
    }

    @Override
    public List<String> obterDetalhes() {
        if (this.recursoConfidencial == null) {
            this.recursoConfidencial = new RecursoConfidencial(this.id);
        }
        return this.recursoConfidencial.obterDetalhes();
    }

    @Override
    public List<Float> obterValores(Usuario usuario) {
        if (!usuario.isAutorizado()) {
            throw new IllegalArgumentException("Usuário não autorizado");
        }
        if (this.recursoConfidencial == null) {
            this.recursoConfidencial = new RecursoConfidencial(this.id);
        }
        return this.recursoConfidencial.obterValores(usuario);
    }
}
