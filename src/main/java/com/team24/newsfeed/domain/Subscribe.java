package com.team24.newsfeed.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "friend")
@NoArgsConstructor
public class Subcribe extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean requsetFriend;
    private boolean isFfriend;


    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> friendList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responseUser;


}
