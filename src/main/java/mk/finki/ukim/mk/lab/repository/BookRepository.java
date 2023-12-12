package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findAllByYear(int year);
}
