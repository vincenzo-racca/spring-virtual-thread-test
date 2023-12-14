package com.vincenzoracca.reactive.repo;

import com.vincenzoracca.reactive.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserJDBCRepository extends ReactiveCrudRepository<User, Long> {

    Flux<User> findAllByNameAndSurname(String name, String surname);
}
