package com.vincenzoracca.reactive.service;

import com.vincenzoracca.reactive.config.ConfigProperties;
import com.vincenzoracca.reactive.model.NameDTO;
import com.vincenzoracca.reactive.model.UserDTO;
import com.vincenzoracca.reactive.repo.UserJDBCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserJDBCRepository userJDBCRepository;

    private final WebClient webClient;

    private final ConfigProperties configProperties;


    public Flux<UserDTO> mapSurnamesInUpperCase(String name, String surname) {
        long start = Instant.now().toEpochMilli();
        log.info("mapSurnamesInUpperCase started with parameters name: {}, surname: {}", name, surname);
        return userJDBCRepository.findAllByNameAndSurname(name, surname)
                .flatMap(user -> webClient.post()
                        .uri(configProperties.getMockclientUrl() + "/upper")
                        .body(new NameDTO(user.surname()), NameDTO.class)
                        .retrieve()
                        .bodyToMono(NameDTO.class))
                .map(nameDTO -> new UserDTO(name, nameDTO.name()))
                .doOnComplete(() -> log.info("mapSurnamesInUpperCase terminated: {} with parameters name: {}, surname: {}", Instant.now().toEpochMilli() - start, name, surname));

    }
}
