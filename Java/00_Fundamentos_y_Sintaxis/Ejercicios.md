# 🏋️ Ejercicios Prácticos: Módulo 00 (Fundamentos y Sintaxis)

Pon a prueba lo aprendido en este módulo. Crea un archivo llamado `SolucionesModulo00.java` para resolver estos 3 retos.

---

### Reto 1: Clasificador de Números Primos y Estadísticas
**Consigna:** Escribe un método estático `boolean esPrimo(int n)` eficiente (evaluando hasta $\sqrt{n}$). Luego, en el `main`, recorre los números del 1 al 100 e imprime:
1. Todos los números primos encontrados en una sola línea.
2. La cantidad total de primos.
3. La suma acumulada de todos los primos.

> **Pista:** Si $n \le 1$, no es primo. Si $n == 2$, es primo. Si es par, no es primo. Luego incrementa de 2 en 2 hasta $\le \sqrt{n}$.

---

### Reto 2: Conversor de Temperatura y Switch Expressions
**Consigna:** Implementa un método que reciba una temperatura (`double`), una unidad origen (`char`: `'C'`, `'F'`, `'K'`) y una unidad destino (`char`: `'C'`, `'F'`, `'K'`).
Utiliza **Switch Expressions** modernas de Java para calcular y retornar la temperatura convertida con precisión de dos decimales.
- Fórmulas:
  - Celsius a Fahrenheit: $(C \times 9/5) + 32$
  - Celsius a Kelvin: $C + 273.15$
  - Fahrenheit a Celsius: $(F - 32) \times 5/9$

---

### Reto 3: Simulador de Ruleta de la Suerte (Paso por Valor)
**Consigna:**
1. Diseña una clase simple `Jugador` con campos `nombre` (String) y `fichas` (int).
2. Crea un método `apostar(Jugador jugador, int cantidad)` que reduzca las fichas del jugador si tiene suficiente saldo, o lance un mensaje de saldo insuficiente.
3. Crea un método `intentarCambiarJugador(Jugador jugador)` que asigne `jugador = new Jugador("Trampita", 1000000)`.
4. Demuestra en el `main` cómo el método de apuesta sí modifica el estado del jugador original, pero el de cambio de jugador no tiene efecto sobre la variable del invocador.

---

### 💡 Solución de Referencia
Una vez que hayas intentado resolverlos por tu cuenta, puedes verificar la lógica en:
`javac SolucionesModulo00.java && java SolucionesModulo00`
