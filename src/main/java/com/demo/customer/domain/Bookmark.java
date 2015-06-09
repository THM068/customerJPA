package com.demo.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by tm1c14 on 04/06/2015.
 */

@Entity
public class Bookmark {

    @JsonIgnore
    @ManyToOne
    private Account account;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uri;
    private String description;

    protected Bookmark() {}

    public Bookmark(Account account, String uri, String description) {
        this.setUri(uri);
        this.setDescription(description);
        this.setAccount(account);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format("[ id %d, account %s, uri %s", id, account.getUsername(), uri);
    }
}
