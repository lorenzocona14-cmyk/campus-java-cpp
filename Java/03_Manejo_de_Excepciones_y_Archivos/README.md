# Módulo 03: Manejo Robusto de Excepciones y Persistencia con Java NIO.2

## 🎯 Objetivos de Aprendizaje
1. Comprender la jerarquía `Throwable`: la diferencia crítica entre `Error`, `Checked Exception` y `Unchecked Exception` (`RuntimeException`).
2. Dominar el bloque **`try-with-resources`** y la interfaz `AutoCloseable` para evitar fugas de descriptores de archivos y conexiones.
3. Crear excepciones personalizadas de negocio con encadenamiento de causas (*exception chaining*).
4. Manipular archivos y directorios con el estándar moderno de Java: **NIO.2** (`java.nio.file.Files` y `Path`).

---

## 🏛️ Jerarquía de Excepciones en Java

```
                         java.lang.Throwable
                                  │
                  ┌───────────────┴───────────────┐
                  ▼                               ▼
          java.lang.Error                java.lang.Exception
        (Fallos críticos de JVM:                  │
         OutOfMemoryError,               ┌────────┴────────┐
         StackOverflowError)             ▼                 ▼
          [¡NUNCA CAPTURAR!]    Checked Exceptions   RuntimeException
                               (IOException,          (NullPointerException,
                                SQLException)         IllegalArgumentException)
                               [Obliga a 'try/catch'  [Errores de programación;
                                o declarar 'throws']   no requiere captura obligatoria]
```

---

## 🔄 El Patrón Try-With-Resources

Antes de Java 7, cerrar un archivo requería un bloque `finally` anidado propenso a errores. Hoy, cualquier clase que implemente `AutoCloseable` se cierra automáticamente al finalizar el bloque:

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    // Si ocurre un error, el reader se cierra antes de que se ejecute el catch.
} catch (IOException e) {
    // Manejo del error
}
```

---

## 📂 Archivos de Código
- [`ExcepcionesAvanzadasDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/03_Manejo_de_Excepciones_y_Archivos/ExcepcionesAvanzadasDemo.java)
- [`ArchivosNioDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/03_Manejo_de_Excepciones_y_Archivos/ArchivosNioDemo.java)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/03_Manejo_de_Excepciones_y_Archivos/Ejercicios.md)
