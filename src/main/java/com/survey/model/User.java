package com.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "mail", unique = true) // nome colonna Id, Primary key
    private String mail;

    @Column(name = "pass")
    private String pass;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    public User() {

    }


    public User(String mail, String pass, boolean isAdmin) {
        this.mail = mail;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User [mail=" + mail + ", pass=" + pass + ", isAdmin=" + isAdmin + "]";
    }
}
