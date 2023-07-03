package com.example.basicSpring.controller;

import com.example.basicSpring.model.Author;
import com.example.basicSpring.model.Book;
import com.example.basicSpring.repository.AuthorRepository;
import com.example.basicSpring.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-add"; // Return the add book form if there are validation errors
        }

        Author author = book.getAuthor();
        authorRepository.save(author); // Save the author first

        String title = book.getTitle(); // Get the title from the form
        book.setTitle(title); // Set the title on the book object

        bookRepository.save(book);
        return "redirect:/books"; // Redirect to the book list page
    }


//    @PostMapping("/add")
//    public String addBook(@ModelAttribute("book") Book book, Model model) {
//        Author author = book.getAuthor();
//        authorRepository.save(author);
//        Book savedBook = bookRepository.save(book);
//        model.addAttribute("newBook", savedBook);
//        return "redirect:/books";
//    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book-list";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "book-edit"; // Updated template name to match the HTML file
    }

    @PostMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book updatedBook, BindingResult bindingResult, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));

        if (bindingResult.hasErrors()) {
            model.addAttribute("book", updatedBook);
            return "book-edit"; // Return the edit book form if there are validation errors
        }

        if (updatedBook.getTitle() != null) {
            book.setTitle(updatedBook.getTitle());
        }

        Author updatedAuthor = updatedBook.getAuthor();
        if (updatedAuthor != null && updatedAuthor.getName() != null) {
            Author author = book.getAuthor();
            author.setName(updatedAuthor.getName());
            authorRepository.save(author);
        }

        bookRepository.save(book);
        return "redirect:/books"; // Redirect to the book list page
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

}


