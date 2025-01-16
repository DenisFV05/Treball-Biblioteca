package com.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Usuari {
    private static final String FILE_PATH = "src\\main\\json\\usuaris.json";

    // Llegeix el fitxer JSON i retorna un JSONArray
    public static JSONArray llegirFitxerJson() {
        try {
            String contingut = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            return new JSONArray(contingut);
        } catch (IOException e) {
            System.out.println("Error en llegir el fitxer JSON: " + e.getMessage());
            return new JSONArray();
        }
    }

    // Escriu un JSONArray al fitxer JSON
    public static void escriureFitxerJson(JSONArray jsonArray) {
        try {
            Files.write(Paths.get(FILE_PATH), jsonArray.toString(4).getBytes());
        } catch (IOException e) {
            System.out.println("Error en escriure al fitxer JSON: " + e.getMessage());
        }
    }

    // Crea un nou usuari i l’afegeix al fitxer JSON
    public static void crearUsuari() {
        Scanner scanner = new Scanner(System.in);
        try {
            JSONArray usuaris = llegirFitxerJson();

            System.out.print("Introdueix el nom de l'usuari: ");
            String nom = validarNom(scanner);

            System.out.print("Introdueix el primer cognom: ");
            String primerCognom = validarNom(scanner);

            System.out.print("Introdueix el segon cognom: ");
            String segonCognom = validarNom(scanner);

            System.out.print("Introdueix el telèfon de l'usuari: ");
            String telefon = validarTelefon(scanner);

            System.out.print("Vols guardar aquest usuari? (s/n): ");
            String confirmacio = scanner.nextLine().trim().toLowerCase();

            if (confirmacio.equals("s")) {
                int nouId = obtenirSeguentId(usuaris);

                JSONObject nouUsuari = new JSONObject();
                nouUsuari.put("idUsuari", nouId);
                nouUsuari.put("nom", nom);
                nouUsuari.put("cognoms", primerCognom + " " + segonCognom);
                nouUsuari.put("telefon", telefon);

                usuaris.put(nouUsuari);
                escriureFitxerJson(usuaris);

                System.out.println("Usuari afegit correctament.");
            } else {
                System.out.println("Usuari no desat.");
            }
        } finally {
            scanner.close();
        }
    }

    // Valida el nom o cognom introduït per l'usuari
    private static String validarNom(Scanner scanner) {
        String entrada;
        do {
            System.out.print("Entrada no vàlida. Introdueix un valor vàlid (mínim 4 caràcters, sense espais): ");
            entrada = scanner.nextLine().trim();
        } while (entrada.isEmpty() || entrada.length() < 4 || entrada.contains(" "));
        return entrada.substring(0, 1).toUpperCase() + entrada.substring(1).toLowerCase();
    }

    // Valida que el telèfon sigui correcte
    private static String validarTelefon(Scanner scanner) {
        String entrada;
        do {
            System.out.print("Entrada no vàlida. Introdueix un telèfon vàlid (9 dígits): ");
            entrada = scanner.nextLine().trim();
        } while (!entrada.matches("\\d{9}")); // verifica si la cadena contiene exactamente 9 digitos consecutivos, \\d es cualquier digito 0-9
        return entrada;
    }

    // Obté el següent ID disponible
    private static int obtenirSeguentId(JSONArray usuaris) {
        int maxId = 0;
        for (int i = 0; i < usuaris.length(); i++) {
            JSONObject usuari = usuaris.getJSONObject(i);
            maxId = Math.max(maxId, usuari.getInt("idUsuari"));
        }
        return maxId + 1;
    }
}
