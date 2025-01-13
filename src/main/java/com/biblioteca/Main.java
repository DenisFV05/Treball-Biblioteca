package com.biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int optMain = 0;

        // MENU PRINCIPAL
        do {
            System.out.println("""
                -- Gestió de biblioteca --
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

    // MENU LIBROS
    public static void menuLibros(Scanner scanner) {
        int optLibros;

        do {
            System.out.println("""
                -- Gestió de llibres --
                1. Afegir
                2. Modificar
                3. Eliminar
                4. Llistar
                0. Tornar al menú principal
                Escull una opció: """);
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

    // MENU PRINCIPAL LISTAR LIBROS
    public static void menuListarLibros(Scanner scanner) {
        int optListarLibros;

        do {
            System.out.println("""
                -- Llistar Llibres --
                1. Tots
                2. En préstec
                3. Per autor
                4. Cercar títol
                0. Tornar al menú de llibres
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

    // MENU MODIFICAR LIBROS
    public static void menuModificarLibros(Scanner scanner){
        int optModificarLibros;

        do { 
            System.out.println("""
                -- Modificar Llibres --
                1. Titol
                2. Autor
                0. Tornar al menú de llibres
                Escull una opció: """);
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

    // MENU USUARIOS
    public static void menuUsuarios(Scanner scanner){
        int optUsuarios;

        do { 
            System.out.println("""
                -- Gestió d'usuaris --
                1. Afegir
                2. Modificar
                3. Eliminar
                4. Llistar
                0. Tornar al menú principal
                Escull una opció: """);
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
                        limpiarPantalla();
                        menuListarUsuaris(scanner);
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

    // MENU MODIFICAR USUARIOS
    public static void menuModificarUsuarios(Scanner scanner){
        int optModificarUsuario;

        do { 
            System.out.println("""
                -- Modificar Usuari --
                1. Nom
                2. Cognom
                3. Telèfon
                0. Tornar al menú d'usuaris
                Escull una opció: """);
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

    // MENU MODIFICAR APELLIDOS
    public static void menuModificarCognoms(Scanner scanner) {
        int optListarUsuaris;

        do { 
            System.out.println("""
                -- Modificar Cognoms --
                1. Primer cognom
                2. Segon cognom
                0. Tornar al menú d'usuaris
                Escull una opció: """);
            if (scanner.hasNextInt()){
                optListarUsuaris = scanner.nextInt();

                switch (optListarUsuaris){
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
                optListarUsuaris = -1;
            }
        } while (optListarUsuaris != 0);
    }

    // MENU LISTAR USUARIOS
    public static void menuListarUsuaris (Scanner scanner){
        int optListarLibros;

        do {
            System.out.println("""
                -- Llistar Usuaris --
                1. Tots
                2. Per primer cognom
                3. Per segon cognom
                4. Per teléfon
                0. Tornar al menú d'usuaris
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
