package com.company.util;

import com.company.db.Database;
import com.company.model.*;
import com.company.repository.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.company.db.Database.*;
import static com.company.repository.KafedraRepository.*;

public class InlineKeyboardUtil {

    //ADMIN


    public static InlineKeyboardMarkup AdminMenu() {

        InlineKeyboardButton aboutLyceum = getButton("Litsey haqida", "about_ly_admin");
        InlineKeyboardButton bosLyceum = getButton("Rahbariyat", "direktors_ly_admin");
        InlineKeyboardButton cafedraLyceum = getButton("Kafedralar", "cafedras_admin");
        InlineKeyboardButton dtmLyceum = getButton("DTM natijalari", "exams_dtm_admin");
        InlineKeyboardButton registrationLyceum = getButton("Onlayn registratsiya", "online_reg_admin");
        InlineKeyboardButton test = getButton("Test sinovi", "test");
        InlineKeyboardButton sozlama = getButton("Sozlamalar", "sozlamalar");

        List<InlineKeyboardButton> row1 = getRow(aboutLyceum, bosLyceum);
        List<InlineKeyboardButton> row2 = getRow(cafedraLyceum, dtmLyceum);
        List<InlineKeyboardButton> row3 = getRow(test);
        List<InlineKeyboardButton> row4 = getRow(registrationLyceum);
        List<InlineKeyboardButton> row5 = getRow(sozlama);


        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3, row4, row5);
        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup showStudentsRegistration () {
        InlineKeyboardButton withPassport = getButton("Passport orqali", "with_passport");
        InlineKeyboardButton withMetirka = getButton("Metirka orqali", "with_metirka");
        InlineKeyboardButton back = getButton("Ortga qaytish", "backFromShowStudent");

        List<InlineKeyboardButton> row1 = getRow(withPassport);
        List<InlineKeyboardButton> row2 = getRow(withMetirka);
        List<InlineKeyboardButton> row3 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);
        return new InlineKeyboardMarkup(rowList);
    }




    public static InlineKeyboardMarkup bioVideoFoto () {
        InlineKeyboardButton bioLyceum = getButton("Biografiya", "bio_admin");
        InlineKeyboardButton videoLyceum = getButton("Video", "video_admin");
        InlineKeyboardButton fotoLyceum = getButton("Rasmlar", "rasm_admin");

        List<InlineKeyboardButton> row1 = getRow(bioLyceum);
        List<InlineKeyboardButton> row2 = getRow(videoLyceum, fotoLyceum);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2);
        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup Foto () {
        InlineKeyboardButton fotoLyceum = getButton("Video", "video_adminaa");
        InlineKeyboardButton BackLyceum = getButton("Rasmlar", "rasm_admin_adaaaa");

        List<InlineKeyboardButton> row1 = getRow(fotoLyceum);
        List<InlineKeyboardButton> row2 = getRow(BackLyceum);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2);
        return new InlineKeyboardMarkup(rowList);
    }




    //SOZLAMA
    public static InlineKeyboardMarkup sozlama() {
        InlineKeyboardButton Rahbarlar = getButton("Rahbarlar", "rahbarlar");
        InlineKeyboardButton Oqtuvchilar = getButton("O'qituvchilar", "oqituvchilar");
        InlineKeyboardButton litseyHaqida = getButton("Litsey haqida", "litsey_haqida");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "sozlamalar_back");//

        List<InlineKeyboardButton> row1 = getRow(Rahbarlar, Oqtuvchilar);
        List<InlineKeyboardButton> row2 = getRow(litseyHaqida);
        List<InlineKeyboardButton> row3 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);

        return new InlineKeyboardMarkup(rowList);
    }

    //SOZLAMA LITSEY

    public static InlineKeyboardMarkup sozlamaLitsey() {
        InlineKeyboardButton bioData = getButton("Biografiya", "biografiya_data");
        InlineKeyboardButton videoData = getButton("Video", "video_data");
        InlineKeyboardButton rasmData = getButton("Rasmlar", "rasm_data");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "sozlama_back");//

        List<InlineKeyboardButton> row1 = getRow(bioData, videoData);
        List<InlineKeyboardButton> row2 = getRow(rasmData);
        List<InlineKeyboardButton> row3 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);

        return new InlineKeyboardMarkup(rowList);
    }

    //SOZLAMA BIO

    public static InlineKeyboardMarkup sozlamaBio() {
        InlineKeyboardButton bioShow = getButton("Biografiyani ko'rish", "bio_show_data");
        InlineKeyboardButton bioDelete = getButton("Biografiyani o'chirish", "bio_delete_data");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "bio_back");
        List<InlineKeyboardButton> row1 = getRow(bioShow, bioDelete);
        List<InlineKeyboardButton> row2 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2);

        return new InlineKeyboardMarkup(rowList);
    }

    //SOZLAMA VIDEO

    public static InlineKeyboardMarkup sozlamaVideo() {
        InlineKeyboardButton videoShow = getButton("Videoni ko'rish", "video_show_data");
        InlineKeyboardButton videoDelete = getButton("Videoni o'chirish", "video_delete_data");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "video_back");
        List<InlineKeyboardButton> row1 = getRow(videoShow, videoDelete);
        List<InlineKeyboardButton> row2 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2);

        return new InlineKeyboardMarkup(rowList);
    }

    //SOZLAMA RASM

    public static InlineKeyboardMarkup sozlamaRasm() {
        InlineKeyboardButton rasmShow = getButton("Rasmlarni ko'rish", "rasm_show_data");
        InlineKeyboardButton rasmDelete = getButton("Rasmni o'chirish", "rasm_delete_data");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "rasm_back");
        List<InlineKeyboardButton> row1 = getRow(rasmShow, rasmDelete);
        List<InlineKeyboardButton> row2 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2);

        return new InlineKeyboardMarkup(rowList);
    }


    //RAHBAR
    public static InlineKeyboardMarkup sozlamaRahbar() {
        InlineKeyboardButton allRahbarlar = getButton("Rahbarlarni ko'rish", "all_rahbar");
        InlineKeyboardButton deleteRahbarlar = getButton("Rahbarlarni o'chirish", "delete_rahbar");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "rahbarlar_back");//

        List<InlineKeyboardButton> row1 = getRow(allRahbarlar);
        List<InlineKeyboardButton> row2 = getRow(deleteRahbarlar);
        List<InlineKeyboardButton> row3 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);

        return new InlineKeyboardMarkup(rowList);
    }

    //OQITUVCHI
    public static InlineKeyboardMarkup sozlamaOqtuvchi() {
        InlineKeyboardButton allOqtuvchi = getButton("O'qituvchilarni ko'rish", "all_oqtuvchi");
        InlineKeyboardButton deleteOqtuvchi = getButton("O'qituvchilarni o'chirish", "delete_oqtuvchi");
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "oqtuvchi_back");

        List<InlineKeyboardButton> row1 = getRow(allOqtuvchi);
        List<InlineKeyboardButton> row2 = getRow(deleteOqtuvchi);
        List<InlineKeyboardButton> row3 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup rektoratForAdminInlineMarkup () {

        LevelRepository.loadLevelList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Level level : Database.levelList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(level.getName());
            button.setCallbackData("add_rektor/" + level.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = getButton("⏪ Ortga ", "backFromLevel");
        buttonList.add(button);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);

    }
    public static InlineKeyboardMarkup biografiya() {

        LyceumRepository.loadLyceumList();


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (lHaqida lHaqida : Database.L_HAQIDA_LIST) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(lHaqida.getName());
            button.setCallbackData("add_bio/"+ lHaqida.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton Backbutton = getButton("⏪ Ortga qaytish", "backFromBio");
        buttonList.add(Backbutton);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup video() {

        lVideoRepository.loadlVideoList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (lVideo lVideo : L_VIDEO_LIST) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(lVideo.getName());
            button.setCallbackData("add_video/"+ lVideo.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton Backbutton = getButton("⏪ Ortga qaytish", "backFromVideo");
        buttonList.add(Backbutton);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);

    }

    public static InlineKeyboardMarkup kafedraForAdminInlineMurkup() {

        loadKafedraList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Kafedra kafedra : Database.kafedraList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(kafedra.getName());
            button.setCallbackData("add_teacher/" + kafedra.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button2 = getButton("⏪ Ortga qaytish", "backFromKafedra");
        buttonList.add(button2);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup confirmAddTeacherMarkup() {

        InlineKeyboardButton commit = getButton("Ha", "add_product_commit");
        InlineKeyboardButton cancel = getButton("Yo'q", "add_product_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }


    public static InlineKeyboardMarkup confirmAddFoto() {

        InlineKeyboardButton commit = getButton("Ha", "add_foto_commit");
        InlineKeyboardButton cancel = getButton("Yo'q", "add_foto_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }

    public static InlineKeyboardMarkup confirmAddRahbarMarkup() {

        InlineKeyboardButton commit = getButton("Ha", "add_rahbar_commit");
        InlineKeyboardButton cancel = getButton("Yo'q", "add_rahbar_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }


    public static InlineKeyboardMarkup deleteTeacher() {
        TeacherRepository.loadTeacherList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Teacher teacher : teacherList) {
            InlineKeyboardButton button = new InlineKeyboardButton(teacher.getName() + " " + teacher.getSurname() + " ❌");
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            button.setCallbackData("deleteTeacher/" + teacher.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("⏪ Ortga qaytish");
        button.setCallbackData("backFromDeleteTeacher");
        buttonList.add(button);
        rowList.add(buttonList);
        return new InlineKeyboardMarkup(rowList);

    }

    public static InlineKeyboardMarkup deleteBio () {
        BioRepository.loadBioLyceumList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (BioLyceum bioLyceum : bioLyceumList) {
            InlineKeyboardButton button = new InlineKeyboardButton(bioLyceum.getDescription());
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            button.setCallbackData("delete_bio/"+ bioLyceum.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("⏪ Ortga qaytish");
        button.setCallbackData("backFromDeleteBio");
        buttonList.add(button);
        rowList.add(buttonList);
        return new InlineKeyboardMarkup(rowList);

    }

    public static InlineKeyboardMarkup deleteVideo () {
        VideoRepository.loadVideoRepository();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (VideoLyceum videoLyceum : videoLyceumList) {
            InlineKeyboardButton button = new InlineKeyboardButton(videoLyceum.getDescription());
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            button.setCallbackData("delete_video/"+ videoLyceum.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("⏪ Ortga qaytish");
        button.setCallbackData("backFromDeleteVideo");
        buttonList.add(button);
        rowList.add(buttonList);
        return new InlineKeyboardMarkup(rowList);

    }

    public static InlineKeyboardMarkup deleteRahbar() {
        RahbariyatRepository.loadRahbariyatlist();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Rahbariyat rahbariyat : rahbariyatlist) {
            InlineKeyboardButton button = new InlineKeyboardButton(rahbariyat.getFirstName() + " " + rahbariyat.getLastName() + " ❌");
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            button.setCallbackData("deleteRahbar/" + rahbariyat.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("⏪ Ortga qaytish");
        button.setCallbackData("backfromDeleteRahbar");
        buttonList.add(button);
        rowList.add(buttonList);
        return new InlineKeyboardMarkup(rowList);
    }




















    //USER

    public static InlineKeyboardMarkup about_lyceum () {
        InlineKeyboardButton bio = getButton("\uD83D\uDCDC Biografiya \uD83D\uDCDC", "bio");
        InlineKeyboardButton video = getButton("\uD83C\uDFA5 Video \uD83C\uDFA5", "video");
        InlineKeyboardButton rasm = getButton("\uD83D\uDCF8 Rasmlar \uD83D\uDCF8 ", "photo_user");
        InlineKeyboardButton menu = getButton("\uD83D\uDCCB Asosiy menyu \uD83D\uDCCB", "backToMenu");

        List<InlineKeyboardButton> row1 = getRow(bio);
        List<InlineKeyboardButton> row2 = getRow(video, rasm);
        List<InlineKeyboardButton> row3 = getRow(menu);
        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);

        return new InlineKeyboardMarkup(rowList);
    }
    public static InlineKeyboardMarkup backFromTeacher() {
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "backFromTeacher");
        List<InlineKeyboardButton> rowList = getRow(back);

        return new InlineKeyboardMarkup(getRowList(rowList));
    }

    public static InlineKeyboardMarkup backFromBio() {
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "backFromBiografiya");
        List<InlineKeyboardButton> rowList = getRow(back);

        return new InlineKeyboardMarkup(getRowList(rowList));
    }
 public static InlineKeyboardMarkup backFromVideo() {
        InlineKeyboardButton back = getButton(" Ortga qaytish", "backFromVideo");
        List<InlineKeyboardButton> rowList = getRow(back);

        return new InlineKeyboardMarkup(getRowList(rowList));
    }


    public static InlineKeyboardMarkup confirmAddBioImage() {

        InlineKeyboardButton commit = getButton("Ha", "add_bio_image");
        InlineKeyboardButton cancel = getButton("Yo'q", "add_image_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }

    public static InlineKeyboardMarkup confirmAddStudentPImage() {

        InlineKeyboardButton commit = getButton("Ha", "add_student_p_image");
        InlineKeyboardButton cancel = getButton("Yo'q", "student_p_image_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }


    public static InlineKeyboardMarkup confirmAddStudentMImage() {

        InlineKeyboardButton commit = getButton("Ha", "add_student_m_image");
        InlineKeyboardButton cancel = getButton("Yo'q", "student_m_image_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }

    public static InlineKeyboardMarkup confirmAddVideo() {

        InlineKeyboardButton commit = getButton("Ha", "add_lyceum_video");
        InlineKeyboardButton cancel = getButton("Yo'q", "video_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }



    public static InlineKeyboardMarkup backFromRahbar() {
        InlineKeyboardButton back = getButton("⏪ Ortga qaytish", "backFromRahbarToRahbariyat");
        List<InlineKeyboardButton> rowList = getRow(back);

        return new InlineKeyboardMarkup(getRowList(rowList));
    }


    public static InlineKeyboardMarkup teacherInlineMarkupForStudent(List<Teacher> teacherList) {

        TeacherRepository.loadTeacherList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Teacher teacher : teacherList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(teacher.getName() + " " + teacher.getSurname());
            button.setCallbackData("teacherForUser/" + teacher.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = getButton("⏪ Ortga qaytish", "backToKafedra");
        InlineKeyboardButton button1 = getButton("Asosiy menyu \uD83D\uDCCB", "backToAsMenu");
        buttonList.add(button);
        buttonList.add(button1);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);

    }


    public static InlineKeyboardMarkup biografiyaInlineForStudent(List<BioLyceum> bioLyceumList) {

        BioRepository.loadBioLyceumList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (BioLyceum bioLyceum : Database.bioLyceumList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(bioLyceum.getDescription());
            button.setCallbackData("bio_show_user/"+bioLyceum.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = getButton("⏪ Ortga qaytish", "backFromBioStudent");
        buttonList.add(button);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup  videoForUser(List<VideoLyceum> videoLyceumList) {

        VideoRepository.loadVideoRepository();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (VideoLyceum videoLyceum : Database.videoLyceumList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(videoLyceum.getDescription());
            button.setCallbackData("video_show_user/"+videoLyceum.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = getButton("⏪ Ortga qaytish", "backFromVideoStudent");
        InlineKeyboardButton buttonMenu = getButton("Asosiy menyu \uD83D\uDCCB", "backFromVideoStudentToMenu");
        buttonList.add(button);
        buttonList.add(buttonMenu);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup getFotoLyceum (int step, int size) {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        if (step != 0) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("⏪");
            button.setCallbackData("C/" + (step - 1));
            row.add(button);

        }


        if (step + 1 != size) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("⏩");
            button.setCallbackData("C/" + (step + 1));
            row.add(button);

        }
        rowList.add(row);
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("⏪ Ortga qaytish");
        button1.setCallbackData("_back");
        row1.add(button1);
        rowList.add(row1);


        markup.setKeyboard(rowList);

        return markup;

    }

    public static InlineKeyboardMarkup fotoForUser() {

        FotoRepository.loadFotoRepository();
        List<FotoLyceum> fotoLyceums = Database.fotoLyceumList;
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        for (FotoLyceum fotoLyceum : Database.fotoLyceumList) {

            InlineKeyboardButton button = getButton(fotoLyceum.getDescription(), "fotoForUser" + fotoLyceum.getId());
            List<InlineKeyboardButton> row = getRow(button);
            rowList.add(row);

        }

        InlineKeyboardButton back = getButton("Ortga", "backFromFotoForUser");
        List<InlineKeyboardButton> backrow = getRow(back);
        rowList.add(backrow);

        return new InlineKeyboardMarkup(rowList);

    }



    public static InlineKeyboardMarkup rahbarInlineMarkupForStudent(List<Rahbariyat> rahbariyatList) {

        RahbariyatRepository.loadRahbariyatlist();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Rahbariyat rahbariyat : rahbariyatList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(rahbariyat.getFirstName() + " " + rahbariyat.getLastName());
            button.setCallbackData("rahbarForStudent/" + rahbariyat.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = getButton("⏪ Ortga qaytish", "backFromShowRahbar");
        InlineKeyboardButton buttonMenu = getButton("Asosiy menyu \uD83D\uDCCB", "backFromShowRahbarToMenu");
        buttonList.add(button);
        buttonList.add(buttonMenu);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup rahbariyatForStudentMarkup() {

        LevelRepository.loadLevelList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Level level : Database.levelList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(level.getName());
            button.setCallbackData("levels_show/" + level.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = getButton("⏪ Ortga qaytish", "backFromRahbar");
        buttonList.add(button);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup lyceumAboutForUserInlineMurkup() {

        LyceumRepository.loadLyceumList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (lHaqida lHaqida : Database.L_HAQIDA_LIST) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(lHaqida.getName());
            button.setCallbackData("lyceum_about_show/" + lHaqida.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button1 = getButton("⏪ Ortga qaytish", "backLyceumAbout");
        buttonList.add(button1);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup lVideo() {

        lVideoRepository.loadlVideoList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (lVideo lVideo : Database.L_VIDEO_LIST) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(lVideo.getName());
            button.setCallbackData("lyceum_video_show/" + lVideo.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button1 = getButton("⏪ Ortga qaytish", "backLyceumVideo");
        buttonList.add(button1);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }




    public static InlineKeyboardMarkup kafedraForUserInlineMurkup() {

        loadKafedraList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (Kafedra kafedra : Database.kafedraList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(kafedra.getName());
            button.setCallbackData("kafedras_show/" + kafedra.getId());
            buttonList.add(button);
            rowList.add(buttonList);

        }
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button1 = getButton("⏪ Ortga qaytish", "backButton");
        buttonList.add(button1);
        rowList.add(buttonList);

        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup paspportAndMetirka () {

        InlineKeyboardButton studentPassport = getButton("\uD83D\uDCDD Ariza (Passport) \uD83D\uDCDD", "student_passport" );
        InlineKeyboardButton studentMetirka = getButton("\uD83D\uDCDD Ariza (Tug'ilganlik haqida guvohnoma) \uD83D\uDCDD", "student_metirka" );
        InlineKeyboardButton backAriza = getButton("⏪ Ortga qaytish", "back_ariza" );

        List<InlineKeyboardButton> row = getRow(studentPassport);
        List<InlineKeyboardButton> row1 = getRow(studentMetirka);
        List<InlineKeyboardButton> row2 = getRow(backAriza);
        List<List<InlineKeyboardButton>> rowList = getRowList(row, row1, row2);

        return new InlineKeyboardMarkup(rowList);

    }

    public static InlineKeyboardMarkup userMenu() {

        InlineKeyboardButton aboutLyceum = getButton("Litsey haqida", "about_lyceum");
        InlineKeyboardButton bosLyceum = getButton("Rahbariyat", "direktors_ly");
        InlineKeyboardButton cafedraLyceum = getButton("Kafedralar", "cafedras");
        InlineKeyboardButton dtmLyceum = getButton("DTM natijalari", "exams_dtm");
        InlineKeyboardButton registrationLyceum = getButton("Onlayn registratsiya", "online_reg");
        InlineKeyboardButton test = getButton("Test sinovi", "test");

        List<InlineKeyboardButton> row1 = getRow(aboutLyceum, bosLyceum, cafedraLyceum);
        List<InlineKeyboardButton> row2 = getRow(dtmLyceum, test);
        List<InlineKeyboardButton> row3 = getRow(registrationLyceum);
        ;

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);
        return new InlineKeyboardMarkup(rowList);
    }

    ;

    private static InlineKeyboardButton getButton(String demo, String data) {
        InlineKeyboardButton button = new InlineKeyboardButton(demo);
        button.setCallbackData(data);
        return button;
    }

    private static List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return Arrays.asList(buttons);
    }


    private static List<List<InlineKeyboardButton>> getRowList(List<InlineKeyboardButton>... rows) {
        return Arrays.asList(rows);
    }


}
