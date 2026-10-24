# 🏋️ Ejercicios Prácticos: Módulo 03 (Excepciones y Archivos)

---

### Reto 1: Procesador de Archivo CSV Seguro
**Consigna:**
1. Crea un programa que lea un archivo `usuarios.csv` con columnas `ID,NOMBRE,EDAD`.
2. Para cada línea:
   - Si la edad no es un número entero válido, captura `NumberFormatException` y registra la línea como errónea en un archivo `errores.log` sin detener el procesamiento del resto del archivo.
   - Si la edad es menor a 0 o mayor a 120, lanza y maneja una excepción personalizada `EdadInvalidaException`.
3. Garantiza que todos los lectores y escritores de archivo se cierren mediante `try-with-resources`.

---

### Reto 2: Gestor de Configuración con AutoCloseable
**Consigna:**
1. Implementa una clase `BloqueoArchivo` que implemente `AutoCloseable`.
2. En el constructor, simula la adquisición de un candado de archivo (*file lock*).
3. En el método `close()`, simula la liberación del candado e imprime `"Candado liberado exitosamente"`.
4. Demuestra que incluso si ocurre un error dentro del bloque `try`, el candado se libera obligatoriamente.
