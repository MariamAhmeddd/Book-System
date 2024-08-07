package com.example.BookSystem.mapper;

import com.example.BookSystem.domain.BookEntity;
import com.example.BookSystem.domain.UserEntity;
import com.example.BookSystem.dto.BookDto;
import com.example.BookSystem.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    @Autowired
    private ModelMapper modelMapper;

    public BookEntity bookDtoToBookEntity(BookDto bookDto)
    {
        return modelMapper.map(bookDto, BookEntity.class);
    }
    public BookDto bookEntityToBookDto(BookEntity bookEntity) {return modelMapper.map(bookEntity, BookDto.class);}
}
