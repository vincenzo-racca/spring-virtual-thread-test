package com.vincenzoracca.blocking.service;

import com.vincenzoracca.blocking.config.ConfigProperties;
import com.vincenzoracca.blocking.model.NameDTO;
import com.vincenzoracca.blocking.model.User;
import com.vincenzoracca.blocking.model.UserDTO;
import com.vincenzoracca.blocking.repo.UserJDBCRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserJDBCRepository userJDBCRepository;

    private final RestTemplate restTemplate;

    private final ConfigProperties configProperties;


    public Iterable<UserDTO> mapSurnamesInUpperCase(String name, String surname) {
        long start = Instant.now().toEpochMilli();
        log.info("mapSurnamesInUpperCase started with parameters name: {}, surname: {}", name, surname);
        List<UserDTO> response = new ArrayList<>();
        Iterable<User> users = userJDBCRepository.findAllByNameAndSurname(name, surname);
        for (User user : users) {
            NameDTO surnameDTOResponse = restTemplate.postForObject(configProperties.getMockclientUrl() + "/upper",
                    new NameDTO(user.surname()), NameDTO.class);
            response.add(new UserDTO(name, surnameDTOResponse.name()));
        }
        log.info("mapSurnamesInUpperCase terminated: {} with parameters name: {}, surname: {}", Instant.now().toEpochMilli() - start, name, surname);
        return response;
    }
}
