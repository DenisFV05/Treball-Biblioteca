// TREBALL EN GRUP | ALEX | DENIS | JAVI

package com.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        try{
            // Ruta de l'arxiu json llibres
            String rutaJsonLlibres = "src/main/json/llibres.json";
            String contingut = new String(Files.readAllBytes(Paths.get(rutaJsonLlibres)));

            // Convertim el contingut a un JSONArray
            JSONArray llibres = new JSONArray(contingut);

            // Iterem per accedir a cadasucn dels llibres
            for (int i = 0; i < llibres.length(); i++){
                // Agafem cada llibre com a Object
                JSONObject llibre = llibres.getJSONObject(i);

                // Guardem les dades del llibre
                int id = llibre.getInt("id");
                String titol = llibre.getString("titol");
                String autor = llibre.getString("autor");

                // Mostrem les dades del llibre
                if (i == 0){
                    System.out.println("----- LLISTAT DE LLIBRES -----");
                }
                System.out.println("ID: " + id);
                System.out.println("TITOL: " + titol);
                System.out.println("AUTOR: " + autor);
                if (i == (llibres.length() - 1)){
                    System.out.println();
                } else {
                    System.out.println("------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

