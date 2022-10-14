package com.company.repository;

import com.company.db.Database;
import com.company.model.StudentP;

import java.sql.*;
import java.util.Optional;

public class StudentPRepository {

    public static StudentP getStudentPById(Integer studentPId) {
        StudentPRepository.loadStudentPList();
        Optional<StudentP> optional = Database.studentPList.stream()
                .filter(studentP -> studentP.getId().equals(studentPId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadStudentPList() {
        Connection connection = Database.getConnection();
        if (connection!=null) {
            try (Statement statement = connection.createStatement()) {

                Database.studentPList.clear();

                String query = "SELECT * FROM student_p WHERE NOT deleted;";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String middlname = resultSet.getString("middle_name");
                    String birthday = resultSet.getString("birthday");
                    String city = resultSet.getString("city");
                    String district = resultSet.getString("district");
                    String manzil = resultSet.getString("manzil");
                    String maktabCity = resultSet.getString("maktab_city");
                    String maktabDistrict = resultSet.getString("maktab_district");
                    String maktabNumber = resultSet.getString("maktab_number");
                    String passportId = resultSet.getString("passport_id");
                    String phone = resultSet.getString("phone");
                    String fathName = resultSet.getString("fath_name");
                    String fathSurname = resultSet.getString("fath_surname");
                    String fathPhone = resultSet.getString("fath_phone");
                    String mathName = resultSet.getString("math_name");
                    String mathSurname = resultSet.getString("math_surname");
                    String mathPhone = resultSet.getString("math_phone");
                    String image = resultSet.getString("image");
                    String pImage = resultSet.getString("p_image");
                    String yotoqxona = resultSet.getString("yotoxona");
                    boolean deleted = resultSet.getBoolean("deleted");

                    StudentP studentP = new StudentP(id,name,surname,middlname,birthday,city,
                            district, manzil, maktabCity, maktabDistrict, maktabNumber, passportId,phone,
                            fathName, fathSurname, fathPhone, mathName, mathSurname, mathPhone,
                            pImage, image, yotoqxona, deleted);

                    Database.studentPList.add(studentP);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addStudentP (StudentP studentP) {
        Connection connection = Database.getConnection();

        if (connection != null) {
            String sql = "INSERT INTO student_p(name, surname, middle_name, birthday, city, district, manzil," +
                    "maktab_city, maktab_district, maktab_number, passport_id, phone, fath_name, fath_surname, " +
                    "fath_phone, math_name, math_surname, math_phone, image, p_image, yotoxona)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1,studentP.getName());
                preparedStatement.setString(2,studentP.getSurname());
                preparedStatement.setString(3,studentP.getMiddlname());
                preparedStatement.setString(4,studentP.getBirthday());
                preparedStatement.setString(5,studentP.getCity());
                preparedStatement.setString(6,studentP.getDistrict());
                preparedStatement.setString(7,studentP.getManzil());
                preparedStatement.setString(8,studentP.getMaktabCity());
                preparedStatement.setString(9,studentP.getMaktabDistrict());
                preparedStatement.setString(10,studentP.getMaktabNumber());
                preparedStatement.setString(11,studentP.getPassportId());
                preparedStatement.setString(12,studentP.getPhone());
                preparedStatement.setString(13,studentP.getFathName());
                preparedStatement.setString(14,studentP.getFathSurname());
                preparedStatement.setString(15,studentP.getFathPhone());
                preparedStatement.setString(16,studentP.getMathName());
                preparedStatement.setString(17,studentP.getMathSurname());
                preparedStatement.setString(18,studentP.getMathPhone());
                preparedStatement.setString(19,studentP.getImage());
                preparedStatement.setString(20,studentP.getPImage());
                preparedStatement.setString(21,studentP.getYotoqxona());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadStudentPList();

    }
}
