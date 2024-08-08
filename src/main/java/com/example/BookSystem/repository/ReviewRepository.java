package com.example.BookSystem.repository;

import com.example.BookSystem.domain.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity,Long> {
    List<ReviewEntity> findByBookIsbn(String isbn);
}
