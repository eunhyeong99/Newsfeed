package com.team24.newsfeed.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@Table(name="subscribe")
@NoArgsConstructor
public class Subscribe extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "friend_id", nullable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User friend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;


    public Subscribe(User friend, User user) {
        this.friend = friend;
        this.user = user;
    }

}

