package com.example.BookSystem.service;

import com.example.BookSystem.domain.BookEntity;
import com.example.BookSystem.domain.ReviewEntity;
import com.example.BookSystem.domain.UserEntity;
import com.example.BookSystem.dto.ReviewDto;
import com.example.BookSystem.mapper.ReviewMapper;
import com.example.BookSystem.repository.BookRepository;
import com.example.BookSystem.repository.ReviewRepository;
import com.example.BookSystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper, BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    //User Create Review for a Book
    public ReviewDto createReview(Long userId, String bookId, String content)
    {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("No User with this ID"+ userId));
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
            () -> new EntityNotFoundException("No Book with this ID"+ bookId));
        ReviewEntity review = ReviewEntity.builder()
                .content(content)
                .user(userEntity)
                .book(bookEntity)
                .build();

        ReviewEntity savedReview = reviewRepository.save(review);
        return reviewMapper.reviewEntityToReviewDto(savedReview);
    }

    //User Delete it
    public void deleteReview(Long id)
    {
        reviewRepository.deleteById(id);
    }
    //User Update it
    public ReviewDto updateReview(Long id, ReviewDto reviewDto)
    {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No Review with this ID"+ id)
        );
        reviewEntity.setContent(reviewDto.getContent());
        ReviewEntity updated = reviewRepository.save(reviewEntity);
        return reviewMapper.reviewEntityToReviewDto(updated);
    }
}
