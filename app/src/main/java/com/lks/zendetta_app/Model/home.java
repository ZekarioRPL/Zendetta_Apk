package com.lks.zendetta_app.Model;

public class home {
    public String id, name, date, earlyVisit, endVisit, gambar, status;

    public home(String id, String name, String date, String earlyVisit, String endVisit, String gambar) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.earlyVisit = earlyVisit;
        this.endVisit = endVisit;
        this.gambar = gambar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEarlyVisit() {
        return earlyVisit;
    }

    public void setEarlyVisit(String earlyVisit) {
        this.earlyVisit = earlyVisit;
    }

    public String getEndVisit() {
        return endVisit;
    }

    public void setEndVisit(String endVisit) {
        this.endVisit = endVisit;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
