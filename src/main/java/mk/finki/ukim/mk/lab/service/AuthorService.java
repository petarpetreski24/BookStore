package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAuthors();
    Optional<Author> findById(Long id);
    Optional<Author> addAuthor(String name, String surname, String biography);
    Optional<Author> editAuthor(long authorId,String name, String surname, String biography);
    void deleteAuthor(long authorId);
}
