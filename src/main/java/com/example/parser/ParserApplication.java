package com.example.parser;

import com.example.parser.service.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(ParserApplication.class, args);
        Reader reader = new FileReader("fo_random.csv");

        if (args.length > 0 && (args[0].equals("min") || args[0].equals("max"))) {
            Parser.getInstance().parse(reader);

            if (args[0].equals("min")) {
                Parser.getInstance().geMin();
            } else {
                Parser.getInstance().geMax();
            }
        } else {
            System.out.println("Please input correct arguments (min/ max).");
            System.exit(1);
        }

        System.exit(0);
    }
}
