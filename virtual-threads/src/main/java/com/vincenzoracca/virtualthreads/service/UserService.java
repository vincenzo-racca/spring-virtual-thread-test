package com.vincenzoracca.virtualthreads.service;

import com.vincenzoracca.virtualthreads.model.NameDTO;
import com.vincenzoracca.virtualthreads.model.User;
import com.vincenzoracca.virtualthreads.model.UserDTO;
import com.vincenzoracca.virtualthreads.repo.UserJDBCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserJDBCRepository userJDBCRepository;

    private final RestClient restClient;


    public Iterable<UserDTO> mapSurnamesInUpperCase(String name, String surname) {
        long start = Instant.now().toEpochMilli();
        log.info("mapSurnamesInUpperCase started with parameters name: {}, surname: {}", name, surname);
        List<UserDTO> response = new ArrayList<>();
        Iterable<User> users = userJDBCRepository.findAllByNameAndSurname(name, surname);
        for (User user : users) {
            NameDTO surnameDTOResponse = restClient.post()
                    .uri("http://localhost:8092/upper")
                    .body(new NameDTO(user.surname()))
                    .retrieve()
                    .body(NameDTO.class);

            response.add(new UserDTO(name, surnameDTOResponse.name()));
        }
        log.info("mapSurnamesInUpperCase terminated: {} with parameters name: {}, surname: {}", Instant.now().toEpochMilli() - start, name, surname);
        return response;
    }
}
