package com.vincenzoracca.virtualthreads.repo;

import com.vincenzoracca.virtualthreads.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJDBCRepository extends CrudRepository<User, Long> {

    Iterable<User> findAllByNameAndSurname(String name, String surname);
}
