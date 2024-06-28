package com.example.padel;

public class Lesson {

    public String coach;
    public String description, day,hour,field,prenotati;


    public Lesson(){

    }

    public String getPrenotati() {
        return prenotati;
    }

    public void setPrenotati(String prenotati) {
        this.prenotati = prenotati;
    }

    public Lesson(String coach, String description, String day, String hour, String field, String prenotati){
        this.coach = coach;
        this.description = description;
        this.day = day;
        this.hour = hour;
        this.field = field;
        this.prenotati = prenotati;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
