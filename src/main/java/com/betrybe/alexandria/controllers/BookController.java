package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.BookDTO;
import com.betrybe.alexandria.controllers.dto.BookDetailsDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.BookDetails;
import com.betrybe.alexandria.services.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /* --------------------------BookController------------------------------------*/
  @PostMapping()
  public ResponseEntity<ResponseDTO<Book>> createBook(@RequestBody BookDTO bookDTO) {
    Book newBook = bookService.insertBook(bookDTO.toBook());
    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro criado com sucesso!", newBook);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> updateBook(@PathVariable Long bookId, BookDTO bookDTO) {
    Optional<Book> optionalBook = bookService.updateBook(bookId, bookDTO.toBook());

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Livro atualizado com sucesso!", optionalBook.get()
    );

    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> removeBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removeBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Livro removido com sucesso!", null
    );
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> getBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.getBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Livro encontrado com sucesso!", optionalBook.get()
    );
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping()
  public List<BookDTO> getAllBooks(@RequestParam(required = false, defaultValue = "0") int pageNumber, @RequestParam(required = false, defaultValue = "20") int pageSize) {
    return bookService.getAllBooks(pageNumber, pageSize);
  }

  /* --------------------------BookDetailsController------------------------------------*/

  @PostMapping("/{bookId}/details")
  public ResponseEntity<ResponseDTO<BookDetails>> createBookDetails(@PathVariable Long bookId, @RequestBody BookDetailsDTO bookDetailsDTO) {
    Optional<BookDetails> optionalBookDetails = bookService.insertBookDetails(bookId, bookDetailsDTO.toBookDetails());

    if (optionalBookDetails.isPresent()) {
      ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>("Detalhes do livro criado com sucesso!", optionalBookDetails.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>("Livro não encontrado, insira um id válido!",null);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);

  }

  @PutMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetails>> updateBookDetails(@PathVariable Long id, BookDetailsDTO bookDetailsDTO) {
    Optional<BookDetails> optionalBookDetails = bookService.updateBookDetails(id, bookDetailsDTO.toBookDetails());

    if (optionalBookDetails.isEmpty()) {
      ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", id), null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>(
        "Livro atualizado com sucesso!", optionalBookDetails.get()
    );

    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetails>> removeBookDetailsById(@PathVariable Long id) {
    Optional<BookDetails> optionalBookDetails = bookService.removeBookDetailsById(id);

    if (optionalBookDetails.isEmpty()) {
      ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>(
        "Livro removido com sucesso!", null
    );
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetails>> getBookDetailsById(@PathVariable Long id) {
    Optional<BookDetails> optionalBookDetails = bookService.getBookDetailsById(id);

    if (optionalBookDetails.isEmpty()) {
      ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetails> responseDTO = new ResponseDTO<>(
        "Livro encontrado com sucesso!", optionalBookDetails.get()
    );
    return ResponseEntity.ok(responseDTO);
  }

  /* --------------------------Book-Publisher------------------------------------*/

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public ResponseEntity<ResponseDTO<Book>> setPublisherFromBook(@PathVariable Long bookId, @PathVariable Long publisherId) {
    Optional<Book> optionalBook = bookService.setPublisher(bookId, publisherId);

    if(optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d ou a editora de ID %d", bookId, publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Editora vinculada ao livro com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/publisher")
  public ResponseEntity<ResponseDTO<Book>> removePublisherFromBook(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removePublisher(bookId);
    if(optionalBook.isEmpty()){
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi possível remover a editora do livro com id %d", bookId),
          null
      );

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        String.format("Editora removida do livro de ID %d", bookId),
        optionalBook.get()
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
  }
}
