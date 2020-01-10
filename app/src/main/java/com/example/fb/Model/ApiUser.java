package com.example.fb.Model;

public class ApiUser {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String date;

    private String gender;

    private String profileimage;

    private String token;


    public ApiUser(String firstname, String lastname, String email, String password, String date, String gender,String profileimage) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.profileimage= profileimage;


    }



    public ApiUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getToken() {
        return token;
    }
}
