package com.company.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BioLyceum {

    private Integer id;
    private Integer lyceumId;
    private String description;
    private String image;

    private boolean deleted=false;

    public BioLyceum(Integer lyceumId, String image, String description) {
        this.lyceumId = lyceumId;
        this.description = description;
        this.image = image;
    }
}
