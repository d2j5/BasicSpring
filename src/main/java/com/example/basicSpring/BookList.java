package com.example.basicSpring;

import com.example.basicSpring.model.Book;
import com.example.basicSpring.model.Author;
import com.example.basicSpring.repository.AuthorRepository;
import com.example.basicSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookList implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookList(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("James Joyce");
        Author author2 = new Author("Jorge Luis Borges");
        authorRepository.save(author1);
        authorRepository.save(author2);

        Book book1 = new Book("Ulises", author1);
        Book book2 = new Book("Collected Fictions", author2);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
