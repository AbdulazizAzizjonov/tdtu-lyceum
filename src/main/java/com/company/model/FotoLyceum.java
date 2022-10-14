package com.company.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FotoLyceum {
    private Integer id;
    private String description;
    private String image;
    private boolean deleted=false;

    public FotoLyceum(String description, String image) {
        this.description = description;
        this.image = image;
    }
}
