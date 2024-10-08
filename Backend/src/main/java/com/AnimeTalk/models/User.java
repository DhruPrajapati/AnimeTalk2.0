package com.AnimeTalk.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String password;

    private List<Integer> followers = new ArrayList<>();

    private List<Integer> followings= new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
}
