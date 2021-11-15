package tienda.daniel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TiendaDanielRicoteMompoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaDanielRicoteMompoApplication.class, args);
	}

}
