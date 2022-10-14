package com.company.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Kafedra {
    private Integer id;
    private String name;
    private boolean isDeleted = false;

    public Kafedra(String name) {
        this.name = name;
    }

    public Kafedra(Integer id) {
        this.id = id;
    }
}
