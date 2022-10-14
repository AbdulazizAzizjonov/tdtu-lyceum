package com.company.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;

public class KeyboardUtil {



    public static ReplyKeyboard shaharMurkap(){
        KeyboardButton toshkentBut = new KeyboardButton("Toshkent shahar");
        KeyboardButton toshkentVilBut = new KeyboardButton("Toshkent viloyati");
        KeyboardButton AndijonVilBut = new KeyboardButton("Andijon");
        KeyboardButton NavoiyVilBut = new KeyboardButton("Navoiy");
        KeyboardButton BuxoroVilBut = new KeyboardButton("Buxoro");
        KeyboardButton SamarqandVilBut = new KeyboardButton("Samarqand");
        KeyboardButton SirdaryoVilBut = new KeyboardButton("Sirdaryo");
        KeyboardButton FargonaVilBut = new KeyboardButton("Farg'ona");
        KeyboardButton NamanganVilBut = new KeyboardButton("Namangan");
        KeyboardButton XorazmVilBut = new KeyboardButton("Xorazm");
        KeyboardButton QashqadaryoVilBut = new KeyboardButton("Qashqadaryo");
        KeyboardButton SurxandaryoVilBut = new KeyboardButton("Surxandaryo");
        KeyboardButton JizzaxVilBut = new KeyboardButton("Jizzax");
        KeyboardButton QoraqalpogʻistonVilBut = new KeyboardButton("Qoraqalpogʻiston");
        KeyboardRow row1 = getRow(toshkentBut, toshkentVilBut);
        KeyboardRow row2 = getRow(AndijonVilBut, NavoiyVilBut);
        KeyboardRow row3 = getRow(BuxoroVilBut, SamarqandVilBut);
        KeyboardRow row4 = getRow(SirdaryoVilBut, FargonaVilBut);
        KeyboardRow row5 = getRow(NamanganVilBut, XorazmVilBut);
        KeyboardRow row6 = getRow(QashqadaryoVilBut, SurxandaryoVilBut);
        KeyboardRow row7 = getRow(JizzaxVilBut, QoraqalpogʻistonVilBut);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7);
        return getMarkup(rowList);

    }


    public static ReplyKeyboard ToshkentMurkap(){
        KeyboardButton bektemir = new KeyboardButton("Bektemir tumani");
        KeyboardButton mirobod = new KeyboardButton("Mirobod tumani");
        KeyboardButton mirzo = new KeyboardButton("Mirzo Ulug‘bek tumani");
        KeyboardButton sergeli = new KeyboardButton("Sergeli  tumani");
        KeyboardButton olmazor = new KeyboardButton("Olmazor tumani");
        KeyboardButton uchtepa = new KeyboardButton("Uchtepa tumani");
        KeyboardButton shayxontohur = new KeyboardButton("Shayxontohur tumani");
        KeyboardButton yashnobod = new KeyboardButton("Yashnobod tumani");
        KeyboardButton chilonzor = new KeyboardButton("Chilonzor tumani");
        KeyboardButton yunusobod = new KeyboardButton("Yunusobod tumani");
        KeyboardButton yakkasaroy = new KeyboardButton("Yakkasaroy tumani");
        KeyboardButton yangi = new KeyboardButton("Yangi hayot tumani");
        KeyboardRow row1 = getRow(bektemir, mirobod);
        KeyboardRow row2 = getRow(mirzo, sergeli);
        KeyboardRow row3 = getRow(olmazor, uchtepa);
        KeyboardRow row4 = getRow(shayxontohur, yashnobod);
        KeyboardRow row5 = getRow(chilonzor, yunusobod);
        KeyboardRow row6 = getRow(yakkasaroy, yangi);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6);
        return getMarkup(rowList);

    }

    public static ReplyKeyboard ToshkentViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Bekobod tumani");
        KeyboardButton boston = new KeyboardButton("Bo'stonliq tumani");
        KeyboardButton boka = new KeyboardButton("Bo'ka tumani");
        KeyboardButton chinoz = new KeyboardButton("Chinoz  tumani");
        KeyboardButton qibray = new KeyboardButton("Qibray tumani");
        KeyboardButton ohangaron = new KeyboardButton("Ohangaron tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Oqqo'rg'on tumani");
        KeyboardButton parkent = new KeyboardButton("Parkent tumani");
        KeyboardButton piskent = new KeyboardButton("Piskent tumani");
        KeyboardButton quyi = new KeyboardButton("Quyi chirchiq tumani");
        KeyboardButton orta = new KeyboardButton("O'rta Chirchiq tumani");
        KeyboardButton yangiyol = new KeyboardButton("Yangiyo'l tumani");
        KeyboardButton yuqori = new KeyboardButton("Yuqori Chirchiq tumani");
        KeyboardButton zangiota = new KeyboardButton("Zangiota tumani");
        KeyboardButton angren = new KeyboardButton("Angren tumani");
        KeyboardButton bekobodSh = new KeyboardButton("Bekobod shahri");
        KeyboardButton chirchiq = new KeyboardButton("Chirchiq shahri");
        KeyboardButton olmaliq = new KeyboardButton("Olmaliq shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(boka, chinoz);
        KeyboardRow row3 = getRow(qibray, ohangaron);
        KeyboardRow row4 = getRow(oqqorgon, parkent);
        KeyboardRow row5 = getRow(piskent, quyi);
        KeyboardRow row6 = getRow(orta, yangiyol);
        KeyboardRow row7 = getRow(yuqori, zangiota);
        KeyboardRow row8 = getRow(angren, bekobodSh);
        KeyboardRow row9 = getRow(chirchiq, olmaliq);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7, row8, row9);
        return getMarkup(rowList);

    }

    public static ReplyKeyboard AndijonViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Andijon shahri");
        KeyboardButton boston = new KeyboardButton("Andijon tumani ");
        KeyboardButton chinoz = new KeyboardButton("Asaka tumani");
        KeyboardButton qibray = new KeyboardButton("Baliqchi tumani");
        KeyboardButton ohangaron = new KeyboardButton("Bo'z tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Buloqboshi tumani");
        KeyboardButton parkent = new KeyboardButton("Izboskan tumani");
        KeyboardButton piskent = new KeyboardButton("Jalolquduq tumani");
        KeyboardButton quyi = new KeyboardButton(" Marhamat tumani");
        KeyboardButton orta = new KeyboardButton("Oltinko'l tumani");
        KeyboardButton yangiyol = new KeyboardButton("Paxtaobod tumani");
        KeyboardButton yuqori = new KeyboardButton("Qo'rg'ontepa tumani");
        KeyboardButton zangiota = new KeyboardButton("Shahrixon tumani");
        KeyboardButton angren = new KeyboardButton("Ulug'nor tumani");
        KeyboardButton bekobodSh = new KeyboardButton(" Xo'jaobod tumani");
        KeyboardButton olmaliq = new KeyboardButton("Xonobod shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, qibray);
        KeyboardRow row3 = getRow(ohangaron, oqqorgon);
        KeyboardRow row4 = getRow(parkent, piskent);
        KeyboardRow row5 = getRow(quyi, orta);
        KeyboardRow row6 = getRow(yangiyol, yuqori);
        KeyboardRow row7 = getRow(zangiota, angren);
        KeyboardRow row8 = getRow(bekobodSh, olmaliq);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7, row8);
        return getMarkup(rowList);

    }



    public static ReplyKeyboard NavoiyViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Konimex tumani");
        KeyboardButton boston = new KeyboardButton("Karmana tumani");
        KeyboardButton chinoz = new KeyboardButton("Qiziltepa");
        KeyboardButton ohangaron = new KeyboardButton("Xatirchi tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Navbahor tumani");
        KeyboardButton parkent = new KeyboardButton("Nurota tumani");
        KeyboardButton piskent = new KeyboardButton("Tomdi tumani");
        KeyboardButton quyi = new KeyboardButton("Uchquduq tumani");
        KeyboardButton orta = new KeyboardButton("Navoiy shahri");
        KeyboardButton yangiyol = new KeyboardButton("Zarafshon shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5);
        return getMarkup(rowList);

    }


    public static ReplyKeyboard BuxoroViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Olot tumani");
        KeyboardButton boston = new KeyboardButton("Buxoro tumani");
        KeyboardButton chinoz = new KeyboardButton("Gʻijduvon tumani");
        KeyboardButton ohangaron = new KeyboardButton("Jondor tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Kogon tumani");
        KeyboardButton parkent = new KeyboardButton("Qorakoʻl tumani");
        KeyboardButton piskent = new KeyboardButton("Qorovulbozor tumani");
        KeyboardButton quyi = new KeyboardButton("Peshku tumani");
        KeyboardButton orta = new KeyboardButton("Romitan tumani");
        KeyboardButton yangiyol = new KeyboardButton("Shofirkon tumani");
        KeyboardButton yangiyol2 = new KeyboardButton("Vobkent tumani");
        KeyboardButton yangiyol3 = new KeyboardButton("Buxoro shahri");
        KeyboardButton yangiyol4 = new KeyboardButton("Kogon shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow(yangiyol2, yangiyol3);
        KeyboardRow row7 = getRow(yangiyol4);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7);
        return getMarkup(rowList);

    }


    public static ReplyKeyboard SamarqandViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Bulungʻur tumani");
        KeyboardButton boston = new KeyboardButton("Ishtixon tumani");
        KeyboardButton chinoz = new KeyboardButton("Jomboy tumani");
        KeyboardButton qibray = new KeyboardButton("Kattaqoʻrgʻon tumani");
        KeyboardButton ohangaron = new KeyboardButton("Qoʻshrabot tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Narpay tumani");
        KeyboardButton parkent = new KeyboardButton("Nurobod tumani");
        KeyboardButton piskent = new KeyboardButton("Oqdaryo tumani");
        KeyboardButton quyi = new KeyboardButton("Paxtachi tumani");
        KeyboardButton orta = new KeyboardButton("Payariq tumani");
        KeyboardButton yangiyol = new KeyboardButton("Pastdargʻom tumani");
        KeyboardButton yuqori = new KeyboardButton("Samarqand tumani");
        KeyboardButton zangiota = new KeyboardButton("Toyloq tumani");
        KeyboardButton angren = new KeyboardButton("Urgut tumani");
        KeyboardButton bekobodSh = new KeyboardButton("Kattaqo'rg'on shahri");
        KeyboardButton olmaliq = new KeyboardButton("Samarqand shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, qibray);
        KeyboardRow row3 = getRow(ohangaron, oqqorgon);
        KeyboardRow row4 = getRow(parkent, piskent);
        KeyboardRow row5 = getRow(quyi, orta);
        KeyboardRow row6 = getRow(yangiyol, yuqori);
        KeyboardRow row7 = getRow(zangiota, angren);
        KeyboardRow row8 = getRow(bekobodSh, olmaliq);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7, row8);
        return getMarkup(rowList);

    }



    public static ReplyKeyboard SirdaryoViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Oqoltin tumani");
        KeyboardButton boston = new KeyboardButton("Boyovut tumani");
        KeyboardButton chinoz = new KeyboardButton("Guliston tumani");
        KeyboardButton ohangaron = new KeyboardButton("Xovos tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Mirzaobod tumani");
        KeyboardButton parkent = new KeyboardButton("Sayxunobod tumani ");
        KeyboardButton piskent = new KeyboardButton( "Sardoba tumani");
        KeyboardButton quyi = new KeyboardButton("Sirdaryo tumani");
        KeyboardButton orta = new KeyboardButton("Yangiyer shahri");
        KeyboardButton yangiyol = new KeyboardButton("Shirin shahri");
        KeyboardButton yangiyol1 = new KeyboardButton("Guliston shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow( yangiyol1);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6);
        return getMarkup(rowList);

    }



    public static ReplyKeyboard FargonaMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Oltiariq tumani");
        KeyboardButton boston = new KeyboardButton("Bagʻdod tumani");
        KeyboardButton boka = new KeyboardButton("Beshariq tumani");
        KeyboardButton chinoz = new KeyboardButton("Buvayda tumani");
        KeyboardButton qibray = new KeyboardButton("Dangʻara tumani");
        KeyboardButton ohangaron = new KeyboardButton("Fargʻona tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Furqat tumani");
        KeyboardButton parkent = new KeyboardButton("Qoʻshtepa tumani");
        KeyboardButton piskent = new KeyboardButton("Quva tumani");
        KeyboardButton quyi = new KeyboardButton("Rishton tumani");
        KeyboardButton orta = new KeyboardButton("Soʻx tumani");
        KeyboardButton yangiyol = new KeyboardButton("Toshloq tumani");
        KeyboardButton yuqori = new KeyboardButton("Uchkoʻprik tumani");
        KeyboardButton zangiota = new KeyboardButton("Oʻzbekiston tumani");
        KeyboardButton angren = new KeyboardButton("Yozyovon tumani");
        KeyboardButton bekobodSh = new KeyboardButton("Farg'ona shahri");
        KeyboardButton chirchiq = new KeyboardButton("Marg'ilon shahri");
        KeyboardButton olmaliq = new KeyboardButton("Qo'qon shahri");
        KeyboardButton olmaliq1 = new KeyboardButton("Quvasoy shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(boka, chinoz);
        KeyboardRow row3 = getRow(qibray, ohangaron);
        KeyboardRow row4 = getRow(oqqorgon, parkent);
        KeyboardRow row5 = getRow(piskent, quyi);
        KeyboardRow row6 = getRow(orta, yangiyol);
        KeyboardRow row7 = getRow(yuqori, zangiota);
        KeyboardRow row8 = getRow(angren, bekobodSh);
        KeyboardRow row9 = getRow(chirchiq, olmaliq);
        KeyboardRow row10 = getRow(olmaliq1);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7, row8, row9,row10);
        return getMarkup(rowList);

    }


    public static ReplyKeyboard NamanganViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Chortoq tumani");
        KeyboardButton boston = new KeyboardButton("Chust tumani");
        KeyboardButton chinoz = new KeyboardButton("Kosonsoy tumani");
        KeyboardButton ohangaron = new KeyboardButton("Mingbuloq tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Namangan tumani");
        KeyboardButton parkent = new KeyboardButton("Norin tumani");
        KeyboardButton piskent = new KeyboardButton( "Pop tumani");
        KeyboardButton quyi = new KeyboardButton("Toʻraqoʻrgʻon tumani");
        KeyboardButton orta = new KeyboardButton("Uchqoʻrgʻon tumani");
        KeyboardButton yangiyol = new KeyboardButton("Uychi tumani");
        KeyboardButton yangiyol1 = new KeyboardButton("Yangiqoʻrgʻon tumani");
        KeyboardButton yangiyol2 = new KeyboardButton("Davlatobod tumani");
        KeyboardButton yangiyol3 = new KeyboardButton("Namangan shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow( yangiyol1, yangiyol2);
        KeyboardRow row7 = getRow( yangiyol3);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7);
        return getMarkup(rowList);

    }




    public static ReplyKeyboard XorazmViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Urganch tumani");
        KeyboardButton boston = new KeyboardButton("Hazorasp tumani");
        KeyboardButton chinoz = new KeyboardButton("Xonqa tumani");
        KeyboardButton ohangaron = new KeyboardButton("Qo‘shko‘pir tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Shovot tumani");
        KeyboardButton parkent = new KeyboardButton("Bog‘ot tumani");
        KeyboardButton piskent = new KeyboardButton( "Gurlan tumani");
        KeyboardButton quyi = new KeyboardButton("Xiva tumani");
        KeyboardButton orta = new KeyboardButton("Yangiariq tumani");
        KeyboardButton yangiyol = new KeyboardButton("Yangibozor tumani");
        KeyboardButton yangiyol2 = new KeyboardButton("Tuproqqal’a tumani");
        KeyboardButton yangiyol1 = new KeyboardButton("Urganch shahri");
        KeyboardButton yangiyol3 = new KeyboardButton("Xiva shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow( yangiyol1, yangiyol2);
        KeyboardRow row7 = getRow( yangiyol3);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7);
        return getMarkup(rowList);

    }

    public static ReplyKeyboard QashqadaryoViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Chiroqchi tumani");
        KeyboardButton boston = new KeyboardButton("Dehqonobod tumani");
        KeyboardButton chinoz = new KeyboardButton("G'uzor tumani");
        KeyboardButton ohangaron = new KeyboardButton("Qamashi tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Qarshi tumani");
        KeyboardButton parkent = new KeyboardButton("Koson tumani");
        KeyboardButton piskent = new KeyboardButton( "Kasbi tumani");
        KeyboardButton quyi = new KeyboardButton("Kitob tumani");
        KeyboardButton orta = new KeyboardButton("Mirishkor tumani");
        KeyboardButton yangiyol = new KeyboardButton("Muborak tumani");
        KeyboardButton yangiyol1 = new KeyboardButton("Nishon tumani");
        KeyboardButton yangiyol2 = new KeyboardButton("Shahrisabz tumani");
        KeyboardButton yangiyol3 = new KeyboardButton("Yakkabog' tumani");
        KeyboardButton yangiyol4 = new KeyboardButton("Ko'kdala tumani");
        KeyboardButton yangiyol5 = new KeyboardButton("Qarshi shahri");
        KeyboardButton yangiyol6 = new KeyboardButton("Shahrisabz shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow( yangiyol1, yangiyol2);
        KeyboardRow row7 = getRow( yangiyol3, yangiyol4);
        KeyboardRow row8 = getRow( yangiyol5, yangiyol6);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7);
        return getMarkup(rowList);

    }


    public static ReplyKeyboard SurxandaryoViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Angor tumani");
        KeyboardButton boston = new KeyboardButton("Boysun tumani");
        KeyboardButton chinoz = new KeyboardButton("Denov tumani");
        KeyboardButton ohangaron = new KeyboardButton("Jarqoʻrgʻon tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Qiziriq tumani");
        KeyboardButton parkent = new KeyboardButton(" Qumqoʻrgʻon tumani");
        KeyboardButton piskent = new KeyboardButton( "Muzrabot tumani");
        KeyboardButton quyi = new KeyboardButton("Oltinsoy tumani");
        KeyboardButton orta = new KeyboardButton("Sariosiyo tumani");
        KeyboardButton yangiyol = new KeyboardButton("Sherobod tumani");
        KeyboardButton yangiyol1 = new KeyboardButton("Shoʻrchi tumani");
        KeyboardButton yangiyol2 = new KeyboardButton("Termiz tumani");
        KeyboardButton yangiyol3 = new KeyboardButton("Uzun tumani");
        KeyboardButton yangiyol4 = new KeyboardButton("Termiz shahri");
        KeyboardButton yangiyol5 = new KeyboardButton("Bandixon tumani");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow( yangiyol1, yangiyol2);
        KeyboardRow row7 = getRow( yangiyol3, yangiyol4);
        KeyboardRow row8 = getRow( yangiyol5);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7, row8);
        return getMarkup(rowList);

    }

    public static ReplyKeyboard JizzaxViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Arnasoy tumani");
        KeyboardButton boston = new KeyboardButton("Baxmal tumani");
        KeyboardButton chinoz = new KeyboardButton("Doʻstlik tumani");
        KeyboardButton ohangaron = new KeyboardButton("Forish tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Gʻallaorol tumani");
        KeyboardButton parkent = new KeyboardButton("Sharof Rashidov tumani");
        KeyboardButton piskent = new KeyboardButton( "Mirzachoʻl tumani");
        KeyboardButton quyi = new KeyboardButton("Paxtakor tumani");
        KeyboardButton orta = new KeyboardButton("Yangiobod tumani");
        KeyboardButton yangiyol = new KeyboardButton("Zomin tumani");
        KeyboardButton yangiyol1 = new KeyboardButton("Zafarobod tumani");
        KeyboardButton yangiyol2 = new KeyboardButton("Zarbdor tumani");
        KeyboardButton yangiyol3 = new KeyboardButton("Jizzax shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, ohangaron);
        KeyboardRow row3 = getRow(oqqorgon, parkent);
        KeyboardRow row4 = getRow(piskent, quyi);
        KeyboardRow row5 = getRow(orta, yangiyol);
        KeyboardRow row6 = getRow( yangiyol1, yangiyol2);
        KeyboardRow row7 = getRow( yangiyol3);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7);
        return getMarkup(rowList);

    }


    public static ReplyKeyboard QoraqalpoqViloyatiMurkap(){
        KeyboardButton bekobod = new KeyboardButton("Amudaryo tumani");
        KeyboardButton boston = new KeyboardButton("Beruniy tumani");
        KeyboardButton chinoz = new KeyboardButton("Chimboy tumani");
        KeyboardButton ohangaron = new KeyboardButton("Ellikqalʼa tumani");
        KeyboardButton oqqorgon = new KeyboardButton("Kegeyli tumani");
        KeyboardButton parkent = new KeyboardButton("Moʻynoq tumani");
        KeyboardButton piskent = new KeyboardButton("Nukus tumani");
        KeyboardButton quyi = new KeyboardButton("Qanlikoʻl tumani");
        KeyboardButton orta = new KeyboardButton("Qoʻngʻirot tumani");
        KeyboardButton yangiyol = new KeyboardButton("Qoraoʻzak tumani");
        KeyboardButton yuqori = new KeyboardButton("Shumanay tumani");
        KeyboardButton zangiota = new KeyboardButton("Taxtakoʻpir tumani");
        KeyboardButton angren = new KeyboardButton("Toʻrtkoʻl tumani");
        KeyboardButton angren1 = new KeyboardButton("Xoʻjayli tumani");
        KeyboardButton bekobodSh = new KeyboardButton("Nukus shahri");
        KeyboardButton olmaliq = new KeyboardButton("Taxiatosh shahri");
        KeyboardRow row1 = getRow(bekobod, boston);
        KeyboardRow row2 = getRow(chinoz, olmaliq);
        KeyboardRow row3 = getRow(ohangaron, oqqorgon);
        KeyboardRow row4 = getRow(parkent, piskent);
        KeyboardRow row5 = getRow(quyi, orta);
        KeyboardRow row6 = getRow(yangiyol, yuqori);
        KeyboardRow row7 = getRow(zangiota, angren);
        KeyboardRow row8 = getRow(angren1, bekobodSh);
        List<KeyboardRow> rowList = getRowList(row1, row2, row3, row4, row5, row6, row7, row8);
        return getMarkup(rowList);

    }

    public static  ReplyKeyboard Yotoxona () {
        KeyboardButton ha = new KeyboardButton("HA ✅");
        KeyboardButton yoq = new KeyboardButton("Yo'q ❌");
        KeyboardRow row1 = getRow(ha, yoq);
        List<KeyboardRow> rowList = getRowList(row1);
        return getMarkup(rowList);
    }



    public static  ReplyKeyboard AsosiyMenu () {
        KeyboardButton asosiyMenu = new KeyboardButton("\uD83D\uDCCB Asosiy menyu \uD83D\uDCCB");
        KeyboardRow row1 = getRow(asosiyMenu);
        List<KeyboardRow> rowList = getRowList(row1);
        return getMarkup(rowList);
    }



    private static KeyboardButton getButton(String demo){
        return new KeyboardButton(demo);
    }

    private static KeyboardRow getRow(KeyboardButton ... buttons){
        return new KeyboardRow(Arrays.asList(buttons));
    }

    private static List<KeyboardRow> getRowList(KeyboardRow ... rows){
        return Arrays.asList(rows);
    }

    private static ReplyKeyboardMarkup getMarkup(List<KeyboardRow> keyboard){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
}
