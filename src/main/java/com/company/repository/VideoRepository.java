package com.company.repository;

import com.company.db.Database;
import com.company.model.BioLyceum;
import com.company.model.VideoLyceum;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VideoRepository {

    public static List<VideoLyceum> getVideoByLyceumId (Integer lyceumId) {
        VideoRepository.loadVideoRepository();
        return Database.videoLyceumList.stream()
                .filter(videoLyceum -> videoLyceum.getLyceumId().equals(lyceumId))
                .collect(Collectors.toList());
    }


    public static VideoLyceum getVideoById(Integer videoId) {
        loadVideoRepository();
        Optional<VideoLyceum> optional = Database.videoLyceumList.stream()
                .filter(lyceum -> lyceum.getId().equals(videoId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadVideoRepository() {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()){

                Database.videoLyceumList.clear();

                String sql = "SELECT * FROM lyceum_video WHERE NOT deleted";

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int lyceumId = resultSet.getInt("l_video_id");
                    String description = resultSet.getString("description");
                    String video = resultSet.getString("video");
                    boolean deleted = resultSet.getBoolean("deleted");

                    VideoLyceum videoLyceum = new VideoLyceum(id, lyceumId, description, video, deleted);

                    Database.videoLyceumList.add(videoLyceum);
                }
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addVideo (VideoLyceum videoLyceum) {
        Connection connection = Database.getConnection();

        if (connection!= null) {
            String sql = "INSERT INTO lyceum_video(description, video, l_video_id )" +
                    "VALUES(?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1, videoLyceum.getDescription());
                preparedStatement.setString(2, videoLyceum.getVideo());
                preparedStatement.setInt(3, videoLyceum.getLyceumId());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadVideoRepository();
    }

    public static void deleteVideo(Integer id) {
        Connection connection = Database.getConnection();
        if (connection!=null) {

            String query = "DELETE FROM lyceum_video WHERE id = ?;";
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
