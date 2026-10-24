import java.util.Objects;

/**
 * Lección 01 del Módulo de POO:
 * Encapsulamiento riguroso, invariantes de clase y métodos fundamentales de java.lang.Object.
 */
public class CuentaBancariaDemo {

    public static class CuentaBancaria {
        // Variables de clase (estáticas): compartidas por todas las instancias
        private static int contadorCuentas = 0;
        private static double tasaInteresAnual = 0.05; // 5%

        // Variables de instancia: estado propio de cada objeto
        private final String numeroCuenta; // 'final' significa inmutable tras la construcción
        private String titular;
        private double saldo;

        // Constructor principal
        public CuentaBancaria(String titular, double saldoInicial) {
            if (titular == null || titular.trim().isEmpty()) {
                throw new IllegalArgumentException("El titular no puede ser nulo ni vacío.");
            }
            if (saldoInicial < 0) {
                throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
            }

            contadorCuentas++;
            this.numeroCuenta = String.format("CTA-%05d", contadorCuentas);
            this.titular = titular.trim();
            this.saldo = saldoInicial;
        }

        // Constructor secundario (Sobrecarga / Constructor Chaining)
        public CuentaBancaria(String titular) {
            this(titular, 0.0); // Delega en el constructor principal
        }

        // Getters (Lectura controlada)
        public String getNumeroCuenta() { return numeroCuenta; }
        public String getTitular() { return titular; }
        public double getSaldo() { return saldo; }

        // Setter con validación de negocio
        public void setTitular(String nuevoTitular) {
            if (nuevoTitular == null || nuevoTitular.trim().isEmpty()) {
                throw new IllegalArgumentException("Nombre de titular inválido.");
            }
            this.titular = nuevoTitular.trim();
        }

        // Métodos de comportamiento de negocio
        public void depositar(double monto) {
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
            }
            this.saldo += monto;
            System.out.printf("[%s] Depósito exitoso de $%.2f. Nuevo saldo: $%.2f%n", numeroCuenta, monto, saldo);
        }

        public boolean retirar(double monto) {
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
            }
            if (monto > this.saldo) {
                System.out.printf("[%s] Fondos insuficientes. Solicitado: $%.2f | Disponible: $%.2f%n",
                                  numeroCuenta, monto, saldo);
                return false;
            }
            this.saldo -= monto;
            System.out.printf("[%s] Retiro exitoso de $%.2f. Nuevo saldo: $%.2f%n", numeroCuenta, monto, saldo);
            return true;
        }

        public static int getCantidadCuentasCreadas() {
            return contadorCuentas;
        }

        // Sobrescritura de métodos de java.lang.Object:

        @Override
        public String toString() {
            return String.format("CuentaBancaria{numero='%s', titular='%s', saldo=$%.2f}",
                                 numeroCuenta, titular, saldo);
        }

        // Contrato equals y hashCode: dos cuentas son iguales si tienen el mismo numeroCuenta
        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // Misma referencia en memoria
            if (o == null || getClass() != o.getClass()) return false; // Nulo o de clase distinta
            CuentaBancaria that = (CuentaBancaria) o;
            return Objects.equals(this.numeroCuenta, that.numeroCuenta);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numeroCuenta);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE ENCAPSULAMIENTO Y VALIDACIÓN ===");

        CuentaBancaria cta1 = new CuentaBancaria("Juan Perez", 1500.0);
        CuentaBancaria cta2 = new CuentaBancaria("Maria Lopez"); // Inicia con saldo 0

        System.out.println("Cta 1 creada: " + cta1);
        System.out.println("Cta 2 creada: " + cta2);

        cta1.depositar(500.0);
        cta1.retirar(300.0);
        cta1.retirar(5000.0); // Intento de sobregiro

        cta2.depositar(1200.0);

        System.out.println("\nTotal de cuentas en el sistema: " + CuentaBancaria.getCantidadCuentasCreadas());
        System.out.println("¿cta1 es igual a cta2?: " + cta1.equals(cta2));
    }
}
