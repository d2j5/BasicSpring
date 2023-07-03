package com.example.basicSpring.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotEmpty(message = "Insert book's title")
@Size(min = 3, max = 250)
    private String title;

    @ManyToOne
    @NotNull(message = "The book author is mandatory")
    private Author author;

    public Book() {
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public static List<String> getBookInfoList(List<Book> books) {
        List<String> bookInfoList = new ArrayList<>();

        for (Book book : books) {
            String bookInfo = "ID: " + book.getId()
                    + ", Title: " + book.getTitle()
                    + ", Author: " + book.getAuthor().getName();

            bookInfoList.add(bookInfo);
        }
        return bookInfoList;
    }
}
