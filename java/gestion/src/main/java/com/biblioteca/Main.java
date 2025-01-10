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
                              "\n3. Eliminar" +
                              "\n4. Llistar" +
                              "\n0. Tornar al menú principal" +
                              "\nEscull una opció: ");
            if (scanner.hasNextInt()) {
                optLibros = scanner.nextInt();

                switch (optLibros) {
                    case 1:
                        // Afegir llibre
                        break;
                    case 2:
                        // Modificar llibre
                        break;
                    case 3:
                        // Eliminar llibre
                        break;
                    case 4:
                        limpiarPantalla();
                        menuListarLibros(scanner);
                        break;
                    case 0:
                        limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opció no vàlida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n");
                scanner.next();
                optLibros = -1;
            }
        } while (optLibros != 0);
    }

    // Menú per llistar llibres
    public static void menuListarLibros(Scanner scanner) {
        int optListarLibros;

        do {
            System.out.println("Llistar llibres\n1. Tots" +
                                "\n2. En préstec" +
                                "\n3. Per autor" +
                                "\n4. Cercar títol" +
                                "\n0. Tornar al menú principal" +
                                "\nEscull una opció: ");
            if (scanner.hasNextInt()) {
                optListarLibros = scanner.nextInt();

                switch (optListarLibros) {
                    case 1:
                        // Llistar tots els llibres
                        break;
                    case 2:
                        // Llistar llibres en préstec
                        break;
                    case 3:
                        // Llistar per autor
                        break;
                    case 4:
                        // Cercar títol
                        break;
                    case 0:
                        limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opció no vàlida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n");
                scanner.next();
                optListarLibros = -1;
            }
        } while (optListarLibros != 0);
    }
}
