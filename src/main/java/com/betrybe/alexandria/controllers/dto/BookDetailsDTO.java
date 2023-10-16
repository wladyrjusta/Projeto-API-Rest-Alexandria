package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.BookDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDetailsDTO(Long id, String summary, @JsonProperty("page_count") Integer pageCount, String year, String isbn) {

  public BookDetails toBookDetails() {
    return new BookDetails(id, summary, pageCount, year, isbn, null);
  }

}
