package com.example.padel;

public class Coach {


    public String name;
    public String speciality;

    public Coach(){

    }
    public Coach(String name,String speciality){
        this.name = name;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}

