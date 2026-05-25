package com.practice2.library.DTO;

import java.util.Set;

public class BookResponse {
    private Long id;
    private String title;
    private Set<String> authors;

    public BookResponse(Long id, String title,Set<String>authors){
        this.id = id;
        this.title = title;
        this.authors = authors;
    }
    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public Set<String> getAuthors(){
        return authors ;
    }

}
