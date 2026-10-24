# 🏆 Proyecto Integrador Java: CryptoBank Core Engine

> **"Un verdadero programador demuestra su nivel al integrar todos los conceptos aislados en una arquitectura limpia, robusta y testeable."**

---

## 🎯 Especificación del Proyecto

El objetivo de este proyecto es construir el núcleo de un sistema bancario y billetera digital en consola que integre:
1. **POO y Encapsulamiento:** Clases `Cuenta`, `Usuario`, y Records para `Transaccion`.
2. **Excepciones de Negocio:** Manejo estricto de saldos, cuentas inexistentes y montos inválidos.
3. **Colecciones:** Estructuras `Map` para búsqueda $O(1)$ de cuentas y listas para historial de movimientos.
4. **Stream API:** Módulos de analítica financiera (transacciones de mayor cuantía, promedio de gastos, balance por tipo de movimiento).
5. **Persistencia con NIO.2:** Exportación de auditoría de transacciones a archivo físico en disco.

---

## 🚀 Cómo Ejecutar el Proyecto

Compila y corre el archivo principal:
```powershell
javac *.java
java Main
```

O si deseas ejecutar directamente:
```powershell
java Main.java
```
