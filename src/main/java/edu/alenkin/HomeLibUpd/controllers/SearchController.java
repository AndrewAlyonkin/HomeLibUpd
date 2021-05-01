package edu.alenkin.HomeLibUpd.controllers;

import edu.alenkin.HomeLibUpd.dbUtil.DbHelper;
import edu.alenkin.HomeLibUpd.model.Book;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@ManagedBean
@SessionScoped
public class SearchController implements Serializable {
    private SearchType searchType;
    private String searchString;
    private Map<String, SearchType> searchList = new HashMap<>();
    private List<Book> currentBookList;
    private char[] letters = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё',
            'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Э', 'Ю', 'Я'};

    public List<Book> getCurrentBookList() {
        return currentBookList;
    }

    DbHelper helper = new DbHelper();

    public SearchController() {
        ResourceBundle bundle = ResourceBundle.getBundle("msg", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        searchList.put(bundle.getString("author"), SearchType.AUTHOR);
        searchList.put(bundle.getString("title"), SearchType.TITLE);
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public void fillBooksByGenre() {
        Map<String, String> parameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long genre_id = Long.parseLong(parameters.get("genre_id"));
        getBooksByGenre(genre_id);
    }

    public void getBooksByGenre(Long genreId) {
        if (genreId==0) getAllBooks();
        if (currentBookList == null || currentBookList.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            currentBookList = helper.execute("SELECT a.fio as \"author\", b.image as \"image\", b.id as \"bookId\"," +
                            "a.id as \"id\", a.birthday as \"birthday\", b.page_count as \"pages\"," +
                            " b.publish_year as \"year\",p.name as \"name\",b.name as \"bookName\"," +
                            " b.isbn as \"isbn\", g.id as \"genreId\", b.descr as \"descr\", b.rating as \"rating\"" +
                            " FROM book b " +
                            "LEFT JOIN genre g ON(b.genre_id = g.id) " +
                            "LEFT JOIN publisher p ON(b.publisher_id=p.id) " +
                            "LEFT JOIN author a ON(b.author_id=a.id) " +
                            "WHERE (g.id=?) " +
                            "ORDER BY a.fio " +
                            "LIMIT 5; ",
                    resSet -> Book.buildBook(resSet), prepst -> prepst.setLong(1, genreId));
        }
    }

    public void getBooksByLetter(String letter) {
        if (currentBookList == null || currentBookList.isEmpty()) {
            currentBookList = helper.execute("SELECT a.fio as \"author\", b.image as \"image\", b.id as \"bookId\"," +
                            "a.id as \"id\", a.birthday as \"birthday\", b.page_count as \"pages\"," +
                            " b.publish_year as \"year\",p.name as \"name\",b.name as \"bookName\"," +
                            " b.isbn as \"isbn\", g.id as \"genreId\", b.descr as \"descr\", b.rating as \"rating\"" +
                            " FROM book b " +
                            "LEFT JOIN genre g ON(b.genre_id = g.id) " +
                            "LEFT JOIN publisher p ON(b.publisher_id=p.id) " +
                            "LEFT JOIN author a ON(b.author_id=a.id) " +
                            "WHERE (LOWER(b.name) LIKE(?))" +
                            "ORDER BY a.fio;",
                    resSet -> Book.buildBook(resSet), prepst -> prepst.setString(1, "%"+letter.toLowerCase()+"%"));
        }
    }

    public void searchBooks(String searching, String searchType) {
        String param;
        if (searchType.equals("name")) {
            param = "b.name";
        } else param = "a.fio";
        if (currentBookList == null || currentBookList.isEmpty()) {
            currentBookList = helper.execute("SELECT a.fio as \"author\", b.image as \"image\", b.id as \"bookId\", " +
                            "a.id as \"id\", a.birthday as \"birthday\", b.page_count as \"pages\"," +
                            " b.publish_year as \"year\",p.name as \"name\",b.name as \"bookName\"," +
                            " b.isbn as \"isbn\", g.id as \"genreId\", b.descr as \"descr\", b.rating as \"rating\"" +
                            " FROM book b " +
                            "LEFT JOIN genre g ON(b.genre_id = g.id) " +
                            "LEFT JOIN publisher p ON(b.publisher_id=p.id) " +
                            "LEFT JOIN author a ON(b.author_id=a.id) " +
                            "WHERE (LOWER("+param+") LIKE(?)) " +
                            "ORDER BY a.fio;",
                    resSet -> Book.buildBook(resSet),
                    prepst -> prepst.setString(1, "%"+searching.toLowerCase()+"%"));
        }
    }
    public void getAllBooks() {
        if (currentBookList == null || currentBookList.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            currentBookList = helper.execute("SELECT a.fio as \"author\", b.image as \"image\", b.id as \"bookId\"," +
                            "a.id as \"id\", a.birthday as \"birthday\", b.page_count as \"pages\"," +
                            " b.publish_year as \"year\",p.name as \"name\",b.name as \"bookName\"," +
                            " b.isbn as \"isbn\", g.id as \"genreId\", b.descr as \"descr\", b.rating as \"rating\"" +
                            " FROM book b " +
                            "LEFT JOIN genre g ON(b.genre_id = g.id) " +
                            "LEFT JOIN publisher p ON(b.publisher_id=p.id) " +
                            "LEFT JOIN author a ON(b.author_id=a.id) " +
                            "ORDER BY a.fio;",
                    resSet -> Book.buildBook(resSet));
        }
    }


    public byte[] getImage(int index) {
        return helper.getImageFromDb(index);
    }

    public char[] getRussianLetters() {
        return letters;
    }
}
