# 🏋️ Ejercicios Prácticos: Módulo 05 (Concurrencia y Multithreading)

---

### Reto 1: Descargador Concurrente Simulado con Virtual Threads
**Consigna:**
1. Tienes una lista de 50 URLs simuladas (`"https://api.ejemplo.com/recurso/1"` hasta `50`).
2. Utiliza `Executors.newVirtualThreadPerTaskExecutor()` para "descargar" (dormir el hilo entre 50ms y 200ms de forma aleatoria) cada recurso concurrentemente.
3. Almacena los resultados de cada descarga en una estructura concurrente (`ConcurrentHashMap<String, Integer>` con URL y tamaño de bytes descargado).
4. Muestra en pantalla el tiempo total transcurrido.

---

### Reto 2: Productor-Consumidor Clásico con BlockingQueue
**Consigna:**
1. Implementa el patrón clásico Productor-Consumidor utilizando `java.util.concurrent.ArrayBlockingQueue<String>` con capacidad máxima de 5 elementos.
2. Un hilo Productor genera mensajes cada 100ms.
3. Dos hilos Consumidores procesan los mensajes cada 250ms.
4. Observa cómo la cola bloquea automáticamente al productor cuando está llena y a los consumidores cuando está vacía, sin necesidad de usar `wait()` ni `notify()` manuales.
