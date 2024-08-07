package com.example.BookSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    private String isbn;
    private String title;
    private String author;
    @ManyToMany(mappedBy = "books")
    private List<UserEntity> users;
    @OneToMany(mappedBy = "book")
    private List<ReviewEntity> reviews; // Add this line


}
