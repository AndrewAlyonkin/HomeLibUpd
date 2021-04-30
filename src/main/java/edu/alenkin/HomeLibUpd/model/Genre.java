package edu.alenkin.HomeLibUpd.model;

public class Genre {
    private String name;
    private Long id = 0L;

    public Genre(long id, String genre) {
        this.id = id;
        this.name = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
