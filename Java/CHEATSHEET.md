# ☕ Java Cheat Sheet: Guía Rápida de Bolsillo

> **Mantén este archivo abierto mientras programas para consultar sintaxis en 5 segundos.**

---

## 🔢 1. Tipos Primitivos y Clases Wrapper

| Primitivo | Bits | Rango / Valores | Wrapper | Valor por Defecto |
| :--- | :---: | :--- | :--- | :---: |
| `byte` | 8 | $-128$ a $127$ | `Byte` | `0` |
| `short` | 16 | $-32,768$ a $32,767$ | `Short` | `0` |
| `int` | 32 | $\approx -2.14 \times 10^9$ a $2.14 \times 10^9$ | `Integer` | `0` |
| `long` | 64 | Termina en `L` (ej: `5000000000L`) | `Long` | `0L` |
| `float` | 32 | Termina en `f` (ej: `3.14f`) | `Float` | `0.0f` |
| `double` | 64 | Decimal estándar de 64 bits | `Double` | `0.0d` |
| `char` | 16 | Carácter Unicode UTF-16 (`'A'`, `'\u0040'`) | `Character` | `'\u0000'` |
| `boolean` | 1 | `true` o `false` | `Boolean` | `false` |

---

## 🔄 2. Sintaxis Moderna de Java (Java 14 - 25)

### Switch Expressions (Retorno de valor sin `break`):
```java
String resultado = switch (dia) {
    case "LUNES", "VIERNES" -> "Día movido";
    case "SABADO", "DOMINGO" -> "Descanso";
    default -> {
        System.out.println("Calculando...");
        yield "Día laboral estándar"; // Retorna valor desde bloque
    }
};
```

### Records (DTO inmutable en 1 línea):
```java
// Genera: constructor, getters nombre(), precio(), toString, equals y hashCode
public record Producto(String id, String nombre, double precio) {}
```

### Text Blocks (Strings multilínea):
```java
String html = """
    <html>
        <body><h1>Hola %s</h1></body>
    </html>
    """.formatted("Mundo");
```

---

## 🗃️ 3. Árbol de Decisión de Colecciones (JCF)

- ¿Necesitas acceso rápido por índice posicional $O(1)$? $\rightarrow$ **`ArrayList<T>`**
- ¿Necesitas inserciones constantes en extremos o Pila/Cola? $\rightarrow$ **`ArrayDeque<T>`**
- ¿Necesitas valores únicos sin duplicados?
  - Sin importar el orden $O(1)$: $\rightarrow$ **`HashSet<T>`**
  - Ordenados de menor a mayor $O(\log n)$: $\rightarrow$ **`TreeSet<T>`**
  - En el orden exacto en que se insertaron: $\rightarrow$ **`LinkedHashSet<T>`**
- ¿Necesitas pares Clave-Valor?
  - Búsqueda ultrarrápida $O(1)$: $\rightarrow$ **`HashMap<K, V>`**
  - Claves ordenadas $O(\log n)$: $\rightarrow$ **`TreeMap<K, V>`**

---

## 🌊 4. Recetario de la Stream API

```java
// 1. Filtrar y transformar a nueva lista inmutable:
List<String> activos = usuarios.stream()
    .filter(Usuario::estaActivo)
    .map(Usuario::getNombre)
    .toList();

// 2. Agrupar por una propiedad:
Map<String, List<Empleado>> porSector = nomina.stream()
    .collect(Collectors.groupingBy(Empleado::getSector));

// 3. Reducir / Sumar:
double total = pedidos.stream()
    .mapToDouble(Pedido::getTotal)
    .sum();

// 4. Estadísticas en 1 pasada:
DoubleSummaryStatistics stats = precios.stream()
    .collect(Collectors.summarizingDouble(p -> p));
// stats.getAverage(), stats.getMax(), stats.getMin(), stats.getCount()
```

---

## 🧵 5. Concurrencia Moderna: Virtual Threads (Java 21+)

```java
// Lanza millones de tareas concurrentes de I/O sin saturar memoria:
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> realizarPeticionHttp());
} // Auto-join implícito de todas las tareas
```

---

## ⚡ 6. Comandos de Consola Frecuentes

```powershell
# Ejecución directa sin compilar previamente (Java 11+):
java MiArchivo.java

# Ciclo clásico:
javac MiArchivo.java
java MiArchivo

# Compilar varios archivos con paquetes:
javac -d bin src/*.java
java -cp bin com.paquete.Main
```
