package com.company.repository;

import com.company.db.Database;
import com.company.model.Level;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LevelRepository {

    public static Level getLevelById(Integer id) {
        loadLevelList();

        for (Level level : Database.levelList){
            if (level.getId().equals(id)){
                return level;
            }
        }
        return null;
    }

    public static void loadLevelList() {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()) {

                Database.levelList.clear();

                String sql = "SELECT * FROM level WHERE NOT deleted";

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean deleted = resultSet.getBoolean("deleted");

                    Level level = new Level(id, name, deleted);

                    Database.levelList.add(level);
                }
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
