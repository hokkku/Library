package com.practice2.library.DTO;
import java.util.Set;
public class AuthorResponse {
    private Long id;
    private String name;
    private Set<String>books;

    public AuthorResponse(Long id, String name,Set<String>books){
        this.id = id;
        this.name = name;
        this.books = books;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Set<String> getBooks(){
        return books;
    }

}
