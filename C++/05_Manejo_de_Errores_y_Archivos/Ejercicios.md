# 🏋️ Ejercicios Prácticos: Módulo 05 (Errores y Archivos en C++)

---

### Reto 1: Parser de Archivos INI / Clave=Valor Seguro
**Consigna:**
1. Crea una clase `LectorConfiguracion` que reciba una ruta de archivo.
2. Si el archivo no existe o no se puede abrir, lanza una excepción `std::runtime_error("Archivo de configuración no encontrado")`.
3. Lee el archivo línea por línea separando por el carácter `=`. Guarda los pares en un `std::unordered_map<std::string, std::string>`.
4. Si una línea no contiene `=`, lanza una excepción personalizada `FormatoInvalidoException` indicando el número de línea exacto del fallo.

---

### Reto 2: Copiador de Archivos Binarios Rápido
**Consigna:**
1. Implementa una función `bool copiarArchivo(const std::string& origen, const std::string& destino)`.
2. Utiliza `std::ifstream` y `std::ofstream` en modo binario (`std::ios::binary`).
3. Lee y escribe en bloques usando un búfer `char buffer[4096]` con `.read()` y `.write()`.
4. Demuestra que todos los flujos se cierran automáticamente incluso si la copia es interrumpida.
