package com.company.repository;

import com.company.db.Database;
import com.company.model.FotoLyceum;
import com.company.model.VideoLyceum;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FotoRepository {

//    public static List<FotoLyceum> getFotoBylFotoId (Integer lFotoId) {
//        loadFotoRepository();
//        return Database.fotoLyceumList.stream()
//                .filter(fotoLyceum -> fotoLyceum.getLFotoId().equals(lFotoId))
//                .collect(Collectors.toList());
//    }

    public static  FotoLyceum getFotoById (Integer fotoId) {
        loadFotoRepository();
        Optional<FotoLyceum> optional = Database.fotoLyceumList.stream()
                .filter(fotoLyceum -> fotoLyceum.getId().equals(fotoId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadFotoRepository () {
        Connection connection = Database.getConnection();
        if (connection!= null) {
            try (Statement statement = connection.createStatement()) {

                Database.fotoLyceumList.clear();

                String sql = "SELECT * FROM lyceum_foto WHERE NOT deleted";

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String description = resultSet.getString("description");
                    String image = resultSet.getString("image");
                    boolean deleted = resultSet.getBoolean("deleted");

                    FotoLyceum fotoLyceum = new FotoLyceum(id, description, image, deleted);

                    Database.fotoLyceumList.add(fotoLyceum);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addFoto (FotoLyceum fotoLyceum) {
        Connection connection = Database.getConnection();

        if (connection!= null) {
            String sql = "INSERT INTO lyceum_foto(description, image)" +
                    "VALUES(?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1, fotoLyceum.getDescription());
                preparedStatement.setString(2, fotoLyceum.getImage());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadFotoRepository();
    }


    public static void deleteFoto(Integer id) {
        Connection connection = Database.getConnection();
        if (connection!=null) {

            String query = "DELETE FROM lyceum_foto WHERE id = ?;";
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
