package com.company.service;

import com.company.model.Student;
import com.company.repository.StudentRepository;

public class UserService {

    private StudentRepository studentRepository = new StudentRepository();
    public  Student getStudentById(String chatId) {

        Student student = studentRepository.getStudentById(chatId);


        return student;
    }
}
