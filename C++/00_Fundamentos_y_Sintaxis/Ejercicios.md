# 🏋️ Ejercicios Prácticos: Módulo 00 (Fundamentos de C++)

---

### Reto 1: Función de Intercambio (Swap)
**Consigna:**
1. Escribe una función `void intercambiar(int& a, int& b)` que intercambie los valores de dos variables enteras pasadas por referencia (sin devolver nada).
2. En el `main`, declara `x = 100` e `y = 200`. Llama a la función y demuestra que `x` ahora vale 200 e `y` vale 100.
3. ¿Por qué esto es imposible de hacer en Java con tipos primitivos `int`? Explica la diferencia arquitectural.

---

### Reto 2: Inversor de Cadenas In-Place (In-situ)
**Consigna:**
1. Implementa una función `void invertirCadena(std::string& texto)` que invierta los caracteres de un string modificando la memoria original sin crear un nuevo string (usando índices o dos punteros desde los extremos).
2. Mide la memoria: demuestra que no asignas nuevo espacio de memoria en el Heap.

---

### Reto 3: Módulo de Geometría
**Consigna:**
1. Crea una cabecera `geometria.hpp` dentro de un namespace `Geometria` con funciones para calcular el perímetro y área de un círculo y un triángulo rectángulo.
2. Implementa los cálculos en `geometria.cpp`.
3. Crea un `main.cpp` que use el módulo y compílalo con:
   `g++ -std=c++14 main.cpp geometria.cpp -o programa.exe`
