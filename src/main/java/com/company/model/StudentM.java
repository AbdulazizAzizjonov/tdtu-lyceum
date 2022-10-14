package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class StudentM {
    private Integer id;
    private String name;
    private String surname;
    private String middlname;
    private String birthday;
    private String city;
    private String district;
    private String manzil;
    private String maktabCity;
    private String maktabDistrict;
    private String maktabNumber;
    private String metirkaId;
    private String phone;
    private String fathName;
    private String fathSurname;
    private String fathPhone;
    private String mathName;
    private String mathSurname;
    private String mathPhone;
    private String mImage;
    private String image;
    private String yotoqxona;
    private boolean deleted=false;

    public StudentM(String name, String surname, String middlname, String birthday, String city, String district, String manzil, String maktabCity, String maktabDistrict, String maktabNumber, String metirkaId, String phone, String fathName, String fathSurname, String fathPhone, String mathName, String mathSurname, String mathPhone, String mImage, String image, String yotoqxona) {
        this.name = name;
        this.surname = surname;
        this.middlname = middlname;
        this.birthday = birthday;
        this.city = city;
        this.district = district;
        this.manzil = manzil;
        this.maktabCity = maktabCity;
        this.maktabDistrict = maktabDistrict;
        this.maktabNumber = maktabNumber;
        this.metirkaId = metirkaId;
        this.phone = phone;
        this.fathName = fathName;
        this.fathSurname = fathSurname;
        this.fathPhone = fathPhone;
        this.mathName = mathName;
        this.mathSurname = mathSurname;
        this.mathPhone = mathPhone;
        this.mImage = mImage;
        this.image = image;
        this.yotoqxona = yotoqxona;
    }
}
