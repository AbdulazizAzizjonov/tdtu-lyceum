package com.company.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Level {
    private Integer id;
    private String name;
    private boolean is_delete = false;

    public Level(String name) {
        this.name = name;
    }

    public Level(Integer id) {
        this.id = id;
    }
}
