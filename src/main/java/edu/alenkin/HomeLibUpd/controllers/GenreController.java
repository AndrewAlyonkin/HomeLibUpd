package edu.alenkin.HomeLibUpd.controllers;

import edu.alenkin.HomeLibUpd.dbUtil.DbHelper;
import edu.alenkin.HomeLibUpd.model.Genre;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(eager = true)
@SessionScoped
public class GenreController {
    private List<Genre> genresList;
    private DbHelper helper = new DbHelper();

    public GenreController() {
        fillGenresList();
    }

    private void fillGenresList() {
        genresList =  helper.execute("SELECT * FROM genre",
                resSet -> new Genre(
                        Long.parseLong(resSet.getString("id")),
                        resSet.getString("name"))
        );

    }

    public List<Genre> getGenresList() {
        return genresList;
    }
}
