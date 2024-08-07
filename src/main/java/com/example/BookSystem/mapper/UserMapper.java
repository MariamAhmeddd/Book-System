package com.example.BookSystem.mapper;

import com.example.BookSystem.domain.BookEntity;
import com.example.BookSystem.domain.UserEntity;
import com.example.BookSystem.dto.BookDto;
import com.example.BookSystem.dto.UserDto;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UserEntity userDtoToUserEntity(UserDto userDto)
    {
        return modelMapper.map(userDto, UserEntity.class);
    }
    public UserDto userEntityToUserDto(UserEntity userEntity)
    {
        return modelMapper.map(userEntity, UserDto.class);
    }

}
