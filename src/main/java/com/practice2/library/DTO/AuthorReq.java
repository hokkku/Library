package com.practice2.library.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class AuthorReq {
    private String name;
    private Set<String> bookTitles;
    public String getName() {
        return name;
    }
    public Set<String> getBookTitles() {
        return bookTitles;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBookTitles(Set<String> bookTitles) {
        this.bookTitles = bookTitles;
    }
}

