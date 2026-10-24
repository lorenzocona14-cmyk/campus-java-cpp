# 🏋️ Ejercicios Prácticos: Módulo 04 (Lambdas y Streams)

---

### Reto 1: Pipeline de Análisis de Ventas
**Consigna:**
Dada una lista de objetos `Venta(String vendedor, String producto, double monto, String mes)`:
1. Filtra las ventas que ocurrieron en el mes de `"DICIEMBRE"`.
2. Calcula el monto total facturado por cada vendedor en ese mes utilizando `Collectors.groupingBy()` y `Collectors.summingDouble()`.
3. Encuentra el vendedor estrella (el que logró el mayor monto de facturación) usando `max()` y un `Comparator`.

---

### Reto 2: Aplanamiento de Listas con flatMap
**Consigna:**
Tienes una lista de departamentos de una empresa, donde cada `Departamento` tiene un nombre y una lista de empleados (`List<String> empleados`).
1. Utiliza `flatMap` para obtener un único `Set<String>` con todos los empleados de la empresa sin duplicados y en orden alfabético.

---

### Reto 3: Búsqueda Segura con Optional
**Consigna:**
1. Implementa un método `Optional<Cliente> buscarClientePorDni(String dni)` que busque en un repositorio en memoria.
2. Si el cliente existe, extrae su email, conviértelo a mayúsculas y muéstralo.
3. Si el cliente no existe, lanza una excepción de negocio `ClienteNoEncontradoException` utilizando `.orElseThrow()`.
