package com.company.container;

import com.company.LyceumBot;
import com.company.enums.AdminStatus;
import com.company.enums.StudentStatus;
import com.company.model.*;

import java.util.HashMap;
import java.util.Map;

public class ComponentContainer {
    public static final String BOT_TOKEN = "5307616877:AAFqvG4K5z6zSXnsVZwb1AlsPQ7jA2sq9oc";

    public static final String PATH = "src/main/resources/";
    public static final String GR = "https://t.me/+ABkUBW_jGfY2MzBi";
    public static final String BOT_NAME = "tdtualbot";

//    public static final String ADMIN_ID = "289536638";
    public static final String ADMIN_ID = "1870514088";

    public static LyceumBot LYCEUMBot;

    public static Map<String, StudentP> studentPMap = new HashMap<>();

    public static Map<String, StudentStatus> studentStepMap = new HashMap<>();

    public static Map<String, StudentM> studentMMap = new HashMap<>();

    public static Map<String, StudentStatus> studentMStepMap = new HashMap<>();

    public static Map<String, Teacher> teacherMap = new HashMap<>();

    public static Map<String, AdminStatus> teacherStepMap = new HashMap<>();


    public static Map<String, Rahbariyat> rahbariyatMap = new HashMap<>();

    public static Map<String, AdminStatus> rahbariyatStepMap = new HashMap<>();

    public static Map<String, BioLyceum> aboutLyceumMap = new HashMap<>();

    public static Map<String, AdminStatus> aboutLyceumStepMap = new HashMap<>();

    public static Map<String, VideoLyceum> videoLyceumMap = new HashMap<>();

    public static Map<String, AdminStatus> videoLyceumStepMap = new HashMap<>();


    public static Map<String, FotoLyceum> fotoLyceumMap = new HashMap<>();

    public static Map<String, AdminStatus> fotoLyceumStepMap = new HashMap<>();
}
