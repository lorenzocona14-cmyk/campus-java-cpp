# Módulo 01: Punteros, Referencias y Gestión de Memoria en C++

## 🎯 Objetivos de Aprendizaje
1. Comprender qué es exactamente un **Puntero**: una variable que almacena la **dirección física en memoria** de otra variable.
2. Diferenciar con total nitidez entre un **Puntero** (`int*`) y una **Referencia** (`int&`).
3. Dominar la **Aritmética de Punteros** y entender cómo los arrays en C/C++ decaen en punteros.
4. Administrar memoria dinámica en el **Heap** mediante los operadores `new`, `delete`, `new[]` y `delete[]`.
5. Identificar y prevenir los 3 errores mortales de memoria: **Memory Leaks** (fugas), **Dangling Pointers** (punteros colgantes) y **Double Free** (doble liberación).

---

## 🔍 Tabla Comparativa: Punteros vs Referencias

| Característica | Puntero (`T* ptr`) | Referencia (`T& ref`) |
| :--- | :--- | :--- |
| **¿Qué es?** | Variable independiente que almacena una dirección. | Un alias directo / sobrenombre de otra variable. |
| **¿Puede ser nulo?** | SÍ (`nullptr`). Debe comprobarse antes de usar. | NO. Siempre debe referenciar un objeto válido. |
| **¿Puede reasignarse?** | SÍ (puede apuntar a otra variable en cualquier momento). | NO. Se enlaza en la inicialización de por vida. |
| **Sintaxis de acceso** | Requiere desreferenciación (`*ptr`) o flecha (`ptr->`). | Sintaxis idéntica a una variable normal (`ref.campo`). |
| **Aritmética** | SÍ (`ptr++`, `ptr + 5`). | NO tiene aritmética. |

---

## ⚠️ Los 3 Peligros Mortales de la Memoria Cruda

1. **Memory Leak (Fuga de Memoria):**
   Reservas memoria en el Heap con `new` y pierdes el puntero antes de llamar a `delete`. La memoria queda ocupada hasta que el proceso termina.
2. **Dangling Pointer (Puntero Colgante / Salvaje):**
   Llamas a `delete ptr;` pero no pones `ptr = nullptr;`. El puntero sigue apuntando a una dirección que ya no te pertenece. Si intentas escribir en ella, corrompes datos o provocas un fallo de segmentación (*Segmentation Fault*).
3. **Double Free (Doble Liberación):**
   Llamar a `delete` dos veces sobre el mismo puntero. El gestor de memoria colapsa inmediatamente.

---

## 📂 Archivos de Código
- [`01_punteros_y_direcciones.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/01_Gestion_de_Memoria_Punteros_y_Referencias/01_punteros_y_direcciones.cpp)
- [`02_aritmetica_de_punteros_y_arrays.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/01_Gestion_de_Memoria_Punteros_y_Referencias/02_aritmetica_de_punteros_y_arrays.cpp)
- [`03_memoria_dinamica_heap.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/01_Gestion_de_Memoria_Punteros_y_Referencias/03_memoria_dinamica_heap.cpp)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/01_Gestion_de_Memoria_Punteros_y_Referencias/Ejercicios.md)
