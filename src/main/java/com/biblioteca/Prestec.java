package com.biblioteca;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

public class Prestec {
    public static void prestecListar(String[] args){
        try {
            String filePath = "./src/main/json/prestecs.json";
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONArray jsonArray = new JSONArray(content);

            System.out.println(jsonArray);
        } catch (Exception e) {
        }
    }
}
