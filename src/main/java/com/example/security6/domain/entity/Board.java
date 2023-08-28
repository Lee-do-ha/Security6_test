package com.example.security6.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "boards")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardAuthor")
    private User boardAuthor;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;
    private Long boardView;

}
