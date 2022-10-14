package com.company.repository;

import com.company.db.Database;
import com.company.model.lFoto;
import com.company.model.lVideo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class lFotoRepository {

    public static lFoto getlFotoById (Integer lFotoId) {
        loadlFotoList();
        Optional<lFoto> optional= Database.L_FOTO_LIST.stream()
                .filter(lFoto -> lFoto.getId().equals(lFotoId))
                .findFirst();
        return optional.orElse(null);

    }

    public static void loadlFotoList() {
        Connection connection = Database.getConnection();
        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.L_FOTO_LIST.clear();

                String query = "SELECT * FROM l_foto WHERE NOT deleted;";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean deleted = resultSet.getBoolean("deleted");

                    lFoto lFoto = new lFoto(id, name, deleted);
                    Database.L_FOTO_LIST.add(lFoto);

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
