# 🏋️ Ejercicios Prácticos: Módulo 01 (POO en Java)

---

### Reto 1: Jerarquía de Vehículos y Tarifas de Peaje
**Consigna:**
1. Crea una clase abstracta `Vehiculo` con atributos privados `placa` (String) y `ejes` (int), y un método abstracto `double calcularTarifaPeaje()`.
2. Implementa las clases hijas:
   - `Auto`: Tarifa fija de $150.
   - `Camion`: Tarifa base de $150 + $75 por cada eje adicional superior a 2.
   - `Moto`: Tarifa fija de $50 con 50% de descuento si es eléctrica (agrega un booleano `esElectrica`).
3. En una clase ejecutable, crea una lista polimórfica de vehículos (`List<Vehiculo>`) y calcula el total recaudado en una cabina de peaje.

---

### Reto 2: El Contrato de Inmutabilidad con Records
**Consigna:**
1. Define un `record Producto(String codigo, String nombre, double precio, int stock)` con un constructor compacto que valide que el precio sea $\ge 0$ y el stock $\ge 0$.
2. Agrega un método en el record `boolean tieneStock()` que retorne si el stock es mayor a cero.
3. Intenta modificar uno de los atributos de un producto instanciado. Explica por qué el compilador te lo impide y cuál es la ventaja de la inmutabilidad en sistemas concurrentes.

---

### Reto 3: Sistema de Notificaciones con Interfaces
**Consigna:**
1. Define una interfaz `CanalNotificacion` con el método `void enviar(String destinatario, String mensaje)`.
2. Implementa 3 canales: `EmailNotificacion`, `SmsNotificacion` y `WhatsAppNotificacion`.
3. Crea una clase `GestorCampañas` que reciba una lista de `CanalNotificacion` y despache un mismo mensaje a través de todos los canales registrados utilizando polimorfismo.
