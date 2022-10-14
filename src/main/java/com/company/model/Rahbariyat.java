package com.company.model;

import com.company.enums.RahbariyatStatus;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rahbariyat {
    private Integer id;
    private Integer levelId;
    private String firstName;
    private String lastName;
    private String level;
    private String phone_number;
    private String room;
    private String image;

    private boolean isDeleted = false;

    public Rahbariyat(Integer levelId, String level, String firstName, String lastName, String phone_number, String room, String image) {
       this.levelId = levelId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.phone_number = phone_number;
        this.room = room;
        this.image = image;
    }
}
