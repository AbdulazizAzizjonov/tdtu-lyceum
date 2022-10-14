package com.company.repository;

import com.company.db.Database;
import com.company.model.Rahbariyat;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RahbariyatRepository {

    public static List<Rahbariyat> getRahbarByLevelId(Integer levelId) {
        RahbariyatRepository.loadRahbariyatlist();
        return Database.rahbariyatlist.stream()
                .filter(rahbariyat -> rahbariyat.getLevelId().equals(levelId))
                .collect(Collectors.toList());
    }

    public static Rahbariyat getRahbariyatById(Integer rahbariyatId) {
        RahbariyatRepository.loadRahbariyatlist();
        Optional<Rahbariyat> optional = Database.rahbariyatlist.stream()
                .filter(rahbariyat -> rahbariyat.getId().equals(rahbariyatId))
                .findFirst();
        if(!optional.isPresent()){
            return null;
        }
        Rahbariyat rahbariyat = optional.get();
        return rahbariyat;
    }

    public static void loadRahbariyatlist() {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {

                Database.rahbariyatlist.clear();

                String query = "SELECT * FROM rektorat WHERE NOT deleted; ";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    int levelId = resultSet.getInt("level_id");
                    String lastName = resultSet.getString("last_name");
                    String level = resultSet.getString("level");
                    String phone = resultSet.getString("phone_number");
                    String room = resultSet.getString("room");
                    String image = resultSet.getString("image");
                    boolean deleted = resultSet.getBoolean("deleted");

                    Rahbariyat rahbariyat = new Rahbariyat(id, levelId, firstName, lastName, level, phone, room, image, deleted);

                    Database.rahbariyatlist.add(rahbariyat);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addRahbar(Rahbariyat rahbariyat) {
        Connection connection = Database.getConnection();
        if (connection != null) {
            String query = "INSERT INTO rektorat(first_name, last_name, level_id, level, phone_number, room, image)" +
                    "VALUES(?,?,?,?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, rahbariyat.getFirstName());
                preparedStatement.setString(2, rahbariyat.getLastName());
                preparedStatement.setInt(3, rahbariyat.getLevelId());
                preparedStatement.setString(4, rahbariyat.getLevel());
                preparedStatement.setString(5, rahbariyat.getPhone_number());
                preparedStatement.setString(6, rahbariyat.getRoom());
                preparedStatement.setString(7, rahbariyat.getImage());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        loadRahbariyatlist();
    }

    public static void deleteRahbar(Integer id) {
        Connection connection = Database.getConnection();
        if (connection!=null) {

            String query = "DELETE FROM rektorat WHERE id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,id);

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
