package com.example.BookSystem.repository;

import com.example.BookSystem.domain.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity,String> {
}
