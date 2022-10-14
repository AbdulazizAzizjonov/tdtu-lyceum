package com.company.repository;

import com.company.db.Database;
import com.company.model.lHaqida;

import java.sql.*;
import java.util.Optional;

public class LyceumRepository {

    public static lHaqida getLyceumById(Integer lyceumId) {
        LyceumRepository.loadLyceumList();
        Optional<lHaqida> optional = Database.L_HAQIDA_LIST.stream()
                .filter(lHaqida -> lHaqida.getId().equals(lyceumId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadLyceumList() {
        Connection connection = Database.getConnection();
        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.L_HAQIDA_LIST.clear();

                String query = "SELECT * FROM l_haqida WHERE NOT deleted;";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean deleted = resultSet.getBoolean("deleted");

                    lHaqida lHaqida = new lHaqida(id, name, deleted);
                    Database.L_HAQIDA_LIST.add(lHaqida);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
