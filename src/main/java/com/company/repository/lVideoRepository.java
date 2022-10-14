package com.company.repository;

import com.company.db.Database;
import com.company.model.lHaqida;
import com.company.model.lVideo;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class lVideoRepository {

    public static lVideo getlVideoById (Integer lVideoId) {
        loadlVideoList();
        Optional<lVideo> optional= Database.L_VIDEO_LIST.stream()
                .filter(lVideo -> lVideo.getId().equals(lVideoId))
                .findFirst();
        return optional.orElse(null);

    }

    public static void loadlVideoList() {
        Connection connection = Database.getConnection();
        if (connection != null) {

            try (Statement statement = connection.createStatement()) {

                Database.L_VIDEO_LIST.clear();

                String query = "SELECT * FROM l_video WHERE NOT deleted;";

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    boolean deleted = resultSet.getBoolean("deleted");

                     lVideo lVideo = new lVideo(id, name, deleted);
                    Database.L_VIDEO_LIST.add(lVideo);

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
