# 🚀 Reto de 30 Días: Dominio Dual de Java y C++

> **"El programador que domina Java comprende la arquitectura empresarial, el diseño de software a gran escala y la robustez orientada a objetos. El programador que domina C++ comprende qué ocurre realmente en el silicio, la memoria y el hardware. Dominar ambos te convierte en un ingeniero de software de élite."**
> — *Tu Profesor y Mentor en este reto*

---

## 🎯 Visión General del Reto

Bienvenido a uno de los retos más ambiciosos y transformadores de tu carrera: **aprender Java y C++ de manera simultánea y sólida en 30 días**.

Este repositorio no es un compilado de tutoriales superficiales. Está diseñado con la pedagogía de los mejores cursos universitarios de ciencias de la computación del mundo (MIT, Stanford, Berkeley), optimizado para que cada concepto teórico tenga su **correspondencia inmediata en código limpio, ejecutable, comentado y con ejercicios prácticos**.

---

## ⚖️ El Espejo Mental: Java frente a C++

Aprender ambos lenguajes a la vez tiene una ventaja descomunal: **el aprendizaje por contraste**. Cuando entiendes cómo resuelve un problema C++ y cómo lo resuelve Java, el conocimiento se fija al 100%.

| Aspecto | ☕ Java | ⚡ C++ |
| :--- | :--- | :--- |
| **Filosofía** | *"Write once, run anywhere"* (Portabilidad y seguridad de memoria). | *"Zero-cost abstractions"* y control absoluto del hardware. |
| **Compilación** | Código fuente (`.java`) $\rightarrow$ Bytecode (`.class`) $\rightarrow$ Ejecutado por JVM (con compilación JIT en caliente). | Código fuente (`.cpp`) $\rightarrow$ Código máquina nativo (`.exe` / binario ELF) mediante Preprocesador, Compilador y Linker. |
| **Memoria** | Todo objeto vive en el **Heap**, administrado automáticamente por el **Garbage Collector (GC)**. Tipos primitivos en Stack. | Control total. Tú decides si un objeto vive en el **Stack** o en el **Heap** (`new`/`delete` o Punteros Inteligentes). |
| **Gestión de Recursos** | `try-with-resources` (`AutoCloseable`). | **RAII** (*Resource Acquisition Is Initialization*), constructores y destructores deterministas. |
| **Punteros** | No existen punteros explícitos; todo manejo de objetos se hace mediante **referencias seguras**. | Punteros directos a memoria (`*`), aritmética de punteros, referencias (`&`), punteros inteligentes (`unique_ptr`, `shared_ptr`). |
| **Genéricos / Metaprogramación**| **Generics** mediante *Type Erasure* (borrado de tipos en tiempo de ejecución). | **Templates** mediante monomorfización en tiempo de compilación (código ultra rápido y especializado). |
| **Paradigma** | Orientado a Objetos puro / Funcional moderno. Todo pertenece a una clase. | Multiparadigma: Procedural, Orientado a Objetos, Genérico, Funcional. |

---

## 📅 Plan Maestro de Estudio: 30 Días (4 Semanas)

Dedica un bloque de tiempo a Java y otro a C++ cada día (o alterna días si prefieres inmersión total).

### 🟢 Semana 1: Fundamentos, Sintaxis y Control de Memoria
* **Día 1-2:** Sintaxis base, tipos primitivos, operadores y control de flujo en ambos lenguajes.
* **Día 3-4:** Funciones y paso de parámetros (Valor vs Referencia). En C++: headers (`.hpp`) y fuentes (`.cpp`).
* **Día 5-7:** **El Núcleo de C++:** Stack vs Heap, punteros crudos, referencias, memoria dinámica (`new`/`delete`) y fugas de memoria. En Java: Comprender la JVM, la pila de llamadas y el Heap.

### 🟡 Semana 2: Programación Orientada a Objetos (POO) y RAII
* **Día 8-10:** Clases, encapsulamiento, constructores. En C++: Destructores y el principio sagrado **RAII**. En Java: Métodos especiales y Records.
* **Día 11-12:** Herencia y Polimorfismo. En C++: métodos `virtual`, `override`, destructores virtuales y la tabla virtual (`vtable`). En Java: interfaces, clases abstractas, `@Override`.
* **Día 13-14:** C++: La Regla de los Tres y Regla de los Cinco (Constructores de copia y asignación). Java: Contrato `equals()` y `hashCode()`, clonación e inmutabilidad.

### 🟠 Semana 3: Memoria Moderna, Colecciones y Estructuras de Datos
* **Día 15-17:** C++ Moderno: Punteros Inteligentes (`std::unique_ptr`, `std::shared_ptr`, `std::weak_ptr`) y Move Semantics (`std::move`).
* **Día 18-20:** Java Collections Framework (`ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`) vs C++ STL (`std::vector`, `std::set`, `std::map`, `std::unordered_map`).
* **Día 21:** Algoritmos estándar (`<algorithm>` en C++ vs Collections/Arrays en Java).

### 🔴 Semana 4: Paradigma Funcional, Errores, I/O y Proyectos Finales
* **Día 22-24:** Manejo de Excepciones robusto e I/O de archivos (`Files`/NIO.2 en Java vs `<fstream>` en C++).
* **Día 25-27:** Programación Funcional y Lambdas: Stream API y `Optional` en Java vs Lambdas y algoritmos funcionales en C++.
* **Día 28-30:** **Proyectos Integradores Finales**: Construcción, depuración y entrega del proyecto en Java y el proyecto en C++.

---

## 🛠️ Herramientas Instaladas en tu Equipo

Tu máquina ya cuenta con los compiladores listos:
1. **Java:** Eclipse Adoptium JDK 25 (`javac` y `java` accesibles directamente desde terminal).
2. **C++:** MinGW GCC 6.3.0 en `C:\MinGW\bin\g++.exe`.

En cada carpeta (`Java/` y `C++/`) encontrarás scripts listos para compilar y ejecutar con un solo clic o comando.

---

## 📁 Estructura del Entorno de Aprendizaje

```text
Aprendiendo a programar Java y C++/
│
├── README.md                      <-- Guía general y comparativa maestra (este archivo)
│
├── Java/                          <-- Tu academia completa de Java
│   ├── README.md                  <-- Guía maestra y hoja de ruta Java
│   ├── 00_Fundamentos_y_Sintaxis/
│   ├── 01_Programacion_Orientada_a_Objetos/
│   ├── 02_Estructuras_de_Datos_y_Colecciones/
│   ├── 03_Manejo_de_Excepciones_y_Archivos/
│   ├── 04_Java_Moderno_Streams_y_Lambdas/
│   ├── 05_Concurrencia_y_Multithreading/
│   └── 06_Proyecto_Final_Integrador/
│
└── C++/                           <-- Tu academia completa de C++
    ├── README.md                  <-- Guía maestra y hoja de ruta C++
    ├── 00_Fundamentos_y_Sintaxis/
    ├── 01_Gestion_de_Memoria_Punteros_y_Referencias/
    ├── 02_Programacion_Orientada_a_Objetos_y_RAII/
    ├── 03_Punteros_Inteligentes_y_Semantica_Movimiento/
    ├── 04_STL_Plantillas_y_Algoritmos/
    ├── 05_Manejo_de_Errores_y_Archivos/
    └── 06_Proyecto_Final_Integrador/
```

¡Comencemos explorando cada carpeta! Sigue las instrucciones de cada módulo paso a paso.
