package com.example.test;

public class User {
    private String id;
    private String name;
    private String familia;
    private String otchestvo;
    private String mail;
    private String login;
    private String password;

    public User() {
        // Пустой конструктор по умолчанию, необходимый для Firebase
    }

    public User(String id, String name, String familia, String otchestvo, String mail, String login, String password) {
        this.id = id;
        this.name = name;
        this.familia = familia;
        this.otchestvo = otchestvo;
        this.mail = mail;
        this.login = login;
        this.password = password;
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

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
