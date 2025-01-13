package com.biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int optMain = 0;

        // Mostrar el menu per consola
        do {
            System.out.println("""
                Gestió de biblioteca
                1. Llibres
                2. Usuaris
                3. Préstecs
                0. Sortir
                Escull una opció: """);
            if (scanner.hasNextInt()) { 
                optMain = scanner.nextInt();

                switch (optMain) {
                    case 1:
                        limpiarPantalla();
                        menuLibros(scanner);
                        break;
                    case 2:
                        limpiarPantalla();
                        menuUsuarios(scanner);
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
                        limpiarPantalla();
                        menuModificarLibros(scanner);
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
            System.out.println("Llistar Llibres\n1. Tots" +
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

    public static void menuModificarLibros(Scanner scanner){
        int optModificarLibros;

        do { 
            System.out.println("Modificar Llibres\n1.Titol" +
                                "\n2.Autor" +
                                "\n0. Tornar al menú principal");
            if (scanner.hasNextInt()) {
                optModificarLibros = scanner.nextInt();

                switch (optModificarLibros) {
                    case 1:
                        
                        break;
                    case 2:

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
                optModificarLibros = -1;
            }
        } while (optModificarLibros != 0);
    }

    public static void menuUsuarios(Scanner scanner){
        int optUsuarios;

        do { 
            System.out.println("Gestió d'usuaris\n1. Afegir" +
                              "\n2. Modificar" +
                              "\n3. Eliminar" +
                              "\n4. Llistar" +
                              "\n0. Tornar al menú principal" +
                              "\nEscull una opció");
            if (scanner.hasNextInt()) {
                optUsuarios = scanner.nextInt();

                switch (optUsuarios) {
                    case 1:
                        
                        break;
                    case 2:
                        limpiarPantalla();
                        menuModificarUsuarios(scanner);
                        break;
                    case 3:

                        break;
                    case 4:

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
                optUsuarios = -1;
            }
        } while (optUsuarios != 0);
    }

    public static void menuModificarUsuarios(Scanner scanner){
        int optModificarUsuario;

        do { 
            System.out.println("Modificar Usuari\n1. Nom" +
                                "\n2. Cognoms" +
                                "\n3. Telèfon" +
                                "\n0. Tornar al menú principal" +
                                "\nEscull una opció");
            if (scanner.hasNextInt()){
                optModificarUsuario = scanner.nextInt();

                switch (optModificarUsuario) {
                    case 1:
                        
                        break;
                    case 2:
                        limpiarPantalla();
                        menuModificarCognoms(scanner);
                        break;
                    case 3:

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
                optModificarUsuario = -1; 
            }
        } while (optModificarUsuario != 0);
    }

    public static void menuModificarCognoms(Scanner scanner) {
        int optListarUsuaris;

        do { 
            System.out.println("Llistar Usuaris\n1. Tots" +
                                "\n2. Per nom" +
                                "\n3. Per cognom" +
                                "\n4. Per telèfon" +
                                "\n0. Tornar al menú principal" +
                                "\nEscull una opció: ");
            if (scanner.hasNextInt()){
                optListarUsuaris = scanner.nextInt();

                switch (optListarUsuaris){
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
                    System.out.println("Opció no vàlida. Torna-ho a intentar.\n");
                }
            } else {
                System.out.println("Si us plau, ingressa un número vàlid.\n");
                scanner.next();
                optListarUsuaris = -1;
            }
        } while (optListarUsuaris != 0);
    }

    public static void menuListarUsuaris (Scanner scanner){
        int optListarLibros;

        do {
            System.out.println("""
                Llistar Llibres
                1. Tots +
                2. En préstec +
                3. Per autor +
                4. Cercar títol +
                0. Tornar al menú principal +
                Escull una opció: """);
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
