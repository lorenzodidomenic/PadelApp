package com.example.padel;

public class Lesson {

    public String coach;
    public String description;


    public Lesson(){

    }

    public Lesson(String coach, String description){
        this.coach = coach;
        this.description = description;

    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
