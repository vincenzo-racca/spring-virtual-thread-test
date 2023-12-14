package com.vincenzoracca.virtualthreads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class VirtualThreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualThreadsApplication.class, args);
	}

	@Bean
	RestClient restClient(RestClient.Builder builder) {
		return builder.requestFactory(new JdkClientHttpRequestFactory()).build();
	}

}
