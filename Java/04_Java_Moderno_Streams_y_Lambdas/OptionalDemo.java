import java.util.Optional;

/**
 * Lección 03 del Módulo Funcional:
 * Erradicando el NullPointerException con la Mónada Optional.
 */
public class OptionalDemo {

    record Perfil(String biografia, String sitioWeb) {}
    record Usuario(int id, String nombre, Optional<Perfil> perfil) {}

    public static void main(String[] args) {
        System.out.println("=== 1. CREACIÓN DE OPTIONAL ===");
        Optional<String> conValor = Optional.of("Hola Mundo");
        Optional<String> vacio = Optional.empty();
        Optional<String> puedeSerNulo = Optional.ofNullable(obtenerValorDudoso(false));

        System.out.println("conValor tiene valor: " + conValor.isPresent());
        System.out.println("vacio está vacío: " + vacio.isEmpty());

        System.out.println("\n=== 2. EL ANTIPATRÓN VS EL PATRÓN CORRECTO ===");
        // ❌ ANTIPATRÓN (No hagas esto, es volver al 'if x != null'):
        // if (conValor.isPresent()) { System.out.println(conValor.get()); }

        // ✅ PATRÓN ELEGANTE (Programación funcional con ifPresent y orElse):
        conValor.ifPresent(val -> System.out.println("Valor encontrado: " + val));

        String resultado = vacio.orElse("Valor predeterminado por defecto");
        System.out.println("Resultado de orElse: " + resultado);

        System.out.println("\n=== 3. ENCADENAMIENTO MONÁDICO (map, filter, flatMap) ===");
        Usuario usuarioConPerfil = new Usuario(
            101, 
            "Lionel Messi", 
            Optional.of(new Perfil("Futbolista", "https://messi.com"))
        );

        Usuario usuarioSinPerfil = new Usuario(
            102, 
            "Usuario Anónimo", 
            Optional.empty()
        );

        imprimirSitioWeb(usuarioConPerfil);
        imprimirSitioWeb(usuarioSinPerfil);
    }

    public static void imprimirSitioWeb(Usuario usuario) {
        // Navegación segura sin anidar 5 niveles de 'if != null'
        String web = usuario.perfil()
            .map(Perfil::sitioWeb)
            .filter(url -> url.startsWith("https://"))
            .orElse("Sin sitio web seguro");

        System.out.println("Sitio web de " + usuario.nombre() + ": " + web);
    }

    public static String obtenerValorDudoso(boolean retornarValor) {
        return retornarValor ? "Dato presente" : null;
    }
}
