package com.example.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;


@SpringBootApplication
public class ContactApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ContactApplication.class, args);

            Desktop.getDesktop().browse(new URI("http://127.0.0.1:8080"));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

}
