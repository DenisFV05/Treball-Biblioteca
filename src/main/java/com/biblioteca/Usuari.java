package com.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usuari {
    private static final String FILE_PATH = "src\\main\\json\\usuaris.json"; // Ruta del json

    // Llegeix el fitxer JSON i retorna un JSONArray
    public static JSONArray llegirFitxerJson() {
        try {
            String contingut = new String(Files.readAllBytes(Paths.get(FILE_PATH))); // Lee el archivo
            return new JSONArray(contingut);
        } catch (IOException e) {
            System.out.println("Error en llegir el fitxer JSON: " + e.getMessage()); // Error si no lo lee
            return new JSONArray();
        }
    }

    // Escriu un JSONArray al fitxer JSON
    public static void escriureFitxerJson(JSONArray jsonArray) {
        try {
            Files.write(Paths.get(FILE_PATH), jsonArray.toString(4).getBytes()); // Escribe en el archivo
        } catch (IOException e) {
            System.out.println("Error en escriure al fitxer JSON: " + e.getMessage()); // Error si no puede
        }
    }

    // Crea un nou usuari i l’afegeix al fitxer JSON
    public static void crearUsuari() {
        Scanner scanner = new Scanner(System.in); // Start del input
        try {
            JSONArray usuaris = llegirFitxerJson();

            System.out.print("\nIntrodueix el nom de l'usuari: "); // Pregunta y valida
            String nom = validarNom(scanner);

            System.out.print("\nIntrodueix el primer cognom: "); // Pregunta y valida
            String primerCognom = validarNom(scanner);

            System.out.print("\nIntrodueix el segon cognom: "); // Pregunta y valida
            String segonCognom = validarNom(scanner);

            System.out.print("\nIntrodueix el telèfon de l'usuari: "); // Pregunta y valida
            String telefon = validarTelefon(scanner);

            System.out.print("\nVols guardar aquest usuari? (s/n): ");
            String confirmacio = scanner.nextLine().trim().toLowerCase(); // Se confirma los datos del scanner

            if (confirmacio.equals("s")) { // Si la respuesta es si
                int nouId = obtenirSeguentId(usuaris); // id nueva teniendo en cuenta las que ya hay

                JSONObject nouUsuari = new JSONObject(); // Se crea el usuari nuevo
                nouUsuari.put("idUsuari", nouId);
                nouUsuari.put("nom", nom);
                nouUsuari.put("cognoms", primerCognom + " " + segonCognom);
                nouUsuari.put("telefon", telefon);

                usuaris.put(nouUsuari); // Se introducen en el json
                escriureFitxerJson(usuaris); // Se escribe en el json

                System.out.println("\nUsuari afegit correctament.");
            } else { // Si escribimos no o lo que sea
                System.out.println("\nUsuari no desat.");
            }
        } finally {
            // NO SE CIERRA EL SCANNER QUE DA PROBLEMAS
        }
    }

    // Valida el nom o cognom introduït per l'usuari
    private static String validarNom(Scanner scanner) {
        String entrada;
        System.out.print("Introdueix el nom (mínim 4 caràcters, sense espais): ");
        entrada = scanner.nextLine().trim(); // Obtenemos la entrada del nombre
        while (entrada.isEmpty() || entrada.length() < 4 || entrada.contains(" ")) { // Mientras el nombre este vacio, sea muy corto o tenga espacio
            System.out.println("Entrada no vàlida. Introdueix un valor vàlid (mínim 4 caràcters, sense espais):");
            entrada = scanner.nextLine().trim(); // Repetimos la pregunta
        }
        return entrada.substring(0, 1).toUpperCase() + entrada.substring(1).toLowerCase(); // Si esta bien devuelve String primera letra en mayus y el resto en minus
    }

    // Valida que el telèfon sigui correcte
    private static String validarTelefon(Scanner scanner) {
        String entrada;
        // Primero pedimos la entrada y luego validamos.
        System.out.print("Introdueix el telèfon (9 dígits): ");
        entrada = scanner.nextLine().trim(); // Obtenemos la entrada del nombre
        while (!entrada.matches("\\d{9}")) {// verifica si la cadena contiene exactamente 9 digitos consecutivos, \\d es cualquier digito 0-9
            System.out.print("Entrada no vàlida. Introdueix un telèfon vàlid (9 dígits): ");
            entrada = scanner.nextLine().trim(); // Repetimos la pregunta
        } 
        return entrada;
    }

    // Obté el següent ID disponible
    private static int obtenirSeguentId(JSONArray usuaris) {
        int maxId = 0;
        for (int i = 0; i < usuaris.length(); i++) { // Tiene en cuenta el numero de usuarios actual
            JSONObject usuari = usuaris.getJSONObject(i);
            maxId = Math.max(maxId, usuari.getInt("idUsuari")); // Usa la función para calcular el máximo
        }
        return maxId + 1; // Devuelve una id nueva
    }

    // Modificar un usuari existent
    public static void modificarUsuari() {
        Scanner scanner = new Scanner(System.in); // Abrir input
        try {
            JSONArray usuaris = llegirFitxerJson(); // Leer los usuarios

            System.out.print("Introdueix l'ID de l'usuari que vols modificar: "); // Introducir la ID del usuario a modificar
            int idUsuari = Integer.parseInt(scanner.nextLine());

            // Buscamos el usuario con el id proporcionado
            JSONObject usuari = buscarUsuariPerId(usuaris, idUsuari);

            if (usuari != null) { // Si el usuario no esta vacio
                System.out.print("Introdueix el nou nom (deixa en blanc per mantenir el valor actual): ");
                String nouNom = scanner.nextLine().trim();
                if (!nouNom.isEmpty()) { // Si no se pone nada se queda el valor actual
                    nouNom = validarNom(scanner);
                    usuari.put("nom", nouNom);
                }

                System.out.print("Introdueix el nou primer cognom (deixa en blanc per mantenir el valor actual): ");
                String nouPrimerCognom = scanner.nextLine().trim();
                if (!nouPrimerCognom.isEmpty()) { // Si no se pone nada se queda el valor actual
                    nouPrimerCognom = validarNom(scanner);
                    usuari.put("cognoms", nouPrimerCognom + " " + usuari.getString("cognoms").split(" ")[1]); // modificar solo el primer apellido de un usuario, manteniendo el segundo apellido intacto
                }

                System.out.print("Introdueix el nou segon cognom (deixa en blanc per mantenir el valor actual): ");
                String nouSegonCognom = scanner.nextLine().trim();
                if (!nouSegonCognom.isEmpty()) { // Si no se pone nada se queda el valor actual
                    nouSegonCognom = validarNom(scanner);
                    usuari.put("cognoms", usuari.getString("cognoms").split(" ")[0] + " " + nouSegonCognom); // modificar solo el segundo apellido
                }

                System.out.print("Introdueix el nou telèfon (deixa en blanc per mantenir el valor actual): ");
                String nouTelefon = scanner.nextLine().trim();
                if (!nouTelefon.isEmpty()) { // Si no se pone nada se queda el valor actual
                    nouTelefon = validarTelefon(scanner);
                    usuari.put("telefon", nouTelefon);
                }

                escriureFitxerJson(usuaris); // Guardar els canvis
                System.out.println("Usuari modificat correctament.");
            } else {
                System.out.println("Usuari no trobat.");
            }

        } catch (Exception e) {
            System.out.println("Error en modificar l'usuari: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Buscar un usuari per id
    private static JSONObject buscarUsuariPerId(JSONArray usuaris, int idUsuari) {
        for (int i = 0; i < usuaris.length(); i++) { // Bucle para encontrar el usuario
            JSONObject usuari = usuaris.getJSONObject(i);
            if (usuari.getInt("idUsuari") == idUsuari) {
                return usuari;
            }
        }
        return null; // Si no es troba l'usuari
    }

    public static void eliminarUsuari() {
        Scanner scanner = new Scanner(System.in);
        try {
            JSONArray usuaris = llegirFitxerJson(); // Leer json
    
            System.out.print("Introdueix l'ID de l'usuari que vols eliminar: ");
            int idUsuari = Integer.parseInt(scanner.nextLine());
    
            // Buscar el usuari per ID y eliminarlo
            for (int i = 0; i < usuaris.length(); i++) { // Bucle que recorre los usuaris
                JSONObject usuariActual = usuaris.getJSONObject(i); // Selecciona el usuari a eliminar
                if (usuariActual.getInt("idUsuari") == idUsuari) {
                    usuaris.remove(i); // Eliminar l'usuari a l'índex 'i'
                    escriureFitxerJson(usuaris); // Guardar els canvis
                    System.out.println("Usuari eliminat correctament.");
                    return; // Salir del mètodo después de eliminar
                }
            }
    
            // Si no se encuentra el usuario
            System.out.println("Usuari no trobat.");
    
        } catch (Exception e) {
            System.out.println("Error en eliminar l'usuari: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    

        
}
