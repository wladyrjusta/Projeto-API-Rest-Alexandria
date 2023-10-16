package com.betrybe.alexandria.services;

import com.betrybe.alexandria.controllers.dto.BookDTO;
import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.BookDetails;
import com.betrybe.alexandria.models.entities.Publisher;
import com.betrybe.alexandria.models.repositories.BookDetailsRepository;
import com.betrybe.alexandria.models.repositories.BookRepository;
import com.betrybe.alexandria.models.repositories.PublisherRepository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final BookDetailsRepository bookDetailsRepository;
  private final PublisherRepository publisherRepository;

  @Autowired
  public BookService(
      BookRepository bookRepository,
      BookDetailsRepository bookDetailsRepository,
      PublisherRepository publisherRepository
  ) {
    this.bookRepository = bookRepository;
    this.bookDetailsRepository = bookDetailsRepository;
    this.publisherRepository = publisherRepository;
  }

  /* --------------------------BookService------------------------------------*/

  public Book insertBook(Book book) {
    return bookRepository.save(book);
  }

  public Optional<Book> updateBook(Long id, Book book) {
    Optional<Book> optionalBook = bookRepository.findById(id);

    if (optionalBook.isPresent()) {
      Book bookFromDB = optionalBook.get();
      bookFromDB.setTitle(book.getTitle());
      bookFromDB.setGenre(book.getGenre());

      Book updatedBook = bookRepository.save(bookFromDB);
      return Optional.of(updatedBook);
    }
    return optionalBook;
  }

  public Optional<Book> removeBookById(Long id) {
    Optional<Book> bookOptional = bookRepository.findById(id);

    if (bookOptional.isPresent()) {
      bookRepository.deleteById(id);
    }

    return bookOptional;
  }

  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  public List<BookDTO> getAllBooks(int pageNumber, int pageSize) {
    List<BookDTO> bookDTOList = new ArrayList<>();

    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Book> books = bookRepository.findAll(pageable);

    books.forEach((book) -> {
      BookDTO bookDTO = new BookDTO(
          book.getId(), book.getTitle(), book.getGenre()
      );
      bookDTOList.add(bookDTO);
    });
    return bookDTOList;
  }

  /* --------------------------BookDetailsService------------------------------------*/

  public Optional<BookDetails> insertBookDetails(Long bookId, BookDetails bookDetails) {
   Optional<Book> optionalBook = bookRepository.findById(bookId);

   if (optionalBook.isPresent()) {
     Book book = optionalBook.get();
     bookDetails.setBook(book);
     BookDetails newBookDetails = bookDetailsRepository.save(bookDetails);
     return Optional.of(newBookDetails);
   }
   return Optional.empty();
  }

  public Optional<BookDetails> updateBookDetails(Long bookId, BookDetails bookDetails) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      BookDetails bookDetailsFromDB = book.getDetails();
      bookDetailsFromDB.setSummary(bookDetails.getSummary());
      bookDetailsFromDB.setPageCount(bookDetails.getPageCount());
      bookDetailsFromDB.setYear(bookDetails.getYear());
      bookDetailsFromDB.setIsbn(bookDetails.getIsbn());

      BookDetails updatedBookDetails = bookDetailsRepository.save(bookDetailsFromDB);
      return  Optional.of(updatedBookDetails);
    }

    return Optional.empty();
  }

  public Optional<BookDetails> removeBookDetailsById(Long bookId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      Optional<BookDetails> optionalBookDetails = bookDetailsRepository.findById(book.getDetails().getId());

      if (optionalBookDetails.isPresent()) {
        book.setDetails(null);
        BookDetails bookDetails = optionalBookDetails.get();
        bookDetailsRepository.deleteById(bookDetails.getId());
        return Optional.of(bookDetails);
      }
    }

    return Optional.empty();
  }

  public Optional<BookDetails> getBookDetailsById(Long id) {
    return bookDetailsRepository.findById(id);
  }

  /* --------------------------Book-Publisher------------------------------------*/

  public Optional<Book> setPublisher(Long bookId, Long publisherID) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isEmpty()) {
      return Optional.empty();
    }

    Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherID);
    if (optionalPublisher.isEmpty()) {
      return Optional.empty();
    }

    Book book = optionalBook.get();
    Publisher publisher = optionalPublisher.get();

    book.setPublisher(publisher);
    Book updatedBook = bookRepository.save(book);

    return Optional.of(updatedBook);
  }

  public Optional<Book> removePublisher(Long bookId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    if(optionalBook.isEmpty()){
      return Optional.empty();
    }

    Book book = optionalBook.get();
    book.setPublisher(null);

    Book newBook = bookRepository.save(book);
    return Optional.of(newBook);
  }
}
