package br.senac.lll.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File; // <-- ADICIONE ESTE IMPORT

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        // --- CÓDIGO DE DIAGNÓSTICO ---
        // Pede ao Java para resolver o caminho relativo que está no seu properties
        File databaseFile = new File("../data/meu-banco");
        
        // Imprime o caminho completo e final no console
        System.out.println("------------------------------------------------------------------");
        System.out.println(">>> ONDE O BANCO ESTÁ SENDO CRIADO: " + databaseFile.getAbsolutePath());
        System.out.println("------------------------------------------------------------------");
        // -----------------------------
    }

}