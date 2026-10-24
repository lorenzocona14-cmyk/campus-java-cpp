import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

/**
 * Lección 02 del Módulo de Archivos:
 * Manejo Moderno de Archivos con Java NIO.2 (Files y Path).
 */
public class ArchivosNioDemo {

    public static void main(String[] args) {
        Path directorio = Path.of("datos_demo");
        Path archivo = directorio.resolve("transacciones_registro.txt");

        try {
            System.out.println("=== 1. CREACIÓN DE DIRECTORIOS Y ARCHIVOS ===");
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
                System.out.println("Directorio creado: " + directorio.toAbsolutePath());
            }

            System.out.println("\n=== 2. ESCRITURA EN ARCHIVO ===");
            String contenidoInicial = """
                ID_TRANSACCION,USUARIO,MONTO,ESTADO
                TX001,JuanPerez,150.50,EXITO
                TX002,MariaLopez,2400.00,PENDIENTE
                TX003,CarlosRuiz,85.20,FALLIDO
                """;

            // Escribe la cadena creando o reemplazando el archivo
            Files.writeString(archivo, contenidoInicial);
            System.out.println("Archivo escrito correctamente.");

            // Añadir contenido (Append)
            String nuevaLinea = "TX004,LuciaGomez,990.00,EXITO\n";
            Files.writeString(archivo, nuevaLinea, StandardOpenOption.APPEND);
            System.out.println("Línea adicional agregada.");

            System.out.println("\n=== 3. LECTURA COMPLETA EN MEMORIA (Files.readAllLines) ===");
            List<String> todasLasLineas = Files.readAllLines(archivo);
            for (String linea : todasLasLineas) {
                System.out.println("  [Fila]: " + linea);
            }

            System.out.println("\n=== 4. PROCESAMIENTO EFICIENTE CON STREAMS (Files.lines) ===");
            // Files.lines abre un flujo perezoso (Lazy Stream).
            // Ideal para archivos gigantescos de gigabytes porque no carga todo en memoria.
            try (Stream<String> lineasStream = Files.lines(archivo)) {
                System.out.println("Transacciones que fueron un EXITO:");
                lineasStream
                    .filter(linea -> linea.endsWith("EXITO"))
                    .forEach(linea -> System.out.println("  -> " + linea));
            }

            System.out.println("\n=== 5. LIMPIEZA ===");
            // Borramos el archivo de prueba para no dejar basura
            Files.deleteIfExists(archivo);
            Files.deleteIfExists(directorio);
            System.out.println("Directorio y archivo de prueba eliminados limpiamente.");

        } catch (IOException e) {
            System.err.println("Error de entrada/salida (I/O): " + e.getMessage());
            e.printStackTrace();
        }
    }
}
