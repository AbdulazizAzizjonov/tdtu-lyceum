package com.company.repository;

import com.company.db.Database;
import com.company.model.Kafedra;

import java.sql.*;

public class KafedraRepository {

    public static Kafedra getKafedraById(Integer id) {

        loadKafedraList();

        for (Kafedra kafedra : Database.kafedraList) {
            if (kafedra.getId().equals(id)) {
                return kafedra;
            }
        }
        return null;
    }

    public static void loadKafedraList() {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {

                Database.kafedraList.clear();

                String sql = "SELECT * FROM kafedra WHERE NOT deleted";

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean deleted = resultSet.getBoolean("deleted");

                    Kafedra kafedra = new Kafedra(id, name, deleted);

                    Database.kafedraList.add(kafedra);
                }
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
