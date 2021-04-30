package edu.alenkin.HomeLibUpd.model;

import edu.alenkin.HomeLibUpd.dbUtil.DbHelper;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
@ManagedBean
public class Genres implements Serializable {
    private DbHelper helper = new DbHelper();
   private static List<Genre> genreList = new ArrayList<>();

    public Genres() {
        genreList.add(new Genre(1, "ergvfd"));
        genreList.add(new Genre(2, "esdfrgvfd"));
        genreList.add(new Genre(3, "ervsregvfd"));
        genreList.add(new Genre(4, "ergvfqsdd"));
    }




    public List<Genre> getGenreList() {
        if (genreList != null && !genreList.isEmpty()) return genreList;

        return helper.execute("SELECT * FROM genre",
                resSet -> new Genre(
                        Long.parseLong(resSet.getString("id")),
                        resSet.getString("name"))

        );
    }
}
