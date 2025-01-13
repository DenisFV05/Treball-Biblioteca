package com.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class Llibre {




    
    // Método para leer el archivo JSON de libros y mostrar su contenido
    public static void mostrarLlibres() {
        try {
            String filePath = "./src/main/json/llibres.json";  
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            // Mostrar los libros
            System.out.println("Listado de Libros:");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject llibre = jsonArray.getJSONObject(i); // Obtener cada objeto (libro)
                int id = llibre.getInt("id");
                String titol = llibre.getString("titol");
                String autor = llibre.getString("autor"); 

                // Mostrar los datos del libro
                System.out.println("ID: " + id + ", Títol: " + titol + ", Autor: " + autor);
                System.out.println("-----------------------------");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }

}
