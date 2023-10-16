package com.betrybe.alexandria.models.repositories;

import com.betrybe.alexandria.models.entities.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> { }
