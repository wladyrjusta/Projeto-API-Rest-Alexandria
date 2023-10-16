package com.betrybe.alexandria.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_details")
public class BookDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String summary;
  @Column(name = "page_count")
  private Integer pageCount;
  private String year;
  private String isbn;
  @OneToOne()
  @JoinColumn(name = "book_id")
  @JsonIgnore
  private Book book;

  public BookDetails() { }

  public BookDetails(Long id, String summary, Integer pageCount, String year, String isbn, Book book) {
    this.id = id;
    this.summary = summary;
    this.pageCount = pageCount;
    this.year = year;
    this.isbn = isbn;
    this.book = book;
  }

  public Long getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public String getYear() {
    return year;
  }

  public String getIsbn() {
    return isbn;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

}
