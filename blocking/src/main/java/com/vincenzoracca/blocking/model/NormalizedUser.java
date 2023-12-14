package com.vincenzoracca.blocking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("normalized_users")
public record NormalizedUser(

    @Id
    Long id,
    String name,
    String surname

){}
