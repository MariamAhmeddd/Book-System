package com.example.BookSystem.controller;

import com.example.BookSystem.dto.BookDto;
import com.example.BookSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Create Book
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto)
    {
        BookDto savedBook = bookService.saveBook(bookDto);
        return new ResponseEntity<BookDto>(savedBook, HttpStatus.CREATED);
    }

    //Update Book
    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String isbn, @RequestBody BookDto bookDto)
    {
        BookDto updated = bookService.updateBook(isbn, bookDto);
        return new ResponseEntity<BookDto>(updated,HttpStatus.OK);
    }

    //Delete Book
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn)
    {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get List of Books
    @GetMapping
    public ResponseEntity<List<BookDto>> listBooks()
    {
        return new ResponseEntity<List<BookDto>>(bookService.listBooks(), HttpStatus.OK);
    }

    //Get One Book
    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto>getOneBook(@PathVariable String isbn)
    {
        BookDto retrieve = bookService.getOneBook(isbn);
        return new ResponseEntity<BookDto>(retrieve,HttpStatus.OK);
    }
}
