package com.betrybe.alexandria.models.repositories;


import com.betrybe.alexandria.models.entities.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Page<Book> findAll(Pageable pageable);
}
