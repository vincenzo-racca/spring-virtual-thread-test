package com.vincenzoracca.virtualthreads.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(

    @Id
    Long id,
    String name,
    String surname

){}
