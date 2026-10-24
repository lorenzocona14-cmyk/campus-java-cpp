# Módulo 00: Fundamentos y Sintaxis en C++

## 🎯 Objetivos de Aprendizaje
1. Comprender la estructura de un archivo fuente de C++, directivas de preprocesador (`#include`) y espacios de nombres (`namespaces`).
2. Conocer los tipos primitivos nativos, sus modificadores (`signed`, `unsigned`) y el operador `sizeof`.
3. Distinguir entre `const` (inmutable en tiempo de ejecución) y `constexpr` (evaluado en tiempo de compilación).
4. Dominar el paso de parámetros: **Paso por Valor** vs **Paso por Referencia** (`&`) vs **Referencia Constante** (`const &`).
5. Aprender a organizar un proyecto profesional dividiendo el código en **Cabeceras (`.hpp`)** e **Implementaciones (`.cpp`)**.

---

## 🏛️ Conceptos Clave

### 1. `std::cout` vs `std::endl` vs `\n`
En C++, `std::endl` no solo inserta un salto de línea; también fuerza un vaciado del búfer de salida (*buffer flush*), lo cual es costoso en bucles rápidos. En código de alto rendimiento, se prefiere `\n`.

### 2. Paso de Parámetros en Funciones: Regla de Oro
- Si el dato es pequeño y primitivo (`int`, `double`, `char`, `bool`): **Pasa por valor**.
- Si el dato es un objeto pesado (`std::string`, `std::vector`) y solo lo vas a leer: **Pasa por referencia constante (`const T&`)** para evitar duplicar memoria en la pila.
- Si la función necesita modificar la variable original del llamador: **Pasa por referencia (`T&`)**.

### 3. Include Guards vs `#pragma once`
Para evitar que una misma cabecera se incluya dos veces en la misma unidad de compilación (lo que causaría error de redefinición), se usan guardas de inclusión:
```cpp
#pragma once // Directiva moderna soportada por prácticamente todos los compiladores
```
O el estándar clásico:
```cpp
#ifndef MI_CABECERA_HPP
#define MI_CABECERA_HPP
// declaraciones
#endif
```

---

## 📂 Archivos de Código
- [`01_hola_mundo.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/00_Fundamentos_y_Sintaxis/01_hola_mundo.cpp)
- [`02_tipos_y_constantes.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/00_Fundamentos_y_Sintaxis/02_tipos_y_constantes.cpp)
- [`03_funciones_y_referencias.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/00_Fundamentos_y_Sintaxis/03_funciones_y_referencias.cpp)
- [`04_modularizacion/`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/00_Fundamentos_y_Sintaxis/04_modularizacion)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/00_Fundamentos_y_Sintaxis/Ejercicios.md)
