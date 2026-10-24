# Módulo 05: Concurrencia, Multithreading y Virtual Threads (Java 21+)

## 🎯 Objetivos de Aprendizaje
1. Distinguir con claridad entre **Concurrencia** (manejar muchas cosas a la vez) y **Paralelismo** (hacer muchas cosas simultáneamente en múltiples núcleos).
2. Comprender las **condiciones de carrera** (*race conditions*) y resolverlas con `synchronized` y clases atómicas (`AtomicInteger`).
3. Utilizar **`ExecutorService`** para administrar pools de hilos eficientemente.
4. Dominar la mayor revolución de Java en la última década: **Virtual Threads (Project Loom)** introducidos en Java 21.

---

## 🚀 La Revolución: Hilos de Plataforma vs Virtual Threads

| Característica | Hilos de Plataforma Clásicos | Virtual Threads (Java 21+) |
| :--- | :--- | :--- |
| **Mapeo con el S.O.** | 1 hilo Java = 1 hilo nativo del S.O. | Millones de hilos virtuales montados sobre pocos hilos portadores (*carrier threads*). |
| **Consumo de memoria** | $\approx 1 \text{ MB}$ por hilo (Stack fijo reservado). | $\approx 1 \text{ KB}$ por hilo (Crece dinámicamente en el Heap). |
| **Tiempo de creación** | Lento (llamada al kernel del S.O.). | Casi instantáneo (puro objeto Java). |
| **Comportamiento en I/O**| Bloquea el hilo del S.O. desperdiciando CPU. | Se "desmonta" (*yield*) liberando el carrier thread para otro trabajo. |
| **Límite práctico** | Unos pocos miles antes del colapso. | **Millones de tareas concurrentes**. |

---

## 📂 Archivos de Código
- [`HilosBasicosYConcurrenciaDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/05_Concurrencia_y_Multithreading/HilosBasicosYConcurrenciaDemo.java)
- [`VirtualThreadsDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/05_Concurrencia_y_Multithreading/VirtualThreadsDemo.java)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/05_Concurrencia_y_Multithreading/Ejercicios.md)
