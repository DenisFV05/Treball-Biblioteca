package com.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Prestec {
    /**
     *  Explicació de la funció "prestecFuncionAgafarNouLlibre"
     * 
     * @param scanner (Hagafa l'input del usuari per escollir l'ID d'un usuari i l'ID d'un llibre)
     * En aquesta funció no cal el "return" ya que la funció fa el préstec, informa al usuari
     * 
     * [FUNCIÓ]
     * Primer. Fa el set-up dels arxius .json per poder modificarlos.
     * Segon. Itera l'arxiu usuaris.json i es fa una tabla amb tots el usuaris.
     * Tercer. Escollir l'ID del usuari que vols seleccionar per fer el préstec.
     * Quart. Itera l'arxiu llibres.json i es fa una tabla amb tots el llibres disponibles.
     * Cinquè. Escollir l'ID del llibre que vols seleccionar per fer el préstec.
     * Sisè. Es crea un nou objecte a l'arxiu prestecs.json y et dona la data de retorn.
     * @exception Controla que si n'hi ha un error puguis saber quin error ha sigut.
     */

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

        //////////////////////////////////// [ ITERAR LOS PRESTAMOS Y SU GESTIÓM ] //////////////////////////////////
            for (int i = 0; i < jsonArrayPrestecs.length(); i++) {
                JSONObject prestec = jsonArrayPrestecs.getJSONObject(i);

                // Dar formato a las variables de los prestamos
                int id = prestec.getInt("id");
                int idLlibre = prestec.getInt("idLlibre");
                int idUsuari = prestec.getInt("idUsuari");
                String dataPrestec = prestec.getString("dataPrestec");
                String dataDevolucio = prestec.getString("dataDevolucio");
            }

        /////////////////////////////////// [ LISTADO DE USUARIOS Y PODER ESCOGERLO ] ///////////////////////////////

            // Header de la lista de usuarios
            System.out.println("\nID    | Nom            | Cognoms             | Teléfon");
            System.out.println("--------------------------------------------------------");

            // Iterar los usuarios
            for (int i = 0; i < jsonArrayUsuaris.length(); i++) {
                JSONObject usuari = jsonArrayUsuaris.getJSONObject(i);

                // Dar formato a las variables de los usuarios
                int idUsuari = usuari.getInt("idUsuari");
                String nom = usuari.getString("nom");
                String cognoms = usuari.getString("cognoms");
                int telefon = usuari.getInt("telefon");

                // Imprimir los datos de los usuarios
                System.out.printf("%-6d| %-15s| %-20s| %-10d%n", idUsuari, nom, cognoms, telefon);
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
                int idUsuari = usuari.getInt("idUsuari");

                // Si encuentra el ID escrito en usuari.json
                if(idUsuari == selectUsuariId) {
                    // [GUARDADO]
                    usuariSeleccionado = usuari; // Donde se guardara el usuario para mas tarde
                }
            }
            // Si no se encuentra el ID (Mensajes)
            if (usuariSeleccionado == null) {
                System.out.println("No s'ha trobat l'usuari amb l'ID: " + selectUsuariId + "\n");
                Menu.menuPrestecs(scanner);
            } else {
                String nom = usuariSeleccionado.getString("nom");
                System.out.println("L'usuari amb ID: " +"["+selectUsuariId+"]"+" "+nom+","+" ha estat seleccionat.");
            }
            

        ///////////////////////////////////// [ LISTAR LIBROS DISPONIBLES] /////////////////////////////////////////////

            // Header de la lista de libros disponibles
            System.out.println("\nID    | Títol                    | Autor");
            System.out.println("-------------------------------------------------------");
            
            // Iterar los libros
            JSONArray idsLibrosPrestados = new JSONArray();
            for (int i = 0; i < jsonArrayPrestecs.length(); i++) {
                JSONObject prestec = jsonArrayPrestecs.getJSONObject(i);
                int idLibroPrestado = prestec.getInt("idLlibre");
                idsLibrosPrestados.put(idLibroPrestado); // Guardar el ID de los libros prestados
            }
            for (int i = 0; i < jsonArrayLlibres.length(); i++) {
                // Dar formato a las variables de los usuarios
                JSONObject llibre = jsonArrayLlibres.getJSONObject(i);

                int idLlibre = llibre.getInt("idLlibre");
                String titol = llibre.getString("titol");
                String autor = llibre.getString("autor");

                // Verificar los libros reservaods e imprimir los que no
                if (!idsLibrosPrestados.toList().contains(idLlibre)) {
                    System.out.printf("%-6d| %-25s| %20s%n", idLlibre, titol, autor);
                }
            }

            // Solicitar el ID y verificar el Input
            int selectLibroId = -1;
            boolean inputLibroValido = false;
            while (!inputLibroValido) {
                System.out.println("\nIntroudeix l'ID d'un llibre per seleccionar-lo: ");

                try {
                    selectLibroId = scanner.nextInt();
                    inputLibroValido = true;
                } catch (Exception e) {
                    System.out.println("Error: Has d'introduir un número vàlid per seleccionar el llibre.");
                    scanner.nextLine();
                }
            }

            // Buscar el libro en base del ID seleccionado
            JSONObject llibreSeleccionado = null;
            for (int i = 0; i < jsonArrayLlibres.length(); i++){
                JSONObject llibre = jsonArrayLlibres.getJSONObject(i);
                int idLlibre = llibre.getInt("idLlibre");

                // Si se encuentra el ID escrito en llibres.json
                if(idLlibre == selectLibroId) {
                    // [GUARDADO]
                    llibreSeleccionado = llibre;
                }
            }
            // Si no se encuentra el ID (Mensajes)
            if (llibreSeleccionado == null){
                System.out.println("No s'ha trobat el llibre amb l'ID: " + selectLibroId + "\n");
            } else {
                String titol = llibreSeleccionado.getString("titol");
                System.out.println("El llibre amb l'ID: " +"["+selectLibroId+"]"+" "+titol+","+" ha estat seleccionat.");
            }

            ///////////////////////////// [GUARDAR NUEVO PRESTAMO EN PRESTECS.JSON] /////////////////////////////////////////////

            // Crear nuevo objeto en prestecs.json
            JSONObject newPrestec = new JSONObject();
            int newId = jsonArrayPrestecs.length() + 1; // Añade uno mas
            newPrestec.put("id", newId);
            newPrestec.put("idLlibre", selectLibroId);
            newPrestec.put("idUsuari", selectUsuariId);

            // Calcular la fecha
            LocalDate currenDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataPrestec = currenDate.format(formatter);
            LocalDate dataDevolucio = currenDate.plusWeeks(2); // Prestamo de 2 semanas
            String dataDevolucioStr = dataDevolucio.format(formatter);

            newPrestec.put("dataPrestec", dataPrestec);
            newPrestec.put("dataDevolucio", dataDevolucioStr);

            // Añadir nuevo prestamo al Array
            jsonArrayPrestecs.put(newPrestec);

            // Guardamos el nuevo contenido en el archivo
            Files.write(Paths.get(filePathPrestecs), jsonArrayPrestecs.toString(4).getBytes());
            
            System.out.println("\nLa data de devolució és: " + dataDevolucioStr);

        } catch (Exception e) {
            // Control de manejo de expeciones
            System.err.println("Ha hagut un error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    
    public static void prestecFuncionRetornarLlibre (Scanner scanner){
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
        /////////////////////////////////// [ LISTADO DE USUARIOS Y PODER ESCOGERLO ] ///////////////////////////////
        for (int i = 0; i < jsonArrayPrestecs.length(); i++) {
            JSONObject prestec = jsonArrayPrestecs.getJSONObject(i);

            // Dar formato a las variables de los prestamos
            int id = prestec.getInt("id");
            int idLlibre = prestec.getInt("idLlibre");
            int idUsuari = prestec.getInt("idUsuari");
            String dataPrestec = prestec.getString("dataPrestec");
            String dataDevolucio = prestec.getString("dataDevolucio");
        }

    /////////////////////////////////// [ LISTADO DE USUARIOS Y PODER ESCOGERLO ] ///////////////////////////////

        // Header de la lista de usuarios
        System.out.println("\nID    | Nom            | Cognoms             | Teléfon");
        System.out.println("--------------------------------------------------------");

        // Iterar los usuarios
        for (int i = 0; i < jsonArrayUsuaris.length(); i++) {
            JSONObject usuari = jsonArrayUsuaris.getJSONObject(i);

            // Dar formato a las variables de los usuarios
            int idUsuari = usuari.getInt("idUsuari");
            String nom = usuari.getString("nom");
            String cognoms = usuari.getString("cognoms");
            int telefon = usuari.getInt("telefon");

            // Imprimir los datos de los usuarios
            System.out.printf("%-6d| %-15s| %-20s| %-10d%n", idUsuari, nom, cognoms, telefon);
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
            int idUsuari = usuari.getInt("idUsuari");

            // Si encuentra el ID escrito en usuari.json
            if(idUsuari == selectUsuariId) {
                // [GUARDADO]
                usuariSeleccionado = usuari; // Donde se guardara el usuario para mas tarde
            }
        }
        // Si no se encuentra el ID (Mensajes)
        if (usuariSeleccionado == null) {
            System.out.println("No s'ha trobat l'usuari amb l'ID: " + selectUsuariId + "\n");
            Menu.menuPrestecs(scanner);
        } else {
            String nom = usuariSeleccionado.getString("nom");
            System.out.println("L'usuari amb ID: " +"["+selectUsuariId+"]"+" "+nom+","+" ha estat seleccionat.");
        }
        } catch (Exception e) {
        }
    }
}
