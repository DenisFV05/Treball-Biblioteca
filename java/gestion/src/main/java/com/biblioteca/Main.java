package com.biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int optMain = 0;

        // Mostrar el menu per consola
        do {
            System.out.println("Gestió de biblioteca\n1. Llibres" +
                              "\n2. Usuaris" +
                              "\n3. Préstecs" +
                              "\n0. Sortir" +
                              "\nEscull una opció: ");
            
            if (scanner.hasNextInt()) { 
                optMain = scanner.nextInt();

                switch (optMain) {
                    case 1:
                        limpiarPantalla();
                        menuLibros(scanner);
                        break;
                    case 2:
                        // Aquí podries afegir la gestió d'usuaris
                        break;
                    case 3:
                        // Aquí podries afegir la gestió de préstecs
                        break;
                    case 0:
                        limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opció no vàlida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n ");
                scanner.next();
                optMain = -1;
            }
        } while (optMain != 0);
                
        scanner.close();
    }

    // Funció per netejar la pantalla
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    // Menú de gestió de llibres
    public static void menuLibros(Scanner scanner) {
        int optLibros;

        do {
            System.out.println("Gestió de llibres\n1. Afegir" +
                              "\n2. Modificar" +
                  
