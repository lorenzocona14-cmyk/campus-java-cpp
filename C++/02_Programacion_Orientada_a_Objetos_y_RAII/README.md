# Módulo 02: Programación Orientada a Objetos y el Principio Sagrado: RAII

## 🎯 Objetivos de Aprendizaje
1. Comprender la filosofía de diseño de C++: **RAII** (*Resource Acquisition Is Initialization*).
2. Entender por qué el **Destructor determinista** de C++ es superior al Garbage Collector para la gestión de recursos de sistema (archivos, sockets, memoria, locks).
3. Dominar **La Regla de los Tres (Rule of Three)**: Destructor, Constructor de Copia y Operador de Asignación de Copia para evitar el desastre del *shallow copy*.
4. Implementar polimorfismo en tiempo de ejecución con métodos virtuales (`virtual`), `override`, y entender la **Tabla Virtual (`vtable`)**.
5. Grabar a fuego la regla más importante de herencia en C++: **El Destructor de la Clase Base SIEMPRE debe ser `virtual`**.

---

## 🛡️ ¿Qué es RAII?
En Java o Python, cuando abres un archivo o bloqueas un mutex, dependes de `finally`, `try-with-resources` o del recolector de basura.
En C++, los recursos se amarran al **tiempo de vida de un objeto en el Stack**:
- **En el Constructor:** Se adquiere el recurso (`new`, abrir archivo, bloquear mutex).
- **En el Destructor:** Se libera el recurso (`delete`, cerrar archivo, desbloquear mutex).

Cuando la variable sale del ámbito `{ }`, el compilador **GARANTIZA al 100%** que el destructor se ejecutará, incluso si ocurre una excepción. ¡Cero fugas de recursos!

---

## ⚖️ La Regla de los Tres (Rule of Three)

Si tu clase gestiona memoria dinámica u otro recurso manualmente, el compilador genera copias bit a bit superficiales (*shallow copy*). Esto causaría que dos objetos apunten al mismo bloque en el Heap y ambos intenten hacer `delete` (Double Free Crash).

Por tanto, si necesitas definir uno de estos 3, **debes definir los tres**:
1. **Destructor:** `~MiClase()`
2. **Constructor de Copia (Copia Profunda):** `MiClase(const MiClase& otro)`
3. **Operador de Asignación de Copia:** `MiClase& operator=(const MiClase& otro)`

---

## 📂 Archivos de Código
- [`01_clases_y_raii.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/02_Programacion_Orientada_a_Objetos_y_RAII/01_clases_y_raii.cpp)
- [`02_regla_de_los_tres.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/02_Programacion_Orientada_a_Objetos_y_RAII/02_regla_de_los_tres.cpp)
- [`03_herencia_polimorfismo_vtable.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/02_Programacion_Orientada_a_Objetos_y_RAII/03_herencia_polimorfismo_vtable.cpp)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/02_Programacion_Orientada_a_Objetos_y_RAII/Ejercicios.md)
