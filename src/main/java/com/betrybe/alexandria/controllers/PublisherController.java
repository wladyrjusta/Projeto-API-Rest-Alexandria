package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.BookDTO;
import com.betrybe.alexandria.controllers.dto.PublisherDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.Publisher;
import com.betrybe.alexandria.services.PublisherService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "publishers")
public class PublisherController {

  private final PublisherService publisherService;

  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @PostMapping()
  public ResponseEntity<ResponseDTO<Publisher>> createPublisher(@RequestBody PublisherDTO publisherDTO) {
    Publisher newPublisher = publisherService.insertPublisher(publisherDTO.toPublisher());

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Publisher criado com sucesso!", newPublisher
    );

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(responseDTO);
  }

  @PutMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> updatePublisher(@PathVariable Long publisherId, PublisherDTO publisherDTO) {
    Optional<Publisher> optionalPublisher = publisherService.updatePublisher(publisherId, publisherDTO.toPublisher());

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o publisher de ID %d", publisherId), null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Publisher atualizado com sucesso!", optionalPublisher.get()
    );
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> removePublisherById(@PathVariable Long publisherId) {
    Optional<Publisher> optionalPublisher = publisherService.removePublisherById(publisherId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o publisher de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Publisher removido com sucesso!", null
    );
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> getBookById(@PathVariable Long bookId) {
    Optional<Publisher> optionalPublisher = publisherService.getPublisherById(bookId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o Publisher de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Publisher encontrado com sucesso!", optionalPublisher.get()
    );
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping()
  public List<PublisherDTO> getAllBooks() {
    List<Publisher> allPublishers = publisherService.getAllPublishers();
    return allPublishers.stream()
        .map((publisher) -> new PublisherDTO(
            publisher.getId(), publisher.getName(),
            publisher.getAddress()
        ))
        .collect(Collectors.toList());
  }

}
