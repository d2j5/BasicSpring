package com.example.basicSpring.model;

import com.example.basicSpring.model.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class Author {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "You should enter author's name")
    @Size(min = 3, max = 250)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<com.example.basicSpring.model.Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}