package com.company.controller;

import com.company.db.Database;
import com.company.enums.StudentStatus;
import com.company.model.*;
import com.company.repository.*;
import com.company.util.InlineKeyboardUtil;
import com.company.util.KeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.company.container.ComponentContainer.*;

public class MainController {


    public void handleMessage(User user, Message message) {
        if (message.hasText()) {
            handleText(user, message);
        } else if (message.hasPhoto()) {
            handlePhoto(user, message);
        }
    }

    private void handlePhoto(User user, Message message) {
        List<PhotoSize> photoSizeList = message.getPhoto();


        String chatId = String.valueOf(message.getChatId());

        if (studentStepMap.containsKey(chatId)) {
            StudentP studentP = studentPMap.get(chatId);

            if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MATHER_PHONE)) {
                studentP.setPImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_IMAGE);
                SendMessage sendMessage = new SendMessage(
                        chatId, "3x4 rasmingizni jo'nating."
                );
                LYCEUMBot.sendMsg(sendMessage);

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_IMAGE)) {
                studentP.setImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(studentP.getImage()));
                sendPhoto.setCaption(String.format(
                        "Ismi:  %s\n\n" +
                                "Famiylasi:  %s\n\n" +
                                "Sharifi:  %s\n\n" +
                                "Tugilgan sanasi:  %s\n\n" +
                                "Tug'ilgan shahari(viloyat):  %s\n\n" +
                                "Tug'ilgan tumani:  %s\n\n" +
                                "Yashash manzili:   %s \n\n" +
                                "Maktabi joylashgan shahari(viloyat):   %s\n\n" +
                                "Maktabi joylashgan tumani:   %s\n\n" +
                                "Maktab raqami:   %s\n\n" +
                                "Passport seriasi:   %s\n\n" +
                                "Telefon raqami:   %s\n\n" +
                                "Yotoqxona xizmati:   %s\n\n" +
                                "Otasining ismi:  %s\n\n" +
                                "Otasining famiylasi: %s\n\n" +
                                "Otasining telefon raqami:  %s\n\n" +
                                "Onasining ismi:  %s\n\n" +
                                "Onasining famiylasi:  %s\n\n" +
                                "Onasining telefon raqami:  %s\n\n\n" +
                                "\t\t\t\t\t\t\t\t❗️ DIQQAT  ❗️\t\t\n" +
                                "Mazkur ma'lumotlarni diqqat bilan kuzating,\n" +
                                "to'g'ri ekanligiga ishonch hosil qilsangiz\n" +
                                "ha tugmasini bosing, aks holda yo'q tugmasini\n" +
                                "bosib qaytadan urinib ko'ring.",
                        studentP.getName(), studentP.getSurname(), studentP.getMiddlname(),
                        studentP.getBirthday(), studentP.getCity(), studentP.getDistrict(), studentP.getManzil(),
                        studentP.getMaktabCity(), studentP.getMaktabDistrict(), studentP.getMaktabNumber(),
                        studentP.getPassportId(), studentP.getPhone(), studentP.getYotoqxona(),
                        studentP.getFathName(), studentP.getFathSurname(), studentP.getPhone(),
                        studentP.getMathName(), studentP.getMathSurname(), studentP.getMathPhone()));

                sendPhoto.setReplyMarkup(InlineKeyboardUtil.confirmAddStudentPImage());

                LYCEUMBot.sendMsg(sendPhoto);
            }

        } else if (studentMStepMap.containsKey(chatId)) {
            StudentM studentM = studentMMap.get(chatId);

            if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MATHER_PHONE)) {
                studentM.setMImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_IMAGE);
                SendMessage sendMessage = new SendMessage(
                        chatId, "3x4 rasmingizni jo'nating."
                );
                LYCEUMBot.sendMsg(sendMessage);

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_IMAGE)) {
                studentM.setImage(photoSizeList.get(photoSizeList.size() - 1).getFileId());

                SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(studentM.getImage()));
                sendPhoto.setCaption(String.format(
                        "Ismi:  %s\n\n" +
                                "Famiylasi:  %s\n\n" +
                                "Sharifi:  %s\n\n" +
                                "Tugilgan sanasi:  %s\n\n" +
                                "Tug'ilgan shahari(viloyat):  %s\n\n" +
                                "Tug'ilgan tumani:  %s\n\n" +
                                "Yashash manzili:   %s \n\n" +
                                "Maktabi joylashgan shahari(viloyat):   %s\n\n" +
                                "Maktabi joylashgan tumani:   %s\n\n" +
                                "Maktab №:   %s\n\n" +
                                "Metirka raqammi:   %s\n\n" +
                                "Telefon raqami:   %s\n\n" +
                                "Yotoqxona xizmati:   %s\n\n" +
                                "Otasining ismi:  %s\n\n" +
                                "Otasining famiylasi: %s\n\n" +
                                "Otasining telefon raqami:  %s\n\n" +
                                "Onasining ismi:  %s\n\n" +
                                "Onasining famiylasi:  %s\n\n" +
                                "Onasining telefon raqami:  %s\n\n\n" +
                                "\t\t\t\t\t\t\t\t❗️ DIQQAT  ❗️\t\t\n" +
                                "Mazkur ma'lumotlarni diqqat bilan kuzating,\n" +
                                "to'g'ri ekanligiga ishonch hosil qilsangiz\n" +
                                "ha tugmasini bosing, aks holda yo'q tugmasini\n" +
                                "bosib qaytadan urinib ko'ring.",
                        studentM.getName(), studentM.getSurname(), studentM.getMiddlname(),
                        studentM.getBirthday(), studentM.getCity(), studentM.getDistrict(), studentM.getManzil(),
                        studentM.getMaktabCity(), studentM.getMaktabDistrict(), studentM.getMaktabNumber(),
                        studentM.getMetirkaId(), studentM.getPhone(), studentM.getYotoqxona(),
                        studentM.getFathName(), studentM.getFathSurname(), studentM.getFathPhone(),
                        studentM.getMathName(), studentM.getMathSurname(), studentM.getMathPhone()));

                sendPhoto.setReplyMarkup(InlineKeyboardUtil.confirmAddStudentMImage());


                LYCEUMBot.sendMsg(sendPhoto);
            }
        } else {

            SendMessage sendMessage = new SendMessage(chatId,
                    "Xatolik qaytadan start bosib urinib ko'ring");

            LYCEUMBot.sendMsg(sendMessage);
        }
    }


    public void handleLocation(User user, Message message) {
    }

    public void handleContact(User user, Message message, String chatId) {
    }

    private void handleText(User user, Message message) {

        String text = message.getText();
        String chatId = String.valueOf(message.getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        Student student = StudentRepository.getStudentById(String.valueOf(message.getChatId()));

        if (text.equals("/start")) {
            if (student == null) {
                sendMessage.setText("Assalomu alaykum!\n" +
                        "@tdtualbot botiga xush kelibsiz \uD83D\uDE0A  \n" +
                        "Quyidagilardan birini tanlang \uD83D\uDC47");
                sendMessage.setChatId(chatId);
                sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());


            } else {
                System.out.println("BILMADIM");
            }

        } else if (studentStepMap.containsKey(chatId)) {
            StudentP studentP = studentPMap.get(chatId);

            if (studentStepMap.get(chatId).equals(StudentStatus.SELECT_STUDENT_BIO)) {
                studentP.setName(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_NAME);
                sendMessage.setText("Famiylangizni kiriting:");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_NAME)) {
                studentP.setSurname(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_SURNAME);
                sendMessage.setText("Sharifingizni kiriting:");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_SURNAME)) {
                studentP.setMiddlname(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MIDDLENAME);
                sendMessage.setText("Tugilgan sanangizni kiriting:\n(Masalan: xx.xx.xxxx   kun.oy.yil)");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MIDDLENAME)) {
                try {
                    String[] split = text.split("\\.");

                    int day = Integer.parseInt(split[0]);
                    int month = Integer.parseInt(split[1]);
                    int year = Integer.parseInt(split[2]);

                    LocalDate localDate = LocalDate.of(year, month, day);

                    if (localDate.isBefore(LocalDate.now())) {
                        studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_BIRTHDAY);
                        sendMessage.setText("Tugilgan shahringiz yoki viloyatingizni tanlang!");
                        sendMessage.setReplyMarkup(KeyboardUtil.shaharMurkap());
                        studentP.setBirthday(text);

                    } else {
                        sendMessage.setText("Hozirgi sanadan oldingi sana kiritilishi kerak ❗");
                    }
                } catch (Exception e) {
                    sendMessage.setText("Sana kiritilishida xatolik ❗\nQaytadan urinib ko`ring.");
                }

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_BIRTHDAY)) {

                if (text.equals("Toshkent shahar")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentMurkap());

                } else if (text.equals("Toshkent viloyati")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentViloyatiMurkap());

                } else if (text.equals("Andijon")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.AndijonViloyatiMurkap());

                } else if (text.equals("Navoiy")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NavoiyViloyatiMurkap());

                } else if (text.equals("Buxoro")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.BuxoroViloyatiMurkap());

                } else if (text.equals("Samarqand")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SamarqandViloyatiMurkap());

                } else if (text.equals("Sirdaryo")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SirdaryoViloyatiMurkap());

                } else if (text.equals("Farg'ona")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.FargonaMurkap());

                } else if (text.equals("Namangan")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NamanganViloyatiMurkap());

                } else if (text.equals("Xorazm")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.XorazmViloyatiMurkap());

                } else if (text.equals("Qashqadaryo")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QashqadaryoViloyatiMurkap());

                } else if (text.equals("Surxandaryo")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SurxandaryoViloyatiMurkap());

                } else if (text.equals("Jizzax")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.JizzaxViloyatiMurkap());

                } else if (text.equals("Qoraqalpogʻiston")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentP.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QoraqalpoqViloyatiMurkap());

                } else {
                    sendMessage.setText("Bunday shahar yoq. Qaytadan kiriting.");
                }

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_CITY)) {
                studentP.setDistrict(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_DISTRICT);
                sendMessage.setText("Manzilingizni kiriting: ");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_DISTRICT)) {
                studentP.setManzil(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MANZIL);
                sendMessage.setText("Maktabingiz joylashgan shaharni(viloyat) tanlang: ");
                sendMessage.setReplyMarkup(KeyboardUtil.shaharMurkap());

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MANZIL)) {

                if (text.equals("Toshkent shahar")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentMurkap());

                } else if (text.equals("Toshkent viloyati")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentViloyatiMurkap());

                } else if (text.equals("Andijon")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.AndijonViloyatiMurkap());

                } else if (text.equals("Navoiy")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NavoiyViloyatiMurkap());

                } else if (text.equals("Buxoro")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.BuxoroViloyatiMurkap());

                } else if (text.equals("Samarqand")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SamarqandViloyatiMurkap());

                } else if (text.equals("Sirdaryo")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SirdaryoViloyatiMurkap());

                } else if (text.equals("Farg'ona")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.FargonaMurkap());

                } else if (text.equals("Namangan")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NamanganViloyatiMurkap());

                } else if (text.equals("Xorazm")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.XorazmViloyatiMurkap());

                } else if (text.equals("Qashqadaryo")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QashqadaryoViloyatiMurkap());

                } else if (text.equals("Surxandaryo")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SurxandaryoViloyatiMurkap());

                } else if (text.equals("Jizzax")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.JizzaxViloyatiMurkap());

                } else if (text.equals("Qoraqalpogʻiston")) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentP.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QoraqalpoqViloyatiMurkap());

                } else {
                    sendMessage.setText("Bunday shahar(viloyat) yo'q. Qaytadan kiriting.");
                }


            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MAKTAB_CITY)) {
                studentP.setMaktabDistrict(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_DISTRICT);
                sendMessage.setText("Maktabingiz № kiriting: ");


            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MAKTAB_DISTRICT)) {
                studentP.setMaktabNumber(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MAKTAB_NUMBER);
                sendMessage.setText("Passportingizni seriasini sini kiriting\n(Masalan AB1234567)");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MAKTAB_NUMBER)) {
                if (Pattern.matches("[A-Z]{2}[0-9]{7}", text)) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_PASSPORT_ID);
                    sendMessage.setText("Telefon raqamingizni kiriting:\n" +
                            "(Masalan: +998XXXXXXXXX)");
                    studentP.setPassportId(text);

                } else {
                    sendMessage.setText("Passport seriasi noto'g'ri kiritildi ❗️\nIltimos tekshirib qaytadan kiritib ko`ring.");
                }
            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_PASSPORT_ID)) {
                if (Pattern.matches("[+]998[0-9][0-9]{8}", text)) {
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_PHONE);
                    sendMessage.setText("Yotoqxona xizmatiga ehtiyojingiz bormi:");
                    studentP.setPhone(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.Yotoxona());
                } else {
                    sendMessage.setText("Raqam noto'g'ri kiritildi  ❗️\nIltimos tekshirib qaytadan kiritb ko`ring.");
                }

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_PHONE)) {
                studentP.setYotoqxona(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_YOTOXONA);
                sendMessage.setText("Otangizni ismini kiriting:");
                sendMessage.setReplyMarkup(KeyboardUtil.AsosiyMenu());

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_YOTOXONA)) {

                if (text.equals("\uD83D\uDCCB Asosiy menyu \uD83D\uDCCB")) {
                    sendMessage.setText("Asosiy menyuga qaytingiz.\n" +
                            "Quyidagilardan birini tanlang \uD83D\uDC47");
                    sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());
                }
                else {
                    studentP.setFathName(text);
                    studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_FATHER_NAME);
                    sendMessage.setText("Otangizni famiylasini kiriting:");
                }
            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_FATHER_NAME)) {
                studentP.setFathSurname(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_FATHER_SURNAME);
                sendMessage.setText("Otangizni telefon raqamini kiriting:\n" +
                        "(Masalan +998XXXXXXXXX)");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_FATHER_SURNAME)) {
                studentP.setFathPhone(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_FATHER_PHONE);
                sendMessage.setText("Onangizni ismini kiriting:");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_FATHER_PHONE)) {
                studentP.setMathName(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MATHER_NAME);
                sendMessage.setText("Onangizni famiylasini kiriting:");

            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MATHER_NAME)) {
                studentP.setMathSurname(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MATHER_SURNAME);
                sendMessage.setText("Onangizni telefon raqamini kiriting:\n" +
                        "(Masalan +998XXXXXXXXX)");
            } else if (studentStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_P_MATHER_SURNAME)) {
                studentP.setMathPhone(text);
                studentStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_P_MATHER_PHONE);
                sendMessage.setText("Metirkangizni rasmni jo'nating.");
            }

        } else if (studentMStepMap.containsKey(chatId)) {

            StudentM studentM = studentMMap.get(chatId);

            if (studentMStepMap.get(chatId).equals(StudentStatus.SELECT_STUDENT_M_BIO)) {
                studentM.setName(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_NAME);
                sendMessage.setText("Famiylangizni kiriting:");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_NAME)) {
                studentM.setSurname(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_SURNAME);
                sendMessage.setText("Sharifingizni kiriting:");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_SURNAME)) {
                studentM.setMiddlname(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MIDDLENAME);
                sendMessage.setText("Tugilgan sanangizni kiriting:\n(Masalan: xx.xx.xxxx kun/oy/yil");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MIDDLENAME)) {
                try {
                    String[] split = text.split("\\.");

                    int day = Integer.parseInt(split[0]);
                    int month = Integer.parseInt(split[1]);
                    int year = Integer.parseInt(split[2]);

                    LocalDate localDate = LocalDate.of(year, month, day);

                    if (localDate.isBefore(LocalDate.now())) {
                        System.out.println("Birthday tekshirdi");
                        studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_BIRTHDAY);
                        sendMessage.setText("Tugilgan shahringiz yoki viloyatingizni tanlang!");
                        sendMessage.setReplyMarkup(KeyboardUtil.shaharMurkap());
                        studentM.setBirthday(text);

                    } else {
                        sendMessage.setText("Hozirgi sanadan oldingi sana kiritilishi kerak ❗");
                    }
                } catch (Exception e) {
                    sendMessage.setText("Sana kiritilishida xatolik ❗\nQaytadan urinib ko`ring.");
                }

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_BIRTHDAY) || text.equals("Farg'ona")) {

                if (text.equals("Toshkent shahar")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentMurkap());

                } else if (text.equals("Toshkent viloyati")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentViloyatiMurkap());

                } else if (text.equals("Andijon")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.AndijonViloyatiMurkap());

                } else if (text.equals("Navoiy")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NavoiyViloyatiMurkap());

                } else if (text.equals("Buxoro")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.BuxoroViloyatiMurkap());

                } else if (text.equals("Samarqand")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SamarqandViloyatiMurkap());

                } else if (text.equals("Sirdaryo")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SirdaryoViloyatiMurkap());

                } else if (text.equals("Farg'ona")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.FargonaMurkap());

                } else if (text.equals("Namangan")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NamanganViloyatiMurkap());

                } else if (text.equals("Xorazm")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.XorazmViloyatiMurkap());

                } else if (text.equals("Qashqadaryo")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QashqadaryoViloyatiMurkap());

                } else if (text.equals("Surxandaryo")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SurxandaryoViloyatiMurkap());

                } else if (text.equals("Jizzax")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.JizzaxViloyatiMurkap());

                } else if (text.equals("Qoraqalpogʻiston")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_CITY);
                    sendMessage.setText("Tumaningiz yoki shahringizni tanlang");
                    studentM.setCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QoraqalpoqViloyatiMurkap());

                } else {
                    sendMessage.setText("Bunday shahar yoq. Qaytadan kiriting.");
                }

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_CITY)) {
                studentM.setDistrict(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_DISTRICT);
                sendMessage.setText("Manzilingizni kiriting: ");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_DISTRICT)) {
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MANZIL);
                studentM.setManzil(text);
                sendMessage.setText("Maktabingiz joylashgan shaharni(viloyat) tanlang: ");
                sendMessage.setReplyMarkup(KeyboardUtil.shaharMurkap());

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MANZIL)) {

                if (text.equals("Toshkent shahar")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentMurkap());

                } else if (text.equals("Toshkent viloyati")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.ToshkentViloyatiMurkap());

                } else if (text.equals("Andijon")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.AndijonViloyatiMurkap());

                } else if (text.equals("Navoiy")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NavoiyViloyatiMurkap());

                } else if (text.equals("Buxoro")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.BuxoroViloyatiMurkap());

                } else if (text.equals("Samarqand")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SamarqandViloyatiMurkap());

                } else if (text.equals("Sirdaryo")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SirdaryoViloyatiMurkap());

                } else if (text.equals("Farg'ona")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.FargonaMurkap());

                } else if (text.equals("Namangan")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.NamanganViloyatiMurkap());

                } else if (text.equals("Xorazm")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.XorazmViloyatiMurkap());

                } else if (text.equals("Qashqadaryo")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QashqadaryoViloyatiMurkap());

                } else if (text.equals("Surxandaryo")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.SurxandaryoViloyatiMurkap());

                } else if (text.equals("Jizzax")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.JizzaxViloyatiMurkap());

                } else if (text.equals("Qoraqalpogʻiston")) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY);
                    sendMessage.setText("Maktabingiz joylashgan tumanni tanlang: ");
                    studentM.setMaktabCity(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.QoraqalpoqViloyatiMurkap());

                } else {
                    sendMessage.setText("Bunday shahar(viloyat) yo'q. Qaytadan kiriting.");
                }

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MAKTAB_CITY)) {
                studentM.setMaktabDistrict(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_DISTRICT);
                sendMessage.setText("Maktabingiz № kiriting: ");
              

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MAKTAB_DISTRICT)) {
                studentM.setMaktabNumber(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MAKTAB_NUMBER);
                sendMessage.setText("Metirkangizni nomerini kiriting\n(Masalan AB1234567)");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MAKTAB_NUMBER)) {
                if (Pattern.matches("[A-Z]{2}[0-9]{7}", text)) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_METIRKA_ID);
                    sendMessage.setText("Telefon raqamingizni kiriting:\n" +
                            "Masalan: +998XXXXXXXXX)");
                    studentM.setMetirkaId(text);

                } else {
                    sendMessage.setText("Metirka raqam noto'g'ri kiritildi ❗️\nIltimos tekshirib qaytadan kiritb ko`ring.");
                }

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_METIRKA_ID)) {
                if (Pattern.matches("[+]998[0-9][0-9]{8}", text)) {
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_PHONE);
                    sendMessage.setText("Yotoqxona xizmatiga ehtiyojingiz bormi:");
                    studentM.setPhone(text);
                    sendMessage.setReplyMarkup(KeyboardUtil.Yotoxona());
                } else {
                    sendMessage.setText("Raqam noto`g`ri kiritildi ❗\nIltimos tekshirib qaytadan kiritb ko`ring.");
                }

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_PHONE)) {
                studentM.setYotoqxona(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_YOTOXONA);
                sendMessage.setText("Otangizni ismini kiriting:");
                sendMessage.setReplyMarkup(KeyboardUtil.AsosiyMenu());

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_YOTOXONA)) {

                if (text.equals("\uD83D\uDCCB Asosiy menyu \uD83D\uDCCB")) {
                    sendMessage.setText("Asosiy menyu tugmasi bosildi ✅ \n\n" +
                            "Quyidagilardan birini tanlang \uD83D\uDC47");
                    sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());
                }
                else {
                    studentM.setFathName(text);
                    studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_FATHER_NAME);
                    sendMessage.setText("Otangizni famiylasini kiriting:");
                }
            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_FATHER_NAME)) {
                studentM.setFathSurname(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_FATHER_SURNAME);
                sendMessage.setText("Otangizni telefon raqamini kiriting:\n" +
                        "(Masalan +998XXXXXXXXX)");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_FATHER_SURNAME)) {
                studentM.setFathPhone(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_FATHER_PHONE);
                sendMessage.setText("Onangizni ismini kiriting:");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_FATHER_PHONE)) {
                studentM.setMathName(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MATHER_NAME);
                sendMessage.setText("Onangizni famiylasini kiriting:");

            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MATHER_NAME)) {
                studentM.setMathSurname(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MATHER_SURNAME);
                sendMessage.setText("Onangizni telefon raqamini kiriting:\n" +
                        "(Masalan +998XXXXXXXXX)");
            } else if (studentMStepMap.get(chatId).equals(StudentStatus.ENTERED_STUDENT_M_MATHER_SURNAME)) {
                studentM.setMathPhone(text);
                studentMStepMap.put(chatId, StudentStatus.ENTERED_STUDENT_M_MATHER_PHONE);
                sendMessage.setText("Metirkangizni rasmni jo'nating.");
            } else if (text.equals("\uD83D\uDCCB Asosiy menyu \uD83D\uDCCB")) {
                sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());
            }
        }
        LYCEUMBot.sendMsg(sendMessage);;
    }


    public void handleCallBack(User user, Message message, String data) {
        String chatId = String.valueOf(message.getChatId());

        if (data.equals("about_lyceum")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Litsey haqidagi ma'lumotlar quyidagilarda."
            );
            sendMessage.setReplyMarkup(InlineKeyboardUtil.about_lyceum());


            LYCEUMBot.sendMsg(sendMessage);


        } else if (data.equals("bio")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Litsey biografiyasi quyidagi\nbo'limda bayon etilgan\uD83D\uDE0A!"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.lyceumAboutForUserInlineMurkup());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("lyceum_about_show/")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int lyceumId = Integer.parseInt(data.split("/")[1]);
            List<BioLyceum> bioLyceumList = BioRepository.getBioByLyceumId(lyceumId);

            LyceumRepository.loadLyceumList();
            BioRepository.loadBioLyceumList();
            SendMessage sendMessage = new SendMessage(
                    chatId, "Litsey biografiyasi"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.biografiyaInlineForStudent(bioLyceumList));

            LYCEUMBot.sendMsg(sendMessage);


        } else if (data.startsWith("bio_show_user/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);


            int bioId = Integer.parseInt(data.split("/")[1]);
            BioLyceum bioLyceum = BioRepository.getBioById(bioId);

            SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(bioLyceum.getImage()));

            sendPhoto.setCaption(String.format("Litsey  biografiyasi\n" +
                    "Tavsifnoma: %s", bioLyceum.getDescription()));

            sendPhoto.setReplyMarkup(InlineKeyboardUtil.backFromBio());

            LYCEUMBot.sendMsg(sendPhoto);

        } else if (data.equals("video")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );
            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Videolar bo'limiga o'tingiz ✅"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.lVideo());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("lyceum_video_show/")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int lVideoId = Integer.parseInt(data.split("/")[1]);
            List<VideoLyceum> videoLyceumList = VideoRepository.getVideoByLyceumId(lVideoId);

            lVideoRepository.loadlVideoList();
            VideoRepository.loadVideoRepository();
            SendMessage sendMessage = new SendMessage(
                    chatId, "Litsey videolari"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.videoForUser(videoLyceumList));

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("video_show_user/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);


            int lVideoId = Integer.parseInt(data.split("/")[1]);
            VideoLyceum videoLyceum = VideoRepository.getVideoById(lVideoId);

            SendVideo sendVideo = new SendVideo(chatId, new InputFile(videoLyceum.getVideo()));

            sendVideo.setCaption(String.format("Litsey  biografiyasi\n" +
                    "Tavsifnoma: %s", videoLyceum.getDescription()));

            sendVideo.setReplyMarkup(InlineKeyboardUtil.backFromVideo());

            LYCEUMBot.sendMsg(sendVideo);

        } else if (data.equals("cafedras")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Kafedralar bo'limiga o'tingiz ✅"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.kafedraForUserInlineMurkup());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.startsWith("kafedras_show/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int kafedraId = Integer.parseInt(data.split("/")[1]);
            List<Teacher> teacherList = TeacherRepository.getTeacherByKafedraId(kafedraId);

            KafedraRepository.loadKafedraList();
            TeacherRepository.loadTeacherList();
            SendMessage sendMessage = new SendMessage(
                    chatId, "Kafedra o'qtuvchilari"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.teacherInlineMarkupForStudent(teacherList));

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("teacherForUser/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int teacherId = Integer.parseInt(data.split("/")[1]);
            Teacher teacher = TeacherRepository.getTeacherById(teacherId);

            SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(teacher.getImage()));

            sendPhoto.setCaption(String.format("Kafedrasi:  %s \n\nIsmi:  %s \n\nFamiylasi:  %s \n\nDarajasi:  %s \n\nTelefon raqami:  " +
                            "%s \n\nXonasi:  %s",
                    Objects.requireNonNull(KafedraRepository.getKafedraById(teacher.getKafedraId())).getName(), teacher.getName(),
                    teacher.getSurname(), teacher.getLevel(), teacher.getPhone(), teacher.getRoom()));

            sendPhoto.setReplyMarkup(InlineKeyboardUtil.backFromTeacher());

            LYCEUMBot.sendMsg(sendPhoto);


        } else if (data.equals("backToKafedra")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagi Kafedralardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.kafedraForUserInlineMurkup());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("backFromTeacher")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagi Kafedralardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.kafedraForUserInlineMurkup());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("backButton")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Siz hozir bosh menyudasiz quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("direktors_ly")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Rahbariyat bo'limiga o'tingiz ✅"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.rahbariyatForStudentMarkup());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.startsWith("levels_show/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int levelId = Integer.parseInt(data.split("/")[1]);
            List<Rahbariyat> rahbariyatList = RahbariyatRepository.getRahbarByLevelId(levelId);

            LevelRepository.loadLevelList();
            RahbariyatRepository.loadRahbariyatlist();
            SendMessage sendMessage = new SendMessage(
                    chatId, "Rahbarlar"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.rahbarInlineMarkupForStudent(rahbariyatList));

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.startsWith("rahbarForStudent/")) {

            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            int rahbarId = Integer.parseInt(data.split("/")[1]);

            Rahbariyat rahbariyat = RahbariyatRepository.getRahbariyatById(rahbarId);

            assert rahbariyat != null;
            SendPhoto sendPhoto = new SendPhoto(chatId, new InputFile(rahbariyat.getImage()));

            sendPhoto.setCaption(String.format("Ismi: %s \n" +
                            "Famiylasi: %s \n" +
                            "Lavozimi: %s \n" +
                            "Telefon rqaqami: %s \n" +
                            "Xonasi: %s \n",
                    rahbariyat.getFirstName(), rahbariyat.getLastName(), rahbariyat.getLevel(),
                    rahbariyat.getPhone_number(), rahbariyat.getRoom()));

            sendPhoto.setReplyMarkup(InlineKeyboardUtil.backFromRahbar());
            LYCEUMBot.sendMsg(sendPhoto);
        } else if (data.equals("backFromRahbar")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz bosh menyuga qaytingiz ✅ \n\n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromRahbarToRahbariyat")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n\n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.rahbariyatForStudentMarkup());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromShowRahbar")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.rahbariyatForStudentMarkup());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromBiografiya")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.lyceumAboutForUserInlineMurkup());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backLyceumAbout")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Siz hozirda bosh menyudasiz. \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.about_lyceum());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromBioStudent")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.lyceumAboutForUserInlineMurkup());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromVideoStudent")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.about_lyceum());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backToMenu")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz asosiy menyuga qaytingiz ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backLyceumVideo")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.about_lyceum());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromVideo")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.lVideo());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("online_reg")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ro'yxatdan o'tish uchun\no'zingizda mavjud bo'lgan\nshaxsni tasdiqlovchi hujjatga muvoffiq\nquyidagilardan birini tanlang!"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.paspportAndMetirka());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("back_ariza")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);

        } else if (data.equals("student_passport")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(chatId,
                    "Ismingizni kiriting:");

            LYCEUMBot.sendMsg(sendMessage);

            studentPMap.remove(chatId);
            studentStepMap.remove(chatId);

            studentStepMap.put(chatId, StudentStatus.CLICKED_ADD_STUDENT);

            studentPMap.put(chatId,
                    new StudentP(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));

            studentStepMap.put(chatId, StudentStatus.SELECT_STUDENT_BIO);

        } else if (data.equals("add_student_p_image")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            StudentP studentP = studentPMap.get(chatId);

            StudentPRepository.addStudentP(studentP);

            studentPMap.remove(chatId);
            studentStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Sizning malumotlaringiz bazaga saqlandi ✅\n\n" + "Amalni tanlang:"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("student_metirka")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(chatId,
                    "Ismingizni kiriting:");

            LYCEUMBot.sendMsg(sendMessage);

            studentMMap.remove(chatId);
            studentMStepMap.remove(chatId);

            studentMStepMap.put(chatId, StudentStatus.CLICKED_ADD_STUDENT_M);

            studentMMap.put(chatId,
                    new StudentM(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));


            studentMStepMap.put(chatId, StudentStatus.SELECT_STUDENT_M_BIO);

        } else if (data.equals("add_student_m_image")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            StudentM studentM = studentMMap.get(chatId);

            StudentMRepository.addStudentM(studentM);

            studentMMap.remove(chatId);
            studentMStepMap.remove(chatId);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Sizning malumotlaringiz bazaga saqlandi ✅\n\n" + "Amalni tanlang:"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());
            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backFromShowRahbarToMenu")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz asosiy menyuga qaytingiz ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("backToAsMenu")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz asosiy menyuga qaytingiz ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("photo_user")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            SendMessage sendMessage = new SendMessage(
                    chatId, "slo"
            );

            LYCEUMBot.sendMsg(sendMessage);

            LYCEUMBot.sendMsg(deleteMessage);

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(String.valueOf(user.getId()));

            FotoRepository.loadFotoRepository();
            List<FotoLyceum> fotoLyceums = Database.fotoLyceumList.stream()
                    .filter(fotoLyceum -> !fotoLyceum.isDeleted())
                    .toList();

            if (!fotoLyceums.isEmpty()) {
                InputFile inputFile = new InputFile(fotoLyceums.get(0).getImage());
                sendPhoto.setPhoto(inputFile);
                sendPhoto.setCaption(fotoLyceums.get(0).getDescription());
                sendPhoto.setReplyMarkup(InlineKeyboardUtil.getFotoLyceum(0, fotoLyceums.size()));

                LYCEUMBot.sendMsg(sendPhoto);

            }
        } else if (data.startsWith("C/")) {

            String[] split = data.split("/");
            int step = Integer.parseInt(split[1]);
            FotoRepository.loadFotoRepository();

            List<FotoLyceum> fotoLyceums = Database.fotoLyceumList.stream()
                    .filter(fotoLyceum -> !fotoLyceum.isDeleted())
                    .toList();

            FotoLyceum fotoLyceum = fotoLyceums.get(step);

            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(String.valueOf(user.getId()));
            sendPhoto.setCaption(fotoLyceum.getDescription());

            InputFile inputFile = new InputFile(fotoLyceum.getImage());
            sendPhoto.setPhoto(inputFile);

            sendPhoto.setReplyMarkup(InlineKeyboardUtil.getFotoLyceum(step, fotoLyceums.size()));

            LYCEUMBot.sendMsg(sendPhoto);

            DeleteMessage deleteMessage = new DeleteMessage(chatId,
                    message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);
        } else if (data.equals("backFromVideoStudentToMenu")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Siz asosiy menyuga qaytingiz ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.userMenu());

            LYCEUMBot.sendMsg(sendMessage);
        } else if (data.equals("_back")) {
            DeleteMessage deleteMessage = new DeleteMessage(
                    chatId, message.getMessageId()
            );

            LYCEUMBot.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage(
                    chatId, "Ortga qaytish tugmasi bosildi ✅ \n" +
                    "Quyidagilardan birini tanlang \uD83D\uDC47"
            );

            sendMessage.setReplyMarkup(InlineKeyboardUtil.about_lyceum());

            LYCEUMBot.sendMsg(sendMessage);
        }
    }
}