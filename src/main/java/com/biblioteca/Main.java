package com.biblioteca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Llamar función del Menu
        Menu.mostrarMenuPrincipal(scanner);
        
        scanner.close();
    }
}
