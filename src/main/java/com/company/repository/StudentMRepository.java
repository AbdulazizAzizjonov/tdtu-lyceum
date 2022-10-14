package com.company.repository;

import com.company.db.Database;
import com.company.model.StudentM;
import com.company.model.StudentP;

import java.sql.*;
import java.util.Optional;

public class StudentMRepository {

    public static StudentM getStudentPById(Integer studentMId) {
        StudentPRepository.loadStudentPList();
        Optional<StudentM> optional = Database.studentMList.stream()
                .filter(studentM -> studentM.getId().equals(studentMId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadStudentMList() {
        Connection connection = Database.getConnection();
        if (connection!=null) {
            try (Statement statement = connection.createStatement()) {

                Database.studentMList.clear();

                String query = "SELECT * FROM student_m WHERE NOT deleted;";

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
                    String metirkaId = resultSet.getString("metirka_id");
                    String phone = resultSet.getString("phone");
                    String fathName = resultSet.getString("fath_name");
                    String fathSurname = resultSet.getString("fath_surname");
                    String fathPhone = resultSet.getString("fath_phone");
                    String mathName = resultSet.getString("math_name");
                    String mathSurname = resultSet.getString("math_surname");
                    String mathPhone = resultSet.getString("math_phone");
                    String image = resultSet.getString("image");
                    String mImage = resultSet.getString("m_image");
                    String yotoqxona = resultSet.getString("yotoxona");
                    boolean deleted = resultSet.getBoolean("deleted");

                    StudentM studentM = new StudentM(id,name,surname,middlname,birthday,city,
                            district, manzil, maktabCity, maktabDistrict, maktabNumber, metirkaId,
                            phone, fathName, fathSurname, fathPhone, mathName, mathSurname, mathPhone,
                            mImage, image, yotoqxona, deleted);


                    Database.studentMList.add(studentM);

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addStudentM (StudentM studentM) {
        Connection connection = Database.getConnection();

        if (connection != null) {
            String sql =  "INSERT INTO student_m(name, surname, middle_name, birthday, city, district, manzil," +
                    "maktab_city, maktab_district, maktab_number, metirka_id, phone, fath_name,fath_surname," +
                    "fath_phone, math_name, math_surname, math_phone, image, m_image, yotoxona)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1,studentM.getName());
                preparedStatement.setString(2,studentM.getSurname());
                preparedStatement.setString(3,studentM.getMiddlname());
                preparedStatement.setString(4,studentM.getBirthday());
                preparedStatement.setString(5,studentM.getCity());
                preparedStatement.setString(6,studentM.getDistrict());
                preparedStatement.setString(7,studentM.getManzil());
                preparedStatement.setString(8,studentM.getMaktabCity());
                preparedStatement.setString(9,studentM.getMaktabDistrict());
                preparedStatement.setString(10,studentM.getMaktabNumber());
                preparedStatement.setString(11,studentM.getMetirkaId());
                preparedStatement.setString(12,studentM.getPhone());
                preparedStatement.setString(13,studentM.getFathName());
                preparedStatement.setString(14,studentM.getFathSurname());
                preparedStatement.setString(15,studentM.getFathPhone());
                preparedStatement.setString(16,studentM.getMathName());
                preparedStatement.setString(17,studentM.getMathSurname());
                preparedStatement.setString(18,studentM.getMathPhone());
                preparedStatement.setString(19,studentM.getImage());
                preparedStatement.setString(20,studentM.getMImage());
                preparedStatement.setString(21,studentM.getYotoqxona());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadStudentMList();

    }
}
