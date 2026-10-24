import java.util.ArrayList;
import java.util.List;

/**
 * Lección 02 de POO:
 * Herencia, Clases Abstractas, Interfaces Múltiples y Polimorfismo Dinámico.
 */
public class PolimorfismoEInterfacesDemo {

    // --- INTERFACES ---
    interface Notificable {
        // En una interfaz, los métodos sin cuerpo son implícitamente 'public abstract'
        void enviarNotificacion(String mensaje);

        // Método por defecto (default method - Java 8+):
        default void logNotificacion(String mensaje) {
            System.out.println("[AUDITORIA NOTIFICABLE]: " + mensaje);
        }

        // Método estático de utilidad en la interfaz (Java 8+):
        static boolean esCanalValido(String canal) {
            return canal != null && !canal.isBlank();
        }
    }

    interface Bonificable {
        double calcularBonoAnual();
    }

    // --- CLASE ABSTRACTA BASE ---
    abstract static class Empleado implements Notificable {
        private final String id;
        private final String nombre;

        public Empleado(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public String getId() { return id; }
        public String getNombre() { return nombre; }

        // Método abstracto: obliga a cada subclase a definir cómo calcula su salario
        public abstract double calcularSalarioMensual();

        // Implementación de la interfaz Notificable
        @Override
        public void enviarNotificacion(String mensaje) {
            System.out.printf("Notificando al empleado %s (%s): %s%n", nombre, id, mensaje);
            logNotificacion("Notificación enviada a " + nombre);
        }

        @Override
        public String toString() {
            return String.format("[%s] %s | Salario: $%.2f", id, nombre, calcularSalarioMensual());
        }
    }

    // --- SUBCLASES CONCRETAS ---
    static class EmpleadoAsalariado extends Empleado implements Bonificable {
        private double salarioFijoMensual;

        public EmpleadoAsalariado(String id, String nombre, double salarioFijoMensual) {
            super(id, nombre); // Llama al constructor de la clase abstracta
            this.salarioFijoMensual = salarioFijoMensual;
        }

        @Override
        public double calcularSalarioMensual() {
            return salarioFijoMensual;
        }

        @Override
        public double calcularBonoAnual() {
            // Bono equivalente a 1.5 meses de sueldo fijo
            return salarioFijoMensual * 1.5;
        }
    }

    static class EmpleadoPorComision extends Empleado implements Bonificable {
        private double sueldoBase;
        private double totalVentasMes;
        private double porcentajeComision; // Ej: 0.10 para 10%

        public EmpleadoPorComision(String id, String nombre, double sueldoBase, double porcentajeComision) {
            super(id, nombre);
            this.sueldoBase = sueldoBase;
            this.porcentajeComision = porcentajeComision;
            this.totalVentasMes = 0.0;
        }

        public void registrarVenta(double monto) {
            this.totalVentasMes += monto;
        }

        @Override
        public double calcularSalarioMensual() {
            return sueldoBase + (totalVentasMes * porcentajeComision);
        }

        @Override
        public double calcularBonoAnual() {
            return 1000.0; // Bono fijo de incentivo
        }
    }

    // --- DEMOSTRACIÓN DE POLIMORFISMO ---
    public static void main(String[] args) {
        System.out.println("=== 1. POLIMORFISMO DINÁMICO EN ACCIÓN ===");
        
        // Creamos una lista homogénea de tipo abstracto 'Empleado'
        List<Empleado> nomina = new ArrayList<>();

        EmpleadoAsalariado emp1 = new EmpleadoAsalariado("EMP-001", "Lucía Morales", 3200.0);
        EmpleadoPorComision emp2 = new EmpleadoPorComision("EMP-002", "Javier Soto", 1200.0, 0.15);
        emp2.registrarVenta(25000.0); // 15% de 25,000 = 3,750 extra

        nomina.add(emp1);
        nomina.add(emp2);

        double costoNominaTotal = 0;

        for (Empleado e : nomina) {
            // Vinculación Dinámica (Dynamic Binding):
            // La JVM resuelve en tiempo de ejecución qué método 'calcularSalarioMensual()' ejecutar
            // según el tipo REAL del objeto en memoria, no el tipo de la variable de referencia.
            System.out.println(e);
            costoNominaTotal += e.calcularSalarioMensual();

            // Pattern Matching for instanceof (Java 16+):
            // Comprueba el tipo y crea la variable con el cast de forma automática y segura
            if (e instanceof EmpleadoPorComision comisionado) {
                System.out.println("  -> (Es vendedor comisionado, ventas del mes: $" + 
                                   (comisionado.calcularSalarioMensual() - 1200.0) / 0.15 + ")");
            }

            if (e instanceof Bonificable conBono) {
                System.out.printf("  -> Bono proyectado: $%.2f%n", conBono.calcularBonoAnual());
            }

            e.enviarNotificacion("Tu recibo de sueldo está disponible.");
            System.out.println();
        }

        System.out.printf("Costo total de nómina de la empresa: $%.2f%n", costoNominaTotal);
    }
}
