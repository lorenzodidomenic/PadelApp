package com.example.padel;

public class User {

    public String name,surname,telephone,email;
    public Float skillValue,softSkillValue;

    public int numGames;

    public User(){}
    public User(String name, String surname, String telephone, String email,Float skillValue,Float softSkillValue,int numGames){
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.skillValue = skillValue;
        this.softSkillValue = softSkillValue;
        this.numGames = numGames;
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

    public Float getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(Float skillValue) {
        this.skillValue = skillValue;
    }

    public int getNumGames() {
        return numGames;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    public Float getSoftSkillValue() {
        return softSkillValue;
    }

    public void setSoftSkillValue(Float softSkillValue) {
        this.softSkillValue = softSkillValue;
    }
}
