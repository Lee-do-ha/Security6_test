package com.example.security6.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "boards")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
