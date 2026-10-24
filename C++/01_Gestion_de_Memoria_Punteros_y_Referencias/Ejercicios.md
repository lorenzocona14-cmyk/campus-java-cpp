# 🏋️ Ejercicios Prácticos: Módulo 01 (Punteros y Memoria)

---

### Reto 1: Redimensionador Dinámico de Arreglos (Estilo Vector Manual)
**Consigna:**
1. Crea una función `int* duplicarCapacidad(int* arregloViejo, int tamActual, int nuevoTam)` que:
   - Reserve un nuevo bloque de memoria en el Heap con `new int[nuevoTam]`.
   - Copie los elementos del arreglo viejo al nuevo usando punteros.
   - Libere la memoria del arreglo viejo usando `delete[]`.
   - Retorne el puntero al nuevo bloque.
2. Demuestra su funcionamiento en `main`, agregando elementos dinámicamente y asegurando que no haya fugas de memoria.

---

### Reto 2: Inversión de Arreglo con Dos Punteros
**Consigna:**
Implementa una función `void invertirArreglo(int* inicio, int* fin)` donde `inicio` apunte al primer elemento y `fin` apunte al último.
- Sin usar corchetes `[]` ni variables de índice enteras, avanza `inicio++` y retrocede `fin--` intercambiando sus valores con desreferenciación `*` hasta que se crucen en el centro.

---

### Reto 3: Detección y Análisis de Dangling Pointer
**Consigna:**
1. Escribe una pequeña función `int* crearEnteroPeligroso()` que declare una variable local en el Stack `int local = 50;` y retorne `&local`.
2. Explica en un comentario por qué esto es una catástrofe de seguridad de memoria (*Stack Use After Scope*).
3. Corrige la función para que retorne memoria persistente de forma segura asignada en el Heap con `new`.
