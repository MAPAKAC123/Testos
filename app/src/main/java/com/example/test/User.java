package com.example.test;

public class User {
    private String id, name, familia, otchestvo, mail, login, password, avatarUrl;
    public User() {
        // Пустой конструктор по умолчанию, необходимый для Firebase
    }

    public User(String id, String name, String familia, String otchestvo, String mail, String login, String password, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.familia = familia;
        this.otchestvo = otchestvo;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.avatarUrl = avatarUrl;
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
    public String getAvatarUrl(){ return avatarUrl;}
    public String getFamilia() {
        return familia;
    }
    public String getOtchestvo() {
        return otchestvo;
    }
    public String getMail() {
        return mail;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
