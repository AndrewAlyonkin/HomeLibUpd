package edu.alenkin.HomeLibUpd.model;

import edu.alenkin.HomeLibUpd.dbUtil.Config;
import edu.alenkin.HomeLibUpd.dbUtil.DbHelper;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

    public class Book {
        private byte[] content;
        private Author author;
        private int pages;
        private Year publishDate;

        private String publisher;

        private byte[] image;
        private String name;
        private String isbn;
        private Genre genre;
        private String desc;
        private int rating;
        private int id;
        public Book(Author author, int pages, Year publishDate,
                    String publisher, String name,
                    String isbn, Genre genre, String desc, int rating, byte[] image, int bookId) {
            this.author = author;
            this.pages = pages;
            this.publishDate = publishDate;
            this.publisher = publisher;
            this.name = name;
            this.isbn = isbn;
            this.genre = genre;
            this.desc = desc;
            this.rating = rating;
            this.id = bookId;
        }

        public Book(String name, String isbn, String genre, String genreId, String description, int page_count, int rating) {
            this.name = name;
            this.genre = new Genre(Long.valueOf(genreId), genre);
            this.desc = description;
            this.pages = page_count;
            this.rating = rating;
        }

        public void fillContent() {
            try (Connection conn = Config.get().getConnectionFactory().getConnection()) {
                PreparedStatement prepSt = conn.prepareStatement("SELECT content FROM book WHERE id=?;");
                prepSt.setInt(1, this.getId());
                ResultSet resSet = prepSt.executeQuery();
                while (resSet.next()){
                    this.setContent(resSet.getBytes("content"));
                }
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public int getId() {
            return id;
        }

        public Year getPublishDate() {
            return publishDate;
        }

        public String getDesc() {
            return desc;
        }

        public void setId(int id) {
            this.id = id;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public Author getAuthor() {
            return author;
        }

        public int getPages() {
            return pages;
        }

        public String getPublisher() {
            return publisher;
        }

        public byte[] getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIsbn() {
            return isbn;
        }

        public static Book buildBook(ResultSet resSet) throws SQLException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return new Book(
                    new Author(resSet.getString("author"),
                            Integer.parseInt(resSet.getString("id")),
                            LocalDate.parse(resSet.getString("birthday"), formatter)),
                    Integer.parseInt(resSet.getString("pages")),
                    Year.parse(resSet.getString("year")),
                    resSet.getString("name"),
                    resSet.getString("bookName"),
                    resSet.getString("isbn"),
                    new Genre(Long.parseLong(resSet.getString("genreId")), resSet.getString("name")),
                    resSet.getString("descr"),
                    Integer.parseInt(resSet.getString("rating")),
                    resSet.getBytes("image"),
                    resSet.getInt("bookId"));
        }

    }


