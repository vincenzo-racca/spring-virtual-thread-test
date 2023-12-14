package com.vincenzoracca.virtualthreads.api;

import com.vincenzoracca.virtualthreads.model.UserDTO;
import com.vincenzoracca.virtualthreads.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> findAllByName(@RequestParam String name, @RequestParam String surname) {
        return ResponseEntity.ok(userService.mapSurnamesInUpperCase(name, surname));
    }

}
