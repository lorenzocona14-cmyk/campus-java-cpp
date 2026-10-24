# Módulo 04: Java Moderno, Lambdas y la Stream API

## 🎯 Objetivos de Aprendizaje
1. Adoptar el **paradigma funcional declarativo** introducido en Java 8+.
2. Dominar las cuatro interfaces funcionales fundamentales: `Predicate`, `Function`, `Consumer` y `Supplier`.
3. Procesar flujos de datos complejos de manera fluida y paralela con la **Stream API**.
4. Erradicar el infame `NullPointerException` (el "error del billón de dólares") utilizando la mónada **`Optional<T>`**.

---

## 🧩 Las 4 Interfaces Funcionales Fundamentales

Cualquier interfaz con **un único método abstracto** es una interfaz funcional (`@FunctionalInterface`):

| Interfaz | Firma del Método | Propósito | Ejemplo |
| :--- | :--- | :--- | :--- |
| **`Predicate<T>`** | `boolean test(T t)` | Evaluar una condición / Filtro | `x -> x > 18` |
| **`Function<T, R>`**| `R apply(T t)` | Transformar un tipo `T` a tipo `R` | `String::toUpperCase` |
| **`Consumer<T>`** | `void accept(T t)` | Realizar una acción con efecto secundario | `System.out::println` |
| **`Supplier<T>`** | `T get()` | Generar / Proveer un valor sin recibir nada | `() -> LocalDate.now()` |

---

## 🌊 Anatomía de un Pipeline de Streams

Un Stream en Java tiene **3 etapas indispensables**:

$$\text{Origen (Colección / Array)} \xrightarrow{\text{1. Creación}} \text{Stream} \xrightarrow{\text{2. Operaciones Intermedias}} \text{Stream} \xrightarrow{\text{3. Operación Terminal}} \text{Resultado}$$

- **Operaciones Intermedias (Perezosas / Lazy):** No hacen nada hasta que se invoca una operación terminal.
  Ejemplos: `filter()`, `map()`, `flatMap()`, `distinct()`, `sorted()`.
- **Operaciones Terminales (Eager):** Disparan el procesamiento del pipeline y cierran el Stream.
  Ejemplos: `collect()`, `reduce()`, `count()`, `findFirst()`, `forEach()`.

---

## 📂 Archivos de Código
- [`LambdasYFuncionalesDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/04_Java_Moderno_Streams_y_Lambdas/LambdasYFuncionalesDemo.java)
- [`StreamsApiDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/04_Java_Moderno_Streams_y_Lambdas/StreamsApiDemo.java)
- [`OptionalDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/04_Java_Moderno_Streams_y_Lambdas/OptionalDemo.java)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/04_Java_Moderno_Streams_y_Lambdas/Ejercicios.md)
