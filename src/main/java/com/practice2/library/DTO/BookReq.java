package com.practice2.library.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class BookReq {

    private String title;
    private Set<String> authors;
}