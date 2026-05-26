package com.practice2.library.repository;

import com.practice2.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
    List<Author> findByNameIn(Set<String> names);
}
