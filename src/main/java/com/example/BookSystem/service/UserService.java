package com.example.BookSystem.service;

import com.example.BookSystem.domain.BookEntity;
import com.example.BookSystem.domain.ReviewEntity;
import com.example.BookSystem.domain.UserEntity;
import com.example.BookSystem.dto.BookDto;
import com.example.BookSystem.dto.ReviewDto;
import com.example.BookSystem.dto.UserDto;
import com.example.BookSystem.mapper.ReviewMapper;
import com.example.BookSystem.mapper.UserMapper;
import com.example.BookSystem.repository.BookRepository;
import com.example.BookSystem.repository.ReviewRepository;
import com.example.BookSystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private UserRepository userRepository;
    private UserMapper userMapper;
    private BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bookRepository = bookRepository;
    }
    //Create new User
    public UserDto saveUser(UserDto userDto)
    {
        UserEntity savedEntity = userMapper.userDtoToUserEntity(userDto);
        savedEntity = userRepository.save(savedEntity);
        return userMapper.userEntityToUserDto(savedEntity);
    }

    //Get List of Users
    public List<UserDto> listUsers()
    {
        List<UserEntity> foundUsers = (List<UserEntity>) userRepository.findAll();
        return foundUsers.stream().map(user -> userMapper.userEntityToUserDto(user)).collect(Collectors.toList());
    }

    //Get one user
    public UserDto getOneUser(Long id)
    {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No User with this ID"+ id));
        return userMapper.userEntityToUserDto(entity);
    }

    //Delete User
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    //Update User
    public UserDto updateUser(Long id, UserDto updatedUserDto)
    {
        UserEntity entity = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No User with this ID"+ id));
        entity.setName(updatedUserDto.getName());
        entity.setEmail(updatedUserDto.getEmail());

        UserEntity updatedEntity = userRepository.save(entity);
        return userMapper.userEntityToUserDto(updatedEntity);
    }

    //User can buy book -> adding it to its list of books
    public UserDto buyBook(Long userId, String bookId)
    {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException("No User with this ID"+ userId));
        BookEntity bookEntity = bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("No Book with this ID"+ bookId));
        userEntity.getBooks().add(bookEntity);
        UserEntity updatedUser = userRepository.save(userEntity);
        return userMapper.userEntityToUserDto(updatedUser);
    }
}
