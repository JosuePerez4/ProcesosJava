/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package procesos;

import java.util.Scanner;

/**
 *
 * @author izz00
 */
public class Procesos {

    public static void main(String[] args) {
        Metodos metodos = new Metodos();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n¿Qué quieres hacer?");
                System.out.println("1. Ejecutar comando");
                System.out.println("2. Finalizar proceso (kill)");
                System.out.println("3. Salir");
                System.out.print("Selecciona una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1 -> {
                        System.out.println("Escribe un comando para ejecutar:");
                        String comando = scanner.nextLine();
                        metodos.listarProcesos(comando);
                    }
                    case 2 -> {
                        System.out.println("Escribe el PID del proceso que quieres finalizar:");
                        int pid = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        metodos.killProcess(pid);

                        // Añadir una pausa para mostrar el error antes de continuar
                        System.out.println("Presiona Enter para volver al menú.");
                        scanner.nextLine();  // Esperar que el usuario presione Enter antes de continuar
                    }
                    case 3 -> {
                        continuar = false;
                        System.out.println("Saliendo...");
                    }
                    default ->
                        System.out.println("Opción inválida, intenta de nuevo.");
                }
            }
        }
    }
}
