package com.company.repository;

import com.company.db.Database;
import com.company.model.BioLyceum;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BioRepository {

    public static List<BioLyceum> getBioByLyceumId (Integer lyceumId) {
        BioRepository.loadBioLyceumList();
        return Database.bioLyceumList.stream()
                .filter(bioLyceum -> bioLyceum.getLyceumId().equals(lyceumId))
                .collect(Collectors.toList());
    }

    public static BioLyceum getBioById(Integer bioId) {
        loadBioLyceumList();
        Optional<BioLyceum> optional = Database.bioLyceumList.stream()
                .filter(lyceum -> lyceum.getId().equals(bioId))
                .findFirst();
        return optional.orElse(null);
    }

    public static void loadBioLyceumList() {
        Connection connection = Database.getConnection();
        if (connection != null) {
            try (Statement statement = connection.createStatement()){

                Database.bioLyceumList.clear();

                String sql = "SELECT * FROM lyceum_bio WHERE NOT deleted";

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int lyceumId = resultSet.getInt("lyceum_id");
                    String image = resultSet.getString("image");
                    String description = resultSet.getString("description");
                    boolean deleted = resultSet.getBoolean("deleted");

                    BioLyceum bioLyceum = new BioLyceum(id, lyceumId, description, image, deleted);

                    Database.bioLyceumList.add(bioLyceum);
                }
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void addBio (BioLyceum bioLyceum) {
        Connection connection = Database.getConnection();

        if (connection!= null) {
            String sql = "INSERT INTO lyceum_bio(description, image, lyceum_id)" +
                    "VALUES(?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1, bioLyceum.getDescription());
                preparedStatement.setString(2, bioLyceum.getImage());
                preparedStatement.setInt(3, bioLyceum.getLyceumId());

                int executeUpdate = preparedStatement.executeUpdate();
                System.out.println(executeUpdate);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        loadBioLyceumList();
    }

    public static void deleteBio(Integer id) {
        Connection connection = Database.getConnection();
        if (connection!=null) {

            String query = "DELETE FROM lyceum_bio WHERE id = ?;";
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
