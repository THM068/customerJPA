package com.demo.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tm1c14 on 04/06/2015.
 */
@Entity
public class Account {

    @JsonIgnore
    private String password;
    private String username;

    @OneToMany
    private Set<Bookmark> bookmarks = new HashSet<Bookmark>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    protected  Account() {}
}
