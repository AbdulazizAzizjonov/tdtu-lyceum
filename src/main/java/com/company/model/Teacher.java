package com.company.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Teacher{
    private Integer id;
    private Integer kafedraId;
    private String name;
    private String surname;
    private String level;

    private String phone;
    private String room;
    private String image;
    private boolean isDeleted = false;

    public Teacher(Integer kafedraId, String name, String surname, String level, String phone, String room, String image) {
        this.kafedraId = kafedraId;
        this.name = name;
        this.surname = surname;
        this.level = level;
        this.phone = phone;
        this.room = room;
        this.image = image;
    }

    public Teacher(Integer id) {
        this.id = id;
    }
}
