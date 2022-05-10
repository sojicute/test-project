package com.sojicute.testproject.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String input;

    @Enumerated(EnumType.STRING)
    private Type type;
}
