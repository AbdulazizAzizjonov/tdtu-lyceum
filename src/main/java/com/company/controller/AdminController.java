package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.db.Database;
import com.company.enums.AdminStatus;
import com.company.model.*;
import com.company.repository.*;
import com.company.util.InlineKeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.company.container.ComponentContainer.*;

public class AdminController {

    public void handleMessage(User user, Message message) {
        if (message.hasText()) {
            handleText(user, message);
        } else if (message.hasContact()) {
            handleContact(user, message);
        } else if (message.hasPhoto()) {
            handlePhoto(user, message);
        } else if (message.hasVideo()) {
            handleVideo(user, message);
        }
    }

    private void handleVideo(User user, Message message) {
        List<Video> videoList = Collections.singletonList(message.getVideo());

        String chatId = String.valueOf(message.getChatId());

        if (videoLyceumStepMap.containsKey(chatId)) {
            VideoLyceum videoLyceum = videoLyceumMap.get(chatId);

            if (videoLyceumStepMap.get(chatId).equals(AdminStatus.SELECT_LYCEUM_VIDEO_MALUMOT)) {
                videoLyceum.setVideo(videoList.get(videoList.size() - 1).getFileId());

                SendVideo sendVideo = new SendVideo(chatId, new InputFile(videoLyceum.getVideo()));
                sendVideo.setCaption(String.format(
                        "Video tavsifnomasi: %s \n" +
                                "Video bazaga saqlansinmi? ", videoLyceum.getDescription()
                ));
                sendVideo.setReplyMarkup(InlineKeyboardUtil.confirmAddVideo());

                LYCEUMBot.sendMsg(sendVideo);
            }
        }
    }


    private void handleContact(User user, Message message) {
    }

