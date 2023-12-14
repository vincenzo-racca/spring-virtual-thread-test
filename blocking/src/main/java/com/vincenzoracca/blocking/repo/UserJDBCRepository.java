package com.vincenzoracca.blocking.repo;

import com.vincenzoracca.blocking.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJDBCRepository extends CrudRepository<User, Long> {

    Iterable<User> findAllByNameAndSurname(String name, String surname);
}
