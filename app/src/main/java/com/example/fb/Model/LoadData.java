package com.example.fb.Model;

public class LoadData {
    private String fullname;

    private String status;

    private String time;

    private String loadimage;

    public LoadData(String fullname, String status, String time, String loadimage) {
        this.fullname = fullname;
        this.status = status;
        this.time = time;
        this.loadimage = loadimage;
    }

    public LoadData(String fullname, String status, String time) {
        this.fullname = fullname;
        this.status = status;
        this.time = time;
    }

    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getLoadimage() {
        return loadimage;
    }
}
