package com.biblioteca;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Llibres {

    public static void llistatLlibres(){
        try{
            // Ruta de l'arxiu json llibres
            String rutaJsonLlibres = "src/main/json/llibres.json";
            String contingutLlibres = new String(Files.readAllBytes(Paths.get(rutaJsonLlibres)));
            
            // Convertim el contingut a un JSONArray
            JSONArray llibres = new JSONArray(contingutLlibres);
            
            // Iterem per accedir a cadasucn dels llibres
            for (int i = 0; i < llibres.length(); i++){
                // Agafem cada llibre com a Object
                JSONObject llibre = llibres.getJSONObject(i);

                // Guardem les dades del llibre
                int id = llibre.getInt("idLlibre");
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


    public static void llistatLlibresPrestec(){
        try {
            // Agafem la ruta de l'arxiu json llibres i del prestecs
            String rutaJsonLlibres = "src/main/json/llibres.json";
            String contingutLlibres = new String(Files.readAllBytes(Paths.get(rutaJsonLlibres)));

            String rutaJsonPrestecs = "src/main/json/prestecs.json";
            String contingutPrestecs = new String(Files.readAllBytes(Paths.get(rutaJsonPrestecs)));

            // Convertim el contingut a JSONArray
            JSONArray llibres = new JSONArray(contingutLlibres);

            JSONArray prestecs = new JSONArray(contingutPrestecs);

            if (prestecs.length() == 0){
                System.out.println("No hi ha cap llibre en prestec actualment!");
            } else {
                // Mostrem la capçelera de la llista
                System.out.println("-".repeat(33) + "   " + "LLISTAT DE LLIBRES EN PRESTEC" + "    " + "-".repeat(33));
                System.out.printf("%-5s | %-45s | %-35s", "ID", "TITOL", "AUTOR");
                System.out.println();
                System.out.println("-".repeat(100));

                // Iterem entre tots els llibres en prestec
                for (int i = 0; i < prestecs.length(); i++){
                    
                    // Convertim a JSONObject
                    JSONObject prestec = prestecs.getJSONObject(i);

                    // Agafem el id del llibre per mostrar la informació d'aquest
                    int idLlibrePrestec = prestec.getInt("idLlibre");

                    // Com que ja tenim el seu id, iterem el jsonarray de llibres per buscar l'id i mostrar la informacio
                    for (int j = 0; j < llibres.length(); j++){

                        // Convertim a JSONObject
                        JSONObject llibre = llibres.getJSONObject(j);

                        // Agafem el idLlibre per el qual anem iterant
                        int idLlibreLlibre = llibre.getInt("idLlibre");

                        // Comprovem si el llibre pel qual estem iterant coincideix la id
                        if (idLlibrePrestec == idLlibreLlibre){

                            // Guardem les dades del llibre
                            String titol = llibre.getString("titol");
                            JSONArray autor = llibre.getJSONArray("autor");

                            // Mostrem la informacio del llibre en la llista
                            System.out.printf("%-5d | %-45s | ", idLlibreLlibre, titol);
                            for (int k = 0; k < autor.length(); k++){
                                if (k != 0){
                                    System.out.print(" / " + autor.getString(k));
                                }else{
                                    System.out.print(autor.getString(k));
                                }
                            }
                            System.out.println("");
                            if (i == (prestecs.length() - 1)){
                                System.out.println();
                            } else {
                                System.out.println("-".repeat(100));
                            }
                        }
                    }
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    
    public static void llistatLlibresAutor(){
        try{
            // Ruta de l'arxiu json llibres
            String rutaJsonLlibres = "src/main/json/llibres.json";
            String contingutLlibres = new String(Files.readAllBytes(Paths.get(rutaJsonLlibres)));
        
            // Demanem la paraula autor
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el nombre del autor: ");
            String autorIntroduit = scanner.nextLine();
            
            // Convertim el contingut a un JSONArray
            JSONArray llibres = new JSONArray(contingutLlibres);
        
            // Mostrem la capçelera de la llista
            System.out.println("-".repeat(33) + "   " + "LLISTAT DE LLIBRES PER AUTOR" + "    " + "-".repeat(33));
            System.out.printf("%-5s | %-45s | %-35s", "ID", "TITOL", "AUTOR");
            System.out.println();
            System.out.println("-".repeat(100));

            boolean llibreTrobat = false;

            // Iterem per accedir a cadasucn dels llibres
            for (int i = 0; i < llibres.length(); i++){
                // Agafem cada llibre com a Object
                JSONObject llibre = llibres.getJSONObject(i);
        
                // Guardem les dades del llibre
                int id = llibre.getInt("idLlibre");
                String titol = llibre.getString("titol");
                JSONArray autor = llibre.getJSONArray("autor");
        
                // Si l'autor introduit es troba, es mostren les dades d'aquest
                for (int j = 0; j < autor.length(); j++){
                    if ((autor.getString(j).toLowerCase()).contains(autorIntroduit.toLowerCase())){
                        // Sabem que s'ha trobat almenys un llibre de l'autor, per tant, no mostrarem missatge de que no s'ha trobat cap llibre
                        llibreTrobat = true;
                        System.out.printf("%-5s | %-45s | ", id, titol);
                        for (int k = 0; k < autor.length(); k++){
                            if (k != 0){
                                System.out.print(" / " + autor.getString(k));
                            }else{
                                System.out.print(autor.getString(k));
                            }
                        }
                        System.out.println("");
                        if (i == (llibres.length() - 1)){
                            System.out.println();
                        } else {
                            System.out.println("-".repeat(100));
                        }
                    }
                }
            }
            if (!llibreTrobat) {
                System.out.println("No s'han trobat llibres per a l'autor introduït.");
                System.out.println();   // Salto de linea para mejora visual
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void llistatLlibresTitol(){
        try{
            // Ruta de l'arxiu json llibres
            String rutaJsonLlibres = "src/main/json/llibres.json";
            String contingutLlibres = new String(Files.readAllBytes(Paths.get(rutaJsonLlibres)));
            
            // Demanem la paraula que volem buscar en el titol
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdueix la paraula que vols buscar en els titols: ");
            String paraulaTitol = scanner.nextLine();
        
            // Convertim el contingut a JSONArray
            JSONArray llibres = new JSONArray(contingutLlibres);
        
            //Iterem per accedir a cadascun dels llibres
            for (int i = 0; i < llibres.length(); i++){
                // Agafem cada llibre com a Object
                JSONObject llibre = llibres.getJSONObject(i);
        
                // Guardem les dades del llibre
                int id = llibre.getInt("idLlibre");
                String titol = llibre.getString("titol");
                JSONArray autor = llibre.getJSONArray("autor");
        
                // Mostrem la capçelera de la llista
                if (i == 0){
                    System.out.println("-".repeat(33) + "   " + "LLISTAT DE LLIBRES PER TITOL" + "    " + "-".repeat(33));
                    System.out.printf("%-5s | %-45s | %-35s", "ID", "TITOL", "AUTOR");
                    System.out.println();
                    System.out.println("-".repeat(100));
                }
        
                // Si trobem la paraula introduida en algun titol, mostrem el id, titol i autor/autors del llibre
                if ((titol.toLowerCase()).contains((paraulaTitol.toLowerCase()))){
                    System.out.printf("%-5d | %-45s | ", id, titol);
                    for (int j = 0; j < autor.length(); j++){
                        if (j != 0){
                            System.out.print(" / " + autor.getString(j));
                        } else {
                            System.out.print(autor.getString(j));
                        }
                    }
                    // Deixem el salt de linea entre llibre i llibre
                    System.out.println();
                    if (i == (llibres.length() - 1)){
                        System.out.println();
                    } else {
                        System.out.println("-".repeat(100));
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void afegirLlibre(){
        try {
            
            // Ruta de l'arxiu json llibres
            String rutaJsonLlibres = "src/main/json/llibres.json";
            String contingutLlibres = new String(Files.readAllBytes(Paths.get(rutaJsonLlibres)));

            // Ruta de l'arxiu json prestecs
            String rutaJsonPrestecs = "src/main/json/prestecs.json";
            String contingutPrestecs = new String(Files.readAllBytes(Paths.get(rutaJsonPrestecs)));

            // Convertim els continguts a JSONArrays per recorrer posteriorment
            JSONArray llibres = new JSONArray(contingutLlibres);
            JSONArray prestecs = new JSONArray(contingutPrestecs);

            // Demanem el titol i l'autor o autors del llibre a afegir
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdueix el titol del nou llibre: ");
            String titolLlibreAfegir = scanner.nextLine();

            System.out.print("Introdueix l'autor o els autors del nou llibre (Separats per comes ','): ");
            String autors = scanner.nextLine();

            // idLlibre ha de ser unic
            int id = llibres.length() + 1;

            if (!(autors.contains(","))){
                // Afegim l'unic autor a format llista
                List<String> llistaAutors = new ArrayList<>();
                llistaAutors.add(autors);

                // Afegim les claus al llibre
                JSONObject nouLlibre = new JSONObject();

                nouLlibre.put("idLlibre", id);
                nouLlibre.put("titol", titolLlibreAfegir);
                nouLlibre.put("autor", llistaAutors);

                // Afegim el llibre al JSONArray
                llibres.put(nouLlibre);

                // Actualitzem el llibres.json
                try (FileWriter actulitzar = new FileWriter(rutaJsonLlibres)){
                    actulitzar.write(llibres.toString(4));
                    actulitzar.flush();
                }

            } else {
                // Afegim els autors a format llista
                List<String> llistaAutors = new ArrayList<>();
                for (String autor : autors.split(",")){
                    llistaAutors.add(autor.trim());     // Treu espais innecessaris
                }

                // Afegim les claus al llibre
                JSONObject nouLlibre = new JSONObject();

                nouLlibre.put("idLlibre", id);
                nouLlibre.put("titol", titolLlibreAfegir);
                nouLlibre.put("autor", llistaAutors);

                // Afegim el llibre al JSONArray
                llibres.put(nouLlibre);

                // Actualitzem el llibres.json
                try (FileWriter actulitzar = new FileWriter(rutaJsonLlibres)){
                    actulitzar.write(llibres.toString(4));
                    actulitzar.flush();
                }
            }

            System.out.printf("El llibre [%s] amb id [%d] escrit per [%s] s'ha afegit correctament", titolLlibreAfegir, id, autors);

            // Salt de linea
            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
