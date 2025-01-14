package com.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Prestec {
    public static void prestecFuncionAgafarNouLlibre(Scanner scanner){
        try {
            // Variables del archivo (prestecs.json)
            String filePathPrestecs = "./src/main/json/prestecs.json";
            String contentPrestecs = new String(Files.readAllBytes(Paths.get(filePathPrestecs)));

            JSONArray jsonArrayPrestecs = new JSONArray(contentPrestecs);

            // Variables del archivo (usuaris.json)
            String filePathUsuaris = "./src/main/json/usuaris.json";
            String contentUsuaris = new String(Files.readAllBytes(Paths.get(filePathUsuaris)));

            JSONArray jsonArrayUsuaris = new JSONArray(contentUsuaris);

            // Variables del archivo (llibres.json)
            String filePathLlibres = "./src/main/json/llibres.json";
            String contentLlibres = new String(Files.readAllBytes(Paths.get(filePathLlibres)));

            JSONArray jsonArrayLlibres = new JSONArray(contentLlibres);

        /////////////////////////////////// [LISTADO DE USUARIOS Y PODER ESCOGERLO] ///////////////////////////////

            // Header de la lista de usuarios
            System.out.println("\nID    | Nom            | Cognoms             | Teléfon");
            System.out.println("--------------------------------------------------------");

            // Iterar los usuarios
            for (int i = 0; i < jsonArrayUsuaris.length(); i++) {
                JSONObject usuari = jsonArrayUsuaris.getJSONObject(i);

                // Dar formato a las variables de los usuarios
                int id = usuari.getInt("id");
                String nom = usuari.getString("nom");
                String cognoms = usuari.getString("cognoms");
                int telefon = usuari.getInt("telefon");

                // Imprimir los datos de los usuarios
                System.out.printf("%-6d| %-15s| %-20s| %-10d%n", id, nom, cognoms, telefon);
            }
            
            // Solicitar el ID y verificar el Input
            int selectUsuariId = -1; // Se añade un valor no valido para que salte el comprobante
            boolean inputValido = false;
            while (!inputValido){
                System.out.println("\nIntrodueix l'ID d'un usuari per seleccionar-lo: ");

                try {
                    selectUsuariId = scanner.nextInt();
                    inputValido = true; // Si el selectUsuariId es un (int) es = true
                } catch (Exception e) {
                    System.out.println("Error: Has d'introduir un número vàlid per seleccionar l'usuari.");
                    scanner.nextLine();
                }
            }

            // Buscar el usuario en base el ID seleccionado
            JSONObject usuariSeleccionado = null;
            for (int i = 0; i < jsonArrayUsuaris.length(); i++){
                JSONObject usuari = jsonArrayUsuaris.getJSONObject(i);
                int id = usuari.getInt("id");

                // Si encuentra el ID escrito en usuari.json
                if(id == selectUsuariId) {
                    // [GUARDADO]
                    usuariSeleccionado = usuari; // Donde se guardara el usuario para mas tarde
                }
            }

            // Si no se encuentra el ID (Mensajes)
            if (usuariSeleccionado == null) {
                System.out.println("No s'ha trobat l'usuari amb l'ID: " + selectUsuariId);
            } else {
                System.out.println("L'usuari amb ID: " + selectUsuariId + " ha estat seleccionat.\n");
            }

        ///////////////////////////////////// [ LISTAR LIBROS DISPONIBLES] /////////////////////////////////////////////

            // Header de la lista de libros disponibles
            System.out.println("\nID    | Nom            | Cognoms             | Teléfon");
            System.out.println("--------------------------------------------------------");
            

        } catch (Exception e) {
            // Control de manejo de expeciones
            System.err.println("Ha hagut un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
