package com.betrybe.alexandria.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String genre;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "book")
  private BookDetails details;
  @ManyToOne()
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;
  @ManyToMany
  @JoinTable(
      name = "author_books",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  private List<Author> authors;

  public Book() {}

  public Book(Long id, String title, String genre, BookDetails details, Publisher publisher, List<Author> authors) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.details = details;
    this.publisher = publisher;
    this.authors = authors;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getGenre() {
    return genre;
  }

  public BookDetails getDetails() {
    return details;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setDetails(BookDetails details) {
    this.details = details;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}
