package com.example;

import java.io.Console;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args)
            throws IOException {

        Dotenv dotenv = Dotenv.load();

        Console console = System.console();
        System.out.println("Enter File Name: ");
        String fileName = console.readLine();

        // select a file for Decryption operation
        File file = new File(dotenv.get("root") + fileName + ".pdf");

        // Load the PDF file
        PDDocument pdd = Loader.loadPDF(file, dotenv.get("password"));

        // removing all security from PDF file
        pdd.setAllSecurityToBeRemoved(true);

        // Save the PDF file
        File decrypted = new File(dotenv.get("root") + "decrypted_" + fileName + ".pdf");
        pdd.save(decrypted);

        // Delete password PDF
        file.delete();

        // Close the PDF file
        pdd.close();

        // Rename PDF file
        decrypted.renameTo(file);

        System.out.println("Decryption Done...");

    }
}
