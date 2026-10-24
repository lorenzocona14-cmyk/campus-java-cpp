# 🏋️ Ejercicios Prácticos: Módulo 02 (POO y RAII en C++)

---

### Reto 1: Envoltorio RAII para Medir Tiempos de Ejecución (Timer RAII)
**Consigna:**
1. Implementa una clase `TimerRAII` usando `<chrono>`.
2. En su constructor, guarda el tiempo de inicio (`std::chrono::high_resolution_clock::now()`) y el nombre de una tarea.
3. En su destructor, toma el tiempo final, calcula la duración en microsegundos e imprime:
   `"[Timer]: Tarea 'Procesamiento' tardó 1450 us."`
4. Demuestra su uso encerrando bloques de código entre llaves `{ }`. Al terminar el bloque, la medición se imprime automáticamente.

---

### Reto 2: Implementación de la Regla de los Tres para una Cadena Dinámica
**Consigna:**
1. Diseña una clase `MiString` con:
   - `char* texto;`
   - `size_t longitud;`
2. Implementa:
   - Constructor estándar desde `const char*`.
   - Destructor con `delete[]`.
   - Constructor de copia con reserva y copia profunda.
   - Operador de asignación con comprobación de autoasignación.
3. Prueba en `main` creando `s1("Hola")`, `s2 = s1`, modificando `s1` y verificando que `s2` permanezca inalterado.

---

### Reto 3: Jerarquía Polimórfica de Armas de Videojuego
**Consigna:**
1. Crea una clase base abstracta `Arma` con método virtual puro `void disparar() = 0` y destructor virtual.
2. Implementa `Pistola` (daño 15, munición 12), `Escopeta` (daño 80, munición 2) y `RifleLaser` (daño 50, munición ilimitada pero con calentamiento).
3. Crea un inventario `std::vector<Arma*>` y simula un tiroteo disparando todas las armas polimórficamente.
