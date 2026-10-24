# 🏋️ Ejercicios Prácticos: Módulo 04 (Templates y STL)

---

### Reto 1: Cola Circular Genérica (Ring Buffer Template)
**Consigna:**
1. Implementa una clase plantilla `template <typename T, size_t Tamano> class BufferCircular`.
2. Utiliza un arreglo estático `T buffer[Tamano]` y dos punteros de índice `cabeza` y `cola`.
3. Debe permitir `push(const T& valor)` (sobrescribiendo el más antiguo si está lleno) y `pop()`.
4. Pruébalo con tipos `int` y tipos `std::string`.

---

### Reto 2: Eliminador de Duplicados Preservando Orden
**Consigna:**
Dada una lista `std::vector<int>` con números repetidos:
1. Escribe una función que elimine los duplicados manteniendo el primer orden de aparición.
2. Utiliza un `std::unordered_set<int>` para comprobar en tiempo $O(1)$ si un elemento ya fue visto.
3. El resultado debe generarse en tiempo total $O(n)$.

---

### Reto 3: Pipeline de Transformación Funcional con std::transform
**Consigna:**
1. Tienes un vector de cadenas de texto con nombres de usuarios en minúsculas y espacios: `{" juan ", "  pedro", "maria  "}`.
2. Utiliza `std::transform` con un lambda para limpiar los espacios y convertir cada letra a mayúsculas usando `std::toupper`.
