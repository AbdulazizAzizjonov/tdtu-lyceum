package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.db.Database;
import com.company.model.StudentM;
import com.company.model.StudentP;
import com.company.repository.StudentMRepository;
import com.company.repository.StudentPRepository;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelController {

    public static File registrationXCEL() {

        StudentPRepository.loadStudentPList();

        File file = new File(ComponentContainer.PATH + "files/reg/registratsiyaPaspport.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();

        try (FileOutputStream out = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.createSheet("Ariza_passport");
            XSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("Ismi");
            row0.createCell(1).setCellValue("Famiylasi");
            row0.createCell(2).setCellValue("Sharifi");
            row0.createCell(3).setCellValue("Tug'ilgan sanasi");
            row0.createCell(4).setCellValue("Tug'ilgan shahari");
            row0.createCell(5).setCellValue("Tug'ilgan tumani");
            row0.createCell(6).setCellValue("Manzili");
            row0.createCell(7).setCellValue("Maktab(shahar)");
            row0.createCell(8).setCellValue("Maktab(tumani)");
            row0.createCell(9).setCellValue("Maktab №");
            row0.createCell(10).setCellValue("Passport seriasi");
            row0.createCell(11).setCellValue("Telefon raqami");
            row0.createCell(12).setCellValue("Yotoxona");
            row0.createCell(13).setCellValue("Otasining ismi");
            row0.createCell(14).setCellValue("Otasining famiylasi");
            row0.createCell(15).setCellValue("Otasining telefon raqami");
            row0.createCell(16).setCellValue("Onasining ismi");
            row0.createCell(17).setCellValue("Onasining famiylasi");
            row0.createCell(18).setCellValue("Onasining telefon raqami");


            for (int i = 0; i < Database.studentPList.size(); i++) {
                List<StudentP> studentP = Database.studentPList;

                XSSFRow row = sheet.createRow(i + 1);


                row.createCell(0).setCellValue(studentP.get(i).getName());
                row.createCell(1).setCellValue(studentP.get(i).getSurname());
                row.createCell(2).setCellValue(studentP.get(i).getMiddlname());
                row.createCell(3).setCellValue(studentP.get(i).getBirthday());
                row.createCell(4).setCellValue(studentP.get(i).getCity());
                row.createCell(5).setCellValue(studentP.get(i).getDistrict());
                row.createCell(6).setCellValue(studentP.get(i).getManzil());
                row.createCell(7).setCellValue(studentP.get(i).getMaktabCity());
                row.createCell(8).setCellValue(studentP.get(i).getMaktabDistrict());
                row.createCell(9).setCellValue(studentP.get(i).getMaktabNumber());
                row.createCell(10).setCellValue(studentP.get(i).getPassportId());
                row.createCell(11).setCellValue(studentP.get(i).getPhone());
                row.createCell(12).setCellValue(studentP.get(i).getYotoqxona());
                row.createCell(13).setCellValue(studentP.get(i).getFathName());
                row.createCell(14).setCellValue(studentP.get(i).getFathSurname());
                row.createCell(15).setCellValue(studentP.get(i).getFathPhone());
                row.createCell(16).setCellValue(studentP.get(i).getMathName());
                row.createCell(17).setCellValue(studentP.get(i).getMathSurname());
                row.createCell(18).setCellValue(studentP.get(i).getMathPhone());



            }
            for (int i = 0; i < 19; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            workbook.close();
            return file;


        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }





    public static File registrationXCEL1() {

        StudentMRepository.loadStudentMList();

        File file = new File(ComponentContainer.PATH + "files/reg/registratsiyaMetirka.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();

        try (FileOutputStream out = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.createSheet("Ariza_metirka");
            XSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("Ismi");
            row0.createCell(1).setCellValue("Famiylasi");
            row0.createCell(2).setCellValue("Sharifi");
            row0.createCell(3).setCellValue("Tug'ilgan sanasi");
            row0.createCell(4).setCellValue("Tug'ilgan shahari");
            row0.createCell(5).setCellValue("Tug'ilgan tumani");
            row0.createCell(6).setCellValue("Manzili");
            row0.createCell(7).setCellValue("Maktab(shahari)");
            row0.createCell(8).setCellValue("Maktab(tumani)");
            row0.createCell(9).setCellValue("Maktab №");
            row0.createCell(10).setCellValue("Metirka raqami");
            row0.createCell(11).setCellValue("Telefon raqami");
            row0.createCell(12).setCellValue("Yotoxona");
            row0.createCell(13).setCellValue("Otasining ismi");
            row0.createCell(14).setCellValue("Otasining famiylasi");
            row0.createCell(15).setCellValue("Otasining telefon raqami");
            row0.createCell(16).setCellValue("Onasining ismi");
            row0.createCell(17).setCellValue("Onasining famiylasi");
            row0.createCell(18).setCellValue("Onasining telefon raqami");


            for (int i = 0; i < Database.studentMList.size(); i++) {
                List<StudentM> studentM = Database.studentMList;

                XSSFRow row = sheet.createRow(i + 1);


                row.createCell(0).setCellValue(studentM.get(i).getName());
                row.createCell(1).setCellValue(studentM.get(i).getSurname());
                row.createCell(2).setCellValue(studentM.get(i).getMiddlname());
                row.createCell(3).setCellValue(studentM.get(i).getBirthday());
                row.createCell(4).setCellValue(studentM.get(i).getCity());
                row.createCell(5).setCellValue(studentM.get(i).getDistrict());
                row.createCell(6).setCellValue(studentM.get(i).getManzil());
                row.createCell(7).setCellValue(studentM.get(i).getMaktabCity());
                row.createCell(8).setCellValue(studentM.get(i).getMaktabDistrict());
                row.createCell(9).setCellValue(studentM.get(i).getMaktabNumber());
                row.createCell(10).setCellValue(studentM.get(i).getMetirkaId());
                row.createCell(11).setCellValue(studentM.get(i).getPhone());
                row.createCell(12).setCellValue(studentM.get(i).getYotoqxona());
                row.createCell(13).setCellValue(studentM.get(i).getFathName());
                row.createCell(14).setCellValue(studentM.get(i).getFathSurname());
                row.createCell(15).setCellValue(studentM.get(i).getFathPhone());
                row.createCell(16).setCellValue(studentM.get(i).getMathName());
                row.createCell(17).setCellValue(studentM.get(i).getMathSurname());
                row.createCell(18).setCellValue(studentM.get(i).getMathPhone());



            }
            for (int i = 0; i < 19; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            workbook.close();
            return file;


        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }
}




