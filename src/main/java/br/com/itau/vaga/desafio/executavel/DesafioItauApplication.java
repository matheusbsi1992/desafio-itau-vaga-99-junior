package br.com.itau.vaga.desafio.executavel;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@ComponentScan(value = "br.*")
public class DesafioItauApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioItauApplication.class, args);
    }

    @Bean
    public OpenAPI customAPI() {
        Server server = new Server();
        server.setUrl("https://localhost:9293");
        server.setDescription("Desafio");

        Contact contact = new Contact();
        contact.email("matheusbsi1992@gmail.com");
        contact.name("Matheus Andrade");

        Info info = new Info()
                .title("Itaú Unibanco - Desafio de Programação")
                .version("v1")
                .description("Uso de API para o desafio-itau-vaga-99-junior")
                .contact(contact)
                .termsOfService("https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior")
                .license(
                        new License()
                                .name("Apache 2.0")
                                .url("https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior"));

        return new OpenAPI().info(info).servers(List.of(server));

    }
}