    private void handleText(User user, Message message) {


        String text = message.getText();
        String chatId = String.valueOf(message.getChatId());


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        if (text.equals("/start")) {
            sendMessage.setText("Xush Kelibsiz admin, Qanday ishni bajarmohchisiz:");
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

        } else if (text.equals("/passport")) {

            StudentPRepository.loadStudentPList();

            if (!Database.studentPList.isEmpty()) {

                StudentPRepository.loadStudentPList();


                for (StudentP studentP : Database.studentPList) {
                    SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(studentP.getImage()));
                    sendPhoto.setCaption(String.format(
                            "Ismi:  %s\n" +
                                    "Famiylasi:  %s\n" +
                                    "Sharifi:  %s",
                            studentP.getName(), studentP.getSurname(), studentP.getMiddlname()

                    ));

                    LYCEUMBot.sendMsg(sendPhoto);

                    sendPhoto = new SendPhoto(chatId, new InputFile(studentP.getPImage()));
                    sendPhoto.setCaption(String.format(
                            "Passport seriasi:  %s", studentP.getPassportId()
                    ));

                    LYCEUMBot.sendMsg(sendPhoto);


                }

            }

        } else if (text.equals("/metirka")) {


            StudentMRepository.loadStudentMList();

            if (!Database.studentMList.isEmpty()) {

                StudentMRepository.loadStudentMList();


                for (StudentM studentM : Database.studentMList) {
                    SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(studentM.getImage()));
                    sendPhoto.setCaption(String.format(
                            "Ismi:  %s\n" +
                                    "Famiylasi:  %s\n" +
                                    "Sharifi:  %s",
                            studentM.getName(), studentM.getSurname(), studentM.getMiddlname()

                    ));

                    LYCEUMBot.sendMsg(sendPhoto);

                    sendPhoto = new SendPhoto(chatId, new InputFile(studentM.getMImage()));
                    sendPhoto.setCaption(String.format(
                            "Metirka raqami:  %s", studentM.getMetirkaId()
                    ));

                    LYCEUMBot.sendMsg(sendPhoto);


                }

            }
        } else if (videoLyceumStepMap.containsKey(chatId)) {
            VideoLyceum videoLyceum = videoLyceumMap.get(chatId);

            if (videoLyceumStepMap.get(chatId).equals(AdminStatus.SELECT_LYCEUM_FOR_VIDEO)) {
                videoLyceum.setDescription(text);
                videoLyceumStepMap.put(chatId, AdminStatus.SELECT_LYCEUM_VIDEO_MALUMOT);

                sendMessage.setText("Videoni jonating!");
            }
        } else if (teacherStepMap.containsKey(chatId)) {

            Teacher teacher = teacherMap.get(chatId);

            if (teacherStepMap.get(chatId).equals(AdminStatus.SELECT_KAFEDRA_FOR_ADD_TEACHER)) {
                teacher.setName(text);
                teacherStepMap.put(chatId, AdminStatus.ENTERED_TEACHER_NAME);

                sendMessage.setText("O'qtuvchining famiylasini kiriting: ");


            } else if (teacherStepMap.get(chatId).equals(AdminStatus.ENTERED_TEACHER_NAME)) {
                teacher.setSurname(text);
                teacherStepMap.put(chatId, AdminStatus.ENTERED_TEACHER_SURNAME);

                sendMessage.setText("O'qtuvchining darajasini kiriting: ");


            } else if (teacherStepMap.get(chatId).equals(AdminStatus.ENTERED_TEACHER_SURNAME)) {
                teacher.setLevel(text);
                teacherStepMap.put(chatId, AdminStatus.ENTERED_TEACHER_LEVEL);

                sendMessage.setText("O`qtuvchining xona raqamini kiriting: ");


            } else if (teacherStepMap.get(chatId).equals(AdminStatus.ENTERED_TEACHER_LEVEL)) {
                teacher.setRoom(text);
                teacherStepMap.put(chatId, AdminStatus.ENTERED_TEACHER_ROOM);

                sendMessage.setText("O'qituvchinig telefon raqamini kiriting:");

            } else if (teacherStepMap.get(chatId).equals(AdminStatus.ENTERED_TEACHER_ROOM)) {
                teacher.setPhone(text);
                teacherStepMap.put(chatId, AdminStatus.ENTERED_TEACHER_PHONE);

                sendMessage.setText("O`qtuvchining rasmini jo'nating: ");
            }
        } else if (rahbariyatStepMap.containsKey(chatId)) {
            Rahbariyat rahbariyat = rahbariyatMap.get(chatId);

            if (rahbariyatStepMap.get(chatId).equals(AdminStatus.ADD_RAHBARIYAT)) {
                rahbariyat.setFirstName(text);
                rahbariyatStepMap.put(chatId, AdminStatus.ENTERED_RAHBAR_NAME);

                sendMessage.setText("Rahbarning famiylasini kiriting: ");

            } else if (rahbariyatStepMap.get(chatId).equals(AdminStatus.ENTERED_RAHBAR_NAME)) {
                rahbariyat.setLastName(text);
                rahbariyatStepMap.put(chatId, AdminStatus.ENTERED_RAHBAR_SURNAME);

                sendMessage.setText("Rahbarning lavozimini kiriting: ");

            } else if (rahbariyatStepMap.get(chatId).equals(AdminStatus.ENTERED_RAHBAR_SURNAME)) {
                rahbariyat.setLevel(text);
                rahbariyatStepMap.put(chatId, AdminStatus.ENTERED_RAHBAR_LEVEL);

                sendMessage.setText("Rahbarning telefon raqamini kiriting: ");

            } else if (rahbariyatStepMap.get(chatId).equals(AdminStatus.ENTERED_RAHBAR_LEVEL)) {
                rahbariyat.setPhone_number(text);
                rahbariyatStepMap.put(chatId, AdminStatus.ENTERED_RAHBAR_PHONE);

                sendMessage.setText("Rahbarning xonasini kiriting: ");
            } else if (rahbariyatStepMap.get(chatId).equals(AdminStatus.ENTERED_RAHBAR_PHONE)) {
                rahbariyat.setRoom(text);
                rahbariyatStepMap.put(chatId, AdminStatus.ENTERED_RAHBAR_ROOM);

                sendMessage.setText("Rahbarning rasmini jonating.");
            }
        } else if (aboutLyceumStepMap.containsKey(chatId)) {

            BioLyceum bioLyceum = aboutLyceumMap.get(chatId);

            if (aboutLyceumStepMap.get(chatId).equals(AdminStatus.SELECT_LYCEUM_FOR_MALUMOT)) {
                bioLyceum.setDescription(text);
                aboutLyceumStepMap.put(chatId, AdminStatus.SELECT_LYCEUM_MALUMOT);

                sendMessage.setText("Biografiya uchun rasm jonating!");
            }
        } else if (fotoLyceumStepMap.containsKey(chatId)) {

            FotoLyceum fotoLyceum = fotoLyceumMap.get(chatId);

            if (fotoLyceumStepMap.get(chatId).equals(AdminStatus.SELECT_LYCEUM_FOR_FOTO)) {
                fotoLyceum.setDescription(text);
                fotoLyceumStepMap.put(chatId, AdminStatus.SELECT_LYCEUM_FOTO);

                sendMessage.setText("Rasimni jonating!");
            }
        } else {
            sendMessage.setText("Kechirasiz admin notog'ri xabar kiritingiz.");

        }
        LYCEUMBot.sendMsg(sendMessage);
    }


    private void handlePhoto(User user, Message message) {
        List<PhotoSize> photoSizeList = message.getPhoto();

        String chatId = String.valueOf(message.getChatId());

        if (teacherStepMap.containsKey(chatId)) {
            Teacher teacher = teacherMap.get(chatId);

            if (teacherStepMap.get(chatId).equals(AdminStatus.ENTERED_TEACHER_PHONE)) {
                teacher.setImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(teacher.getImage()));
                sendPhoto.setCaption(String.format(
                        "Kafedrasi:\t %s\n" +
                                "ID :      \t    %s\n" +
                                "Ismi:     \t     %s\n" +
                                "Famiylasi:\t %s\n" +
                                "Darajasi: \t %s\n" +
                                "Telefon raqami \t%s\n" +
                                "Xonasi:   \t  %s\n\n" +
                                "Quyidagi O'qtuvchi bazaga qo'shilsinmi?",
                        KafedraRepository.getKafedraById(teacher.getKafedraId()).getName(), teacher.getId(),
                        teacher.getName(), teacher.getSurname(), teacher.getLevel(), teacher.getPhone(), teacher.getRoom()));
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.confirmAddTeacherMarkup());

                LYCEUMBot.sendMsg(sendPhoto);
            }
        } else if (rahbariyatStepMap.containsKey(chatId)) {
            Rahbariyat rahbariyat = rahbariyatMap.get(chatId);

            if (rahbariyatStepMap.get(chatId).equals(AdminStatus.ENTERED_RAHBAR_ROOM)) {
                rahbariyat.setImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(rahbariyat.getImage()));
                sendPhoto.setCaption(String.format(
                        "Lavozimi:\t %s \n" +
                                "ID : \t %s\n" +
                                "Ismi: \t %s\n" +
                                "Famiylasi: \t %s\n" +
                                "Lavozimi: \t %s\n" +
                                "Telefon raqami: \t %s\n" +
                                "Xonasi: \t %s\n" +
                                "Quyidagi rahbar bazaga qo'shilsinmi?",
                        LevelRepository.getLevelById(rahbariyat.getLevelId()).getName(), rahbariyat.getId(),
                        rahbariyat.getFirstName(), rahbariyat.getLastName(), rahbariyat.getLevel(), rahbariyat.getPhone_number(),
                        rahbariyat.getRoom()));
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.confirmAddRahbarMarkup());

                LYCEUMBot.sendMsg(sendPhoto);
            }
        } else if (aboutLyceumStepMap.containsKey(chatId)) {
            BioLyceum bioLyceum = aboutLyceumMap.get(chatId);

            if (aboutLyceumStepMap.get(chatId).equals(AdminStatus.SELECT_LYCEUM_MALUMOT)) {
                bioLyceum.setImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(bioLyceum.getImage()));
                sendPhoto.setCaption(String.format(
                        "\tTDTU akademik litseyi\t\n" +
                                "Tafsiloti %s \n" +
                                "Bazaga qo'shilsinmi?",
                        bioLyceum.getDescription()));
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.confirmAddBioImage());

                LYCEUMBot.sendMsg(sendPhoto);
            }
        } else if (fotoLyceumStepMap.containsKey(chatId)) {
            FotoLyceum fotoLyceum = fotoLyceumMap.get(chatId);

            if (fotoLyceumStepMap.get(chatId).equals(AdminStatus.SELECT_LYCEUM_FOTO)) {
                fotoLyceum.setImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(fotoLyceum.getImage()));
                sendPhoto.setCaption(String.format(
                        "TDTU akademik litseyi\n" +
                                "Tafsiloti %s \n" +
                                "Bazaga qo'shilsinmiz?",
                        fotoLyceum.getDescription()));
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.confirmAddFoto());

                LYCEUMBot.sendMsg(sendPhoto);
            }
        }
    }

    public void handleCallBack(User user, Message message, String data) {

        String chatId = String.valueOf(message.getChatId());


        if (data.equals("about_ly_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Litsey haqidagi malumotlarni\nquyidagilarga kiriting \uD83D\uDC47\uD83D\uDE0A!"
            );


            sendMessage.setReplyMarkup(InlineKeyboardUtil.bioVideoFoto());

            LYCEUMBot.sendMsg(sendMessage);

            //BACK FROM BIOVIDOFOTO

        } else if (data.equals("bio_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Litsey biografiyasi uchun  \uD83D\uDE0A!"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.biografiya());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("add_bio/")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);


            Integer lyceumId = Integer.parseInt(data.split("/")[1]);


            SendMessage sendMessage = new SendMessage(user.getId().toString(),
                    "Biografiya ostiga ma'lumot yozing!"
            );

            LYCEUMBot.sendMsg(sendMessage);

            aboutLyceumMap.remove(chatId);
            aboutLyceumStepMap.remove(chatId);

            aboutLyceumStepMap.put(chatId, AdminStatus.CLICKED_ADD_BIO);
            aboutLyceumMap.put(chatId,
                    new BioLyceum(null, null, null));

            aboutLyceumStepMap.put(chatId, AdminStatus.SELECT_LYCEUM_FOR_MALUMOT);
            BioLyceum bioLyceum = aboutLyceumMap.get(chatId);
            bioLyceum.setLyceumId(lyceumId);


        } else if (data.equals("add_bio_image")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );
            LYCEUMBot.sendMsg(deleteMessage);

            BioLyceum bioLyceum = aboutLyceumMap.get(chatId);

            BioRepository.addBio(bioLyceum);

            aboutLyceumMap.remove(chatId);
            aboutLyceumStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Bio rasim bazaga saqlandi.\n\n" + "Amalni tanlang:"
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("add_image_cancel")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );
            LYCEUMBot.sendMsg(deleteMessage);


            aboutLyceumMap.remove(chatId);
            aboutLyceumStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Amalni tanlang:"
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("litsey_haqida")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Quyidagilardan birini tanlang"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaLitsey());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("biografiya_data")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Quyidagilardan birini tanlang"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaBio());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("bio_show_data")) {

            BioRepository.loadBioLyceumList();

            if (!Database.bioLyceumList.isEmpty()) {

                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                BioRepository.loadBioLyceumList();

                for (BioLyceum bioLyceum : Database.bioLyceumList) {
                    SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(bioLyceum.getDescription()));
                    sendPhoto.setCaption(String.format("Litsey haqida qisqacha.\n" +
                                    "Litsey %s \n,Tavsifnoma: %s", LyceumRepository.getLyceumById(bioLyceum.getLyceumId()).getName(),
                            bioLyceum.getDescription()));


                    LYCEUMBot.sendMsg(sendPhoto);

                }
                SendMessage sendMessage = new SendMessage(
                        chatId, "Sozlamalar menyusidasiz Quyidagi amallardan birini tanlang \uD83D\uDC47"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());
                LYCEUMBot.sendMsg(sendMessage);

            } else {

                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "Bio mavjud emas!"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());
                LYCEUMBot.sendMsg(sendMessage);
            }
        } else if (data.equals("bio_delete_data")) {

            BioRepository.loadBioLyceumList();

            if (!Database.bioLyceumList.isEmpty()) {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "O'chirmoqchi bo'lgan bioyingizni tanlang \uD83D\uDC47"
                );

                sendMessage.setReplyMarkup(InlineKeyboardUtil.deleteBio());
                LYCEUMBot.sendMsg(sendMessage);

            } else {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "Biolar mavjud emas ❗"
                );

                sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

                LYCEUMBot.sendMsg(sendMessage);
            }
        } else if (data.startsWith("delete_bio/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int bioId = Integer.parseInt(data.split("/")[1]);

            BioRepository.deleteBio(bioId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Bio butunlay o'chirildi!\n\nYana qanday amal bajaramizmi?"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("video_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Video ma'lumot uchun \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.video());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("add_video/")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            Integer lyceumId = Integer.parseInt(data.split("/")[1]);


            SendMessage sendMessage = new SendMessage(user.getId().toString(),
                    "Video tavsifnomasini kiriting."
            );

            LYCEUMBot.sendMsg(sendMessage);


            videoLyceumMap.remove(chatId);
            videoLyceumStepMap.remove(chatId);

            videoLyceumStepMap.put(chatId, AdminStatus.CLICKED_ADD_VIDEO);
            videoLyceumMap.put(chatId,
                    new VideoLyceum(null, null, null));

            videoLyceumStepMap.put(chatId, AdminStatus.SELECT_LYCEUM_FOR_VIDEO);
            VideoLyceum videoLyceum = videoLyceumMap.get(chatId);
            videoLyceum.setLyceumId(lyceumId);

        } else if (data.equals("add_lyceum_video")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            VideoLyceum videoLyceum = videoLyceumMap.get(chatId);

            VideoRepository.addVideo(videoLyceum);

            videoLyceumMap.remove(chatId);
            videoLyceumStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Video bazaga saqlandi.\n\n Amalni tanlang:"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("video_cancel")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            videoLyceumMap.remove(chatId);
            videoLyceumStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Video bekor qilindi ❗️\n" +
                    "Amalni tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("video_delete_data")) {

            VideoRepository.loadVideoRepository();

            if (!Database.videoLyceumList.isEmpty()) {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "O'chirmoqchi bo'lgan videoyingizni tanlang \uD83D\uDC47"
                );

                sendMessage.setReplyMarkup(InlineKeyboardUtil.deleteVideo());
                LYCEUMBot.sendMsg(sendMessage);
            } else {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "Videolar mavjud emas ❗"
                );

                sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

                LYCEUMBot.sendMsg(sendMessage);
            }

        } else if (data.startsWith("delete_video/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int videoId = Integer.parseInt(data.split("/")[1]);

            VideoRepository.deleteVideo(videoId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Video butunlay o'chirildi!\n\nYana qanday amal bajaramizmi?"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("video_show_data")) {

            VideoRepository.loadVideoRepository();

            if (!Database.videoLyceumList.isEmpty()) {

                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                VideoRepository.loadVideoRepository();

                for (VideoLyceum videoLyceum : Database.videoLyceumList) {
                    SendVideo sendVideo = new SendVideo(chatId, new InputFile(videoLyceum.getDescription()));
                    sendVideo.setCaption(String.format("Litsey hayotida video lavgalar \uD83D\uDE0A \n" +
                            "Tavsifnoma: %s ", videoLyceum.getDescription()));

                    sendVideo.setReplyMarkup(InlineKeyboardUtil.sozlama());
                    LYCEUMBot.sendMsg(sendVideo);
                }

                SendMessage sendMessage = new SendMessage(
                        chatId, "Sozlamalar menyusidasiz Quyidagi amallardan birini tanlang \uD83D\uDC47"
                );

                sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());
                LYCEUMBot.sendMsg(sendMessage);
            } else {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "Video mavjud emas!"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());
                LYCEUMBot.sendMsg(sendMessage);
            }
        } else if (data.equals("video_data")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Quyidagilardan birini tanlang!"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaVideo());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("sozlama_back")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz sozlamalarga qaytingiz!"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("cafedras_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Kafedraladan birini tanlang."
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.kafedraForAdminInlineMurkup());
            LYCEUMBot.sendMsg(sendMessage);

            teacherMap.remove(chatId);
            teacherStepMap.remove(chatId);

            teacherStepMap.put(chatId, AdminStatus.CLICKED_ADD_KAFEDRA);
            teacherMap.put(chatId,
                    new Teacher(null, null, null, null, null, null, null));
        } else if (data.startsWith("add_teacher")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int kafedraId = Integer.parseInt(data.split("/")[1]);

            SendMessage sendMessage = new SendMessage(user.getId().toString(),
                    "O'qtuvchining ismini kiriting: "
            );

            LYCEUMBot.sendMsg(sendMessage);

            teacherStepMap.put(chatId, AdminStatus.SELECT_KAFEDRA_FOR_ADD_TEACHER);
            Teacher teacher = teacherMap.get(chatId);
            teacher.setKafedraId(kafedraId);

        } else if (data.equals("add_product_commit")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );
            LYCEUMBot.sendMsg(deleteMessage);

            Teacher teacher = teacherMap.get(chatId);

            TeacherRepository.addTeacher(teacher);

            teacherMap.remove(chatId);
            teacherStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, (teacher.getName() + " " + teacher.getSurname()) + " saqlandi.\n\n" + "Amalni tanlang:"
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("add_product_cancel")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );
            LYCEUMBot.sendMsg(deleteMessage);

            teacherMap.remove(chatId);
            teacherStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Amalni tanlang:"
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("sozlamalar")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("oqituvchilar")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaOqtuvchi());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("all_oqtuvchi")) {


            TeacherRepository.loadTeacherList();

            if (!Database.teacherList.isEmpty()) {


                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                TeacherRepository.loadTeacherList();

                for (Teacher teacher : Database.teacherList) {
                    SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(teacher.getImage()));
                    sendPhoto.setCaption(String.format("Kafedra:  %s\n" +
                                    "Ismi:  %s \nFamiylasi:  %s \nDarajasi  %s\n" +
                                    "Xonasi: %s \n ",
                            KafedraRepository.getKafedraById(teacher.getKafedraId()).getName(),
                            teacher.getName(), teacher.getSurname(), teacher.getLevel(), teacher.getRoom()));

                    LYCEUMBot.sendMsg(sendPhoto);

                }

                SendMessage sendMessage = new SendMessage(
                        chatId, "Bosh menyudasiz Quyidagi amallardan birini tanlang \uD83D\uDC47"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
                LYCEUMBot.sendMsg(sendMessage);

            } else {

                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );
                LYCEUMBot.sendMsg(deleteMessage);


                SendMessage sendMessage = new SendMessage(
                        chatId, "O'qituvchi mavjud emas!"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
                LYCEUMBot.sendMsg(sendMessage);
            }

        } else if (data.equals("delete_oqtuvchi")) {

            TeacherRepository.loadTeacherList();

            if (!Database.teacherList.isEmpty()) {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "O'chirmoqchi bo'lgan o'qituvchingizni tanlang \uD83D\uDC47"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.deleteTeacher());
                LYCEUMBot.sendMsg(sendMessage);

            } else {
                DeleteMessage deleteMessage = new DeleteMessage(chatId,
                        message.getMessageId()
                );
                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "O'qituvchilar mavjud emas ❗️"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

                LYCEUMBot.sendMsg(sendMessage);
            }

        } else if (data.startsWith("deleteTeacher")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int teacherId = Integer.parseInt(data.split("/")[1]);

            TeacherRepository.deleteTeacher(teacherId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "O'qituvchi butunlay o'chirildi!\n\nYana qanday amal bajaramiz?"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("oqtuvchi_back")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz sozlamalarga qaytingiz ✅\n\n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.startsWith("backFromDeleteTeacher")) {

            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytingiz ✅\n\nquyidagi amallardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaOqtuvchi());

            LYCEUMBot.sendMsg(sendMessage);


        } else if (data.equals("rahbarlar")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Amalni tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaRahbar());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("all_rahbar")) {

            RahbariyatRepository.loadRahbariyatlist();

            if (!Database.rahbariyatlist.isEmpty()) {


                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                RahbariyatRepository.loadRahbariyatlist();

                for (Rahbariyat rahbariyat : Database.rahbariyatlist) {
                    SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(rahbariyat.getImage()));
                    sendPhoto.setCaption(String.format(
                            "Rahbariyat: %s\n" +
                                    "Ismi:  %s\n" +
                                    "Famiylasi:  %s\n" +
                                    "Lavozimi  %s\n" +
                                    "Telefon raqami: %s\n" +
                                    "Xonasi %s",
                            LevelRepository.getLevelById(rahbariyat.getLevelId()).getName(),
                            rahbariyat.getFirstName(), rahbariyat.getLastName(), rahbariyat.getLevel(),
                            rahbariyat.getPhone_number(), rahbariyat.getRoom()));

                    LYCEUMBot.sendMsg(sendPhoto);

                }

                SendMessage sendMessage = new SendMessage(
                        chatId, "Bosh menyudasiz Quyidagi amallardan birini tanlang \uD83D\uDC47"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
                LYCEUMBot.sendMsg(sendMessage);

            } else {

                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );
                LYCEUMBot.sendMsg(deleteMessage);


                SendMessage sendMessage = new SendMessage(
                        chatId, "Rahbarlar mavjud emas ❗️"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
                LYCEUMBot.sendMsg(sendMessage);
            }
        } else if (data.equals("delete_rahbar")) {

            RahbariyatRepository.loadRahbariyatlist();

            if (!Database.rahbariyatlist.isEmpty()) {
                DeleteMessage deleteMessage = new DeleteMessage(
                        chatId, message.getMessageId()
                );

                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "O'chirmoqchi bo'lgan rahbaringizni tanlang \uD83D\uDC47"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.deleteRahbar());
                LYCEUMBot.sendMsg(sendMessage);

            } else {
                DeleteMessage deleteMessage = new DeleteMessage(chatId,
                        message.getMessageId()
                );
                LYCEUMBot.sendMsg(deleteMessage);

                SendMessage sendMessage = new SendMessage(
                        chatId, "Rahbarlar mavjud emas ❗️"
                );
                sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

                LYCEUMBot.sendMsg(sendMessage);
            }
        } else if (data.startsWith("deleteRahbar")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int rahbarId = Integer.parseInt(data.split("/")[1]);

            RahbariyatRepository.deleteRahbar(rahbarId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Rahbar butunlay o'chirildi!\n\nYana qanday amal bajaramiz?"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromKafedra")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz bosh menyuga qaytingiz.\n\nQuyidagi amallardan birini tanlang \uD83D\uDC47"
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("sozlamalar_back")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz bosh menyuga qaytingiz ✅\n\n" +
                    "Amalni tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("rahbarlar_back")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz sozlamalarga qaytingiz ✅\n\n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlama());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("backfromDeleteRahbar")) {

            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytingiz ✅\n\nquyidagi amalardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.sozlamaRahbar());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("direktors_ly_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz Rahbariyat bo'limiga o'tingiz ✅\n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.rektoratForAdminInlineMarkup());
            LYCEUMBot.sendMsg(sendMessage);

            rahbariyatMap.remove(chatId);
            rahbariyatStepMap.remove(chatId);

            rahbariyatStepMap.put(chatId, AdminStatus.CLICKED_ADD_RAHBAR);
            rahbariyatMap.put(chatId,
                    new Rahbariyat(null, null, null, null, null, null, null));
        } else if (data.startsWith("add_rektor")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int levelId = Integer.parseInt(data.split("/")[1]);

            SendMessage sendMessage = new SendMessage(user.getId().toString(),
                    "Rahbarning ismini kiriting: "
            );
            LYCEUMBot.sendMsg(sendMessage);

            rahbariyatStepMap.put(chatId, AdminStatus.ADD_RAHBARIYAT);
            Rahbariyat rahbariyat = rahbariyatMap.get(chatId);
            rahbariyat.setLevelId(levelId);

        } else if (data.equals("add_rahbar_commit")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            Rahbariyat rahbariyat = rahbariyatMap.get(chatId);

            RahbariyatRepository.addRahbar(rahbariyat);

            rahbariyatMap.remove(chatId);
            rahbariyatStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, (rahbariyat.getFirstName() + " " + rahbariyat.getLastName()) + " saqlandi.\n\n" + "Amalni tanlang \uD83D\uDC47"
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("with_passport")) {
            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            LYCEUMBot.sendMsg(deleteMessage);

            ExcelController.registrationXCEL();
            SendMessage sendMessage = new SendMessage(
                    chatId, "Passport orqali ariza to'ldirgan\n" +
                    "o'quvchilar ro'yxati \uD83D\uDC47\uD83C\uDFFD"
            );


            SendDocument sendDocument = new SendDocument();
            sendDocument.setDocument(new InputFile(Objects.requireNonNull(ExcelController.registrationXCEL())));
            sendDocument.setChatId(chatId);

            LYCEUMBot.sendMsg(sendMessage);
            LYCEUMBot.sendMsg(sendDocument);


            SendMessage sendMessage1 = new SendMessage(
                    chatId, "Bosh menyu \uD83D\uDC47\uD83C\uDFFD");

            sendMessage1.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

            LYCEUMBot.sendMsg(sendMessage1);

        } else if (data.equals("with_metirka")) {
            DeleteMessage deleteMessage = new DeleteMessage(chatId, message.getMessageId());
            LYCEUMBot.sendMsg(deleteMessage);

            ExcelController.registrationXCEL1();
            SendMessage sendMessage = new SendMessage(
                    chatId, "Metirka orqali ariza to'ldirgan\n" +
                    "o'quvchilar ro'yxati \uD83D\uDC47\uD83C\uDFFD"
            );

            SendDocument sendDocument = new SendDocument();
            sendDocument.setDocument(new InputFile(Objects.requireNonNull(ExcelController.registrationXCEL1())));
            sendDocument.setChatId(chatId);

            LYCEUMBot.sendMsg(sendMessage);
            LYCEUMBot.sendMsg(sendDocument);


            SendMessage sendMessage1 = new SendMessage(
                    chatId, "Bosh menyu \uD83D\uDC47\uD83C\uDFFD");

            sendMessage1.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

            LYCEUMBot.sendMsg(sendMessage1);
        } else if (data.equals("cancel_rahbar_commit")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            rahbariyatMap.remove(chatId);
            rahbariyatStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Amalni tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("online_reg_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            rahbariyatMap.remove(chatId);
            rahbariyatStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Hurmatli admin siz bu yerda\n" +
                    "onlayin tarzzda ro'yxatdan\n" +
                    "o'tgan o'quvchilarni korshingiz mumkin.\n\n" +
                    "Amalni tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.showStudentsRegistration());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("rasm_admin")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    user.getId().toString(), "Foto uchun tavsifnomasini kiriting ❗️"
            );

            LYCEUMBot.sendMsg(sendMessage);

            fotoLyceumMap.remove(chatId);
            fotoLyceumStepMap.remove(chatId);

            fotoLyceumStepMap.put(chatId, AdminStatus.CLICKED_ADD_FOTO);
            fotoLyceumMap.put(chatId,
                    new FotoLyceum(null, null));

            fotoLyceumStepMap.put(chatId, AdminStatus.SELECT_LYCEUM_FOR_FOTO);


        } else if (data.equals("add_foto_commit")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            FotoLyceum fotoLyceum = fotoLyceumMap.get(chatId);

            FotoRepository.addFoto(fotoLyceum);

            fotoLyceumMap.remove(chatId);
            fotoLyceumStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Rasim bazaga saqlandi.");

            sendMessage.setReplyMarkup(InlineKeyboardUtil.AdminMenu());
            LYCEUMBot.sendMsg(sendMessage);
        }
//BACK FOTO BUTTON

    }
}

