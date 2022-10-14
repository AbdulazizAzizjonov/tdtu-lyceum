package com.company.repository;

import com.company.db.Database;
import com.company.model.Kafedra;
import com.company.model.Teacher;
import com.sun.source.tree.TryTree;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeacherRepository {

    public static List <Teacher> getTeacherByKafedraId(Integer kafedraId) {
        TeacherRepository.loadTeacherList();
        return Database.teacherList.stream()
                .filter(teacher -> teacher.getKafedraId().equals(kafedraId))
                .collect(Collectors.toList());
    }


    public static Teacher getTeacherById(Integer teacherId) {
        TeacherRepository.loadTeacherList();
        Optional<Teacher> optional = Database.teacherList.stream()
                .filter(teacher -> teacher.getId().equals(teacherId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadTeacherList() {
        Connection connection = Database.getConnection();
        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.teacherList.clear();

                String query = " SELECT * FROM teacher WHERE NOT deleted; ";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int kafedraId = resultSet.getInt("kafedra_id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String level = resultSet.getString("level");
                    String phone = resultSet.getString("phone");
                    String room = resultSet.getString("room");
                    String image = resultSet.getString("image");
                    boolean deleted = resultSet.getBoolean("deleted");

                    Teacher teacher = new Teacher(id, kafedraId, name, surname, level, phone, room, image, deleted);
                    Database.teacherList.add(teacher);
                }
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addTeacher(Teacher teacher) {
        Connection connection = Database.getConnection();

        if (connection != null) {
            String sql = "INSERT INTO teacher(name, kafedra_id,  surname, level, phone, room, image) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1,teacher.getName());
                preparedStatement.setInt(2,teacher.getKafedraId());
                preparedStatement.setString(3,teacher.getSurname());
                preparedStatement.setString(4,teacher.getLevel());
                preparedStatement.setString(5,teacher.getPhone());
                preparedStatement.setString(6,teacher.getRoom());
                preparedStatement.setString(7,teacher.getImage());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        loadTeacherList();
    }


    public static void deleteTeacher(Integer id) {
        Connection connection = Database.getConnection();
        if (connection != null) {

            String query = " DELETE FROM teacher WHERE id = ? ;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, id);

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println("executeUpdate = " + executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
