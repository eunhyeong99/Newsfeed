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

    private Long toUserId;
    @JoinColumn(name = "fromUserId")
    @ManyToOne(fetch = FetchType.LAZY)

    private User fromUser;


    public Subscribe(long toUserId, User fromUser) {
        this.toUserId = toUserId;
        this.fromUser = fromUser;
    }


}
