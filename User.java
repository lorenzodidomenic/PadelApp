package com.example.padel;

public class User {

    public String name,surname,telephone,email;

    public User(){}
    public User(String name, String surname, String telephone, String email){
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
