package com.vincenzoracca.mockclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MockClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockClientApplication.class, args);
	}


	@RestController
	@RequestMapping("upper")
	@Slf4j
	static class MockApi {

		@PostMapping
		public ResponseEntity<NameDTO> mapUppercase(@RequestBody NameDTO nameDTO) throws InterruptedException {
			String upperCaseName = nameDTO.name().toUpperCase();
			Thread.sleep(500L);
			log.info("Uppercase done: {}", upperCaseName);
			return ResponseEntity.ok(new NameDTO(upperCaseName));
		}
	}

	record NameDTO(String name){}

}
