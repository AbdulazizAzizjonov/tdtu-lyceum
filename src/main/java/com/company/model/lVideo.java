package com.company.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class lVideo {
    private Integer id;
    private String name;
    private boolean deleted=false;
}
