package com.example.module6.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="chatChannel")
public class ChatChannel {
    @Id
    @NotNull
    private String uuid;

    @OneToOne
    @JoinColumn(name = "userIdOne")
    private User userOne;

    @OneToOne
    @JoinColumn(name = "userIdTwo")
    private User userTwo;



    public ChatChannel() {

    }

    public ChatChannel(Object user, Object user1) {
    }

    public void setUserTwo(User user) {
        this.userTwo = user;
    }

    public void setUserOne(User user) {
        this.userOne = user;
    }

    public User getUserOne() {
        return this.userOne;
    }

    public User getUserTwo() {
        return this.userTwo;
    }

    public String getUuid() {
        return this.uuid;
    }
}
