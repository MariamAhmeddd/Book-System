package com.example.BookSystem.controller;

import com.example.BookSystem.dto.BookDto;
import com.example.BookSystem.dto.UserDto;
import com.example.BookSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto savedUser = userService.saveUser(userDto);
        return new ResponseEntity<UserDto>(savedUser,HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto)
    {
        UserDto Updated = userService.updateUser(id, userDto);
        return new ResponseEntity<UserDto>(Updated,HttpStatus.OK);
    }

    //Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Get List of Users
    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers()
    {
        return new ResponseEntity<List<UserDto>>(userService.listUsers(), HttpStatus.OK);
    }
    //Get One User
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getOneUser(@PathVariable Long id)
    {
        UserDto retrieve = userService.getOneUser(id);
        return new ResponseEntity<UserDto>(retrieve,HttpStatus.OK);
    }

    //buying book
    @PostMapping("/{id}/buy-book")
    public ResponseEntity<UserDto>buyBook(@PathVariable Long id, @RequestParam String bookId)
    {
        UserDto userDto = userService.buyBook(id, bookId);
        return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
    }
}
