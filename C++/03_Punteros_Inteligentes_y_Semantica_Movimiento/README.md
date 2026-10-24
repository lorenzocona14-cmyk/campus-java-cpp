# Módulo 03: Punteros Inteligentes y Semántica de Movimiento (Modern C++)

## 🎯 Objetivos de Aprendizaje
1. Eliminar por completo el uso manual de `delete` en código C++ moderno mediante **Punteros Inteligentes** (`<memory>`).
2. Dominar **`std::unique_ptr`** (propiedad exclusiva sin sobrecarga de rendimiento).
3. Dominar **`std::shared_ptr`** (propiedad compartida por conteo de referencias) y **`std::weak_ptr`** (para romper referencias circulares).
4. Comprender la **Semántica de Movimiento (Move Semantics)**: valores lvalue vs rvalue, referencias rvalue (`&&`) y `std::move`.
5. Dominar **La Regla de los Cinco (Rule of Five)** en C++11+.

---

## 🧠 Los 3 Punteros Inteligentes de la Biblioteca Estándar

| Puntero Inteligente | Tipo de Propiedad | ¿Se puede Copiar? | ¿Se puede Mover? | Sobrecarga de Rendimiento |
| :--- | :--- | :---: | :---: | :--- |
| **`std::unique_ptr<T>`** | **Exclusiva** (Un único dueño del recurso). | ❌ NO | ✅ SÍ (`std::move`) | **Cero**. Mismo tamaño y velocidad que un puntero crudo. |
| **`std::shared_ptr<T>`** | **Compartida** (Múltiples dueños con contador). | ✅ SÍ | ✅ SÍ | Pequeña sobrecarga (bloque de control de conteo atómico). |
| **`std::weak_ptr<T>`** | **Observador Débil** (No incrementa el contador).| ✅ SÍ | ✅ SÍ | Mínima (se usa para evitar ciclos de retención). |

---

## ⚡ ¿Qué es la Semántica de Movimiento (*Move Semantics*)?

Antes de C++11, si tenías un objeto con 1 gigabyte de memoria en el Heap y querías pasarlo a otra función o devolverlo, C++ **duplicaba todo el gigabyte** en un nuevo bloque de memoria y luego destruía el anterior.

Con la semántica de movimiento:
$$\text{Objeto A (Dueño original)} \xrightarrow{\text{std::move}} \text{Objeto B (Nuevo dueño)}$$
El puntero interno simplemente se transfiere del objeto A al objeto B, y el puntero de A se establece en `nullptr`. **Cero bytes copiados. Velocidad casi instantánea ($O(1)$).**

---

## 📂 Archivos de Código
- [`01_unique_ptr_demo.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/03_Punteros_Inteligentes_y_Semantica_Movimiento/01_unique_ptr_demo.cpp)
- [`02_shared_y_weak_ptr_demo.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/03_Punteros_Inteligentes_y_Semantica_Movimiento/02_shared_y_weak_ptr_demo.cpp)
- [`03_move_semantics_demo.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/03_Punteros_Inteligentes_y_Semantica_Movimiento/03_move_semantics_demo.cpp)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/03_Punteros_Inteligentes_y_Semantica_Movimiento/Ejercicios.md)
