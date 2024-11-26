import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecursoProxyTest {

    private RecursoProxy recursoProxyAutorizado;
    private RecursoProxy recursoProxyNaoAutorizado;
    private Usuario usuarioAutorizado;
    private Usuario usuarioNaoAutorizado;

    @BeforeEach
    public void setUp() {
        usuarioAutorizado = new Usuario("Admin", true);
        usuarioNaoAutorizado = new Usuario("Visitante", false);

        RecursoConfidencial recurso = new RecursoConfidencial(1, "Recurso 1", "Categoria A", 1000.0f);
        BD.addRecurso(recurso);

        recursoProxyAutorizado = new RecursoProxy(1);
        recursoProxyNaoAutorizado = new RecursoProxy(1);
    }

    @Test
    public void deveRetornarDetalhesRecursoAutorizado() {
        List<String> detalhes = recursoProxyAutorizado.obterDetalhes();
        assertEquals(Arrays.asList("Recurso 1", "Categoria A"), detalhes);
    }

    @Test
    public void deveRetornarValoresRecursoAutorizado() {
        List<Float> valores = recursoProxyAutorizado.obterValores(usuarioAutorizado);
        assertEquals(Arrays.asList(1000.0f), valores);
    }

    @Test
    public void deveRetornarExcecaoUsuarioNaoAutorizadoConsultarValoresRecurso() {
        try {
            recursoProxyNaoAutorizado.obterValores(usuarioNaoAutorizado);
            fail("Exceção esperada");
        } catch (IllegalArgumentException e) {
            assertEquals("Usuário não autorizado", e.getMessage());
        }
    }

    @Test
    public void deveNaoLancarExcecaoUsuarioNaoAutorizadoConsultarDetalhesRecurso() {
        List<String> detalhes = recursoProxyNaoAutorizado.obterDetalhes();
        assertEquals(Arrays.asList("Recurso 1", "Categoria A"), detalhes);
    }

    @Test
    public void deveCriarRecursoNoBanco() {
        RecursoConfidencial recurso = BD.getRecurso(1);
        assertNotNull(recurso);
        assertEquals("Recurso 1", recurso.getDescricao());
        assertEquals("Categoria A", recurso.getCategoria());
        assertEquals(1000.0f, recurso.getValor());
    }
}
