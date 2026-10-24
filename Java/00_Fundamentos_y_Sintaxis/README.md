# Módulo 00: Fundamentos y Sintaxis en Java

## 🎯 Objetivos de Aprendizaje
1. Comprender la anatomía de un archivo `.java` y el método `main`.
2. Diferenciar entre los 8 tipos primitivos y sus clases envolventes (*Wrapper classes*).
3. Dominar el control de flujo clásico y las modernas **Switch Expressions** de Java 14+.
4. Entender el alcance (*scope*) y demostrar por qué **Java siempre pasa argumentos por valor**.

---

## 📚 Conceptos Esenciales

### 1. Los 8 Tipos Primitivos en Java
Java tiene 8 tipos primitivos guardados directamente en la pila (*Stack*):
- **Enteros:** `byte` (8-bit), `short` (16-bit), `int` (32-bit, el estándar), `long` (64-bit, sufijo `L`).
- **Flotantes:** `float` (32-bit, sufijo `f`), `double` (64-bit, doble precisión estándar).
- **Caracteres:** `char` (16-bit Unicode UTF-16, comillas simples `'a'`).
- **Lógicos:** `boolean` (`true` o `false`, **no** es un número como en C++).

### 2. Clases Envolventes (Wrappers) y Autoboxing
Para cada primitivo existe un objeto en el *Heap*:
`int` $\rightarrow$ `Integer`, `double` $\rightarrow$ `Double`, etc.
El compilador realiza **Autoboxing** (convertir primitivo a Wrapper) y **Unboxing** (convertir Wrapper a primitivo) automáticamente.

### 3. Anatomía del punto de entrada:
```java
public class MiClase {
    public static void main(String[] args) {
        // public: Accesible por la JVM desde cualquier lugar.
        // static: No requiere instanciar MiClase para ejecutarse.
        // void: No retorna ningún valor a la JVM (System.exit para códigos de error).
        // String[] args: Parámetros recibidos por línea de comandos.
    }
}
```

---

## 📂 Archivos de Código
- [`HolaMundo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/00_Fundamentos_y_Sintaxis/HolaMundo.java)
- [`TiposDeDatosYOperadores.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/00_Fundamentos_y_Sintaxis/TiposDeDatosYOperadores.java)
- [`ControlDeFlujo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/00_Fundamentos_y_Sintaxis/ControlDeFlujo.java)
- [`MetodosYAlcance.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/00_Fundamentos_y_Sintaxis/MetodosYAlcance.java)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/00_Fundamentos_y_Sintaxis/Ejercicios.md)
