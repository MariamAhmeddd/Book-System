package com.example.BookSystem.mapper;

import com.example.BookSystem.domain.ReviewEntity;
import com.example.BookSystem.dto.ReviewDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ReviewEntity reviewDtoToReviewEntity(ReviewDto reviewDto)
    {
        return modelMapper.map(reviewDto, ReviewEntity.class);
    }
    public ReviewDto reviewEntityToReviewDto(ReviewEntity reviewEntity)
    {
        return modelMapper.map(reviewEntity, ReviewDto.class);
    }
}
