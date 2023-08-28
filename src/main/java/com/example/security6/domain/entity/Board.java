package com.example.security6.domain.entity;

import com.example.security6.domain.dto.BaseTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "boards")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;
    private Long boardView;

}
