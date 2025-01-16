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

            System.out.print("Introdueix el nom de l'usuari: "); // Pregunta y valida
            String nom = validarNom(scanner);

            System.out.print("Introdueix el primer cognom: "); // Pregunta y valida
            String primerCognom = validarNom(scanner);

            System.out.print("Introdueix el segon cognom: "); // Pregunta y valida
            String segonCognom = validarNom(scanner);

            System.out.print("Introdueix el telèfon de l'usuari: "); // Pregunta y valida
            String telefon = validarTelefon(scanner);

            System.out.print("Vols guardar aquest usuari? (s/n): ");
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

                System.out.println("Usuari afegit correctament.");
            } else { // Si escribimos no o lo que sea
                System.out.println("Usuari no desat.");
            }
        } finally {
            scanner.close(); // Se cierra el scanner
        }
    }

    // Valida el nom o cognom introduït per l'usuari
    private static String validarNom(Scanner scanner) {
        String entrada;
        do {
            System.out.print("Entrada no vàlida. Introdueix un valor vàlid (mínim 4 caràcters, sense espais): ");
            entrada = scanner.nextLine().trim(); // Da error y vuelve a preguntar
        } while (entrada.isEmpty() || entrada.length() < 4 || entrada.contains(" ")); // Mientras el nombre este vacio, sea muy corto o tenga espacio
        return entrada.substring(0, 1).toUpperCase() + entrada.substring(1).toLowerCase(); // Si esta bien devuelve String primera letra en mayus y el resto en minus
    }

    // Valida que el telèfon sigui correcte
    private static String validarTelefon(Scanner scanner) {
        String entrada;
        do {
            System.out.print("Entrada no vàlida. Introdueix un telèfon vàlid (9 dígits): ");
            entrada = scanner.nextLine().trim(); // Da error y vuelve a preguntar
        } while (!entrada.matches("\\d{9}")); // verifica si la cadena contiene exactamente 9 digitos consecutivos, \\d es cualquier digito 0-9
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
}
