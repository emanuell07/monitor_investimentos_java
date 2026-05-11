package com.projeto.financeiro.monitor_investimentos;

import com.projeto.financeiro.monitor_investimentos.repository.AtivoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonitorInvestimentosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorInvestimentosApplication.class, args);
    }

    @Bean
    CommandLineRunner teste(AtivoRepository repo) {
        return args -> {
            System.out.println("\n--- VERIFICANDO DADOS NO BANCO ---");
            repo.findAll().forEach(a -> {
                System.out.println("Ativo: " + a.getTicker() + " | Nome: " + a.getNome());
            });
            System.out.println("----------------------------------\n");
        };
    }
}
