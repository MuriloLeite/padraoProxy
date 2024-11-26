import java.util.HashMap;
import java.util.Map;

public class BD {
    private static Map<Integer, RecursoConfidencial> recursos = new HashMap<>();

    public static RecursoConfidencial getRecurso(Integer id) {
        return recursos.get(id);
    }

    public static void addRecurso(RecursoConfidencial recurso) {
        recursos.put(recurso.getId(), recurso);
    }
}
