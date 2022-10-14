package com.company.db;

import com.company.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public interface Database {

    List<Kafedra> kafedraList = new ArrayList<>();
    List<Teacher> teacherList = new ArrayList<>();
    List<Student> studentlist = new ArrayList<>();
    List<Rahbariyat> rahbariyatlist = new ArrayList<>();
    List<Level> levelList = new ArrayList<>();
    List<lHaqida> L_HAQIDA_LIST = new ArrayList<>();

    List<lVideo> L_VIDEO_LIST = new ArrayList<>();
    List<lFoto> L_FOTO_LIST = new ArrayList<>();

    List<BioLyceum> bioLyceumList = new ArrayList<>();

    List<VideoLyceum> videoLyceumList = new ArrayList<>();

    List<FotoLyceum> fotoLyceumList = new ArrayList<>();

    List<StudentP> studentPList = new ArrayList<>();

    List<StudentM> studentMList = new ArrayList<>();

    static Connection getConnection() {

        final String DB_USERNAME = "postgres";
        final String DB_PASSWORD = "2004";
        final String DB_URL = "jdbc:postgresql://localhost:5432/lyceum_db";

        Connection conn = null;
        try {

            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (conn != null) {
                System.out.println("Connection worked");
            } else {
                System.out.println("Connection failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
