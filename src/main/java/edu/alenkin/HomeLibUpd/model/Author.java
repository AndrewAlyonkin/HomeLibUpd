package edu.alenkin.HomeLibUpd.model;

import java.time.LocalDate;

public class Author {
    private String full_name;
    private LocalDate birthDay;
    private int id;

    public Author(String full_name, int id, LocalDate birthDay) {
        this.full_name = full_name;
        this.id = id;
        this.birthDay = birthDay;
    }


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return full_name;
    }
}

