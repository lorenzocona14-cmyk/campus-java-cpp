# 🏋️ Ejercicios Prácticos: Módulo 02 (Colecciones y Estructuras de Datos)

---

### Reto 1: Verificador de Paréntesis Balanceados con Deque
**Consigna:**
Implementa una función `boolean estaBalanceado(String expresion)` que verifique si una cadena con paréntesis `()`, corchetes `[]` y llaves `{}` está correctamente balanceada.
- Ejemplo válido: `"{[()()]}"` $\rightarrow$ `true`
- Ejemplo inválido: `"{[(])}"` $\rightarrow$ `false`
- **Requisito:** Utiliza `ArrayDeque<Character>` como pila (Stack LIFO).

---

### Reto 2: Analizador de Top K Palabras Más Frecuentes
**Consigna:**
Dado un párrafo de texto, encuentra las **K** palabras más frecuentes:
1. Divide el texto en palabras ignorando mayúsculas y signos de puntuación.
2. Cuenta la frecuencia de cada palabra usando un `HashMap<String, Integer>` con `merge()` o `getOrDefault()`.
3. Ordena las entradas por frecuencia descendente y retorna las $K$ palabras principales.

---

### Reto 3: Sistema de Admisión de Urgencias Médicas (Triage)
**Consigna:**
1. Modela una clase `Paciente` con `nombre` (String), `nivelGravedad` (1 a 5, siendo 1 crítico y 5 leve) y `ordenLlegada` (int).
2. Utiliza una `PriorityQueue<Paciente>` para que los pacientes sean atendidos primero por menor nivel de gravedad, y en caso de empate, por menor orden de llegada.
3. Simula la llegada de 5 pacientes en desorden y muestra el orden exacto en el que son llamados a la sala de emergencias.
