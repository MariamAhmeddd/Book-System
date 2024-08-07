package com.example.BookSystem.service;

import com.example.BookSystem.domain.BookEntity;
import com.example.BookSystem.domain.UserEntity;
import com.example.BookSystem.dto.BookDto;
import com.example.BookSystem.dto.UserDto;
import com.example.BookSystem.mapper.BookMapper;
import com.example.BookSystem.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    //Create new book
    public BookDto saveBook(BookDto bookDto)
    {
        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
        bookEntity = bookRepository.save(bookEntity);
        return bookMapper.bookEntityToBookDto(bookEntity);
    }
    //Get list of books
    public List<BookDto> listBooks()
    {
        List<BookEntity> foundBooks = (List<BookEntity>) bookRepository.findAll();
        return foundBooks.stream().map(book -> bookMapper.bookEntityToBookDto(book)).collect(Collectors.toList());
    }

    //Get one book
    public BookDto getOneBook(String isbn)
    {
        BookEntity entity = bookRepository.findById(isbn).orElseThrow(
                () -> new EntityNotFoundException("No book with this ISBN"+ isbn));
        return bookMapper.bookEntityToBookDto(entity);
    }
    //delete book
    public void deleteBook(String isbn)
    {
        bookRepository.deleteById(isbn);
    }
    //update book
    public BookDto updateBook(String isbn, BookDto updatedBookDto)
    {
        BookEntity entity = bookRepository.findById(isbn).orElseThrow(
                () -> new EntityNotFoundException("No Book with this ISBN"+ isbn));
        entity.setTitle(updatedBookDto.getTitle());
        entity.setAuthor(updatedBookDto.getAuthor());
        entity.setIsbn(updatedBookDto.getIsbn());

        BookEntity updatedEntity = bookRepository.save(entity);
        return bookMapper.bookEntityToBookDto(updatedEntity);
    }
    //book's reviews
}

