package br.com.itau.vaga.desafio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.time.LocalTime;
import java.time.OffsetDateTime;

@SpringBootTest
@TestConfiguration
@SpringBootConfiguration
class DesafioItauApplicationTests {

	@Test
	void contextLoads() {
		LocalTime localTime=LocalTime.now();
		OffsetDateTime offsetDateTime=OffsetDateTime.now();
		System.out.println(offsetDateTime.getSecond()+"<<<---- OffsetDateTime.now()");
		System.out.println(localTime.now() +"<<<---Local TIME");
		System.out.println(localTime.getSecond()+"locas");
		System.out.println();
		System.out.println("Teste");
	}

}
