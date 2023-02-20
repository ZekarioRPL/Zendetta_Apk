package com.lks.zendetta_app.Model;

public class patientModel {
    public String id, name, birthdate, phone, lastVisit, NextVisit, gambar, city, TimeStamp;

    public patientModel(String id, String name, String birthdate, String phone, String lastVisit, String nextVisit, String gambar) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.phone = phone;
        this.lastVisit = lastVisit;
        NextVisit = nextVisit;
        this.gambar = gambar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getNextVisit() {
        return NextVisit;
    }

    public void setNextVisit(String nextVisit) {
        NextVisit = nextVisit;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
