# ☕ Java Masterclass: De Cero a Ingeniero Java en 30 Días

> **"Java no es solo un lenguaje; es una plataforma de ingeniería diseñada para software robusto, escalable y mantenible por décadas."**
> — *Profesor Titular de Java*

---

## 🏛️ 1. El Modelo Mental de Java: ¿Cómo Funciona?

Para dominar Java, no debes memorizar sintaxis; debes entender su arquitectura interna:

```
[ Tu Código Fuente: MiPrograma.java ]
               │
               ▼  (javac MiPrograma.java)
[ Bytecode Neutral: MiPrograma.class ]
               │
               ▼  (java MiPrograma)
┌─────────────────────────────────────────────────────────────┐
│                      Java Virtual Machine (JVM)             │
│                                                             │
│  1. Class Loader (Carga el bytecode y verifica la seguridad)│
│  2. Memoria de Ejecución (Pilas de hilos y Heap compartido) │
│  3. Motor de Ejecución:                                     │
│     - Intérprete inicial (arranque rápido)                  │
│     - Compilador JIT (Just-In-Time: HotSpot)                │
│       Detecta código "caliente" y lo compila a CÓDIGO       │
│       MÁQUINA NATIVO con optimizaciones agresivas.          │
│  4. Garbage Collector (Recolección automática de basura)    │
└─────────────────────────────────────────────────────────────┘
               │
               ▼
   [ CPU y Sistema Operativo (Windows, Linux, macOS, ARM) ]
```

### Conceptos Clave de Arquitectura:
1. **Bytecode:** Un conjunto de instrucciones intermedias e independientes del hardware.
2. **Garbage Collector (GC):** Monitorea el Heap y libera automáticamente la memoria de los objetos a los que ya no apunta ninguna referencia activa. ¡No tienes que hacer `free()` o `delete`!
3. **Paso por Valor Absoluto:** En Java, **TODO** se pasa por valor. Cuando pasas una variable primitiva, se copia su valor numérico. Cuando pasas un objeto, se copia el *valor del puntero/referencia*, no el objeto en sí.

---

## 🗺️ 2. Hoja de Ruta de los Módulos de Aprendizaje

Cada carpeta está diseñada como una lección magistral, con código compilable, comentarios didácticos en profundidad y retos prácticos.

| Módulo | Contenido Principal | Objetivo Pedagógico |
| :--- | :--- | :--- |
| [`00_Fundamentos_y_Sintaxis`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/00_Fundamentos_y_Sintaxis) | Primitivos, Wrappers, Autoboxing, Switch Expressions, Métodos. | Dominar la sintaxis básica y cómo Java gestiona los tipos. |
| [`01_Programacion_Orientada_a_Objetos`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/01_Programacion_Orientada_a_Objetos) | Clases, Encapsulamiento, Herencia, Polimorfismo, Interfaces, Records. | Pensar en objetos, diseñar contratos limpios y reutilizables. |
| [`02_Estructuras_de_Datos_y_Colecciones`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/02_Estructuras_de_Datos_y_Colecciones) | `List`, `Set`, `Map`, `Queue`, contratos `equals` y `hashCode`. | Elegir la estructura óptima según complejidad temporal $O(1)$, $O(\log n)$. |
| [`03_Manejo_de_Excepciones_y_Archivos`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/03_Manejo_de_Excepciones_y_Archivos) | Checked vs Unchecked, `try-with-resources`, I/O moderno con NIO.2. | Escribir código a prueba de fallos y persistir datos en disco. |
| [`04_Java_Moderno_Streams_y_Lambdas`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/04_Java_Moderno_Streams_y_Lambdas) | Lambdas, `Predicate`, `Function`, Stream API, `Optional<T>`. | Programar de forma declarativa, elegante y sin `NullPointerException`. |
| [`05_Concurrencia_y_Multithreading`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/05_Concurrencia_y_Multithreading) | Hilos, `ExecutorService`, y **Virtual Threads** (Java 21+). | Aprovechar múltiples núcleos de CPU y miles de tareas concurrentes. |
| [`06_Proyecto_Final_Integrador`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/06_Proyecto_Final_Integrador) | Sistema Bancario y Billetera Digital Completo. | Integrar todos los conocimientos en una aplicación modular real. |

---

## ⚡ 3. Cómo Compilar y Ejecutar en tu Terminal

Tienes instalado el JDK moderno (Adoptium JDK 25). Puedes ejecutar cualquier archivo `.java` de dos formas:

### Opción A: Ejecución directa (Single-file Source-Code Program)
Desde Java 11 en adelante, puedes ejecutar un archivo fuente directamente sin compilar manualmente primero:
```powershell
java Ruta/Al/Archivo.java
```

### Opción B: Ciclo tradicional de compilación
```powershell
# 1. Compilar a bytecode (.class)
javac Archivo.java

# 2. Ejecutar la clase generada (sin la extensión .class)
java Archivo
```

¡También cuentas con el script automatizado [`compilar_y_ejecutar.bat`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/compilar_y_ejecutar.bat) para compilar y probar cualquier módulo!
