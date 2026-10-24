# 🏋️ Ejercicios Prácticos: Módulo 03 (Punteros Inteligentes y Move Semantics)

---

### Reto 1: Fábrica de Objetos con std::unique_ptr
**Consigna:**
1. Crea una clase abstracta `DispositivoIoT` y subclases `Termostato` y `CamaraSeguridad`.
2. Escribe una función de fábrica (*Factory function*) con la firma:
   `std::unique_ptr<DispositivoIoT> crearDispositivo(const std::string& tipo)`
3. En el `main`, crea varios dispositivos usando la fábrica, almacénalos en un vector polimórfico `std::vector<std::unique_ptr<DispositivoIoT>>` y verifica que al salir de la función no haya que liberar nada manualmente.

---

### Reto 2: Árbol Binario sin Fugas de Memoria
**Consigna:**
1. Modela un nodo de un árbol binario `NodoArbol` que contenga un valor entero y dos hijos: `izq` y `der`.
2. ¿Qué puntero inteligente debes usar para los hijos: `unique_ptr` o `shared_ptr`? (Justifica por qué `std::unique_ptr` es la respuesta correcta para relaciones jerárquicas estrictas donde un padre es dueño exclusivo de sus hijos).
3. Construye un pequeño árbol de 5 nodos y demuestra cómo al destruir la raíz (`nullptr`), todo el subárbol se destruye en cascada recursiva sin ninguna fuga de memoria.

---

### Reto 3: Benchmark de Copia vs Movimiento
**Consigna:**
1. Crea una clase `VectorGigante` que en su constructor reserve un array de 10 millones de enteros (`new int[10'000'000]`).
2. Implementa tanto el constructor de copia como el constructor de movimiento con `noexcept`.
3. Mide con `<chrono>` el tiempo que tarda duplicar el objeto con copia frente a moverlo con `std::move`. Comenta la abrumadora diferencia de rendimiento.
