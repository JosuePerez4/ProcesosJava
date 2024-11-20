/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author izz00
 */
public class Metodos {

    /**
     * Lista los procesos en ejecución ejecutando un comando dado.
     *
     * @param comando Comando a ejecutar para listar los procesos.
     */
    public void listarProcesos(String comando) {
    // Validar que el comando sea exactamente "ps au" o "ps aux"
    if (!comando.equals("ps au") && !comando.equals("ps aux")) {
        System.err.println("Error: El comando ingresado no está permitido. Use 'ps au' o 'ps aux'.");
        return; // Salir del método si el comando no es válido
    }

    // Divide el comando en partes para ejecutarlo con ProcessBuilder
    String[] partesComando = comando.split(" ");

    try {
        ProcessBuilder processBuilder = new ProcessBuilder(partesComando);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        System.out.println("Procesos en ejecución:");

        while ((line = reader.readLine()) != null) {
            System.out.println(line); // Imprimir cada línea del resultado
        }

        process.waitFor();

    } catch (IOException | InterruptedException e) {
        System.err.println("Error al ejecutar el comando: " + e.getMessage());
    }
}

    /**
     * Verifica si un PID existe en el sistema.
     *
     * @param pid PID a verificar.
     * @return true si el PID existe, false en caso contrario.
     */
    public boolean verificarPID(int pid) {
        try {
            // Comando para buscar el PID
            ProcessBuilder processBuilder = new ProcessBuilder("ps", "-p", String.valueOf(pid));
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            boolean encontrado = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(String.valueOf(pid))) {
                    encontrado = true;
                    break;
                }
            }

            process.waitFor();
            return encontrado;

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al verificar el PID: " + e.getMessage());
            return false;
        }
    }

    /**
     * Finaliza un proceso con un PID dado si existe.
     *
     * @param pid PID del proceso a finalizar.
     */
    public void killProcess(int pid) {
        if (!verificarPID(pid)) {
            System.err.println("Error: El proceso con PID " + pid + " no existe.");
            return;
        }

        try {
            ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", String.valueOf(pid));
            Process killProcess = killProcessBuilder.start();
            killProcess.waitFor();

            System.out.println("Proceso con PID " + pid + " finalizado.");

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al finalizar el proceso: " + e.getMessage());
        }
    }

    /**
     * Valida que el comando ingresado no sea vacío o inválido.
     *
     * @param comando Comando a validar.
     * @return true si el comando es válido, false en caso contrario.
     */
    public boolean validarComando(String comando) {
        if (comando == null || comando.isBlank()) {
            System.err.println("Error: El comando no puede estar vacío.");
            return false;
        }
        return true;
    }
}
