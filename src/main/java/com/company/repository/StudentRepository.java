package com.company.repository;

import com.company.db.Database;
import com.company.enums.StudentStatus;
import com.company.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class StudentRepository {

    public static void loadStudentList() {

        Connection connection = Database.getConnection();

        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.studentlist.clear();

                String query = " SELECT * FROM student; ";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String status = resultSet.getString("status");
                    boolean isAdmin = resultSet.getBoolean("is_admin");

                    Student student = new Student(id, firstName, lastName,
                            StudentStatus.valueOf(status), isAdmin);

                    Database.studentlist.add(student);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static Student getStudentById(String id){

        loadStudentList();

        Optional<Student> optional = Database.studentlist.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
        return optional.orElse(null);
    }
}
