package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Optional<Book> findBookByIsbn(String isbn);
    Optional<Book> findBookById(long id);
    List<Book> findBookByYear(int year);
    Optional<Book> addBook(String title, String isbn, String genre, int year, long bookStoreId);
    Optional<Book> editBook(int bookId,String title, String isbn, String genre, int year, long bookStoreId);
    Optional<Book> deleteBook(int bookId);
}
