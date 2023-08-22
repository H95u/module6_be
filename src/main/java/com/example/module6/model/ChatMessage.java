package com.example.module6.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name="chatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "authorUserId")
    private User authorUser;

    @OneToOne
    @JoinColumn(name = "recipientUserId")
    private User recipientUser;

    @NotNull
    private LocalDateTime timeSent;

    @NotNull
    private String contents;

    public ChatMessage() {

    }
}
