package com.AnimeTalk.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    private String captions;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;
}
