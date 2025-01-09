// TREBALL EN GRUP | ALEX | DENIS | JAVI

package com.biblioteca;

import java.util.Scanner;

public class Main {
    public static void limpiarPantalla(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int optMain = 0;

        // Mostrar el menu por consola
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

                        break;
                    case 3:

                        break;
                    case 0:
                        limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opció no válida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n ");
                scanner.next();
                optMain = -1;
            }
        } while (optMain != 0);
                
        scanner.close();
            
    }

    public static void menuLibros(Scanner scanner){
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
                        
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        limpiarPantalla();
                        menuListarLibros(scanner);
                        break;
                    case 0:
                        limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opció no válida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n");
                scanner.next();
                optLibros = -1;
            }
        } while (optLibros != 0);
    }

    public static void menuListarLibros(Scanner scanner){
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
                        
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 0:
                        limpiarPantalla();
                        break;
                    default:
                        System.out.println("Opció no válida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n");
                scanner.next();
                optListarLibros = -1;
            }
        } while (optListarLibros != 0);
    }
}


