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
                JSONArray autor = llibre.getJSONArray("autor");

                // Mostrem les dades del llibre
                if (i == 0){
                    System.out.println("-".repeat(38) + "   " + "LLISTAT DE LLIBRES" + "    " + "-".repeat(38));
                    System.out.printf("%-5s | %-45s | %-35s", "ID", "TITOL", "AUTOR");
                    System.out.println();
                    System.out.println("-".repeat(100));
                }
                System.out.printf("%-5d | %-45s | ", id, titol);
                for (int j = 0; j < autor.length(); j++){
                    if (j != 0){
                        System.out.print(" / " + autor.getString(j));
                    }else{
                        System.out.print(autor.getString(j));
                    }
                }
                System.out.println("");
                if (i == (llibres.length() - 1)){
                    System.out.println();
                } else {
                    System.out.println("-".repeat(100));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

