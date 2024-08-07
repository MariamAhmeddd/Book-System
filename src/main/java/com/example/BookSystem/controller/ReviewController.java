package com.example.BookSystem.controller;

import com.example.BookSystem.dto.ReviewDto;
import com.example.BookSystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestParam Long userId, @RequestParam String bookId, @RequestParam String content)
    {
        ReviewDto created = reviewService.createReview(userId, bookId, content);
        return new ResponseEntity<ReviewDto>(created, HttpStatus.CREATED);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto)
    {
        ReviewDto updated = reviewService.updateReview(id, reviewDto);
        return new ResponseEntity<ReviewDto>(updated,HttpStatus.OK);
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id)
    {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
