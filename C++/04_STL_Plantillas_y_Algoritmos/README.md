# Módulo 04: Plantillas (Templates), la STL y Algoritmos en C++

## 🎯 Objetivos de Aprendizaje
1. Dominar las **Plantillas (Templates)** de C++ y comprender por qué son superiores a los Genéricos de Java: **Monomorfización en compilación vs Borrado de tipos (Type Erasure)**.
2. Manejar los contenedores estrella de la **Standard Template Library (STL)**: `std::vector` y `std::unordered_map`.
3. Optimizar el uso de memoria conociendo la diferencia entre **`size()`** y **`capacity()`**, y usando **`reserve()`** y **`emplace_back()`**.
4. Explotar la biblioteca **`<algorithm>`** (`std::sort`, `std::transform`, `std::find_if`, `std::accumulate`).
5. Escribir **Expresiones Lambda en C++** dominando su cláusula de captura (`[]`, `[&]`, `[=]`).

---

## ⚖️ El Choque de Titanes: C++ Templates vs Java Generics

| Característica | ⚡ C++ Templates | ☕ Java Generics |
| :--- | :--- | :--- |
| **Mecanismo** | **Monomorfización:** El compilador genera una versión de código máquina independiente para cada tipo concreto (`vector<int>`, `vector<double>`). | **Type Erasure:** El compilador borra el tipo genérico en tiempo de compilación y lo reemplaza por `Object` con casts implícitos. |
| **Tipos Primitivos** | SÍ (`vector<int>`, `vector<char>`). Datos contiguos sin indirección. | NO (`List<int>` no compila). Obliga a usar Wrappers costosos (`List<Integer>`). |
| **Rendimiento** | **Máximo posible.** Sin sobrecarga en runtime, permite *inlining* completo por el optimizador. | Sobrecarga de punteros en el Heap y *autoboxing* continuo. |

---

## 🏹 Anatomía de una Expresión Lambda en C++

```cpp
[ captura ] ( parámetros ) -> tipo_retorno { cuerpo }
```
- `[]` : No captura ninguna variable externa del ámbito local.
- `[=]`: Captura todas las variables externas **por copia**.
- `[&]`: Captura todas las variables externas **por referencia** (cuidado si el lambda sobrevive al ámbito).
- `[&total, multiplicador]`: Captura mixta explícita.

---

## 📂 Archivos de Código
- [`01_templates_funciones_y_clases.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/04_STL_Plantillas_y_Algoritmos/01_templates_funciones_y_clases.cpp)
- [`02_contenedores_stl.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/04_STL_Plantillas_y_Algoritmos/02_contenedores_stl.cpp)
- [`03_algoritmos_y_lambdas.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/04_STL_Plantillas_y_Algoritmos/03_algoritmos_y_lambdas.cpp)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/04_STL_Plantillas_y_Algoritmos/Ejercicios.md)
