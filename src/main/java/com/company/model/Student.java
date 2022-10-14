package com.company.model;

import com.company.enums.StudentStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private StudentStatus status;
    private boolean isAdmin = false;
}
