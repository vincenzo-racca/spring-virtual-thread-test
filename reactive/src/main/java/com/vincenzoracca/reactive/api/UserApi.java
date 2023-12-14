package com.vincenzoracca.reactive.api;

import com.vincenzoracca.reactive.model.UserDTO;
import com.vincenzoracca.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public Mono<ResponseEntity<Flux<UserDTO>>> findAllByName(@RequestParam String name, @RequestParam String surname) {
        return Mono.just(ResponseEntity.ok(userService.mapSurnamesInUpperCase(name, surname)));
    }

}
