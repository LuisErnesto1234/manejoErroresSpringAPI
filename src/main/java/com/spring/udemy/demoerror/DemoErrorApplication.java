package com.spring.udemy.demoerror;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoErrorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoErrorApplication.class, args);

        Map<String, List<String>> mapaEjemplo = new HashMap<>();

        mapaEjemplo.put("nombre", Arrays.asList("Es obligatorio", "Debe tener al menos 3 caracteres"));
        mapaEjemplo.put("correo", List.of("Debe ser un correo válido"));

        mapaEjemplo.forEach((a, b) -> System.out.println(a + b));

        mapaEjemplo.entrySet().stream().peek(a -> {
            System.out.println(a.getKey() + "|Errores: " + String.join(", ") + a.getValue());
        });
    }

}
